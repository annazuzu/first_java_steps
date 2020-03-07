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
    ContactData contact = new ContactData().withName("Anna").withSurname("Maksimova").withTitleContact("Contact1")
            .withTelMobile("9005905555").withEmail("maxann89@gmail.com").withGroup("test4");
    app.сontact().create(contact, true);
    app.goTo().homePage();
    List<ContactData> after = app.сontact().list();
    Assert.assertEquals(after.size(),before.size() + 1);

    int maxCont = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();

    contact.withId(maxCont);
    before.add(contact);

    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);
  }

}
