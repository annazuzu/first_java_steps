package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.MantisUserData;
import ru.stqa.pft.mantis.model.MantisUsers;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class AdminChangeUsersPassword extends TestBase {

    @BeforeMethod
    public void startMailServer () {
        app.mail().start();
    }

    @Test

    public void testAdminChangeUsersPassword () throws IOException, MessagingException {

        String changedPassword = "changetest";

        // Получаем список юзеров из БД:
        MantisUsers users = app.db().users();
        MantisUserData user = users.iterator().next();

        // Логинимся под админом:
        app.newSession().loginCh("administrator", "root"); // это ок
//        app.newSession().isLoggedInAs("administrator");

        app.changePass().start("administrator", "root");
        app.changePass().clickOnUserName(user.getId());
        app.changePass().clickButtonResetPassword();
//        app.newSession().logout();

        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());
        app.registration().finish(confirmationLink, changedPassword);

        HttpSession session = app.newSession();
        assertTrue(session.loginCh(user.getUsername(), changedPassword));
        assertTrue(session.isLoggedInAs(user.getUsername()));

    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        //сначала ищем такой текст, потом после него должно идти какое-то количество непробельных символов -
        // один или больше, построить.
        // Результат - построенное регулярное выражение.
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        // теперь это регулярное выражение нужно применить к тексту нашего письма и получившееся значение вернуть:
        return  regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer () {
        app.mail().stop();
    }
}

