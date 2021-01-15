package com.company.Model;

public class Alibi {
    private String personnage;
    private int nbSablier;

    public String getPersonnage() {
        return personnage;
    }
    public int getNbSablier() {
        return nbSablier;
    }
    public Alibi(String personnage, int nbSablier) {
        this.personnage = personnage;
        this.nbSablier = nbSablier;
    }
    public Alibi(){}
}
