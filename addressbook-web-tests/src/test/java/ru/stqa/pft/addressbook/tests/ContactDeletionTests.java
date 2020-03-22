package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase{

  @BeforeMethod

  public void ensurePreconditions() {

    if (app.db().contacts().size() == 0) {
      app.сontact().create(new ContactData().withName("Anna").withSurname("Maksimova").withTitleContact("Contact1")
              .withMobilePhone("9005905555").withEmail("maxann89@gmail.com").withAddress("Lenin str, Erepenin").withGroup("test4")/*, true*/);
      app.goTo().homePage();
    }

//    if(! app.сontact().isThereACheckboxInTable()) {
//      app.сontact().create(new ContactData().withName("Anna").withSurname("Maksimova").withTitleContact("Contact1")
//              .withTelMobile("9005905555").withEmail("maxann89@gmail.com").withGroup("test4")/*, true*/);
//      app.goTo().homePage();
//    }
  }

  @Test (enabled = true)
  public void testContactDeletion() throws Exception {
    Contacts before = app.db().contacts();
    verifyContactListInUI();
//    Contacts before = app.сontact().all();
    ContactData deletedContact = before.iterator().next();
    app.сontact().delete(deletedContact);

    assertThat(app.сontact().getContactCount(), equalTo(before.size() - 1));

    Contacts after = app.db().contacts();
//    Contacts after = app.сontact().all();
    assertThat(after, equalTo(before.without(deletedContact)));
    verifyContactListInUI();
  }

}
