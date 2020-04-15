package ru.stqa.pft.rest.tests;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class RestTests extends TestBase{

    @Test (enabled = false)
    public void testCreateIssue() throws IOException {

        Set<Issue> oldIssues = getIssues(); // создаем "старый" набор до изменения + создаем новый класс
        Issue newIssue = new Issue().withSubject("Anna test issue").withDescription("New Anna test issue").withStatus("Open");
        // создаем новый объект, вызываем конструктор
        int issueID = createIssue(newIssue); //создаем проблему - новый метод
        Set<Issue> newIssues = getIssues(); // создаем новый набор
        Assert.assertEquals(newIssues.size(), oldIssues.size() + 1);// сравниваем
        oldIssues.add(newIssue.withId(issueID).withStatus("Open")); //только в старое множество нужно добавить объект
        Assert.assertEquals(newIssues, oldIssues);// сравниваем

    }

    // Сформируем список багов не в статусе "Open":

    @Test
    public void getIssueNotOpen() throws IOException {
        Set<Issue> issues = getIssues();
        System.out.println(issues.size());

        for (Issue issue : issues) {

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

    // Метод создания:
    private int createIssue(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Request.Post("https://bugify.stqa.ru/api/issues.json?limit=500").
                bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                         new BasicNameValuePair("description", newIssue.getDescription()))).
                returnContent().asString();
        JsonElement parsed = JsonParser.parseString(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

}
