package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.NoSuchElementException;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returntoHomePage() {
      click(By.linkText("home page"));
    }

    public void submitContactCreation() {
      click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"),contactData.getName() );
        type(By.name("lastname"), contactData.getSurname());
        type(By.name("title"), contactData.getTitleContact());
        type(By.name("mobile"), contactData.getTelMobile());
        type(By.name("email"), contactData.getEmail());

        if(isElementPresent(By.name("new_group"))) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        }

    }

    public void initContactCreation() {
      click(By.linkText("add new"));
    }

    public void alertAssept() {
      wd.switchTo().alert().accept();
    }

    public void clicktoDeleteButton() {
      click(By.xpath("//input[@value='Delete']"));
    }

    public void selectCheckbox() {
      click(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
    }

    public void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModification() {
        click(By.name("update"));

    }
}
