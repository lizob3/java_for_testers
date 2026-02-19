package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactRemovalTest extends TestBase {

    @Test
    public void canRemoveContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(
                    new ContactData("Name", "Lastname", "Address", "test@test.com", "+375291112233"));
        }
        int contactCount = app.contacts().getCount();
        app.contacts().removeContact();
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount - 1, newContactCount);

    }

    @Test
    void canRemoveAllContactsAtOnce() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(
                    new ContactData("Name", "Lastname", "Address", "test@test.com", "+375291112233"));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }
}