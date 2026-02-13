package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactRemovalTest extends TestBase {

    @Test
    public void canRemoveTest() {
        if (!app.contacts().isContactPresent()){
            app.contacts().createContact(new ContactData("Name", "Lastname", "Address", "test@test.com", "+375291112233"));
        }
        app.contacts().removeContact();
    }
}
