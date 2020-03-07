package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test (enabled = true)
  public void testContactCreation() throws Exception {
    List<ContactData> before = app.сontact().list();
//    int before = app.getContactHelper().getContactCount();
    ContactData contact = new ContactData("Anna", "Maksimova", "Contact1", "9005905555", "maxann89@gmail.com", "test4");
    app.сontact().create(contact, true);
    app.goTo().homePage();
    List<ContactData> after = app.сontact().list();
//    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after.size(),before.size() + 1);
//    Assert.assertEquals(after,before + 1);

    int maxCont = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();

    contact.setId(maxCont);
    before.add(contact);

    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);
  }

}
