package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;

public class TestBase {
    
    protected static ApplicationManager app;
    
    @BeforeEach
    public void setUp() {
        if (app == null) {
            app = new ApplicationManager();
        }
        app.init();
    }

    /*@AfterEach
    public void tearDown() {
        app.driver.findElement(By.linkText("Logout")).click();
        app.driver.quit();
        app.driver = null;
    }*/
}
