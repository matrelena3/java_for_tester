package test;

import model.ClientData;
import org.junit.jupiter.api.Test;


public class ClientCreationTest extends TestBase {

  @Test
  public void CanCreateClient() {

      app.clients().createClient(new ClientData("Ivan", "Ivanoff", "New 12", "896541256325", "ok@ok.ru"));
    }
}
