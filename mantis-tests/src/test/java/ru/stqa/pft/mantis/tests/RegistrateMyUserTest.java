package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;


public class RegistrateMyUserTest extends TestBase {

    // Использую James!

    @Test
    public void testRegistration () throws IOException, MessagingException {
        //чтобы тест компилировался, создаем метод start:
        String user = String.format("ivan");
        String password = "test";
        String email = String.format("ivan@localhost"); //.localdomain
        app.james().createUser(user, password);
        app.registration().start(user, email);
/*        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);*/
        List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user));
        //  в конце проверка идёт, что именно этот пользователь зашёл под данным логином и паролем, что все правильно

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

}
