package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
      BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
      String line = reader.readLine();
      while (line != null) {
          String[] split = line.split(",");
          list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
          line = reader.readLine();
      }

    return list.iterator();
  }

  @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) {

    //    String[] names = new String[] {"test1", "test2", "test3"}; //"параметрицация" тестов циклом
//    for (String name : names) {
      app.goTo().GroupPage();
      Groups before = app.group().all();
//    Set<GroupData> before = app.group().all();
//      GroupData group = new GroupData().withName(/*"test2"*/ name);
      app.group().create(group);
      assertThat(app.group().getGroupCount(),equalTo(before.size() + 1));
      Groups after = app.group().all();
//    Set<GroupData> after = app.group().all();
//    assertThat(after.size(),equalTo(before.size() + 1));
//    app.getSessionHelper().logout(); //DON'T TOUCH!!!

//    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()); //из потока объектов типа GroupData получается поток целых чисел.
//Java сама определяет из списка числе самое большое и преобразует результаты в обычное целое число.
//    before.add(group);

//    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//    before.sort(byId);
//    after.sort(byId);

//    Assert.assertEquals(before, after);
      assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
      //из потока объектов типа GroupData получается поток целых чисел.
//Java сама определяет из списка числе самое большое и преобразует результаты в обычное целое число.
//    }


  }

  @Test (enabled = false)
  public void testBadGroupCreation() {

    app.goTo().GroupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test2'");
    app.group().create(group);
    assertThat(app.group().getGroupCount(),equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before));
  }

}
