package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroup extends TestBase {

    ContactData oneContact;
    GroupData oneAddGroup;
    ContactsAndGroups before;

    @BeforeTest
    public void ensurePreconditions() {

        Groups groups = app.db().groups(); //берем множество групп из БД
        Contacts contacts = app.db().contacts(); //берем множество контактов из БД

        oneContact = null;
        oneAddGroup = null;

        before = app.db().contactsGroups();

        for (ContactData c :
                    contacts) {
            Groups cg = c.getGroups();
            for (GroupData g :
                    groups) {
                if (!cg.contains(g)) {
                    oneAddGroup = g;
                    break;
                }
            }

            if(oneAddGroup != null){
                oneContact = c;
                break;
            }
        }

        if(oneContact == null){
            oneContact = new ContactData().withName("Alex").withSurname("Smotrov");
            oneAddGroup = groups.iterator().next();

            app.сontact().create(oneContact, false);
            app.goTo().homePage();
        }

        if(oneAddGroup == null) {
            oneAddGroup = new GroupData().withName("gr34");

            app.goTo().groupPage();
            app.group().create(oneAddGroup);
        }

    }

    @Test
    public void testContactAddToGroup() throws Exception {

        ContactGroupData contactsGroup = new ContactGroupData().withContactId(oneContact.getId()).withGroupId(oneAddGroup.getId());

        app.сontact().selectCheckboxT(oneContact.getId());
        app.сontact().selectContact(oneAddGroup, oneContact, app);
        app.сontact().clickToAddButton();
        app.сontact().returnToGroupPage(oneAddGroup);

        ContactsAndGroups after = app.db().contactsGroups();

        assertThat(after, equalTo(before.withAdded(contactsGroup)));

        app.сontact().returnToMainPage();

    }
}




