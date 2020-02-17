package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {

    app.initContactCreation();
    app.fillContactForm(new ContactData("Anna", "Maksimova", "Contact1", "9005905555", "maxann89@gmail.com"));
    app.submitContactCreation();
    app.returntoHomePage();
  }

}
