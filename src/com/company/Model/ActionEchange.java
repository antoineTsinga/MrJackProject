package com.company.Model;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Scanner;

public class ActionEchange extends Action{
    private String nomFace;

    public ActionEchange(String nomFace) {
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
    public void makeAction(Joueur joueur, Detective sherlock, Detective watson, Detective toby, Stack<Alibi> alibiRestant, Tile[][] plateau, ArrayList<District> districtsPlateau){
        System.out.println("faire ActionEchange");
        System.out.println("Quels districtes voulez vous échanger?" + '\n' + "veuillez selection deux districtes, en mentionnant le chiffre associé, parmis ceux presentés ci-dessous");
        //Le joueur choisit les deux districts qu'il faut échanger
        afficherDistricte(plateau, districtsPlateau);
        int i = scanner.nextInt();
        District district1 = districtsPlateau.get(i);

        afficherDistricte(plateau, districtsPlateau);
        int j = scanner.nextInt();
        District district2 = districtsPlateau.get(j);
        makeActionEchange(district1, district2, plateau);
        //on remet les districtes apres utilisation
    }
    public void makeActionEchange(District district1, District district2, Tile[][] plateau){
        int ligne1 = district1.getLigne();
        int colonne1 = district1.getColonne();
        int ligne2 = district2.getLigne();
        int colonne2 = district2.getColonne();
        district1.setLigne(ligne2);
        district1.setColonne(colonne2);
        district2.setLigne(ligne1);
        district2.setColonne(colonne1);
        plateau[ligne1][colonne1] = district2;
        plateau[ligne2][colonne2] = district1;
    }
    public void afficherDistricte(Tile[][] plateau, ArrayList<District> districtsPlateau){
        int l = 0;
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                District district = (District) plateau[i][j];
                districtsPlateau.add(district);
                if (district.getAlibi() == null) {
                    System.out.println(l + "-" + "District " + district.getAlibi());
                    l++;
                }
                else{
                    System.out.println(l + "-" + "District " + district.getAlibi().getPersonnage());
                    l++;
                }
            }
        }
    }
}
