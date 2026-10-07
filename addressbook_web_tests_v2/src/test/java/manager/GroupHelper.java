package manager;

import model.GroupData;
import org.openqa.selenium.By;

public class GroupHelper {

    private final ApplicationManager manager;

    public GroupHelper (ApplicationManager manager) {
        this.manager = manager;
    }

    public void openGroupsPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            returnToGroupsPage();
        }
    }

    public void createGroup(GroupData group) {
        openGroupsPage();
        manager.driver.findElement(By.name("new")).click();
        fillGroupForm(group);
        manager.driver.findElement(By.name("submit")).click();
        returnToGroupsPage();
    }

    public void removeGroup() {
        openGroupsPage();
        manager.driver.findElement(By.name("delete")).click();
        returnToGroupsPage();
    }

    private void returnToGroupsPage() {
        manager.driver.findElement(By.linkText("groups")).click();
    }

    public boolean isGroupPresent() {
        return manager.isElementPresent(By.name("selected []"));
    }

    public void modifyGroup(GroupData modifiedGroup) {
        openGroupsPage();
        selectGroup();
        initGroupModification();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnToGroupsPage();
    }

    private void submitGroupModification() {
        manager.driver.findElement(By.name("update")).click();
    }

    private void fillGroupForm(GroupData group) {
        manager.driver.findElement(By.name("group_name")).sendKeys(group.name());
        manager.driver.findElement(By.name("group_header")).sendKeys(group.header());
        manager.driver.findElement(By.name("group_footer")).sendKeys(group.footer());
    }

    private void initGroupModification() {
        manager.driver.findElement(By.name("edit")).click();

    }

    private void selectGroup() {
        manager.driver.findElement(By.name("selected []")).click();
    }
}
