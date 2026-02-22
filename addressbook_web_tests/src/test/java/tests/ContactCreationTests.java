package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstName : List.of ("", "Name")) {
            result.add(new ContactData ("", firstName, "Lastname", "", "test@test.com", "", "src/test/resources/images/avatar.png"));
            result.add(new ContactData ("", firstName, "", "Address", "", "+375291112233", ""));
        }
        for (int i = 1; i < 5; i ++) {
            result.add(new ContactData("", randomString(i * 5), randomString(i * 5), randomString(i * 5),
                    randomEmail(i * 3), randomNumber(12), randomFile("src/test/resources/images")));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContact(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size()-1).id()).withAddress("").withEmail("").withMobilePhone("").withPhoto(""));
        expectedList.sort(compareById);

        Assertions.assertEquals(expectedList, newContacts);
    }
}
