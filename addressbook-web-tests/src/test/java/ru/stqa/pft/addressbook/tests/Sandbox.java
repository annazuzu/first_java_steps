package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class Sandbox extends TestBase{

    private ContactData oneContact;
    private GroupData oneAddGroup;
    private Contacts before;
    private Contacts after;

    @Test

    // Найдем группу, у которой список контактов позволяет добавить ещё один:

    public void getIssueNotOpen() throws IOException {
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();

        System.out.println( groups.size());

        for (GroupData group : groups) {

            if (isIssueOpen(issue.getId()) == true) {
                System.out.println(issue.getSubject());
                System.out.println(issue.getDescription());
                System.out.println(issue.getStatus());
                break;
            } else {
                skipIfNotFixed(issue.getId());
            }
        }
    }
}
