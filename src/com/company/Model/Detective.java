package com.company.Model;

public class Detective extends Tile {
    private String nom;
    private int ligne;
    private int colonne;

    public Detective(String nom, int ligne, int colonne) {
        this.nom = nom;
        this.ligne = ligne;
        this.colonne = colonne;
    }

    public Detective(){}

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getLigne() {
        return ligne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }
}
