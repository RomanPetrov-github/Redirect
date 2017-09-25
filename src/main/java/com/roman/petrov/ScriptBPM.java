package com.roman.petrov;

import java.util.ArrayList;
import java.util.List;

public class ScriptBPM {
    private String author;
    private String number;
    private String description;
    private List<Redirect> redirects = null;
    private String link;

    public ScriptBPM(String author, String number, String description, String link) {
        this.author = author;
        this.number = number;
        this.description = description;
        this.link = link;
        this.redirects = new ArrayList<Redirect>();
    }

    public void addRedirect(String[] line) throws NumberFormatException{
        Redirect redirect = null;
        if(line.length == 3){
            redirect = new Redirect(line[0], line[1], Integer.parseInt(line[2]), "");
        }
        else if (line.length == 4){
            redirect = new Redirect(line[0], line[1], Integer.parseInt(line[2]), line[3]);
        }
        System.out.println(redirect.toString());
        redirects.add(redirect);
    }

    public String getSqlInsert(){
        return  "INSERT INTO requests (author, number, description, link) VALUES (?, ?, ?, ?);";
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String getAuthor() {
        return author;
    }

    public String getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public List<Redirect> getRedirects() {
        return redirects;
    }

    public String getLink() {
        return link;
    }
}
