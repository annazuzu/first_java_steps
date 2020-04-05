package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroup extends TestBase {

    ContactData oneContact;
    GroupData oneAddGroup;
    Contacts before;

    private ContactData findNewContact( Contacts before) {
        Contacts after = app.db().contacts();

        for (ContactData contact : after) {
            if (!before.contains(contact)) {
                return contact;
            }
        }
        throw new RuntimeException("No new contact found");
    }

    @BeforeTest
    public void ensurePreconditions() {

        Groups groups = app.db().groups(); //берем множество групп из БД
        System.out.println(groups);
        Contacts contacts = app.db().contacts(); //берем множество контактов из БД
        System.out.println(contacts);

        oneContact = null;
        oneAddGroup = null;

        for (ContactData contact :
                    contacts) {
            Groups cg = contact.getGroups(); //бежит по циклу и берет группы внутри контактов (выдирает список групп, в которых состоит контакт)
            System.out.println(cg);
            for (GroupData group :
                    groups) {
                if (!cg.contains(group)) { //если список полученных из контакта групп содержит эту группу, то он её пропускает и проверяет следующую
                    oneAddGroup = group;
                    before = oneAddGroup.getContacts(); //загружает из БД список контактов до манипуляций
                    System.out.println("Список контактов в группе " + oneAddGroup.getId() + " до манипуляций " + before);
                    break;
                }
            }

            if(oneAddGroup != null){
                oneContact = contact;
                break;
            }
        }

        if(oneContact == null){ //если нет контакта, то создает новый объект
            oneContact = new ContactData().withName("Alex").withSurname("Smotrov");

            oneAddGroup = groups.iterator().next(); //берет первую группу из списка групп

            app.сontact().create(oneContact, false); //по объекту создает новый контакт
            app.goTo().homePage(); //и переходит на главную страницу (где список контактов)

            oneContact = findNewContact(before);
        }

        if(oneAddGroup == null) {
            oneAddGroup = new GroupData().withName("gr34");

            app.goTo().groupPage();
            app.group().create(oneAddGroup);
        }

    }

    @Test
    public void testContactAddToGroup() throws Exception {

        ContactGroupData contactsGroup = new ContactGroupData().withContactId(oneContact.getId()).withGroupId(oneAddGroup.getId());
//        ContactData contact1 = new ContactData().withId(oneContact.getId()).withName(oneContact.getName()).withSurname(oneContact.getSurname());

        app.сontact().selectCheckboxT(oneContact.getId());
        app.сontact().selectContact(oneAddGroup, oneContact, app);
        app.сontact().clickToAddButton();
        app.сontact().returnToGroupPage(oneAddGroup);
        System.out.printf("Add to group \"%s\": %s\n", oneAddGroup.getName(), oneContact);

//        Groups after = app.db().groups();
        Contacts after = oneAddGroup.getContacts();
//
        assertThat(after, equalTo(before));

//        System.out.println(before.withAdded(contactsGroup));
        System.out.println(after);

//        assertThat(after, equalTo(before.withAdded(contactsGroup.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

//        assertThat(after, equalTo(before.withAdded(contactsGroup)));

        app.сontact().returnToMainPage();

//        Groups newGroupsList = null;
//        for (ContactData c : after) {
//            if (c.getId() == oneContact.getId()) {
//                newGroupsList = c.getGroups();
//                break;
//            }
//        }
//        Assert.assertTrue(newGroupsList != null, "No groups for contact " + oneContact.getId());
//
//
//        boolean newGroupFound = false;
//        for (GroupData g : newGroupsList) {
//            if (oneAddGroup.getName().equals(g.getName())) {
//                newGroupFound = true;
//                break;
//            }
//        }
//
//        GroupData ug = null;
//        Groups groups = app.db().groups();
//        for (GroupData g : groups) {
//            if (g.getId() == oneAddGroup.getId()) {
//                ug = g;
//                break;
//            }
//        }
//        Assert.assertTrue(ug != null);
//
//        Assert.assertTrue(newGroupFound, "Test failed: no new group");
//        app.goTo().homePage();
//        app.сontact().choiceGroup(ug);
//        Contacts contactsOnPage = app.сontact().all();
//        Contacts contactsInDB = ug.getContacts();
//        assertThat(contactsOnPage, equalTo(contactsInDB));

    }
}




