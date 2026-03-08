package manager;

import model.GroupData;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcHelper extends HelperBase {
    public JdbcHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<GroupData> getGroupList() {
        var groups = new ArrayList<GroupData>();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list")) {
            while (result.next()) {
                groups.add(new GroupData()
                        .withId(result.getString("group_id"))
                        .withName(result.getString("group_name"))
                        .withHeader(result.getString("group_header"))
                        .withFooter(result.getString("group_footer"))
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }

    public void checkConsistency() {
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery(
                     "SELECT * FROM `address_in_groups` ag LEFT JOIN addressbook ab ON ab.id = ag.id WHERE ab.id IS NULL")) {
            if (result.next()) {
                throw new IllegalStateException("DB id corrupted");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public long getContactsInGroupCount() {
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery(
                     "SELECT COUNT(*) FROM `address_in_groups`")) {
            result.next();
            long count = result.getLong(1);
            return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addContactToGroup(String contactId, String groupId) {
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             PreparedStatement addstmt = conn.prepareStatement("INSERT INTO `address_in_groups`(id,group_id, deprecated) VALUES(?,?,?)")) {
            addstmt.setObject(1, contactId);
            addstmt.setObject(2, groupId);
            addstmt.setObject(3, new Date(System.currentTimeMillis()));
            addstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public GroupData getOneGroupWithContacts() {
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery(
                     "SELECT gl.group_id, gl.group_name, gl.group_header, gl.group_footer " +
                             "FROM group_list gl INNER JOIN address_in_groups ag " +
                             "ON gl.group_id = ag.group_id")) {
            result.next();
            var group = new GroupData()
                    .withId(result.getString("group_id"))
                    .withName(result.getString("group_name"))
                    .withHeader(result.getString("group_header"))
                    .withFooter(result.getString("group_footer"));
            return group;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
