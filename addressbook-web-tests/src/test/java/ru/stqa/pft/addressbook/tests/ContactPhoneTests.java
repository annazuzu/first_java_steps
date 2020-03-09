package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {
    @Test
    public void testContactPhones () {

        app.goTo().homePage(); //переходим на главную страницу приложения
        ContactData contact = app.сontact().allset().iterator().next(); //загружаем список контактов, выбираем контакт случайным образом
        ContactData contactInfoFromEditForm = app.сontact().infoFromEditForm(contact); //вспомогательный метод, загружающий
        // информацию о контакте из формы на редактирование

        assertThat(contact.getHomePhone(), equalTo(cleanned(contactInfoFromEditForm.getHomePhone())));
        assertThat(contact.getMobilePhone(), equalTo(cleanned(contactInfoFromEditForm.getMobilePhone())));
        assertThat(contact.getWorkPhone(), equalTo(cleanned(contactInfoFromEditForm.getWorkPhone())));

    }
    public String cleanned (String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]",""); //replaceAll() - заменить все вхождения чего-то на что-то
    }
}
