package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static common.CommonFunctions.randomFile;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
        for (var firstName : List.of ("", "Name")) {
            result.add(new ContactData ("", firstName, "Lastname", "", "test@test.com", "", "src/test/resources/images/avatar.png"));
            result.add(new ContactData ("", firstName, "", "Address", "", "+375291112233", ""));
        }

        var mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>() {});
        result.addAll(value);

        return result;
    }

    public static List<ContactData> singleRandomContactProvider() {
        return List.of(new ContactData().withFirstName(CommonFunctions.randomString(5)).withLastName(CommonFunctions.randomString(7))
                .withAddress(CommonFunctions.randomString(9)).withEmail(CommonFunctions.randomEmail(7))
                .withMobilePhone(CommonFunctions.randomNumber(12)));
    }

    @ParameterizedTest
    @MethodSource("singleRandomContactProvider")
    public void canCreateContact(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size()-1).id()).withPhoto(null));
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newContacts);

        var newUiContacts = app.contacts().getList();
        newUiContacts.sort(compareById);
        var newContactsWithEmptyFields = new ArrayList<ContactData>();
        for (var newContact : newContacts) {
            newContactsWithEmptyFields.add(newContact.withAddress("").withEmail("").withMobilePhone("").withPhoto(""));
        }
        Assertions.assertEquals(newContactsWithEmptyFields, newUiContacts);
    }

    @Test
    void canCreateContactinGroup() {
        var contact = new ContactData().withFirstName(CommonFunctions.randomString(5)).withLastName(CommonFunctions.randomString(7))
                .withAddress(CommonFunctions.randomString(9)).withEmail(CommonFunctions.randomEmail(7))
                .withMobilePhone(CommonFunctions.randomNumber(12)).withPhoto(randomFile("src/test/resources/images"));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "name name", "name header", "name footer"));
        }
        var group = app.hbm().getGroupList().get(0);

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContact(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }
}
