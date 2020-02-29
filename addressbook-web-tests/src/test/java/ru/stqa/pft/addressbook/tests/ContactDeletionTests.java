package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion() throws Exception {

    if(! app.getContactHelper().isThereACheckboxInTable()) {
      app.getContactHelper().createContact(new ContactData("Anna", "Maksimova", "Contact1", "9005905555", "maxann89@gmail.com", "test4"), true);
      app.getNavigationHelper().returntoHomePage();
    }
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectCheckbox(before - 1);
    app.getContactHelper().clicktoDeleteButton();
    app.getContactHelper().alertAssept();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after,before - 1);
  }

}
