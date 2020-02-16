package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class ContactCreationTests {
  private WebDriver wdcontact;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wdcontact = new ChromeDriver();
    wdcontact.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wdcontact.get("http://localhost/addressbook/");
    login("admin", "secret");
  }

  private void login(String username, String password) {
    wdcontact.findElement(By.name("user")).clear();
    wdcontact.findElement(By.name("user")).sendKeys(username);
    wdcontact.findElement(By.name("pass")).click();
    wdcontact.findElement(By.name("pass")).clear();
    wdcontact.findElement(By.name("pass")).sendKeys(password);
    wdcontact.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @Test
  public void testContactCreation() throws Exception {

    initContactCreation();
    fillContactForm(new ContactData("Anna", "Maksimova", "Contact1", "9005905555", "maxann89@gmail.com"));
    submitContactCreation();
    returntoHomePage();

//    initContactCreation();
//    fillContactForm(new ContactData("Elena", "Kulikova", "Contact2","5677899", "mail.ru" ));
//    submitContactCreation();
//    returntoHomePage();
  }

  private void returntoHomePage() {
    wdcontact.findElement(By.linkText("home page")).click();
  }

  private void submitContactCreation() {
    wdcontact.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

  private void fillContactForm(ContactData contactData) {
    wdcontact.findElement(By.name("firstname")).click();
    wdcontact.findElement(By.name("firstname")).sendKeys(contactData.getName());
    wdcontact.findElement(By.name("lastname")).click();
    wdcontact.findElement(By.name("lastname")).sendKeys(contactData.getSurname());
    wdcontact.findElement(By.name("title")).click();
    wdcontact.findElement(By.name("title")).sendKeys(contactData.getTitleContact());
    wdcontact.findElement(By.name("mobile")).click();
    wdcontact.findElement(By.name("mobile")).sendKeys(contactData.getTelMobile());
    wdcontact.findElement(By.name("email")).click();
    wdcontact.findElement(By.name("email")).sendKeys(contactData.getEmail());
  }

  private void initContactCreation() {
    wdcontact.findElement(By.linkText("add new")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    logout();
    wdcontact.quit();
  }

  private void logout() {
    wdcontact.findElement(By.linkText("Logout")).click();
  }

  private boolean isElementPresent(By by) {
    try {
      wdcontact.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wdcontact.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

}
