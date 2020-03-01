package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private final String id;
    private final String name;
    private final String surname;
    private final String titleContact;
    private final String telMobile;
    private final String email;
    private String group;

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
    }

    public ContactData(String name, String surname, String titleContact, String telMobile, String email, String group) {
        this.id = null;
        this.name = name;
        this.surname = surname;
        this.titleContact = titleContact;
        this.telMobile = telMobile;
        this.email = email;
        this.group = group;
    }

    public ContactData(String id, String name, String surname, String titleContact, String telMobile, String email, String group) {
        this.id = id;
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
