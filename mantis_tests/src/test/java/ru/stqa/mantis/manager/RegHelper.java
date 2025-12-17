package ru.stqa.mantis.manager;
import org.openqa.selenium.By;

public class RegHelper extends HelperBase {


    public RegHelper(ApplicationManager manager) {
        super(manager);
    }

    public void register(String username) {
        click(By.xpath("//a[@href=\'signup_page.php\']"));
        type(By.name("username"), username);
        var email = String.format("%s@localhost", username);
        type(By.name("email"), email);
        click(By.xpath("//input[@value=\'Signup\']"));
    }

    public void fillEditForm(String username, String password) {
        type(By.id("realname"), username);
        type(By.id("password"), password);
        type(By.id("password-confirm"), password);
        click(By.cssSelector("span[class='bigger-110']"));
    }
}
