package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class GaddRemoveCHelper extends HelperBase{

    public GaddRemoveCHelper(WebDriver wd) {
        super(wd);
    }

    public void gDeleteAllc(GroupData group, ApplicationManager app) throws InterruptedException {
        app.сontact().goToGroup(group);
        app.сontact().massCBcheckbox();
        app.сontact().clickToRemoveButton();
        app.сontact().returnToGroupPage(group);
        app.сontact().returnToMainPage();
    }

    // Сначала ищем любой контакт (так как все группы пусты, то нам сгодится любой):

    public ContactData findMeOneContact (ApplicationManager app) {
        Contacts contacts = app.db().contacts();

        for (ContactData contact : contacts) {
            if (contact != null) {
                return contact;
            }
        }
        throw new RuntimeException("No new group found");
    }

    public void cAddtog (GroupData group, ApplicationManager app) {
        ContactData contact = findMeOneContact(app);
        app.сontact().selectCheckboxByID(contact.getId());
        app.сontact().selectContact(group, contact, app);
        app.сontact().clickToAddButton();
        app.сontact().returnToGroupPage(group);
        app.сontact().returnToMainPage();
    }

}
