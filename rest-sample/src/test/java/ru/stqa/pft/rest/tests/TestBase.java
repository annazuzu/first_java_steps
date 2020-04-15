package ru.stqa.pft.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;


public class TestBase {

    // Геттеры:

    // Авторизация:
    protected Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    // 1. Берем список всех багов:
    protected Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json?limit=500").
                connectTimeout(6000).
                socketTimeout(6000)).
                returnContent().asString();
        JsonElement parsed = JsonParser.parseString(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    // curl http://demo.bugify.com/api/issues/1.json

    // 2. Берем один баг по ID:
    public Set<Issue> getOneIssue(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues/" + issueId + ".json?limit=500").
                connectTimeout(6000).
                socketTimeout(6000)).
                returnContent().asString();
        JsonElement parsed = JsonParser.parseString(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
    }

    // 3. Берем один баг и выясняем его статус.
    boolean isIssueOpen(int issueId) throws IOException {
        Issue issue = getOneIssue(issueId).iterator().next();
        if(issue.getStatus().equals("Open")){
            return false;
        }
        else {
            return true;
        }

    }

    // 4. Если он открыт (баг не закрыт), то пропускаем:
    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

}
