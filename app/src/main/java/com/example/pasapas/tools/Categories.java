package com.example.pasapas.tools;

public class Categories {
    String categorie;
    Integer min;
    Integer max;

    public Categories() {
    }

    public Categories(String categorie, Integer min, Integer max) {
        this.categorie = categorie;
        this.min = min;
        this.max = max;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }
}
