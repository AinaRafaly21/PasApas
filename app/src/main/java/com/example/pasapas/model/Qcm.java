package com.example.pasapas.model;

import java.util.ArrayList;

public class Qcm {
    String question;
    String reponse;
    ArrayList<String> choix;

    public Qcm() {
    }

    public Qcm(String question, String reponse, ArrayList<String> choix) {
        this.question = question;
        this.reponse = reponse;
        this.choix = choix;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public ArrayList<String> getChoix() {
        return choix;
    }

    public void setChoix(ArrayList<String> choix) {
        this.choix = choix;
    }

    @Override
    public String toString() {
        return "Qcm{" +
                "question='" + question + '\'' +
                ", reponse='" + reponse + '\'' +
                ", choix=" + choix +
                '}';
    }
}