package test;

import org.junit.jupiter.api.Test;
import model.ClientData;


public class ClientRemovalTests extends TestBase {

  @Test
  public void  canRemoveClient() {
    if (!app.clients().isClientPresent(app)) {
      app.clients().createClient(new ClientData("Ivan", "Ivanoff", "New 12", "896541256325", "ok@ok.ru"));
    }
    app.clients().removeClient();
  }

}
