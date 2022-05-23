package com.example.pasapas.model;

import java.io.Serializable;

public class Cours implements Serializable {
    String nom;
    String titre;
    String categorie;
    Integer niveau;
    String url_video;

    public Cours() {
    }

    public Cours(String nom, String titre, String categorie, Integer niveau, String url_video) {
        this.nom = nom;
        this.titre = titre;
        this.categorie = categorie;
        this.niveau = niveau;
        this.url_video = url_video;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

    public String getUrl_video() {
        return url_video;
    }

    public void setUrl_video(String url_video) {
        this.url_video = url_video;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

}
