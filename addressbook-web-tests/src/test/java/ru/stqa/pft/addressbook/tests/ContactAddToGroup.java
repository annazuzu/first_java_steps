package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactAddToGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

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

        app.сontact().selectContactAndAddToGroup(groups, contacts);

    }

}
