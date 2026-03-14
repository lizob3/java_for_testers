package manager;

import manager.hbm.ContactRecord;
import manager.hbm.GroupRecord;
import model.ContactData;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.stream.Collectors;

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
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

    static List <ContactData> convertContactRecord(List<ContactRecord> records) {
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

    private static ContactData convert(ContactRecord record) {
        return new ContactData("" + record.id,
                record.firstName,
                record.lastName,
                record.address,
                record.email,
                record.email2,
                record.email3,
                record.workPhone,
                null,
                record.mobilePhone,
                record.homePhone
        );
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
                data.email(), data.email2(), data.email3(), data.mobilePhone());
    }

    public List<GroupData> getGroupList() {
        return convertGroupRecord(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

    public List<ContactData> getContactList() {
        return convertContactRecord(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord order by id ASC", ContactRecord.class).list();
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

    public ContactData createContact(ContactData data) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertContactData(data));
            session.getTransaction().commit();
        });
        return data;
    }

    public List<ContactData> getContactsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertContactRecord(session.get(GroupRecord.class, group.id()).contacts);
        });
    }

    public ContactData getLastContactInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convert(session.get(GroupRecord.class, group.id()).contacts.getLast());
        });
    }

    public Boolean isContactsInGroup(ContactData contact, GroupData group) {
        Long count = sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord g join g.contacts c " +
                    "where g.id = :groupId and c.id = :contactId", Long.class).setParameter("groupId", group.id())
                    .setParameter("contactId", contact.id()).getSingleResult();
        });
        return count > 0;
    }
}
