package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

    @BeforeMethod

    public void ensurePreconditions() {
        if(! app.сontact().isThereACheckboxInTable()) {
            app.сontact().create(new ContactData("Anna", "Maksimova", "Contact1", "9005905555", "maxann89@gmail.com", "test4"), true);
            app.goTo().homePage();
        }
    }

    @Test (enabled = true)

    public void testContactModification() {
        List<ContactData> before = app.сontact().list();
//        int before = app.getContactHelper().getContactCount();
        int index = before.size() - 1;
        ContactData contact = new ContactData(before.get(index).getId(), "Elena", "Kulikova", "Contact2", "9005644444", "kulik@mail.ru", null);
        app.сontact().modify(index, contact);
        app.goTo().homePage();
        List<ContactData> after = app.сontact().list();
//        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after.size(),before.size());

        before.remove(index);
        before.add(contact);
//        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object> (after));
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);
    }

}
