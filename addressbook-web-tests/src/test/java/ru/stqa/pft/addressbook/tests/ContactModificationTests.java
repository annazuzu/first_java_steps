package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{
    @Test

    public void testContactModification() {

        if(! app.getContactHelper().isThereACheckboxInTable()) {
            app.getContactHelper().createContact(new ContactData("Anna", "Maksimova", "Contact1", "9005905555", "maxann89@gmail.com", "test4"), true);
            app.getNavigationHelper().returntoHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
//        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().initContactModification(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Elena", "Kulikova", "Contact2", "9005644444", "kulik@mail.ru", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returntoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
//        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after.size(),before.size());

        before.remove(before.size() - 1);
        before.add(contact);
//        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object> (after));
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);

    }

}
