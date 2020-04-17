package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupNew extends TestBase {

    private ContactData oneContact;
    private GroupData oneAddGroup;
    private Contacts before;
    private Contacts after;

    @BeforeTest
    public void ensurePreconditions() {

        oneContact = null;
        oneAddGroup = null;

        // Группы вообще есть? Если нет, то создаем 1 группу.
        if (app.db().groups().size() == 0)
        {   app.goTo().groupPage1();
            app.group().create(new GroupData().withName("gr3"));
            app.goTo().homePage();
        }

        // Контакты вообще есть? Если нет, то создаем 1 контакт.
        if (app.db().contacts().size() == 0){
            app.goTo().homePage();
            ContactData contact = new ContactData().withName("Alex").withSurname("Smotrov");
            app.сontact().create(contact, false);
            app.goTo().homePage();
            // Если true, то создается контакт с уже добавленной группой.
            // Если false, то создается контакт, не привязанный к группе.
        }

    }

    @Test
    public void testContactAddToGroup() throws InterruptedException {

        Groups groups = app.db().groups();

        oneAddGroup = oneGroupIsFull_orNot();

        //берем список всех контактов из БД:
        Contacts contacts = app.db().contacts();

        Contacts before = null;
        before = oneAddGroup.getContacts();

        Contacts gc = oneAddGroup.getContacts();
        for (ContactData contact :
                contacts) {
            if (!gc.contains(contact)) {
                oneContact = contact;
                break;
            }
        }

        int oneAddGroupId = oneAddGroup.getId();

        app.сontact().selectCheckboxByID(oneContact.getId());
        app.сontact().selectContact(oneAddGroup, oneContact, app);
        app.сontact().clickToAddButton();
        app.сontact().returnToGroupPage(oneAddGroup);

        ContactData contact = new ContactData().
                withId(oneContact.getId()).withName(oneContact.getName()).withSurname(oneContact.getSurname());


        oneAddGroup = reimportMeOneGroup(oneAddGroupId);

        Contacts after = null;
        after = oneAddGroup.getContacts();

        assertThat(after, equalTo(before.withAdded(contact)));
        Assert.assertEquals(after.size(),before.size() + 1);

        app.сontact().returnToMainPage();

    }

    // Вспомогательные методы:

    public GroupData findMeOneGroup () {
        Groups groups = app.db().groups();

        for (GroupData group : groups) {
            if (group != null) {
                oneAddGroup = group;
                return group;
            }
        }

        throw new RuntimeException("No new group found");
    }

    public GroupData reimportMeOneGroup(int oneAddGroupId) {
        Groups groups = app.db().groups();

        for (GroupData group : groups) {
            if (oneAddGroup.getId() == oneAddGroupId) {
                oneAddGroup = group;
                return group;
            }
        }
        throw new RuntimeException("Sorry, but no(");
    }

    public GroupData oneGroupIsFull_orNot() throws InterruptedException {

        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();

        for (GroupData group : groups) {

            Contacts beforeGc = null;
            beforeGc = group.getContacts();

            if (beforeGc.size() < contacts.size()) {
                oneAddGroup = group;
                return group;
            } else {
//                GroupData groupEmpty = new GroupData().withName("Group").withHeader("Tf").withFooter("all groups is full!");
//                app.goTo().groupPage();
//                app.group().create(groupEmpty);
//                return groupEmpty;

                // альтернативный вариант:
                // - переходим на страницу рандомной группы;
                // - выделяем все чекбоксы контактов;
                // - удаляем все контакты внутри данной группы;

                GroupData groupForClean = findMeOneGroup();
                app.сontact().goToGroup(groupForClean);
                app.сontact().massCBcheckbox();
                app.сontact().clickToRemoveButton();
                app.сontact().returnToGroupPage(groupForClean);
                app.сontact().returnToMainPage();
                return groupForClean;
            }

        }
        throw new RuntimeException("All groups is full!"); // не смогла победить эту строчку
    }

}