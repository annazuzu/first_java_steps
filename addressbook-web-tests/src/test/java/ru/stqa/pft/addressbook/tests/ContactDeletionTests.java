package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

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
    List<ContactData> before = app.сontact().list();
    int index = before.size() - 1;
    app.сontact().delete(index);
    List<ContactData> after = app.сontact().list();
    Assert.assertEquals(after.size(),index);

    before.remove(index);
    Assert.assertEquals(before, after);
  }

}
