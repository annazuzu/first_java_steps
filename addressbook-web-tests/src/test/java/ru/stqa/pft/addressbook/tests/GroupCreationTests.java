package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {

    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
//    int before = app.getGroupHelper().getGroupCount();
    GroupData group = new GroupData("testAfterComparator", null, null);
    app.getGroupHelper().createGroup(group);
    List<GroupData> after = app.getGroupHelper().getGroupList();
//    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after.size(),before.size() + 1);
//    app.getSessionHelper().logout();

//==============Старый способ сравнения с исп-ем цикла=================================
//    int max = 0; //сначала устанавливаем максимум на к-л значение (любое), н. "0"
//    for (GroupData g : after) {    //сравниваем этот максимум с идентификатором каждого эл-та. если идентификатор окаывается больше, то эту переменную max увеличиваем
//      if(g.getId() > max) {
//        max = g.getId();
//      }
//    }
//   ==================================================================================
//=======Новый способ сравнения при помощи вспомогательного объекта компаратора========
    //компаратор умеет сравнивать объекты типа GroupData; инициализируем переменную, передавая в нее объект типа "Компаратор"
//переделываем анонимный класс в анонимную функцию. автоматически, средствами среды разработки
    //анонимный(одноразовый) класс с единственным методом compare()
//    Comparator<? super GroupData> byId = (Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    //int max1 = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();   //stream() - поток. в него можно превратить список; byId - переменная-компаратор
//    int max1 = after.stream().max(Comparator.comparingInt(GroupData::getId)).get().getId();
    //метод max отработал и с помощью метода get() получаем его идентификатор getId()
    //этот метод передаем в качестве значения переменной int max
    //создать локальную переменную byId средствами среды разработки

//=====================================================================================

//group.setId(max1);    //когда в конце найдется самый большой идентификатор, то присваиваем его значение в качестве идентификатора новой группы; делаем доп. метод сеттер
    before.add(group);

    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);
    //теперь все группы в сравниваемых списках будут иметь идентификаторы и списки будут сравниваться без учета порядка


  }




}
