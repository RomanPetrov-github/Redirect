package com.tele2.digital;

import com.tele2.digital.exception.ValidateUrlException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.concurrent.Callable;

public class TaskExecutor implements Callable<Integer>{

    private String url;

    public TaskExecutor(String url) {
        if(url.startsWith("http://") || url.startsWith("https://")) {
            this.url = url;
        }
        else{
            this.url = "http://" + url;
        }
    }

    public Integer call() throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();

        HttpResponse response = client.execute(new HttpGet(url));
        int statusCode = response.getStatusLine().getStatusCode();

        if (statusCode == 404 || statusCode == 500 || statusCode == 503) {
            throw new ValidateUrlException("Кривой URL");
        }
        else{
            System.out.println("Валидный URL");
        }
        return statusCode;
    }
}
