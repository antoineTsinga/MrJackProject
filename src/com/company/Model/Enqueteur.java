package com.company.Model;

import java.util.Stack;

public class Enqueteur extends Joueur {
    private String nom;
    private int nbSuspectRestant;
    private Stack<District> AlibiEnVue;

    @Override
    public boolean partiGagnee(){
        return nbSuspectRestant <= 1;
    }

    public Enqueteur(){}

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbSuspectRestant() {
        return nbSuspectRestant;
    }

    public void setNbSuspectRestant(int nbSuspectRestant) {
        this.nbSuspectRestant = nbSuspectRestant;
    }

    public void setAlibiEnVue(Stack<District> alibiEnVue) {
        AlibiEnVue = alibiEnVue;
    }

    public Stack<District> getAlibiEnVue() {
        return AlibiEnVue;
    }

    public boolean mrJackVue(Alibi identiteMrJack) {
        return AlibiEnVue.contains(identiteMrJack);
    }
}




