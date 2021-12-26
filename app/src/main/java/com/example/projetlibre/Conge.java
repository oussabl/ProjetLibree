package com.example.projetlibre;

public class Conge {
    private String KEY;
    private String firstname;
    private String lastname;
    private String date_depart_conge;
    private String date_fin_conge;
    private final int TOTALCONGE=18;

    public String getFirstname() {
        return firstname;
    }

    public String getKEY() {
        return KEY;
    }

    public void setKEY(String KEY) {
        this.KEY = KEY;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDate_depart_conge() {
        return date_depart_conge;
    }

    public void setDate_depart_conge(String date_depart_conge) {
        this.date_depart_conge = date_depart_conge;
    }

    public String getDate_fin_conge() {
        return date_fin_conge;
    }

    public void setDate_fin_conge(String date_fin_conge) {
        this.date_fin_conge = date_fin_conge;
    }

    public int getTOTALCONGE() {
        return TOTALCONGE;
    }
}
