package test;

import manager.ApplicationManadger;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.nio.file.Paths;
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

    public static String randomFile(String dir) {
        var fileNames = new File(dir).list();
        var rnd = new Random();
        var index = rnd.nextInt(fileNames.length);
       return Paths.get(dir, fileNames[index]).toString();
    }
}
