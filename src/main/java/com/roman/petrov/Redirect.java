package com.roman.petrov;

public class Redirect {
    private String urlSource;
    private String urlDestination;
    private int code;
    private String userAgent;

    public Redirect(String urlSource, String urlDestination, int code, String userAgent) {
        this.urlSource = urlSource;
        this.urlDestination = urlDestination;
        this.code = code;
        this.userAgent = userAgent;
    }

    public String getUrlSource() {
        return urlSource;
    }
    public String getUrlDestination() {
        return urlDestination;
    }
    public int getCode() {
        return code;
    }
    public String getUserAgent() {
        return userAgent;
    }

    public String getSqlInsert(){
        return "INSERT INTO redirects (request_id, url_from, url_to, code, user_agent) VALUES (?, ?, ?, ?, ?);";
    }
    @Override
    public String toString() {
        return urlSource + urlDestination + code + userAgent;
    }
}
