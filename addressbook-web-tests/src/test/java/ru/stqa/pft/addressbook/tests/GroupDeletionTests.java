package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().GroupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData("test1", null, null));
    }
  }

  @Test (enabled = true)
  public void testGroupDeletion() throws Exception {
    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
//    int before = app.getGroupHelper().getGroupCount();
    app.group().delete(index);
    List<GroupData> after = app.group().list();
//    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after.size(),index);

    before.remove(index);
//    for (int i = 0; i < after.size(); i++) {
//      Assert.assertEquals(before.get(i), after.get(i));
//    } // метод Assert.assertEquals() сам умеет сравнивать 2 списка. Главное передать их в качестве параметров.
    Assert.assertEquals(before, after);
  }

}
