package com.example.projetlibre;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Employer  implements Serializable {

    @Exclude
    private String key ;
    private String  firstname ;
    private String  lastname ;
    private String  telephone ;
    private String  mission ;
    private String  email ;
    private String  password ;
    private String  date_depart ;
    private String  date_fin ;

    public Employer() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Employer(String firstname, String lastname, String telephone, String mission, String email, String password, String date_depart, String date_fin) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.telephone = telephone;
        this.mission = mission;
        this.email = email;
        this.password = password;
        this.date_depart = date_depart;
        this.date_fin = date_fin;
    }

    public String getFirstname() {
        return firstname;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate_depart() {
        return date_depart;
    }

    public void setDate_depart(String date_depart) {
        this.date_depart = date_depart;
    }

    public String getDate_fin() {
        return date_fin;
    }

    @Override
    public String toString() {
        return "Employe{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", telephone=" + telephone +
                ", mission='" + mission + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", date_depart='" + date_depart + '\'' +
                ", date_fin='" + date_fin + '\'' +
                '}';
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }
}


