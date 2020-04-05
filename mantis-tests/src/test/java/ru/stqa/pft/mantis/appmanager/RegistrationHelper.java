package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationHelper extends HelperBase{

//    private final ApplicationManager app;
//    private WebDriver wd;

    public RegistrationHelper(ApplicationManager app) {
        super(app);
//        this.app = app;
//        wd = app.getDriver(); //* нужно попросить ссылку на драйвер у менеджера
    }

    //чтобы тест компилировался, создаем метод start:
    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php"); //*после этого драйвером можно спокойно пользоваться
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[type='submit']"));
    }

    public void finish(String confirmationLink, String username, String password) {
        // проходим по ссылке:
        wd.get(confirmationLink);
        //заполняем два поля:
        type(By.name("realname"), username);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }

    //http://localhost/mantisbt-2.24.0/mantisbt-2.24.0/verify.php?id=4&confirm_hash=1Qt6Cr2SiB7F_rCgJsze0zpfRFrm9VmB7rN7lu6wiEwE4LUxvC9orB5iqkC_0kIlKTgJYXuGrdemncqtyaYZ
}
