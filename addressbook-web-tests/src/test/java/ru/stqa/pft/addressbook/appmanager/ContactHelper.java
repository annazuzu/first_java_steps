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

    public void create(ContactData contactData, boolean b) {
        initContactCreation();
        fillContactForm(contactData, true);
        submitContactCreation();
        contactsCache = null;
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
        contactsCache = null;

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
        contactsCache = null;
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

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModification(contact.getId());
        String name = wd.findElement(By.name("firstname")).getAttribute("value");
        String surname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withName(name).withSurname(surname).withHomePhone(home).
                withMobilePhone(mobile).withWorkPhone(work).withAddress(address).withEmail(email).withEmail2(email2).
                withEmail3(email3).withAddress(address);
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

    private Contacts contactsCache = null;

    public Contacts /*Set<ContactData>*/ all() {

        if (contactsCache != null) {
            return new Contacts(contactsCache);
        }

        contactsCache = new Contacts();
//        Contacts /*Set<ContactData>*/ contacts = new Contacts()/*new HashSet<>()*/;
//        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String name = element.findElements(By.tagName("td")).get(2).getText();
            String surname = element.findElements(By.tagName("td")).get(1).getText();
//            String allPhones = element.findElements(By.tagName("td")).get(5).getText();
//            String[] phones = element.findElements(By.tagName("td")).get(5).getText().split("\n");//split может использовать
//            // произвольные регулярные выражения
//            String allPhones = cells.get(5).get.text();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
//            contacts.add(new ContactData().withId(id).withName(name).withSurname(surname).withHomePhone(phones[0]).
//                    withMobilePhone(phones[1]).withWorkPhone(phones[2]));
            contactsCache.add(new ContactData().withId(id).withName(name).withSurname(surname));
        }

        return new Contacts(contactsCache);
//        return contacts;
    }

    public Set<ContactData> allset() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String name = element.findElements(By.tagName("td")).get(2).getText();
            String surname = element.findElements(By.tagName("td")).get(1).getText();
            String allPhones = element.findElements(By.tagName("td")).get(5).getText();
            String allEmails = element.findElements(By.tagName("td")).get(4).getText();
//            String[] phones = element.findElements(By.tagName("td")).get(5).getText().split("\n");
//            String[] emails = element.findElements(By.tagName("td")).get(4).getText().split("\n");
            String address = element.findElements(By.tagName("td")).get(3).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withName(name).withSurname(surname).withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
        }

        return contacts;
    }

}
