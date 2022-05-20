package com.example.pasapas.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Enfants implements Serializable {
    String nom;
    Integer age;
    String categorie;
    ArrayList<Niveau> niveau;

    public Enfants() {
    }

    public Enfants(String nom, Integer age, String categorie, ArrayList<Niveau> niveau) {
        this.nom = nom;
        this.age = age;
        this.categorie = categorie;
        this.niveau = niveau;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public ArrayList<Niveau> getNiveau() {
        return niveau;
    }

    public void setNiveau(ArrayList<Niveau> niveau) {
        this.niveau = niveau;
    }

    public void addNiveau(Niveau niveau) {
        this.niveau.add(niveau);
    }

    @Override
    public String toString() {
        return "Enfants{" +
                "nom='" + nom + '\'' +
                ", age=" + age +
                ", categorie='" + categorie + '\'' +
                ", niveau=" + niveau +
                '}';
    }
}
