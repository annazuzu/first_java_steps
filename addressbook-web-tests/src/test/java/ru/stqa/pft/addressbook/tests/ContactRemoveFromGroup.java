package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroup extends TestBase {

    @BeforeMethod

    public void ensurePreconditions() {

        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();

        ContactData contact = contacts.iterator().next();
//        GroupData group = groups.iterator().next();

        if (groups.size() > 0) {

            try {
                testContactRemoveFromGroup();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            app.сontact().create(contact, true);
            app.goTo().homePage();
        }

    }

    @Test
    public void testContactRemoveFromGroup() throws Exception {

        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        ContactsAndGroups before = app.db().contactsGroups();

        ContactData contact = contacts.iterator().next();
        GroupData group = groups.iterator().next();

        ContactGroupData contactsGroup = new ContactGroupData().withContactId(contact.getId()).withGroupId(group.getId());

        app.сontact().testRemoveGroup(groups.iterator().next(), contacts.iterator().next());

        ContactsAndGroups after = app.db().contactsGroups();

//        assertThat(app.сontact().getContactCount(), equalTo(before.size() - 1));

        assertThat(after, equalTo(before.without(contactsGroup)));

        app.сontact().returnToMainPage();

    }

}
