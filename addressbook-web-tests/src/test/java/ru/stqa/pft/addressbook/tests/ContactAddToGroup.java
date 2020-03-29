package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroup extends TestBase {

    ContactData theContact;
    GroupData groupToAdd;
    ContactsAndGroups before;

    @BeforeTest
    public void ensurePreconditions() {

        Groups groups = app.db().groups(); //берем множество групп из БД
        Contacts contacts = app.db().contacts(); //берем множество контактов из БД

        theContact = null;
        groupToAdd = null;

        before = app.db().contactsGroups();

        for (ContactData c :
                    contacts) {
            Groups cg = c.getGroups();
            for (GroupData g :
                    groups) {
                if (!cg.contains(g)) {
                    groupToAdd = g;
                    break;
                }
            }

            if(groupToAdd != null){
                theContact = c;
                break;
            }
        }

        if(theContact == null){
            theContact = new ContactData().withName("Alex").withSurname("Smotrov");
            groupToAdd = groups.iterator().next();

            app.сontact().create(theContact, false);
            app.goTo().homePage();
        }

        if(groupToAdd == null) {
            groupToAdd = new GroupData().withName("gr34");

            app.goTo().groupPage();
            app.group().create(groupToAdd);
        }

    }

    @Test
    public void testContactAddToGroup() throws Exception {

//        ContactsAndGroups before = app.db().contactsGroups();
        ContactGroupData contactsGroup = new ContactGroupData().withContactId(theContact.getId()).withGroupId(groupToAdd.getId());

        app.сontact().selectCheckboxT(theContact.getId());
        app.сontact().selectContact(groupToAdd, theContact, app);
        app.сontact().clickToAddButton();
        app.сontact().returnToGroupPage(groupToAdd);

        ContactsAndGroups after = app.db().contactsGroups();

        assertThat(after, equalTo(before.withAdded(contactsGroup)));

        app.сontact().returnToMainPage();

    }
}




