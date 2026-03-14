package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTest extends TestBase {

    @Test
    void canModifyContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(
                    new ContactData("", "Name", "Lastname", "Address", "test@test.com", "", "", "", "", "+375291112233", ""));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData().withFirstName("modified first name");
        app.contacts().modifyContact(oldContacts.get(index), testData);
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()).withPhoto(null));

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);

        Assertions.assertEquals(expectedList, newContacts);
    }

    @Test
    void addContactToGroup() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(
                    new ContactData("", "Name", "Lastname", "Address", "test@test.com", "", "", "", "", "+375291112233", ""));
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "name name", "name header", "name footer"));
        }

        var contacts = app.hbm().getContactList();
        var groups = app.hbm().getGroupList();

        ContactData contact = null;
        GroupData group = null;

        outerLoop:
        for (var contactFromList : contacts) {
            for (var groupFromList : groups) {
                Boolean contactInGroup = app.hbm().isContactsInGroup(contactFromList, groupFromList);
                if (contactInGroup == false) {
                    contact = contactFromList;
                    group = groupFromList;
                    break outerLoop;
                }
            }
        }
        if (contact == null) {
            app.hbm().createContact(
                    new ContactData("", "Name", "Lastname", "Address", "test@test.com", "", "", "", "", "+375291112233", ""));
            contact = app.hbm().getContactList().getLast();
            group = groups.get(0);
        }

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().addContactToGroup(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        var expectedRelated = new ArrayList<>(oldRelated);
        expectedRelated.add(contact);
        Assertions.assertEquals(expectedRelated, newRelated);
    }
}
