package com.example.projetlibre.Model;

public class Message {
    private String nom;
    private String prenom;
    private String email;
    private String key;
    private String date_envoi;
    private String description;

    public Message(String nom, String prenom, String email, String key, String envoi) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.key = key;
        date_envoi = envoi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEnvoi() {
        return date_envoi;
    }

    public void setEnvoi(String date_envoi) {
        this.date_envoi = date_envoi;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
