package com.tele2.digital;

public class Redirect {
    private String urlSource;
    private String urlDestination;
    private int code;
    private String userAgent;

    private Integer validateStatusCode ;

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

    public void setValidateStatusCode(Integer validateStatusCode) {
        this.validateStatusCode = validateStatusCode;
    }

    @Override
    public String toString() {
        return urlSource + urlDestination + code + userAgent;
    }
}
