package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContact() {
        app.driver.findElement(By.linkText("add new")).click();
        app.driver.findElement(By.name("firstname")).sendKeys("Test name");
        app.driver.findElement(By.name("lastname")).sendKeys("Test last name");
        app.driver.findElement(By.name("address")).sendKeys("Test Address");
        app.driver.findElement(By.name("mobile")).sendKeys("+375291112233");
        app.driver.findElement(By.name("email")).sendKeys("test@test.com");
        app.driver.findElement(By.name("submit")).click();
        app.driver.findElement(By.linkText("home page")).click();
    }
}
