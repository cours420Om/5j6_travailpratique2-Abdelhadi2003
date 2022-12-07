package com.example.tp2mejdoubiabdelhadi;

public class Utilisateur {

    private String nomComplet;

    public Utilisateur(){

    }

    public Utilisateur(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }
}
