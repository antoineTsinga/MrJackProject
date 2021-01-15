package com.company.Model;

public class MrJack extends Joueur {


    private String nom;
    private Alibi identite;
    private int nbSablier;

    @Override
    public boolean partiGagnee(){
        return nbSablier >= 6;
    }

    public MrJack(String nom, Alibi identite, int nbSablier) {
        this.nom = nom;
        this.identite = identite;
        this.nbSablier = nbSablier;
    }

    public MrJack(){}

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Alibi getIdentite() {
        return identite;
    }

    public void setIdentite(Alibi identite) {
        this.identite = identite;
    }

    public int getNbSablier() {
        return nbSablier;
    }

    public void setNbSablier(int nbSablier) {
        this.nbSablier = nbSablier;
    }
}
