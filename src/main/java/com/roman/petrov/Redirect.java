package com.roman.petrov;

public class Redirect {
    private String urlFrom;
    private String urlTo;
    private int code;
    private String userAgent;

    public Redirect(String urlFrom, String urlTo, int code, String userAgent) {
        this.urlFrom = urlFrom;
        this.urlTo = urlTo;
        this.code = code;
        this.userAgent = userAgent;
    }

    public String getSqlInsert(){
        return "INSERT INTO redirects (request_id, url_from, url_to, code, user_agent) VALUES (?, ?, ?, ?, ?);";
    }

    public String getUrlFrom() {
        return urlFrom;
    }

    public String getUrlTo() {
        return urlTo;
    }

    public int getCode() {
        return code;
    }

    public String getUserAgent() {
        return userAgent;
    }

    @Override
    public String toString() {
        return urlFrom + urlTo + code + userAgent;
    }
}
