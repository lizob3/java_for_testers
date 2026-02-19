package tests;

import jdk.jfr.MetadataDefinition;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstName : List.of ("", "Name")) {
            result.add(new ContactData (firstName, "Lastname", "", "test@test.com", ""));
            result.add(new ContactData (firstName, "", "Address", "", "+375291112233"));
            /*for (var lastName : List.of ("", "Lastname")) {
                for (var address : List.of ("", "Address")) {
                    for (var email : List.of ("", "test@test.com")) {
                        for (var mobilePhone : List.of ("", "+375291112233")) {
                            result.add(new ContactData (firstName, lastName, address, email, mobilePhone));
                        }
                    }
                }
            }*/
        }
        for (int i = 1; i < 5; i ++) {
            result.add(new ContactData(randomString(i * 10), randomString(i * 10), randomString(i * 10),
                    randomEmail(i * 5), randomNumber(12)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContact(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }
}
