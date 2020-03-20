package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {
    @Test
    public void testContactPhones () {

        app.goTo().homePage(); //переходим на главную страницу приложения
        ContactData contact = app.сontact().allset().iterator().next(); //загружаем список контактов, выбираем контакт случайным образом
        ContactData contactInfoFromEditForm = app.сontact().infoFromEditForm(contact); //вспомогательный метод, загружающий
        // информацию о контакте из формы на редактирование
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));

    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone()).stream().
                filter((s) -> ! s.equals("")).
                map(ContactPhoneTests::cleanned).
                collect(Collectors.joining("\n"));
    }

    public static String cleanned (String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]","");
        //replaceAll() - заменить все вхождения чего-то на что-то
    }

}
