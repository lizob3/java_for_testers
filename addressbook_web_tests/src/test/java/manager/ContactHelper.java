package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        openHomePage();
    }

    public void createContact(ContactData contact, GroupData group) {
        initContactCreation();
        fillContactForm(contact);
        selectGroup(group, "new_group");
        submitContactCreation();
        openHomePage();
    }

    private void selectGroup(GroupData group, String htmlElementName) {
        new Select(manager.driver.findElement(By.name(htmlElementName))).selectByValue(group.id());
    }

    public void removeContact(ContactData contact) {
        openHomePage();
        selectContact(contact);
        removeSelectedContacts();
        openHomePage();
    }

    public void removeContactFromGroup(GroupData group, ContactData contact) {
        openHomePage();
        selectGroupInList(group, "group");
        selectContact(contact);
        removeSelectedContactsFromGroup();
        openHomePage();
        selectGroupInList(group, "group");
    }

    private void removeSelectedContactsFromGroup() {
        click(By.name("remove"));
    }

    private void selectGroupInList(GroupData group, String htmlElementName) {
        new Select(manager.driver.findElement(By.name(htmlElementName))).selectByValue(group.id());
    }

    public void modifyContact (ContactData contact, ContactData modifiedContact) {
        openHomePage();
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        openHomePage();
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void initContactModification(ContactData contact) {
        var tr = manager.driver.findElement(By.xpath(String.format("//tr[td/input[@id='%s']]", contact.id())));
        tr.findElements(By.tagName("td")).get(7).click();
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("address"), contact.address());
        type(By.name("mobile"), contact.mobilePhone());
        type(By.name("email"), contact.email());
        type(By.name("email2"), contact.email2());
        type(By.name("email3"), contact.email3());
        if (contact.photo() != null && !contact.photo().isEmpty()) {
            attach(By.name("photo"), contact.photo());
        }
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void openHomePage() {
        click(By.id("logo"));
    }

    private void removeSelectedContacts() {
        click(By.name("delete"));
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    public boolean isContactPresent() {
        return manager.isElementPresent(By.name("selected[]"));
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContacts() {
        openHomePage();
        selectAllCheckboxes();
        removeSelectedContacts();
    }

    public List<ContactData> getList() {
//        returnToHomePage();
        var contacts = new ArrayList<ContactData>();
        var trs = manager.driver.findElements(By.cssSelector("tr[name='entry']"));
        for (var tr : trs) {
            var firstName = tr.findElements(By.tagName("td")).get(2).getText();
            var lastName = tr.findElements(By.tagName("td")).get(1).getText();
            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
        }
        return contacts;
    }

    public void addContactToGroup(ContactData contact, GroupData group) {
        openHomePage();
        selectContact(contact);
        selectGroupInList(group, "to_group");
        addSelectedContactToGroup();
        openHomePage();
    }

    private void addSelectedContactToGroup() {
        click(By.name("add"));
    }

    public String getPhonesFromMainPage(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[6]", contact.id()))).getText();
    }

    public Map<String, String> getPhones() {
        openHomePage();
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }

    public String getAddressFromMainPage(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[4]", contact.id()))).getText();
    }

    private void openEditPage(ContactData contact) {
        manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[8]", contact.id()))).click();
    }

    public Map<String, String> getInfoFormMainPage(ContactData contact) {
        var info = new HashMap<String, String>();
        var address = getAddressFromMainPage(contact);
        var phones = getPhonesFromMainPage(contact);
        var emails = getEmailsFromMainPage(contact);

        info.put("address", address);
        info.put("phones", phones);
        info.put("emails", emails);

        return info;
    }

    private String getEmailsFromMainPage(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[5]", contact.id()))).getText();
    }

    public Map<String, String> getInfoFromEditPage(ContactData contact) {
        openEditPage(contact);

        var info = new HashMap<String, String>();

        var address = manager.driver.findElement(By.name("address")).getText();

        var homePhone = manager.driver.findElement(By.name("home")).getAttribute("value");
        var mobilePhone = manager.driver.findElement(By.name("mobile")).getAttribute("value");
        var workPhone = manager.driver.findElement(By.name("work")).getAttribute("value");
        var phones = Stream.of(homePhone, mobilePhone, workPhone)
                .filter(s -> s != null && !"".equals(s)).collect(Collectors.joining("\n"));

        var email = manager.driver.findElement(By.name("email")).getAttribute("value");
        var email2 = manager.driver.findElement(By.name("email2")).getAttribute("value");
        var email3 = manager.driver.findElement(By.name("email3")).getAttribute("value");
        var emails = Stream.of(email, email2, email3)
                .filter(s -> s != null && !"".equals(s)).collect(Collectors.joining("\n"));

        info.put("address", address);
        info.put("phones", phones);
        info.put("emails", emails);

        return info;
    }
}
