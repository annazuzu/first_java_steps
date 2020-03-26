package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ContactHelper extends HelperBase{

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
      click(By.xpath("(//input[@name='submit'])[2]"));
    }


    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"),contactData.getName() );
        type(By.name("lastname"), contactData.getSurname());
        attach(By.name("photo"), contactData.getPhoto());
        type(By.name("title"), contactData.getTitleContact());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("email"), contactData.getEmail());

//        if(creation) {
//            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup()); //Это карточка создания контакта. Если этого элемента
//            // нет на странице создания контакта, то тест упадет и он должен упасть.
//        } else {
//            Assert.assertFalse(isElementPresent(By.name("new_group"))); // Это карточка редактирования (модификации) контакта. Проверка того,
//            //что этого элемента быть не должно на форме модификации контакта.
//        }

        if(creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1); // если указана одна группа, то мы будем пытаться её выбрать из списка
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(String.valueOf(contactData.getGroups().iterator().next().getName()));
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
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

    public void selectCheckboxByID(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initContactModification(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
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
                withEmail3(email3);
    }


    public void submitContactModification() {
        click(By.name("update"));

    }


//--------------------------------------
//BEGIN ADD CONTACT TO GROUP

    public void selectContactAndAddToGroup (GroupData group, ContactData contact) {

        wd.findElement(By.name("to_group")).click();

        if (contact.getGroups().size() > 0) {
//            Assert.assertTrue(contact.getGroups().size() == 1);
            new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(String.valueOf(group.getName()));

        } else {
            app.goTo().GroupPage();
            app.group().create(group);
            selectCheckboxByID(contact.getId());
            wd.findElement(By.name("to_group")).click();
            new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(String.valueOf(group.getName()));
        }

        wd.findElement(By.name("to_group")).click();
        wd.findElement(By.name("add")).click();
        wd.findElement(By.linkText("group page \"" + group.getName() + "\"")).click();
        wd.findElement(By.name("group")).click();

    }

    public void returnToMainPageAfterAddContactToGroup() {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText("[all]");
        wd.findElement(By.name("group")).click();
    }

// END ADD CONTACT TO GROUP

    public void testRemoveGroup(Groups groups, Contacts contacts) throws Exception {

        ContactData contact = contacts.iterator().next();
        GroupData group = groups.iterator().next();

            wd.findElement(By.name("group")).click();
            new Select(wd.findElement(By.name("group"))).selectByVisibleText(String.valueOf(group.getName()));
            wd.findElement(By.name("group")).click();

            //на странице группы:

            List<WebElement> elements = wd.findElements(By.name("entry"));
            for (WebElement element : elements) {

                int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
                wd.findElement(By.cssSelector("input[value='" + id + "']")).click();

            }

        List<ContactData> before = list();

            wd.findElement(By.name("remove")).click();
            wd.findElement(By.linkText("group page \"" + group.getName() + "\"")).click();
            wd.findElement(By.name("group")).click();

        List<ContactData> after = list();
        assertThat(after.size(), equalTo(before.size() - 1));


//        Contacts after = app.сontact().all();
//        assertThat(app.сontact().getContactCount(), equalTo(before.size() - 1));
//        assertThat(after, equalTo(before.without(contact)));


            new Select(wd.findElement(By.name("group"))).selectByVisibleText("[all]");
            wd.findElement(By.name("group")).click();

    }

//    public void equalsGroupsContacts(Groups groups, Contacts contacts) {
//
//        ContactData contact = contacts.iterator().next();
//        GroupData group = groups.iterator().next();
//
//        wd.findElement(By.name("group")).click();
//        new Select(wd.findElement(By.name("group"))).selectByVisibleText(String.valueOf(group.getName()));



//
//    }

//--------------------------------------

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
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String name = element.findElements(By.tagName("td")).get(2).getText();
            String surname = element.findElements(By.tagName("td")).get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contactsCache.add(new ContactData().withId(id).withName(name).withSurname(surname));
        }

        return new Contacts(contactsCache);
    }

    public Set<ContactData> allset() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String name = element.findElements(By.tagName("td")).get(2).getText();
            String surname = element.findElements(By.tagName("td")).get(1).getText();
            String allPhones = element.findElements(By.tagName("td")).get(5).getText();
            String allEmails = element.findElements(By.tagName("td")).get(4).getText();
            String address = element.findElements(By.tagName("td")).get(3).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withName(name).withSurname(surname).withAllPhones(allPhones).
                    withAllEmails(allEmails).withAddress(address));
        }
        return contacts;
    }

}
