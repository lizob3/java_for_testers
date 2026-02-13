package manager;

import org.openqa.selenium.By;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact() {
        click(By.linkText("add new"));
        type(By.name("firstname"), "Test name");
        type(By.name("lastname"), "Test last name");
        type(By.name("address"),"Test Address");
        type(By.name("mobile"),"+375291112233");
        type(By.name("email"),"test@test.com");
        click(By.name("submit"));
        click(By.linkText("home page"));
    }
}
