package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void compareDBPhonesWithMainPagePhones() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(
                    new ContactData("", "Name", "Lastname", "Address", "test@test.com", "", "", "", "", "+375291112233", ""));
        }

        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact ->
            Stream.of(contact.homePhone(), contact.mobilePhone(), contact.workPhone())
                    .filter(s -> s != null && !"".equals(s))
                    .collect(Collectors.joining("\n"))
        ));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);
    }

    @Test
    void compareMainPageInfoWithEditPageInfo() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(
                    new ContactData("", "Name", "Lastname", "Address", "test@test.com", "test2@test2.com", "test3@test3.com", "+375299997755", "", "+375291112233", "+375292224466"));
        }
        var contacts = app.hbm().getContactList();
        var rnd = new Random();
        var contact = contacts.get(rnd.nextInt(contacts.size()));

        var infoFromMainPage = app.contacts().getInfoFormMainPage(contact);
        var infoFromEditPage = app.contacts().getInfoFromEditPage(contact);
        Assertions.assertEquals(infoFromMainPage, infoFromEditPage);

    }
}
