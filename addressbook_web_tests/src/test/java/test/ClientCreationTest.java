package test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctoins;
import model.ClientData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


public class ClientCreationTest extends TestBase {

    public static List<ClientData> clientProvider() throws IOException {
        var result = new ArrayList<ClientData>();
        for (var firstname : List.of("", "Ivan")) {
            for (var lastname : List.of("", "Ivanov")) {
                for (var address : List.of("", "Rus")) {
                    for (var home : List.of("", "110022")) {
                        for (var email : List.of("", "ok@ok.ru")) {
                            for (var photo : List.of("src/test/resources/images/avatar.png")) {
                                result.add(new ClientData("", firstname, lastname, address, home, email, photo, "", "", "", "", "", ""));
                            }
                        }
                    }
                }
            }
        }
        var json = Files.readString(Paths.get("clients.json"));
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("clients.json"), new TypeReference<List<ClientData>>() {
        });
        result.addAll(value);
        return result;
    }


    public static List<ClientData> negativeClientProvider() {
        var result = new ArrayList<ClientData>(List.of(
                new ClientData("", "firstname'", "", "", "", "", "", "", "", "", "", "", "")));
        return result;
    }


    @ParameterizedTest
    @MethodSource("clientProvider")
    public void CanCreateMultipleClients(ClientData client) {
        var oldClients = app.hbm().getClientList();
        app.clients().createClient(client);
        var newClients = app.hbm().getClientList();
        Comparator<ClientData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newClients.sort(compareById);
        var maxId = newClients.get(newClients.size() - 1).id();
        var expectedList = new ArrayList<>(oldClients);
        expectedList.add(client.withId(maxId).withHome("").withEmail("").withPhoto(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newClients, expectedList);

    }

    @ParameterizedTest
    @MethodSource("negativeClientProvider")
    public void CanNotCreateClient(ClientData client) {
        var oldClients = app.hbm().getClientList();
        app.clients().createClient(client);
        var newClients = app.hbm().getClientList();
        Assertions.assertEquals(newClients, oldClients);
    }

    @Test
    void canCreateClientInGroup() {
        var client = new ClientData()
                .withFirstname(CommonFunctoins.randomString(10))
                .withLastname(CommonFunctoins.randomString(10))
                .withPhoto(CommonFunctoins.randomFile("src/test/resources/images"));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group"));
        }
        var group = app.hbm().getGroupList().get(0);

        var oldRelated = app.hbm().getClientsInGroup(group);
        app.clients().create(client, group);
        var newRelated = app.hbm().getClientsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }

    @Test
    void RandomClientInGroup() {
        var pair = app.hbm().findClientNotInAnyGroup().orElseGet(() -> {
            var client = new ClientData()
                    .withFirstname(CommonFunctoins.randomString(10))
                    .withLastname(CommonFunctoins.randomString(10))
                    .withPhoto(CommonFunctoins.randomFile("src/test/resources/images"));
            ClientData newClient = app.hbm().getClientList().stream()
                    .max(Comparator.comparingInt(c -> Integer.parseInt(c.id()))).get();
            return Map.entry(newClient, app.hbm().getGroupList().get(0));
        });
        ClientData client = pair.getKey();
        GroupData group = pair.getValue();
        var oldClients = app.hbm().getClientsInGroup(group).size();
        app.clients().addClientInGroup(client, group);
        var newClients = app.hbm().getClientsInGroup(group).size();

        Assertions.assertEquals(oldClients + 1, newClients);
    }

}

