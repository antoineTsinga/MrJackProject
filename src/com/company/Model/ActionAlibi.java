package com.company.Model;

import java.util.ArrayList;
import java.util.Stack;


public class ActionAlibi extends Action{
    private String nomFace;

    public ActionAlibi(String nomFace) {
        this.nomFace = nomFace;
    }

    @Override
    public String getNom() {
        return getNomFace();
    }

    @Override
    public void setNom(String nomFace) {
        setNomFace(nomFace);
    }

    @Override
    public void makeAction(Joueur joueur, Detective sherlock, Detective watson, Detective toby, Stack<Alibi> alibiRestant, Tile[][] plateau, ArrayList<District> districtsPlateau){
        makeActionAlibi(joueur, alibiRestant, plateau);
    }

    public void makeActionAlibi(Joueur joueur, Stack<Alibi> alibiRestant, Tile[][] plateau ){
        System.out.println("faire ActionAlibi");
        Alibi alibi = alibiRestant.pop(); //on retire une carte Alibi dans tous les cas
        if (joueur.getType().equals("mrJack")){//MrJack
            int nbSablier = ((MrJack) joueur).getNbSablier();
            nbSablier = nbSablier + alibi.getNbSablier(); //mettre à jour la valeur du nombre de sablier
            ((MrJack) joueur).setNbSablier(nbSablier);
        }
        else {
            int nbSuspectRestant = ((Enqueteur) joueur).getNbSuspectRestant();
            ((Enqueteur) joueur).setNbSuspectRestant(nbSuspectRestant);
            for (int i = 1; i <= 3; i++) {//on parcourt le tableau pour trouver le districte correspondant à l'alibi
                for (int j = 1; j <= 3; j++) {
                    if (((District) plateau[i][j]).getAlibi() != null) {
                        if (alibi.getPersonnage().equals((((District) plateau[i][j]).getAlibi()).getPersonnage())) {
                            System.out.println(((District) plateau[i][j]).getAlibi().getPersonnage());
                            ((District) plateau[i][j]).setAlibi(null);
                            nbSuspectRestant--;

                        }
                    }
                }
            }
        }
        System.out.println("fait");
    }

    public String getNomFace() {
        return nomFace;
    }

    public void setNomFace(String nomFace) {
        this.nomFace = nomFace;
    }

}
