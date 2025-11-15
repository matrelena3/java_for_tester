package test;

import manager.ApplicationManadger;
import org.junit.jupiter.api.BeforeEach;

import java.util.Random;


public class TestBase {
    protected static ApplicationManadger app;

    @BeforeEach

    public void setUp() {
        if (app == null) {
            app = new ApplicationManadger();
            app.init(System.getProperty("browser", "firefox"));
        }
    }

    public static String randomString(int n) {
        var rnd = new Random();
        var result = "";
        for (int i = 0; i < n; i++) {
            result = result + (char)('a' + rnd.nextInt(26));
        }
        return result;
    }
}
