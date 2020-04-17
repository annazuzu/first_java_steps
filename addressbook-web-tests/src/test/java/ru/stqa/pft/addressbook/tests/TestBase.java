package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
@Listeners (MyTestListener.class)
public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

//    protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

    @BeforeSuite(alwaysRun = true)
    public void setUp(ITestContext context) throws Exception {
        app.init();
        context.setAttribute("app", app);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    @BeforeMethod (alwaysRun = true) //будет выполняться перед каждым тестовым методом
    public void logTestStart(Method m, Object[] p ) {
        //выводить сообщения в этом методе
        logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));
        //выводим сообщение о том, что запустился тест с определенными параметрами
    }

    @AfterMethod (alwaysRun = true) //будет выполняться после каждого тестового метода
    public void logTestStop(Method m) {
        //выводить сообщения в этом методе
        logger.info("Stop test " + m.getName());
    }

    public void verifyGroupListInUI() {
        if(Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups(); // 1 - загружаем список из БД
            Groups uiGroups = app.group().all(); // 2 - загружаем список из пользовательского интерфейса

            assertThat(uiGroups, equalTo(dbGroups.stream().map((g) -> new GroupData().
                    withId(g.getId()).
                    withName(g.getName())).
                    collect(Collectors.toSet())));
        }
    }

    public void verifyContactListInUI() {
        if(Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.сontact().all();

            assertThat(uiContacts, equalTo(dbContacts.stream().map((g) -> new ContactData().
                    withId(g.getId()).
                    withName(g.getName()).
                    withSurname(g.getSurname())).
                    collect(Collectors.toSet())));
        }
    }

}
