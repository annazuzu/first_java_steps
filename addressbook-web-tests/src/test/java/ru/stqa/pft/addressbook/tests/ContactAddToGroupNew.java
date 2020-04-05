package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactAddToGroupNew extends TestBase {

    ContactData oneContact;
    GroupData oneAddGroup;
    Contacts before;
    Groups afterGroups;

    // Выбираем рандомно группу:

//    private GroupData findMeNewGroup ( Groups group) {
//        Groups groups = app.db().groups();
//
//        for (GroupData g : groups) {
//            if (!before.contains( g )) {
//                return g;
//            }
//            System.out.println("Что выдаст переменная g? " + g );
//        }
//
//        throw new RuntimeException("No new group found");
//    }

    // Выбираем рандомно группу:

    public void  findMeOneGroup (GroupData oneAddGroup) {
        Groups groups = app.db().groups();

        for (GroupData g : groups) {
            if (g != null) {
                oneAddGroup = g;
//                return g;
            }
            System.out.println("Что выдаст переменная g? " + g );
        }

        throw new RuntimeException("No new group found");
    }

    @BeforeTest
    public void ensurePreconditions() { //предусловия

        oneContact = null;
        oneAddGroup = null;

        if (app.db().groups().size() == 0)
        {   app.goTo().groupPage1();
            app.group().create(new GroupData().withName("gr3"));
        }
        // Группы вообще есть? Если нет, то создаем 1 группу.

        if (app.db().contacts().size() == 0){
            app.goTo().homePage();
            ContactData contact = new ContactData().withName("Alex").withSurname("Smotrov");
            app.сontact().create(contact, true);
            // Если true, то создается контакт с уже добавленной группой.
            // Если false, то создается контакт, не привязанный к группе.
        }
        // Контакты вообще есть? Если нет, то создаем 1 контакт.

    }

    @Test
    public void testContactAddToGroup() {

        //берем множество групп из БД:
        Groups groups = app.db().groups();
        System.out.println("В таблице групп столько таких групп: " + groups);

        // Выбираем рандомно группу:
//        findMeOneGroup(oneAddGroup);

        app.сontact().goToGroup(oneAddGroup);

    }



}
