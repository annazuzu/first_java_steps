package ru.stqa.pft.mantis.appmanager;

import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpSession {
    private CloseableHttpClient httpClient;
    private ApplicationManager app;

    public HttpSession(ApplicationManager app) {
        this.app = app;
        httpClient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
        //Как работает http-session? В момент конструирования (когда вызывается метод newSession),
        // внутри создается новый клиент - объект, который будет отправлять запросы на сервер.
        //В созданном объекте устанавливается так называемая стратегия перенаправления.
    }

    //Далее в этом помощнике реализовано два метода. Первый умеет выполнять логин, а второй умеет определять,
    // какой пользователь сейчас залогинен:
    public boolean login(String username) throws IOException {
        HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/login.php");
        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", "test"));
        params.add(new BasicNameValuePair("secure_session", "on"));
        params.add(new BasicNameValuePair("return", "index.php"));
        post.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse response = httpClient.execute(post);
        String body = geTextFrom(response);
        return body.contains(String.format("<span class=\"user-info\">%s</span>", username));
    }

    public boolean loginCh(String username, String password) throws IOException {
        HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/login.php");
        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("secure_session", "on"));
        params.add(new BasicNameValuePair("return", "index.php"));
        post.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse response = httpClient.execute(post);
        String body = geTextFrom(response);
        return body.contains(String.format("<span class=\"user-info\">%s</span>", username));
    }

    private String geTextFrom(CloseableHttpResponse response) throws IOException {
        try {
            return EntityUtils.toString(response.getEntity());
        } finally {
            response.close();
        }
    }

    public boolean isLoggedInAs(String username) throws IOException {
        HttpGet get = new HttpGet(app.getProperty("web.baseUrl") + "/index.php");
        CloseableHttpResponse response = httpClient.execute(get);
        String body = geTextFrom(response);
        return body.contains(String.format("<span class=\"user-info\">%s</span>", username));
    }

}
