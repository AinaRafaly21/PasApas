package com.example.pasapas.tools;

import java.io.Serializable;

public class ParamQuiz implements Serializable {
    String nom;
    String categorie;
    Integer niveau;

    public ParamQuiz() {
    }

    public ParamQuiz(String nom, String categorie, Integer niveau) {
        this.nom = nom;
        this.categorie = categorie;
        this.niveau = niveau;
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

    @Override
    public String toString() {
        return "Param{" +
                "nom='" + nom + '\'' +
                ", categorie='" + categorie + '\'' +
                ", niveau=" + niveau +
                '}';
    }
}
