package com.roman.petrov;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "incident")
public class Incident {
    private String author;
    private String number;
    private String description;
    private String redirect;
    private String link;
    private List<Redirect> listRedirects = null;

    public Incident() {
    }

    public void createListRedirects(){
        List<Redirect> result = new ArrayList<Redirect>();
        String[] split = redirect.split("\n");
        for(int i = 0; i < split.length; i++){
            String[] redir = split[i].trim().split(" ");
            if(redir.length == 3){
                result.add(new Redirect(redir[0], redir[1], Integer.parseInt(redir[2]), ""));
            }
            if(redir.length == 4){
                result.add(new Redirect(redir[0], redir[1], Integer.parseInt(redir[2]), redir[3]));
            }
        }
        if (result.size() > 0){
            listRedirects = result;
        }
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }
    public void setLink(String link) {
        this.link = link;
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
    public String getRedirect() {
        return redirect;
    }
    public String getLink() {
        return link;
    }
    public List<Redirect> getListRedirects() {
        return listRedirects;
    }

    public String getSqlInsert(){
        return  "INSERT INTO requests (author, number, description, link) VALUES (?, ?, ?, ?);";
    }
    @Override
    public String toString() {
        return String.format("author = %s, number = %s, description = %s, redirect = %s, link = %s",
                getAuthor(),
                getNumber(),
                getDescription(),
                getRedirect(),
                getLink());
    }
}
