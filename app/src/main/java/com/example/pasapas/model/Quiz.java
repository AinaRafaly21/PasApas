package com.example.pasapas.model;

import java.util.ArrayList;

public class Quiz {
    String nom;
    String categorie;
    Integer niveau;
    Integer score_min;
    ArrayList<Qcm> qcm;

    public Quiz() {
    }

    public Quiz(String cours, String categorie, Integer niveau, Integer score_min, ArrayList<Qcm> qcm) {
        this.nom = cours;
        this.categorie = categorie;
        this.niveau = niveau;
        this.score_min = score_min;
        this.qcm = qcm;
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

    public Integer getScore_min() {
        return score_min;
    }

    public void setScore_min(Integer score_min) {
        this.score_min = score_min;
    }

    public ArrayList<Qcm> getQcm() {
        return qcm;
    }

    public void setQcm(ArrayList<Qcm> qcm) {
        this.qcm = qcm;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "cours='" + nom + '\'' +
                ", categorie='" + categorie + '\'' +
                ", niveau=" + niveau +
                ", score_min=" + score_min +
                ", qcm=" + qcm +
                '}';
    }
}
