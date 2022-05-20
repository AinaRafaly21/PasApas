package com.example.pasapas.model;

import java.io.Serializable;

public class Niveau implements Serializable {
    String cours;
    Integer score;

    public Niveau() {
    }

    public Niveau(String cours, Integer score) {
        this.cours = cours;
        this.score = score;
    }

    public String getCours() {
        return cours;
    }

    public void setCours(String cours) {
        this.cours = cours;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Niveau{" +
                "cours='" + cours + '\'' +
                ", score=" + score +
                '}';
    }
}
