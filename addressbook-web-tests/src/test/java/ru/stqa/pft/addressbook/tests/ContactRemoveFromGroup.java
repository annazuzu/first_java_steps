package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactRemoveFromGroup extends TestBase {

    @BeforeMethod

    public void ensurePreconditions() {

        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();

        ContactData contact = contacts.iterator().next();
        GroupData group = groups.iterator().next();

        if (group.getContacts().size() > 0) {

        } else {
            app.сontact().create(contact, true);
            app.goTo().homePage();
        }

    }

    @Test
    public void testContactRemoveFromGroup() throws Exception {

        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();

        app.сontact().testRemoveGroup(groups, contacts);

    }


}
