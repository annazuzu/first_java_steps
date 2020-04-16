//package ru.stqa.pft.mantis.tests;
//
//import org.testng.annotations.Test;
//import ru.lanwen.verbalregex.VerbalExpression;
//import ru.stqa.pft.mantis.model.MailMessage;
//import ru.stqa.pft.mantis.model.MantisUserData;
//import ru.stqa.pft.mantis.model.MantisUsers;
//
//import javax.mail.Folder;
//import javax.mail.MessagingException;
//import java.io.IOException;
//import java.util.List;
//
//import static org.testng.AssertJUnit.assertTrue;
//
//public class AdminMyUserTest extends TestBase {
//
//    @Test
//    public void testAdminChangeUsersPassword () throws IOException, MessagingException, InterruptedException {
//
//        // Получаем список юзеров из БД:
//        MantisUsers users = app.db().users();
//        MantisUserData user = users.iterator().next(); // берем человека
//
//        String password = "test"; // потом берем его пароль
//        String changedPassword = "test";
//        String username = user.getUsername();
//
//        // Логинимся под админом:
//        app.newSession().loginCh("administrator", "root"); // это ок
//        app.changePass().start("administrator", "root");
//        app.changePass().clickOnUserName(user.getId());
//
//        // открываем почтовый ящик пользователя до смены пароля, потому что потом мы его не откроем!
//        app.james().initTelnetSession();
//        Folder inbox = app.james().openInbox(username, password);
//        List<MailMessage> allMailbef = app.changePass().getAllMail(inbox);
//
//        app.changePass().clickButtonResetPassword();
//
//        // Далее ждём почту у выбранного юзверя:
////        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
//        List<MailMessage> mailMessages = app.changePass().waitForMail(inbox, allMailbef, 60000);
//        String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());
//        app.registration().finish(confirmationLink, changedPassword);
//
//        // закрываем почтовый ящик:
//        app.james().closeFolder(inbox);
//        app.james().closeTelnetSession();
//
//        assertTrue(app.newSession().loginCh(username, changedPassword));
////        assertTrue(session.isLoggedInAs(user.getUsername()));
//
//    }
//
//    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
//        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
//        //сначала ищем такой текст, потом после него должно идти какое-то количество непробельных символов -
//        // один или больше, построить.
//        // Результат - построенное регулярное выражение.
//        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
//        // теперь это регулярное выражение нужно применить к тексту нашего письма и получившееся значение вернуть:
//        return  regex.getText(mailMessage.text);
//    }
//
//}
//
