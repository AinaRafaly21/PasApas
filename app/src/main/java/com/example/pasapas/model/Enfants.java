package com.example.pasapas.model;

import com.example.pasapas.tools.Categories;
import com.example.pasapas.tools.Tools;

import java.io.Serializable;
import java.util.ArrayList;

public class Enfants implements Serializable {
    String nom;
    Integer age;
    String categorie;

    public Enfants() {
    }

    public Enfants(String nom, Integer age, String categorie) {
        this.nom = nom;
        this.age = age;
        this.categorie = categorie;
    }

    public Enfants(String nom, Integer age) {
        this.nom = nom;
        this.age = age;
        this.setCategorie(age);
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

    public void setCategorie(Integer age) {
        ArrayList<Categories> array = Tools.categories();
        for (Categories categorie: array) {
            System.out.println("Categorie = " + categorie.getCategorie() + " / " + age);
            if(categorie.getMin() < age && categorie.getMax() >= age) {
                this.setCategorie(categorie.getCategorie());
                break;
            }
        }
    }
}
