package manager;

import org.openqa.selenium.By;

public class LoginHelper {

    private final ApplicationManadger manager;

    public LoginHelper(ApplicationManadger manager) {
        this.manager = manager;
    }
    void login(String user, String password) {
        manager.driver.findElement(By.name("user")).sendKeys(user);
        manager.driver.findElement(By.name("pass")).click();
        manager.driver.findElement(By.name("pass")).sendKeys(password);
        manager.driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
    }
}
