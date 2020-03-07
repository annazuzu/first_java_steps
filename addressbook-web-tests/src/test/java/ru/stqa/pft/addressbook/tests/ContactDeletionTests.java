package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

public class ContactDeletionTests extends TestBase{

  @BeforeMethod

  public void ensurePreconditions() {
    if(! app.сontact().isThereACheckboxInTable()) {
      app.сontact().create(new ContactData().withName("Anna").withSurname("Maksimova").withTitleContact("Contact1")
              .withTelMobile("9005905555").withEmail("maxann89@gmail.com").withGroup("test4"), true);
      app.goTo().homePage();
    }
  }

  @Test (enabled = true)
  public void testContactDeletion() throws Exception {
    Set<ContactData> before = app.сontact().all();
    ContactData deletedContact = before.iterator().next();
//    int index = before.size() - 1;
    app.сontact().delete(deletedContact);
    Set<ContactData> after = app.сontact().all();
    Assert.assertEquals(after.size(),before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }

}
