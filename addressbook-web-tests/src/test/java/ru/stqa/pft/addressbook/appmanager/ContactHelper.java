package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.NoSuchElementException;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
      click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"),contactData.getName() );
        type(By.name("lastname"), contactData.getSurname());
        type(By.name("title"), contactData.getTitleContact());
        type(By.name("mobile"), contactData.getTelMobile());
        type(By.name("email"), contactData.getEmail());

        if(creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup()); //Это карточка создания контакта. Если этого элемента
            // нет на странице создания контакта, то тест упадет и он должен упасть.
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group"))); // Это карточка редактирования (модификации) контакта. Проверка того,
            //что этого элемента быть не должно на форме модификации контакта.
        }
//        if(isElementPresent(By.name("new_group"))) {
//            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
//        }

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

    public boolean isThereACheckboxInTable() {
        return isElementPresent(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
    }

    public boolean isThereAZeroNumber() { //неправильный путь, но пусть будет на память
        return isElementPresent(By.cssSelector("span#search_count 0"));
    }

    public void createContact(ContactData contactData, boolean b) {
        initContactCreation();
        fillContactForm(new ContactData("Anna", "Maksimova", "Contact1", "9005905555", "maxann89@gmail.com", "test4"), true);
        submitContactCreation();
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }
}
