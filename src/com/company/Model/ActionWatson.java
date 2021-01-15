package com.company.Model;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Scanner;
public class ActionWatson extends Action {
    private String nomFace;

    public ActionWatson(String nomFace) {
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


    public String getNomFace() {
        return nomFace;
    }

    public void setNomFace(String nomFace) {
        this.nomFace = nomFace;
    }

    Scanner scanner = new Scanner(System.in);
    @Override
    public void makeAction(Joueur joueur, Detective sherlock, Detective watson, Detective toby, Stack<Alibi> alibiRestant, Tile[][] plateau, ArrayList<District> DistrictNonRetournes){
        System.out.println("faire ActionWatson");
        System.out.println("De combien d'espace voulez-vous deplacer watson?");
        int nbEspace = scanner.nextInt();
        makeActionWatson(watson, plateau, nbEspace);
    }
    public void makeActionWatson(Detective detective, Tile[][] plateau, int nbEspace){
        int i = detective.getLigne();
        int j = detective.getColonne();
        if (nbEspace == 1){
            ActionJoker l = new ActionJoker();
            l.makeActionJoker(detective, plateau);
        }
        if (nbEspace == 2){
            if (i == 0) {
                if (j == 3){
                    detective.setLigne(2);
                    detective.setColonne(4);
                    plateau[2][4] = detective;
                    plateau[0][3] = new Detective();
                }
                else if(j == 2){
                    detective.setLigne(1);
                    detective.setColonne(4);
                    plateau[1][4] = detective;
                    plateau[0][2] = new Detective();
                }
                else{
                    detective.setColonne(j+2);
                    plateau[i][j+2] = detective;
                    plateau[i][j] = new Detective();
                }
            }
            else if (i == 4){
                if (j ==1){
                    detective.setLigne(2);
                    detective.setColonne(0);
                    plateau[2][0] = detective;
                    plateau[4][1] = new Detective();
                }
                else if(j == 2){
                    detective.setLigne(3);
                    detective.setColonne(0);
                    plateau[3][0] = detective;
                    plateau[4][2] = new Detective();
                }
                else{
                    detective.setColonne(j-2);
                    plateau[i][j-2] = detective;
                    plateau[i][j] = new Detective();
                }
            }
            else if (j == 0){
                if(i == 1){
                    detective.setLigne(0);
                    detective.setColonne(2);
                    plateau[0][2] = detective;
                    plateau[1][0] = new Detective();
                }
                else if(i == 2){
                    detective.setLigne(0);
                    detective.setColonne(1);
                    plateau[0][1] = detective;
                    plateau[2][0] = new Detective();
                }
                else{
                    detective.setColonne(i-2);
                    plateau[i-2][j] = detective;
                    plateau[i][j] = new Detective();
                }
            }
            else if (j == 4){
                if(i == 3){
                    detective.setLigne(4);
                    detective.setColonne(2);
                    plateau[4][2] = detective;
                    plateau[3][4] = new Detective();
                }
                else if(i == 2){
                    detective.setLigne(4);
                    detective.setColonne(3);
                    plateau[4][3] = detective;
                    plateau[2][4] = new Detective();
                }
                else{
                    detective.setLigne(i+2);
                    plateau[i+2][j] = detective;
                    plateau[i][j] = new Detective();
                }
            }
        }

    }

    public ActionWatson(){}
}
