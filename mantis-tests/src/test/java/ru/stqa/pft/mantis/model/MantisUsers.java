package ru.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MantisUsers extends ForwardingSet<MantisUserData> {

    private Set<MantisUserData> delegate;

    public MantisUsers(MantisUsers users) {
        this.delegate = new HashSet<MantisUserData>(users.delegate);
    }

    public MantisUsers() {
        this.delegate = new HashSet<MantisUserData>();
    }

    public MantisUsers(Collection<MantisUserData> users) {
        this.delegate = new HashSet<MantisUserData>(users);
    }

    @Override
    protected Set<MantisUserData> delegate() {
        return delegate;
    }

    public Set<MantisUserData> getDelegate() {
        return delegate;
    }

    public MantisUsers user (MantisUserData user) {
        MantisUsers users = new MantisUsers(this);
        return users;
    }

    public MantisUsers withAdded (MantisUserData user) {
        MantisUsers users = new MantisUsers(this);
        users.add(user);
        return users;
    }

    public MantisUsers without (MantisUserData user) {
        MantisUsers users = new MantisUsers(this);
        users.remove(user);
        return users;
    }

}
