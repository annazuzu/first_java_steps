package ru.stqa.pft.mantis.appmanager;

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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final Properties properties;
    private WebDriver wd;

    private String browser;
    private RegistrationHelper registrationHelper;
    private FtpHelper ftp;
    private MailHelper mailHelper;
    private JamesHelper jamesHelper;
    private AdminCUPHelper changePass;
    private DbHelper dbHelper;
    private SoapHelper soapHelper;


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

     }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public RegistrationHelper registration() throws MalformedURLException {
        if (registrationHelper == null) {
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public WebDriver getDriver() throws MalformedURLException {

        if (wd == null) {
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
                wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
            }

            wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }

    public FtpHelper ftp() { // он будет возвращать объект типа FtpHelper
        if (ftp == null) {
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public MailHelper mail () {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public JamesHelper james () {
        if (jamesHelper == null) {
            jamesHelper = new JamesHelper(this);
        }
        return jamesHelper;
    }

    public AdminCUPHelper changePass () throws MalformedURLException {
        if (changePass == null) {
            changePass = new AdminCUPHelper(this);
        }
        return changePass;
    }

    public DbHelper db() throws MalformedURLException {
        if (dbHelper == null) {
            dbHelper = new DbHelper(this);
        }
        return dbHelper;
    }

    public SoapHelper soap() {
        if (soapHelper == null) {
            soapHelper = new SoapHelper(this);
        }
        return soapHelper;
    }

}
