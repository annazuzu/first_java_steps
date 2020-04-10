package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroupNew extends TestBase {

    private ContactData oneContact;
    private GroupData oneDelGroup;
    private Contacts before;
    private Contacts after;

    @BeforeTest

    public GroupData findMeOneGroup () {
        Groups groups = app.db().groups();

        for (GroupData group : groups) {
            if (group != null) {
                oneDelGroup = group;
                return group;
            }

        }

        throw new RuntimeException("No new group found");
    }

    public GroupData reimportMeOneGroup(int oneDelGroupId) {
        Groups groups = app.db().groups();

        for (GroupData group : groups) {
            if (oneDelGroup.getId() == oneDelGroupId) {
                oneDelGroup = group;
                return group;
            }

        }
        throw new RuntimeException("Sorry, but no(");
    }

    public GroupData oneGroupIsEmpty_orNot() {
        Groups groups = app.db().groups();

        for (GroupData group : groups) {

            Contacts beforeDc = null;
            beforeDc = group.getContacts();

            if (beforeDc.size() != 0) {
                oneDelGroup = group;
                return group;
            }

        }

        throw new RuntimeException("All groups is empty!");

    }

    public void ensurePreconditions() {

        oneContact = null;
        oneDelGroup = null;

        // Группы вообще есть? Если нет, то создаем 1 группу.
        if (app.db().groups().size() == 0)
        {   app.goTo().groupPage1();
            app.group().create(new GroupData().withName("gr45"));
        }

        // Контакты вообще есть? Если нет, то создаем 1 контакт.
        if (app.db().contacts().size() == 0){
            app.goTo().homePage();
            ContactData contact = new ContactData().withName("Ivan").withSurname("Klevin");
            app.сontact().create(contact, true);
            // Если true, то создается контакт с уже добавленной группой.
            // Если false, то создается контакт, не привязанный к группе.
        }
    }

    @Test
    public void testContactRemoveFromGroup() {

        Groups groups = app.db().groups();

        oneDelGroup = oneGroupIsEmpty_orNot();

        int oneDelGroupId = oneDelGroup.getId();

        Contacts contacts = app.db().contacts();

        Contacts before = null;
        before = oneDelGroup.getContacts();

        Contacts dc = oneDelGroup.getContacts();
        for (ContactData contact :
                contacts) {
            if (dc.contains(contact)) {
                oneContact = contact;
                break;
            }
        }

        app.сontact().goToGroup(oneDelGroup);
        app.сontact().selectCheckboxXpath(oneContact.getId());
        app.сontact().clickToRemoveButton();
        app.сontact().returnToGroupPage(oneDelGroup);

        ContactData contact = new ContactData().
                withId(oneContact.getId()).withName(oneContact.getName()).withSurname(oneContact.getSurname());


        oneDelGroup = reimportMeOneGroup(oneDelGroupId);

        Contacts after = null;
        after = oneDelGroup.getContacts();

        assertThat(after, equalTo(before.without(contact)));

        Assert.assertEquals(after.size(),before.size() - 1);

        app.сontact().returnToMainPage();

    }

}