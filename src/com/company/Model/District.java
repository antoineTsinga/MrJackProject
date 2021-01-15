package com.company.Model;

import java.util.List;

public class District extends Tile {
    private Alibi alibi;
    private boolean nord, est, sud, ouest;
    private int ligne;
    private int colonne;

    public District(Alibi alibi, List<Boolean> orientation) {
        this.alibi = alibi;
        this.nord = orientation.get(0);
        this.est = orientation.get(1);
        this.sud = orientation.get(2);
        this.ouest = orientation.get(3);
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

    public District(){}

    public Alibi getAlibi() {
        return alibi;
    }

    public void setAlibi(Alibi alibi) {
        this.alibi = alibi;
    }

    public boolean isNord() {
        return nord;
    }

    public void setNord(boolean nord) {
        this.nord = nord;
    }

    public boolean isEst() {
        return est;
    }

    public void setEst(boolean est) {
        this.est = est;
    }

    public boolean isSud() {
        return sud;
    }

    public void setSud(boolean sud) {
        this.sud = sud;
    }

    public boolean isOuest() {
        return ouest;
    }

    public void setOuest(boolean ouest) {
        this.ouest = ouest;
    }
}
