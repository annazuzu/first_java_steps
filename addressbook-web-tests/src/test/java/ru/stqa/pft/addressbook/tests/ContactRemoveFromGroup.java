package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroup extends TestBase {

    ContactData oneContact;
    GroupData oneDelGroup;
    ContactsAndGroups before;

    @BeforeTest

    public void ensurePreconditions() {

        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();

        oneContact = null;
        oneDelGroup = null;

        before = app.db().contactsGroups();

        for (ContactData c : contacts) {
            Groups cg = c.getGroups();

            if (cg.size() > 0) {
                oneContact = c;
                oneDelGroup = cg.iterator().next();
                break;
            }
        }

        if (oneContact != null && oneDelGroup != null) {
            return;
        }

        if (oneContact == null) {

            if (contacts.size() > 0) {
                oneContact = contacts.iterator().next();
            } else {
                oneContact = new ContactData().withName("Alex").withSurname("Smotrov");

                Contacts before = app.db().contacts();
                app.сontact().create(oneContact, false);
                app.goTo().homePage();

            }
        }

        if (oneDelGroup == null) {
            if (groups.size() > 0) {
                oneDelGroup = groups.iterator().next();
            } else {
                oneDelGroup = new GroupData().withName("gr34");

                app.goTo().groupPage();
                app.group().create(oneDelGroup);
            }
        }

   }

    @Test
    public void testContactRemoveFromGroup() throws Exception {

        ContactGroupData contactsGroup = new ContactGroupData().
                withContactId(oneContact.getId()).withGroupId(oneDelGroup.getId());

        app.сontact().goToGroup(oneDelGroup);
        app.сontact().selectCheckboxByID(oneContact.getId());
        app.сontact().clickToRemoveButton();
        app.сontact().returnToGroupPage(oneDelGroup);

        ContactsAndGroups after = app.db().contactsGroups();

        assertThat(after, equalTo(before.without(contactsGroup)));

        app.сontact().returnToMainPage();

    }

}
