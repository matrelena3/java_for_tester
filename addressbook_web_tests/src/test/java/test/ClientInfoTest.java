package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClientInfoTest extends TestBase {

    @Test
    void testPhone() {
        var clients = app.hbm().getClientList();
       var expected = clients.stream().collect(Collectors.toMap(client -> client.id(), client ->
             Stream.of(client.home(), client.mobile(), client.secondary(), client.work())
                     .filter(s -> s != null && ! "".equals(s))
                     .collect(Collectors.joining("\n"))
                ));
        var phones = app.clients().getPhones();
            Assertions.assertEquals(expected, phones);
    }

}
