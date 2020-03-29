package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroup extends TestBase {

    ContactData theContact;
    GroupData groupFromDel;
    ContactsAndGroups before;

    @BeforeTest

    public void ensurePreconditions() {

        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();

        theContact = null;
        groupFromDel = null;

        before = app.db().contactsGroups();

        for (ContactData c : contacts) {
            Groups cg = c.getGroups();

            if (cg.size() > 0) {
                theContact = c;
                groupFromDel = cg.iterator().next();
                break;
            }
        }

        if (theContact != null && groupFromDel != null) {
            return;
        }

        if (theContact == null) {

            if (contacts.size() > 0) {
                theContact = contacts.iterator().next();
            } else {
                theContact = new ContactData().withName("Alex").withSurname("Smotrov");

                Contacts before = app.db().contacts();
                app.сontact().create(theContact, false);
                app.goTo().homePage();

//                theContact = findNewContact(before);
            }
        }

        if (groupFromDel == null) {
            if (groups.size() > 0) {
                groupFromDel = groups.iterator().next();
            } else {
                groupFromDel = new GroupData().withName("gr34");

                app.goTo().groupPage();
                app.group().create(groupFromDel);
            }
        }

   }

    @Test
    public void testContactRemoveFromGroup() throws Exception {

//        Groups groups = app.db().groups();
//        Contacts contacts = app.db().contacts();
//        ContactsAndGroups before = app.db().contactsGroups();

        ContactGroupData contactsGroup = new ContactGroupData().withContactId(theContact.getId()).withGroupId(groupFromDel.getId());

//        ContactData contact = contacts.iterator().next();
//        GroupData group = groups.iterator().next();
//        ContactGroupData deletedContact = before.iterator().next();

//        ContactGroupData contactsGroup = new ContactGroupData().withContactId(contact.getId()).withGroupId(group.getId());

//        app.сontact().goToGroup(groupFromDel);
        app.сontact().testRemoveGroup(groupFromDel);
        app.сontact().selectCheckboxT(theContact.getId());
        app.сontact().clickToRemoveButton();
        app.сontact().returnToGroupPage(groupFromDel);

        ContactsAndGroups after = app.db().contactsGroups();

        assertThat(after, equalTo(before.without(contactsGroup)));

        app.сontact().returnToMainPage();

    }

}
