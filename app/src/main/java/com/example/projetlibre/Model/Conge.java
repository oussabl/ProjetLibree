package com.example.projetlibre.Model;

public class Conge {
    private String KEY;
    private String keyPere;
    private String firstname;
    private String lastname;
    private String date_depart_conge;
    private String date_fin_conge;
    private  int TOTALCONGE;

    public Conge() {
    }

    public Conge(String KEY, String firstname, String lastname, String date_depart_conge, String date_fin_conge) {
        this.KEY = KEY;
        this.firstname = firstname;
        this.lastname = lastname;
        this.date_depart_conge = date_depart_conge;
        this.date_fin_conge = date_fin_conge;
    }

    public String getKeyPere() {
        return keyPere;
    }

    public void setKeyPere(String keyPere) {
        this.keyPere = keyPere;
    }

    public void setTOTALCONGE(int TOTALCONGE) {
        this.TOTALCONGE = TOTALCONGE;
    }

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

    public String getUsers(){
        return  lastname+" "+firstname;
    }

    public String getDate_depart_conge() {
        return date_depart_conge;
    }

    public void setDate_depart_conge(String date_depart_conge) {
        this.date_depart_conge = date_depart_conge;
    }

    @Override
    public String toString() {
        return "Conge{" +
                "KEY='" + KEY + '\'' +
                ", keyPere='" + keyPere + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", date_depart_conge='" + date_depart_conge + '\'' +
                ", date_fin_conge='" + date_fin_conge + '\'' +
                ", TOTALCONGE=" + TOTALCONGE +
                '}';
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
