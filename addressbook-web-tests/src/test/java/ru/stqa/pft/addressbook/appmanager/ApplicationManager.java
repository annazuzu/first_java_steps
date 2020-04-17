package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final Properties properties;
    public WebDriver wd;

    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    public GroupHelper groupHelper;
    private String browser;
    private DbHelper dbHelper;
    private GaddRemoveCHelper gcHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
        //создаем объект типа Properties и сохраняем его в поле этого класса
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        //часть имени конфигурационного файла
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        //загружаем. В качесте параметра нужно передать ридер

        dbHelper = new DbHelper();

        if ("".equals(properties.getProperty("selenium.server"))) {
            if (browser.equals(BrowserType.CHROME)) {
                wd = new ChromeDriver();
            } else if (browser.equals(BrowserType.FIREFOX)) {
                wd = new FirefoxDriver();
            } else if (browser.equals(BrowserType.IE)) {
                wd = new InternetExplorerDriver();
            }
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser);
 //           capabilities.setPlatform(Platform.fromString(System.getProperty("platform", "win10")));
            wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
        }

//        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));/*http://localhost/addressbook"*/
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        contactHelper = new ContactHelper(wd);
        gcHelper = new GaddRemoveCHelper(wd);
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));

    }


    public void stop() {
        wd.quit();

    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public ContactHelper сontact() {
        return contactHelper;
    }

    public DbHelper db() {
        return dbHelper;
    }

    public GaddRemoveCHelper gc() {
        return gcHelper;
    }
}
