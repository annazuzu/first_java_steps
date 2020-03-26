package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ContactsAndGroups extends ForwardingSet<ContactGroupData> {

    private Set<ContactGroupData> delegate;

    public ContactsAndGroups(ContactsAndGroups contactsGroups) {
        this.delegate = new HashSet<ContactGroupData>(contactsGroups.delegate());
    }

    public ContactsAndGroups(Collection<ContactGroupData> contactsGroups) {
        this.delegate = new HashSet<ContactGroupData>(contactsGroups);
    }

    public ContactsAndGroups() {
        this.delegate = new HashSet<ContactGroupData>();
    }

    @Override
    protected Set<ContactGroupData> delegate() {
        return delegate;
    }

    public ContactsAndGroups withAdded(ContactGroupData contactsGroup) {
        ContactsAndGroups contactsGroups = new ContactsAndGroups(this);
        contactsGroups.add(contactsGroup);
        return contactsGroups;
    }


    public ContactsAndGroups without(ContactGroupData contactsGroup) {
        ContactsAndGroups contactsGroups = new ContactsAndGroups(this);
        contactsGroups.remove(contactsGroup);
        return contactsGroups;
    }
}
