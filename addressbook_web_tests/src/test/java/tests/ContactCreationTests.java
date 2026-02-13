package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContact() {
        app.contacts().createContact(new ContactData("Name", "Lastname", "Address", "test@test.com", "+375291112233"));
    }
}
