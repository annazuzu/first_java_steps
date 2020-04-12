package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MantisUserData;
import ru.stqa.pft.mantis.model.MantisUsers;

import java.io.IOException;

public class AdminChangeUsersPassword extends TestBase {

    @BeforeMethod
    public void startMailServer () {
        app.mail().start();
    }

    @Test

    public void testAdminChangeUsersPassword () throws IOException {

        // Получаем список юзеров из БД:
        MantisUsers users = app.db().users(); // но пока не получает по неизвестной причине?
        MantisUserData user = users.iterator().next();

        // Логинимся под админом:
        app.newSession().login("administrator"); // это ок

        app.changePass().goToManageUserPage();
        app.changePass().clickOnUserName(user.getId());
        app.changePass().clickButtonResetPassword();

//        app.newSession().logout();

    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer () {
        app.mail().stop();
    }
}

