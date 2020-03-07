package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreationTests extends TestBase {

  @Test (enabled = true)
  public void testGroupCreation() {

    app.goTo().GroupPage();
    Set<GroupData> before = app.group().all();
    GroupData group = new GroupData().withName("test2");
    app.group().create(group);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(),before.size() + 1);
//    app.getSessionHelper().logout(); //DON'T TOUCH!!!

    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()); //из потока объектов типа GroupData получается поток целых чисел.
//Java сама определяет из списка числе самое большое и преобразует результаты в обычное целое число.
    before.add(group);

//    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//    before.sort(byId);
//    after.sort(byId);

    Assert.assertEquals(before, after);

  }

}
