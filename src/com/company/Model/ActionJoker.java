package com.company.Model;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;

public class ActionJoker extends Action{
    private String nomFace;

    public ActionJoker(String nomFace) {
        this.nomFace = nomFace;
    }
    public ActionJoker(){}

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
    Scanner scanner =  new Scanner(System.in);
    @Override
    public void makeAction(Joueur joueur, Detective sherlock, Detective watson, Detective toby, Stack<Alibi> alibiRestant, Tile[][] plateau, ArrayList<District> DistrictNonNetournes){
        System.out.println("faire ActionJoker");
        if(joueur.getType().equals("enqueteur")){
            System.out.println("Quel detective voulez-vous déplacer " + '\n' + "0-sherlock "+ '\n' + "1-watson " + '\n' + "2-toby");
            int NomDetective = scanner.nextInt();
            switch (NomDetective) {
                case 0:
                    makeActionJoker(sherlock, plateau);
                    break;
                case 1:
                    makeActionJoker(watson, plateau);
                    break;
                case 2:
                    makeActionJoker(toby, plateau);
                    break;
            }
        }
        else if (joueur.getType().equals("mrJack")){
            System.out.println("Voulez-deplacer un detective? (y/n)");//mrJack à deux choix possible, deplacer ou non!
            String str = scanner.next();
            if (str.equals("y")){
                System.out.println("Quel detective voulez-vous déplacer " + '\n' + "0-sherlock "+ '\n' + "1-watson " + '\n' + "2-toby");
                int NomDetective = scanner.nextInt();
                switch (NomDetective) {
                    case 0:
                        makeActionJoker(sherlock, plateau);
                        break;
                    case 1:
                        makeActionJoker(watson, plateau);
                        break;
                    case 2:
                        makeActionJoker(toby, plateau);
                        break;
                }
            }
            else if(str.equals("n")) {}

        }

    }

    public void makeActionJoker(Detective detective, Tile[][] plateau){
        int i = detective.getLigne();
        int j = detective.getColonne();
        //il faut implementer tous les cas ou le detective se trouve au bords
        if (i == 0) {
            if (j == 3){//coin superieur droit
                detective.setLigne(1);
                detective.setColonne(4);
                plateau[1][4] = detective;
                plateau[0][3] = new Detective();
            }
            else{
                detective.setColonne(j+1);
                plateau[i][j+1] = detective;
                plateau[i][j] = new Detective();
            }
        }
        else if (i == 4){
            if (j ==1 ){//coin inferieur gauche
                detective.setLigne(3);
                detective.setColonne(0);
                plateau[3][0] = detective;
                plateau[4][1] = new Detective();
            }
            else{
                detective.setColonne(j-1);
                plateau[i][j-1] = detective;
                plateau[i][j] = new Detective();
            }
        }

        else if (j == 0){ //coin superieur gauche
            if(i == 1){
                detective.setLigne(0);
                detective.setColonne(1);
                plateau[0][1] = detective;
                plateau[1][0] = new Detective();
            }
            else{
                detective.setLigne(i-1);
                plateau[i-1][j] = detective;
                plateau[i][j] = new Detective();
            }
        }
        else if (j == 4){
            if(i == 3){//coin inférieur droit
                detective.setLigne(4);
                detective.setColonne(3);
                plateau[4][3] = detective;
                plateau[3][4] = new Detective();
            }
            else{
                detective.setLigne(i+1);
                plateau[i+1][j] = detective;
                plateau[i][j] = new Detective();
            }
        }

    }

}
