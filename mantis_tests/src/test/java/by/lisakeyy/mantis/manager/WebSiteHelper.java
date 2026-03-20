package by.lisakeyy.mantis.manager;

import org.openqa.selenium.By;

public class WebSiteHelper extends HelperBase {

    public WebSiteHelper(ApplicationManager manager) {
        super(manager);
    }

    public void startCreatingAccount(String username, String email) {
        click(By.cssSelector("a[href='signup_page.php']"));
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.xpath("//input[@value=\'Signup\']"));
    }

    public void finishCreatingAccount(String username, String password) {
        type(By.name("realname"), username);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath("//button[@type=\'submit\']"));
    }
}
