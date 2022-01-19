package com.example.projetlibre.Model;

public class PutPDF {

    public String name ;
    public String url;
    public String key;
    public String user;

    public PutPDF() {
    }

    public PutPDF(String name, String url, String key, String user) {
        this.name = name;
        this.url = url;
        this.key = key;
        this.user = user;
    }

    public String getName() {
        return name;
    }




    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
