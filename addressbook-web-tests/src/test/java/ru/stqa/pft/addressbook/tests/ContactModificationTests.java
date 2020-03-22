package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{

    @BeforeMethod

    public void ensurePreconditions() {

        if (app.db().contacts().size() == 0) {
                app.сontact().create(new ContactData().withName("Anna").withSurname("Maksimova").withTitleContact("Contact1")
                        .withMobilePhone("9005905555").withEmail("maxann89@gmail.com").withAddress("Lenin str, Erepenin"), true);
                app.goTo().homePage();
        }

//        if(! app.сontact().isThereACheckboxInTable()) {
//            app.сontact().create((new ContactData().withName("Anna").withSurname("Maksimova").withTitleContact("Contact1")
////                    .withTelMobile("9005905555").withEmail("maxann89@gmail.com").withGroup("test4")/*, true*/);withGroup("test4")/*, true*/);
//            app.goTo().homePage();
//        }
    }

    @Test (enabled = true)
    public void testContactModification() {

        Contacts before = app.db().contacts();
        verifyContactListInUI();
//        Contacts before = app.сontact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withName("Elena").withSurname("Kulikova")
                .withTitleContact("Contact2").withMobilePhone("9005644444").withEmail("kulik@mail.ru").
                        withAddress("Pushkin str, Kolotushkin");
        app.сontact().modify(contact);
        app.goTo().homePage();

        assertThat(app.сontact().getContactCount(), equalTo(before.size()));

        Contacts after = app.db().contacts();
//        Contacts after = app.сontact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUI();
    }

}
