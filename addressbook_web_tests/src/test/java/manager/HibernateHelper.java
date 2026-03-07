package manager;

import manager.hbm.ContactRecord;
import manager.hbm.GroupRecord;
import model.ContactData;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class HibernateHelper extends HelperBase {

    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory = new Configuration()
            .addAnnotatedClass(GroupRecord.class)
            .addAnnotatedClass(ContactRecord.class)
            .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
            // Credentials
            .setProperty(AvailableSettings.USER, "root")
            .setProperty(AvailableSettings.PASS, "")
            .buildSessionFactory();
    }

    static List <GroupData> convertGroupRecord(List<GroupRecord> records) {
        List<GroupData> result = new ArrayList<>();
        for (var record : records) {
            result.add(convert(record));
        }
        return result;
    }

    static List <ContactData> convertContactRecord(List<ContactRecord> records) {
        List<ContactData> result = new ArrayList<>();
        for (var record:records) {
            result.add(convert(record));
        }
        return result;
    }

    private static ContactData convert(ContactRecord record) {
        return new ContactData("" + record.id, record.firstName, record.lastName, record.address, record.email, record.mobilePhone, null);
    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convertGroupData(GroupData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }

    public static ContactRecord convertContactData(ContactData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new ContactRecord(Integer.parseInt(id), data.firstName(), data.lastName(), data.address(),
                data.email(), data.mobilePhone());
    }

    public List<GroupData> getGroupList() {
        return convertGroupRecord(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

    public List<ContactData> getContactList() {
        return convertContactRecord(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }

    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ContactRecord", Long.class).getSingleResult();
        });
    }

    public void createGroup(GroupData data) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertGroupData(data));
            session.getTransaction().commit();
        });
    }

    public void createContact(ContactData data) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertContactData(data));
            session.getTransaction().commit();
        });
    }

    public List<ContactData> getContactsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertContactRecord(session.get(GroupRecord.class, group.id()).contacts);
        });
    }
}
