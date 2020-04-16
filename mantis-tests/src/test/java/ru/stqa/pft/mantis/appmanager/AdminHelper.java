package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.net.MalformedURLException;
import java.util.List;

public class AdminHelper extends HelperBase {

    public AdminHelper(ApplicationManager app) throws MalformedURLException {
        super(app);
    }

    public void start(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php"); //*после этого драйвером можно спокойно пользоваться
        type(By.name("username"), username);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }

    public void clickOnUserName (int id) throws InterruptedException {
        wd.findElement(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s']", id))).click();
        Thread.sleep(5000);
    }

    public void clickButtonResetPassword() throws InterruptedException {
        wd.findElement(By.xpath("//input[@value='Сбросить пароль']")).click();
        Thread.sleep(5000);
        wd.findElement(By.linkText("Продолжить")).click();
    }

    // Работа с почтой:

    public List<MailMessage> waitForMail(String username, String password,  long timeout, List<MailMessage> allMailbef) throws MessagingException {
        long now = System.currentTimeMillis();
        while (System.currentTimeMillis() < now + timeout) {
            List<MailMessage> allMail = app.james().getAllMail(username, password);
            if (allMail.size() == allMailbef.size() + 1) {
                return allMail;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        throw new Error("No new mail :(");
    }

}
