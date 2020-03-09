package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactMailAddressTests extends TestBase {

    @Test

    public void testMailAddress () {

        app.goTo().homePage();
        ContactData contact = app.сontact().allset().iterator().next();
        ContactData contactInfoFromEditForm = app.сontact().infoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));

    }

}
