package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper {

    public WebDriver wd;

    public ContactHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void returntoHomePage() {
      wd.findElement(By.linkText("home page")).click();
    }

    public void submitContactCreation() {
      wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }

    public void fillContactForm(ContactData contactData) {
      wd.findElement(By.name("firstname")).click();
      wd.findElement(By.name("firstname")).sendKeys(contactData.getName());
      wd.findElement(By.name("lastname")).click();
      wd.findElement(By.name("lastname")).sendKeys(contactData.getSurname());
      wd.findElement(By.name("title")).click();
      wd.findElement(By.name("title")).sendKeys(contactData.getTitleContact());
      wd.findElement(By.name("mobile")).click();
      wd.findElement(By.name("mobile")).sendKeys(contactData.getTelMobile());
      wd.findElement(By.name("email")).click();
      wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
    }

    public void initContactCreation() {
      wd.findElement(By.linkText("add new")).click();
    }

    public void alertAssept() {
      wd.switchTo().alert().accept();
    }

    public void clicktoDeleteButton() {
      wd.findElement(By.xpath("//input[@value='Delete']")).click();
    }

    public void selectCheckbox() {
      wd.findElement(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input")).click();
    }
}
