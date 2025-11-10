package test;

import manager.ApplicationManadger;
import org.junit.jupiter.api.BeforeEach;


public class TestBase {
    protected static ApplicationManadger app;

    @BeforeEach

    public void setUp() {
        if (app == null) {
            app = new ApplicationManadger();
            app.init(System.getProperty("browser", "firefox"));
        }

    }
}
