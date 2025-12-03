package manager;

import model.ClientData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;


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

    public void create(ClientData client, GroupData group) {
        openClientPage();
        fillClientForm(client);
        selectGroup(group);
        submitClientCreation();
        returnToHomePage();
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    private void fillClientForm(ClientData client) {
        type(By.name("firstname"), client.firstname());
        type(By.name("lastname"), client.lastname());
        type(By.name("address"), client.address());
        type(By.name("home"), client.home());
        type(By.name("email"), client.email());
        attach(By.name("photo"), client.photo());
    }

    public boolean isClientPresent(ApplicationManadger applicationManadger) {
        return applicationManadger.isElementPresent(By.name("selected[]"));
    }

    public void removeClient(ClientData client) {
        selectClient(client);
        initClientCreation();
        returnToHomePage();
    }

    public void modifyClient(ClientData client, ClientData modifiedClient) {
        returnToHomePage();
        initClientModification(client);
        fillClientForm(modifiedClient);
        submitClientModification();
        returnToHomePage();
    }
    private void initClientModification(ClientData client) { click(By.xpath(String.format("//input[@value='%s']/ancestor::tr//img[@title='Edit']", client.id())));}

    private void submitClientModification() { click(By.name("update"));}


    private void selectClient(ClientData client) {
        click(By.cssSelector(String.format("input[value='%s']", client.id())));
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

    public int getCountClient() {
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public List<ClientData> getList() {
        var clients = new ArrayList<ClientData>();
        var tds = manager.driver.findElements(By.name("entry"));
        for (var td : tds) {
            var lastN = td.findElement(By.xpath("./td[2]"));
            var firstN = td.findElement(By.xpath("./td[3]"));
            var lastname = lastN.getText();
            var firstname = firstN.getText();
            var checkbox = td.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            clients.add(new ClientData().withId(id).withName(firstname, lastname));
        }
        return clients;
    }


    public void addClientInGroup(ClientData client, GroupData group) {
        selectClient(client);
        chooseGroup(group);
        addToGroup();
        returnToHomePage();
    }

    private void addToGroup() {
        click(By.name("add"));
    }
    private void chooseGroup(GroupData group) {
        click(By.name("to_group"));
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }

    public void removeClientFromGroup(ClientData client, GroupData group) {
        selectGroupWithClient(group);
        selectClient(client);
        removeFromGroup();
        returnToHomePage();
    }
    private void selectGroupWithClient(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }
    private void removeFromGroup() {
        click(By.name("remove"));
    }

}
