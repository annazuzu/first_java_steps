package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {

    private Set<ContactData> delegatе;

    public Contacts(Contacts contacts) {
        this.delegatе = new HashSet<ContactData>(contacts.delegatе);
    }

    public Contacts() {
        this.delegatе = new HashSet<ContactData>();
    }

    public Contacts(Collection<ContactData> contacts) {
        this.delegatе = new HashSet<ContactData>(contacts);
    }


    @Override
    protected Set<ContactData> delegate() {
        return  delegatе;
    }

    public Set<ContactData> getDelegatе() {
        return delegatе;
    }

    public Contacts withAdded (ContactData contact) {
        Contacts contacts = new Contacts(this);
        contacts.add(contact);
        return contacts;
    }

    public Contacts without (ContactData contact) {
        Contacts contacts = new Contacts(this);
        contacts.remove(contact);
        return contacts;
    }

}
