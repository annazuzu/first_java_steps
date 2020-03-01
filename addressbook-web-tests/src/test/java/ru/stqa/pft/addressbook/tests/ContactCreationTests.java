package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
//    List<ContactData> before = app.getContactHelper().getContactList();
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().createContact(new ContactData("Anna", "Maksimova", "Contact1", "9005905555", "maxann89@gmail.com", "test4"), true);
    app.getNavigationHelper().returntoHomePage();
//    List<ContactData> after = app.getContactHelper().getContactList();
    int after = app.getContactHelper().getContactCount();
//    Assert.assertEquals(after.size(),before.size() + 1);
    Assert.assertEquals(after,before + 1);
  }

}
