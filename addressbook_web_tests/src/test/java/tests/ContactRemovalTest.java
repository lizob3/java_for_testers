package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTest extends TestBase {

    @Test
    public void canRemoveContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(
                    new ContactData("", "Name", "Lastname", "Address", "test@test.com", "+375291112233", ""));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(expectedList, newContacts);
    }

    @Test
    void canRemoveAllContactsAtOnce() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(
                    new ContactData("", "Name", "Lastname", "Address", "test@test.com", "+375291112233", ""));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }

    @Test
    void canRemoveContactFromGroup() {
        if (app.jdbc().getContactsInGroupCount() == 0) {
            if (app.hbm().getContactCount() == 0) {
                app.hbm().createContact(
                        new ContactData("", "Name", "Lastname", "Address", "test@test.com", "+375291112233", ""));
            }
            if (app.hbm().getGroupCount() == 0) {
                app.hbm().createGroup(new GroupData("", "name name", "name header", "name footer"));
            }
            var contactId = app.hbm().getContactList().getLast().id();
            var groupId = app.hbm().getGroupList().getLast().id();
            app.jdbc().addContactToGroup(contactId, groupId);
        }

        var group = app.jdbc().getOneGroupWithContacts();
        var oldRelated = app.hbm().getContactsInGroup(group);
        var contact = oldRelated.get(0);
        app.contacts().removeContactFromGroup(group, contact);
        var newRelated = app.hbm().getContactsInGroup(group);

        var expectedList = new ArrayList<>(oldRelated);
        expectedList.remove(0);
        Assertions.assertEquals (expectedList, newRelated);
    }
}