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
            app.сontact().create(new ContactData().withName("Anna").withSurname("Maksimova").withTitleContact("Contact1")
                    .withTelMobile("9005905555").withEmail("maxann89@gmail.com").withGroup("test4"), true);
            app.goTo().homePage();
        }
    }

    @Test (enabled = true)

    public void testContactModification() {
        List<ContactData> before = app.сontact().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData().withId(before.get(index).getId()).withName("Elena").withSurname("Kulikova")
                .withTitleContact("Contact2").withTelMobile("9005644444").withEmail("kulik@mail.ru");
        app.сontact().modify(index, contact);
        app.goTo().homePage();
        List<ContactData> after = app.сontact().list();
        Assert.assertEquals(after.size(),before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);
    }

}
