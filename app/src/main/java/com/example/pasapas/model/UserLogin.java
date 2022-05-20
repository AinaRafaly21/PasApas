package com.example.pasapas.model;

public class UserLogin {
    String email;
    String motdepasse;

    public UserLogin(String email, String motdepasse) {
        this.email = email;
        this.motdepasse = motdepasse;
    }

    public UserLogin() {
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

    @Override
    public String toString() {
        return "Login{" +
                "email='" + email + '\'' +
                ", motdepasse='" + motdepasse + '\'' +
                '}';
    }
}
