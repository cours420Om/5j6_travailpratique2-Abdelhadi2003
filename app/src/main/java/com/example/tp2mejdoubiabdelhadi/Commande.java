package com.example.tp2mejdoubiabdelhadi;

public class Commande {

    private String nomPlat;
    private String descriptionIngredient;
    private String boisson;
    private String sauce;

    public Commande() {

    }

    public Commande(String nomPlat, String descriptionIngredient, String boisson, String sauce) {
        this.nomPlat = nomPlat;
        this.descriptionIngredient = descriptionIngredient;
        this.boisson = boisson;
        this.sauce = sauce;
    }

    public String getNomPlat() {
        return nomPlat;
    }

    public void setNomPlat(String nomPlat) {
        this.nomPlat = nomPlat;
    }

    public String getDescriptionIngredient() {
        return descriptionIngredient;
    }

    public void setDescriptionIngredient(String descriptionIngredient) {
        this.descriptionIngredient = descriptionIngredient;
    }

    public String getBoisson() {
        return boisson;
    }

    public void setBoisson(String boisson) {
        this.boisson = boisson;
    }

    public String getSauce() {
        return sauce;
    }

    public void setPrix(String sauce) {
        this.sauce = sauce;
    }

    @Override
    public String toString() {
        return nomPlat;
    }
}
