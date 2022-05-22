package com.example.pasapas.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Users implements Serializable {
    String email;
    String motdepasse;
    String token;
    ArrayList<Enfants> enfants;

    public Users() {
    }

    public Users(String email, String motdepasse, String token, ArrayList<Enfants> enfants) {
        this.email = email;
        this.motdepasse = motdepasse;
        this.token = token;
        this.enfants = enfants;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public ArrayList<Enfants> getEnfants() {
        return enfants;
    }

    public void setEnfants(ArrayList<Enfants> enfants) {
        this.enfants = enfants;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void  addEnfant(Enfants enfants) {
        if(this.getEnfants() == null) this.setEnfants(new ArrayList<Enfants>());
        this.enfants.add(enfants);
    }


    @Override
    public String toString() {
        return "Users{" +
                "email='" + email + '\'' +
                ", motdepasse='" + motdepasse + '\'' +
                ", token='" + token + '\'' +
                ", enfants=" + enfants +
                '}';
    }
}

