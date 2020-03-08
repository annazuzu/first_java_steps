package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @Test (enabled = true)
  public void testContactCreation() throws Exception {
    Contacts before = app.сontact().all();
//    Set<ContactData> before = app.сontact().all();
    ContactData contact = new ContactData().withName("Anna").withSurname("Maksimova").withTitleContact("Contact1")
            .withTelMobile("9005905555").withEmail("maxann89@gmail.com").withGroup("test4");
    app.сontact().create(contact, true);
    app.goTo().homePage();
    assertThat(app.сontact().getContactCount(), equalTo(before.size() + 1));
    Contacts after = app.сontact().all();
//    Set<ContactData> after = app.сontact().all();
//    Assert.assertEquals(after.size(),before.size() + 1);
//    assertThat(after.size(), equalTo(before.size() + 1));

//    int maxCont = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();

//    contact.withId(maxCont);
//    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());

//    before.add(contact);

//    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//    before.sort(byId);
//    after.sort(byId);

    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}
