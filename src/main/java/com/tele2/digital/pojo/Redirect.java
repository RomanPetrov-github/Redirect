package com.tele2.digital.pojo;

public class Redirect {

    private int id;
    private String urlSource;
    private String urlDestination;
    private int code;
    private String userAgent;
    private Integer validateStatusCode ;

    public Redirect() {
    }
    public Redirect(String urlSource, String urlDestination, int code, String userAgent) {
        this.urlSource = urlSource;
        this.urlDestination = urlDestination;
        this.code = code;
        this.userAgent = userAgent;
    }

    public int getId() {
        return id;
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
    public Integer getValidateStatusCode() {
        return validateStatusCode;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setUrlSource(String urlSource) {
        this.urlSource = urlSource;
    }
    public void setUrlDestination(String urlDestination) {
        this.urlDestination = urlDestination;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
    public void setValidateStatusCode(Integer validateStatusCode) {
        this.validateStatusCode = validateStatusCode;
    }

    @Override
    public String toString() {
        return urlSource + urlDestination + code + userAgent;
    }
}
