package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(",");
      list.add(new Object[] {new ContactData().withName(split[0]).withSurname(split[1]).
              withAddress(split[2]).withMobilePhone(split[3]).withEmail(split[4])});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @Test (dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) throws Exception {
    Contacts before = app.сontact().all();
//    Set<ContactData> before = app.сontact().all();
//    File photo = new File("src/test/resources/photo.jpg");
//    ContactData contact = new ContactData().withName("Anna").withSurname("Maksimova").withPhoto(photo).withTitleContact("Contact1")
//            .withTelMobile("9005905555").withEmail("maxann89@gmail.com").withGroup("test4");
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

//  @Test (enabled = false)
//  public void testCurrentDir() {
//    File currentDir = new File(".");
//    System.out.println(currentDir.getAbsolutePath());
//    File photo = new File("src/test/resources/photo.jpg");
//    System.out.println(photo.getAbsolutePath());
//    System.out.println(photo.exists());
//  }

}
