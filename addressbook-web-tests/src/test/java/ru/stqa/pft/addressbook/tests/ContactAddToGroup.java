package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ContactAddToGroup extends TestBase {

//    ContactData oneContact;
//    GroupData oneGroupToAdd;

    @BeforeMethod
    public void ensurePreconditions() {

        if (app.db().groups().size() == 0)
        {   app.goTo().GroupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }

        if (app.db().contacts().size() == 0) {
            app.сontact().create(new ContactData().withName("Anna").withSurname("Maksimova").withTitleContact("Contact1")
                    .withMobilePhone("9005905555").withEmail("maxann89@gmail.com").withAddress("Lenin str, Erepenin"), false);
            app.goTo().homePage();
        }

    }

    @Test
    public void testContactAddToGroup() throws Exception {


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

        app.сontact().returnToMainPage();

    }
}




