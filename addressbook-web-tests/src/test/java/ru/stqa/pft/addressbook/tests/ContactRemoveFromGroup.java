package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactRemoveFromGroup extends TestBase {

    @BeforeMethod

    public void ensurePreconditions() {
//        if() {
//
//        }
    }

    @Test
    public void testContactRemoveFromGroup() throws Exception {

        ContactData contact = new ContactData();
        app.сontact().testRemoveGroup(contact);

    }


}
