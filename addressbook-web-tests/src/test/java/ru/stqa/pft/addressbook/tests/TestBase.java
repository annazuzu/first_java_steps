package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
//    protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
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

}
