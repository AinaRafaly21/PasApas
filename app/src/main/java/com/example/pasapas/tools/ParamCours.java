package com.example.pasapas.tools;

public class ParamCours {
    String nom;
    String categorie;

    public ParamCours() {
    }

    public ParamCours(String nom, String categorie) {
        this.nom = nom;
        this.categorie = categorie;
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

    @Override
    public String toString() {
        return "ParamCours{" +
                "nom='" + nom + '\'' +
                ", categorie='" + categorie + '\'' +
                '}';
    }
}
