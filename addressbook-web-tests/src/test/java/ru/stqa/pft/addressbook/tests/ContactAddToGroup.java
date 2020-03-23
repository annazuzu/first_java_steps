package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactAddToGroup extends TestBase {


    @Test
    public void testContactAddToGroup() throws Exception {

        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();

        ContactData contact = new ContactData();
        app.сontact().selectContactAndAddToGroup(contact);

    }



}
