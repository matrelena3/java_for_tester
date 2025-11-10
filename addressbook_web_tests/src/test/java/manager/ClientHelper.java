package manager;

import model.ClientData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class ClientHelper {
    private final ApplicationManadger manager;

    public ClientHelper(ApplicationManadger manager) {
        this.manager = manager;
    }

    public void openClientPage() {
            manager.driver.findElement(By.linkText("add new")).click();
    }

    public void createClient(ClientData client) {
        openClientPage();
        manager.driver.findElement(By.name("firstname")).click();
        manager.driver.findElement(By.name("firstname")).sendKeys(client.firstname());
        manager.driver.findElement(By.name("lastname")).click();
        manager.driver.findElement(By.name("lastname")).sendKeys(client.lastname());
        manager.driver.findElement(By.name("address")).click();
        manager.driver.findElement(By.name("address")).sendKeys(client.address());
        manager.driver.findElement(By.name("home")).click();
        manager.driver.findElement(By.name("home")).sendKeys(client.home());
        manager.driver.findElement(By.name("email")).click();
        manager.driver.findElement(By.name("email")).sendKeys(client.email());
        manager.driver.findElement(By.name("email")).sendKeys(Keys.ENTER);
        manager.driver.findElement(By.linkText("home")).click();
    }

    public boolean isClientPresent(ApplicationManadger applicationManadger) {
        return applicationManadger.isElementPresent(By.name("selected[]"));
    }

    public void removeClient() {
        manager.driver.findElement(By.name("selected[]")).click();
        manager.driver.findElement(By.name("delete")).click();
        manager.driver.findElement(By.linkText("home")).click();
    }
}
