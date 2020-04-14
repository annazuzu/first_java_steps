package ru.stqa.pft.rest;

import java.util.Objects;

public class Issue {
    //делаем набор атрибутов:
    private int id;
    private String subject;
    private String description;
    private String state_name;

    // Геттеры:

    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return state_name;
    }

    // Сеттеры:


    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public Issue withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    public Issue withStatus(String status) {
        this.state_name = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return getId() == issue.getId() &&
                Objects.equals(getSubject(), issue.getSubject()) &&
                Objects.equals(getDescription(), issue.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSubject(), getDescription());
    }

}
