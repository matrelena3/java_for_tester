package test;

import model.ClientData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ClientCreationTest extends TestBase {

    public static List<ClientData> clientProvider() {
        var result = new ArrayList<ClientData>();
        for (var firstname: List.of("", "Ivan")) {
            for (var lastname : List.of("", "Ivanov")) {
                for (var address : List.of("", "Rus")) {
                    for (var home : List.of("", "110022")) {
                        for (var email : List.of("", "ok@ok.ru")) {
                    result.add(new ClientData("", firstname, lastname, address, home, email));
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new ClientData("", randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10)));
        }
        return result;
    }

    public static List<ClientData> negativeClientProvider() {
        var result = new ArrayList<ClientData>(List.of(
                new ClientData("", "firstname'", "", "", "", "")));
        return result;
    }


    @ParameterizedTest
    @MethodSource("clientProvider")
    public void CanCreateMultipleClients(ClientData client) {
        var oldClients = app.clients().getList();
        app.clients().createClient(client);
        var newClients = app.clients().getList();
        Comparator<ClientData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newClients.sort(compareById);
        var expectedList = new ArrayList<>(oldClients);
        expectedList.add(client.withId(newClients.get(newClients.size() - 1).id()).withAddress("").withHome("").withEmail(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newClients, expectedList);
        Assertions.assertEquals(newClients, expectedList);
    }

    @ParameterizedTest
    @MethodSource("negativeClientProvider")
    public void CanNotCreateClient(ClientData client) {
        var oldClients = app.clients().getList();
        app.clients().createClient(client);
        var newClients = app.clients().getList();
        Assertions.assertEquals(newClients, oldClients);
    }
}
