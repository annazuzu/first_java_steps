package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{
    @Test

    public void testContactModification() {

        if(! app.getContactHelper().isThereACheckboxInTable()) {
            app.getContactHelper().createContact(new ContactData("Anna", "Maksimova", "Contact1", "9005905555", "maxann89@gmail.com", "test4"), true);
            app.getNavigationHelper().returntoHomePage();
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().initContactModification(before - 1);
        app.getContactHelper().fillContactForm(new ContactData("Elena", "Kulikova", "Contact2", "9005644444", "kulik@mail.ru", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returntoHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after,before);

    }

}
