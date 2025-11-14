package manager;

import model.ClientData;
import org.openqa.selenium.By;


public class ClientHelper extends HelperBase {

    public ClientHelper(ApplicationManadger manager) {
        super(manager);
    }



    public void createClient(ClientData client) {
        openClientPage();
        fillClientForm(client);
        submitClientCreation();
        returnToHomePage();
    }
    private void fillClientForm(ClientData client) {
        type(By.name("firstname"), client.firstname());
        type(By.name("lastname"), client.lastname());
        type(By.name("address"), client.address());
        type(By.name("home"), client.home());
        type(By.name("email"), client.email());
    }

    public boolean isClientPresent(ApplicationManadger applicationManadger) {
        return applicationManadger.isElementPresent(By.name("selected[]"));
    }

    public void removeClient() {
        selectClient();
        initClientCreation();
        returnToHomePage();
    }

    private void selectClient() {
        click(By.name("selected[]"));
    }

    public void openClientPage() {
        click(By.linkText("add new"));
    }
    public void initClientCreation() {
        click(By.name("delete"));
    }

    private void returnToHomePage() {
        click(By.linkText("home"));
    }
    public void submitClientCreation() {
        click(By.name("submit"));
    }

}
