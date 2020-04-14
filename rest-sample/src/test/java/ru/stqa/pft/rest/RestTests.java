package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

public class RestTests extends TestBase{

    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> oldIssues = getIssues(); // создаем "старый" набор до изменения + создаем новый класс
        Issue newIssue = new Issue().withSubject("Anna test issue").withDescription("New Anna test issue").withStatus("Open");// создаем новый объект, вызываем конструктор
        int issueID = createIssue(newIssue); //создаем проблему - новый метод
        Set<Issue> newIssues = getIssues(); // создаем новый набор
        Assert.assertEquals(newIssues.size(), oldIssues.size() + 1);// сравниваем
        oldIssues.add(newIssue.withId(issueID).withStatus("Open")); //только в старое множество нужно добавить объект
        Assert.assertEquals(newIssues, oldIssues);// сравниваем

    }


    // Геттеры:
    private Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json?limit=500").
                connectTimeout(6000).
                socketTimeout(6000)).
                returnContent().asString();
        JsonElement parsed = JsonParser.parseString(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());

    }

    private Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
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
