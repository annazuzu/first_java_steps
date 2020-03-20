package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().GroupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test (enabled = true)
    public void testGroupModification() {

        Groups/*Set<GroupData>*/ before = app.group().all();
        GroupData modifedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifedGroup.getId()).withName("test4").withHeader("test5").withFooter("test6");
        app.group().modify(group);
        assertThat(app.group().getGroupCount(),equalTo(before.size()));
        Groups/*Set<GroupData>*/ after = app.group().all();
        assertThat(after, equalTo(before.without(modifedGroup).withAdded(group)));
    }

}
