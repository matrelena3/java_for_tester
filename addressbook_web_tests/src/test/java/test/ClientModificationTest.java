package test;

import model.ClientData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ClientModificationTest extends TestBase {

    @Test
    public void  canModifyClient() {
        if (app.hbm().getClientCount() == 0) {
            app.hbm().createClient(new ClientData("", "Ivan", "Ivanoff", "New 12", "896541256325", "ok@ok.ru", "src/test/resources/images/avatar.png", "", "", ""));
            app.hbm().createClient(new ClientData("", "Vova", "Vovik", "New 007", "1212", "122@ok.ru", "src/test/resources/images/avatar.png", "", "", ""));
        }
        var oldClients = app.hbm().getClientList();
        var rnd = new Random();
        var index = rnd.nextInt(oldClients.size());
        ClientData testData = new ClientData().withName("modified name", "0011");
        app.clients().modifyClient(oldClients.get(index), testData);
        var newClients = app.hbm().getClientList();
        var expectedList = new ArrayList<>(oldClients);
        expectedList.set(index, testData.withId(oldClients.get(index).id()));
        Comparator<ClientData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newClients.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newClients, expectedList);
    }

}