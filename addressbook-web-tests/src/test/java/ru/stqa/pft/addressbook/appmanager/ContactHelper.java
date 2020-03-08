package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.*;

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

    }

    public void initContactCreation() {
      click(By.linkText("add new"));
    }

    public void modify(int index, ContactData contact) {
        initContactModification(index);
        fillContactForm(contact, false);
        submitContactModification();

    }

    public void modify(ContactData contact) {
        initContactModification(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();

    }

    public void delete(int index) {
        selectCheckbox(index);
        clicktoDeleteButton();
        alertAssept();
    }

    public void delete(ContactData contact) {
        selectCheckboxByID(contact.getId());
        clicktoDeleteButton();
        alertAssept();
    }


    public void alertAssept() {
      wd.switchTo().alert().accept();
    }

    public void clicktoDeleteButton() {
      click(By.xpath("//input[@value='Delete']"));
    }

    public void selectCheckbox(int indexContCheckbox) {
        wd.findElements(By.name("selected[]")).get(indexContCheckbox).click();
    }

    private void selectCheckboxByID(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initContactModification(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
//        wd.findElement(By.cssSelector("img[title='Edit']")).get(index)*/click();
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

    public void create(ContactData contactData, boolean b) {
        initContactCreation();
        fillContactForm(contactData, true);
        submitContactCreation();
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String name = element.findElements(By.tagName("td")).get(2).getText();
            String surname = element.findElements(By.tagName("td")).get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withName(name).withSurname(surname));
        }

        return contacts;
    }

    public Contacts /*Set<ContactData>*/ all() {
        Contacts /*Set<ContactData>*/ contacts = new Contacts()/*new HashSet<>()*/;
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String name = element.findElements(By.tagName("td")).get(2).getText();
            String surname = element.findElements(By.tagName("td")).get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withName(name).withSurname(surname));
        }

        return contacts;
    }

    public Set<ContactData> allset() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String name = element.findElements(By.tagName("td")).get(2).getText();
            String surname = element.findElements(By.tagName("td")).get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withName(name).withSurname(surname));
        }

        return contacts;
    }

//    public TreeSet<ContactData> set() {
//        TreeSet<ContactData> contacts = new TreeSet<ContactData>(Comparator.comparing(ContactData::getId));
//        List<WebElement> elements = wd.findElements(By.name("entry"));
//        for (WebElement element : elements) {
//            String name = element.findElements(By.tagName("td")).get(2).getText();
//            String surname = element.findElements(By.tagName("td")).get(1).getText();
//            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
//            contacts.add(new ContactData().withId(id).withName(name).withSurname(surname));
//        }
//
//        return contacts;
//    }


}
