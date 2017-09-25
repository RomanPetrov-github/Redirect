package com.roman.petrov;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private static String AUTHOR = "Vasya";
    private static String NUMBER = "28f";
    private static String DESCRIPTION = "descriptionsdalskja";
    private static String DATA = "https://www.yandex.ru https://www.google.ru 301 user_agent /n";
    private static String LINK = "linkdasdasdadsadasda";

    public static void main(String[] args) {

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/");

        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("author", AUTHOR));
        list.add(new BasicNameValuePair("number", NUMBER));
        list.add(new BasicNameValuePair("description", DESCRIPTION));
        list.add(new BasicNameValuePair("data", DATA));
        list.add(new BasicNameValuePair("link", LINK));

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(list));
            client.execute(httpPost);
            System.out.println("doPost Отправлен");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
