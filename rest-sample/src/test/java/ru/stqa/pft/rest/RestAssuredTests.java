package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

public class RestAssuredTests extends TestBase{

    @BeforeClass
    public void init() {
        RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
    }

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

        String json = RestAssured.get("https://bugify.stqa.ru/api/issues.json?limit=500").asString();

        JsonElement parsed = JsonParser.parseString(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());

    }


    // Метод создания:

    private int createIssue(Issue newIssue) throws IOException {

        String json = RestAssured.given()
                .param("subject", newIssue.getSubject())
                .param("description", newIssue.getDescription())
                .post("https://bugify.stqa.ru/api/issues.json?limit=500").asString();

        JsonElement parsed = JsonParser.parseString(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

}
