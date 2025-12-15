package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctoins;

public class JamesTest extends TestBase{

    @Test
    void canCreateUser() {
        app.jamesCli().addUser(
                String.format("%s@localhost", CommonFunctoins.randomString(8)), "password");
    }
}

