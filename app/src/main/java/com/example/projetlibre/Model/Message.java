package com.example.projetlibre.Model;

public class Message {
    private String  userSource;
    private String  userDestination;
    private String  description;
    private String  title;
    private String  datEnvoi;

    public Message() {
    }

    public Message(String userSource, String userDestination, String description, String title, String datEnvoi) {
        this.userSource = userSource;
        this.userDestination = userDestination;
        this.description = description;
        this.title = title;
        this.datEnvoi = datEnvoi;
    }

    public String getUserSource() {
        return userSource;
    }

    public void setUserSource(String userSource) {
        this.userSource = userSource;
    }

    public String getUserDestination() {
        return userDestination;
    }

    public void setUserDestination(String userDestination) {
        this.userDestination = userDestination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDatEnvoi() {
        return datEnvoi;
    }

    public void setDatEnvoi(String datEnvoi) {
        this.datEnvoi = datEnvoi;
    }
}
