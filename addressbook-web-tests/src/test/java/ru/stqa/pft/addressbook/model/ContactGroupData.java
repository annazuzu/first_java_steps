package ru.stqa.pft.addressbook.model;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javax.persistence.*;

@XStreamAlias("contactGroup")
@Entity
@Table(name = "address_in_groups")
public class ContactGroupData {

    @XStreamOmitField()
    @Column(name = "id")
    @Id
    private int contactId;

    @Column(name = "group_id")
    private int groupId;

    //Геттеры:

    public int getContactId() {
        return contactId;
    }

    public int getGroupId() {
        return groupId;
    }

    //Сеттеры:

    public ContactGroupData withContactId(int contactId) {
        this.contactId = contactId;
        return this;
    }

    public ContactGroupData withGroupId(int groupId) {
        this.groupId = groupId;
        return this;
    }

    @Override
    public String toString() {
        return "ContactGroupData{" +
                "contactId=" + contactId +
                ", groupId=" + groupId +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactGroupData that = (ContactGroupData) o;

        if (contactId != that.contactId) return false;
        return groupId == that.groupId;

    }

    @Override
    public int hashCode() {
        int result = contactId;
        result = 31 * result + groupId;
        return result;
    }
}
