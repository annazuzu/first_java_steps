package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String name;
    private final String surname;
    private final String titleContact;
    private final String telMobile;
    private final String email;
    private String group;

    public ContactData(String name, String surname, String titleContact, String telMobile, String email, String group) {
        this.name = name;
        this.surname = surname;
        this.titleContact = titleContact;
        this.telMobile = telMobile;
        this.email = email;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTitleContact() {
        return titleContact;
    }

    public String getTelMobile() {
        return telMobile;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }
}
