package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {

    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().createContact(new ContactData("Anna", "Maksimova", "Contact1", "9005905555", "maxann89@gmail.com", "test4"), true);
    app.getNavigationHelper().returntoHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after,before + 1);
  }

}
