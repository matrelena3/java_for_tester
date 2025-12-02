package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import model.ClientData;

import java.util.ArrayList;
import java.util.Random;


public class ClientRemovalTests extends TestBase {

  @Test
  public void  canRemoveClient() {
    if (app.hbm().getClientCount() == 0) {
      app.hbm().createClient(new ClientData("", "Ivan", "Ivanoff", "New 12", "896541256325", "ok@ok.ru", ""));
      app.hbm().createClient(new ClientData("", "Vova", "Vovik", "New 007", "1212", "122@ok.ru", ""));

    }
    var oldClients = app.hbm().getClientList();
    var rnd = new Random();
    var index = rnd.nextInt(oldClients.size());
    app.clients().removeClient(oldClients.get(index));
    var newClients = app.hbm().getClientList();
    var expectedList = new ArrayList<>(oldClients);
    expectedList.remove(index);
    Assertions.assertEquals(newClients, expectedList);
  }

}
