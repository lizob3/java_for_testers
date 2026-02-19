package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void removeContact(){
        selectContact();
        removeSelectedContacts();
        returnToHomePage();
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
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void returnToHomePage() {
        click(By.linkText("home"));
    }

    private void removeSelectedContacts() {
        click(By.name("delete"));
    }

    private void selectContact() {
        click(By.name("selected[]"));
    }

    public boolean isContactPresent() {
        return manager.isElementPresent(By.name("selected[]"));
    }

    public int getCount() {
        returnToHomePage();;
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContacts() {
        returnToHomePage();
        selectAllCheckboxes();
        removeSelectedContacts();
    }
}
