package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser() {
        var username = CommonFunctions.randomString(5);
        var email = String.format("%s@localhost" , username);
        var password = "password";
        // создать пользователя (адрес) на почтовом сервере (JamesHelper)
        app.jamesApi().addUser(email, password);
        // открываем браузер, заполняем форму создания и отправляем (браузер, создать класс помощник)
        app.reg().register(username);
        // ждем почту (MailHelper)
        app.mail().receive(email, password, Duration.ofSeconds(60));
        // извлекаем ссылку из письма
        String url = app.mail().extractUrl(username);
        // проходим по ссылке и завершаем регистрацию пользователя (браузер)
        app.driver().get(url);
        app.reg().fillEditForm(username, password);
        // проверяем, что пользователь может залогиниться (HttpSessionHelper)
        //app.session().login(username, password);
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
