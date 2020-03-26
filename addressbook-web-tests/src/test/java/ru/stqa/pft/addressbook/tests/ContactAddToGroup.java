package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ContactAddToGroup extends TestBase {

    ContactData theContact;
    GroupData groupToAdd;

    @BeforeTest
    public void ensurePreconditions() {

        theContact = null;
        groupToAdd = null;

        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();

        for (ContactData c : contacts) {
            Groups cg = c.getGroups();

            for (GroupData g : groups) {
                if (!cg.contains(g)) {
                    groupToAdd = g;
                    break;
                }
            }
            if (groupToAdd != null) {
                theContact = c;
                break;
            }

            if (app.db().contacts().size() == 0) {
                app.сontact().create(new ContactData().withName("Anna").withSurname("Maksimova").withTitleContact("Contact1")
                        .withMobilePhone("9005905555").withEmail("maxann89@gmail.com").withAddress("Lenin str, Erepenin"), false);
                app.goTo().homePage();
            }
        }

    }

    @Test
    public void testContactAddToGroup() throws Exception {

//        Groups groups = app.db().groups();
//        Contacts contacts = app.db().contacts();

        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        ContactsAndGroups before = app.db().contactsGroups();

        ContactData contact = contacts.iterator().next();
        GroupData group = groups.iterator().next();

        ContactGroupData contactsGroup = new ContactGroupData().withContactId(contact.getId()).withGroupId(group.getId());

        app.сontact().selectCheckboxByID(contact.getId());
        app.сontact().selectContactAndAddToGroup(groups.iterator().next(), contacts.iterator().next());

        ContactsAndGroups after = app.db().contactsGroups();

        assertThat(after, equalTo(before.withAdded(contactsGroup)));

        app.сontact().returnToMainPageAfterAddContactToGroup();

    }
}




