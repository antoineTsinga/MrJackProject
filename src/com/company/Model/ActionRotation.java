package com.company.Model;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class ActionRotation extends Action{
    private String nomFace;

    public ActionRotation(String nomFace) {
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

    static Scanner scanner = new Scanner(System.in);
    @Override
    public void makeAction(Joueur joueur, Detective sherlock, Detective watson, Detective toby, Stack<Alibi> alibiRestant, Tile[][] plateau, ArrayList<District> districtsPlateau){
        System.out.println("faire ActionRotation");
        int i = -1;
        do {
            System.out.println("A quels districtes voulez-vous faire une rotation?" + '\n' + "veuillez selectionner un districtes, en mentionnant le chiffre associé, parmis ceux presentés ci-dessous");
            //Le joueur choisit les deux districts qu'il faut échanger
            afficherDistricteNonRetourne(plateau, districtsPlateau);
            i = scanner.nextInt();
            if (0<=i && i<=8) {
                District district1 = districtsPlateau.remove(i);
                makeActionRotation(district1, plateau);
            }
        } while(!(0<=i && i<=8));
    }
    public void makeActionRotation(District district, Tile[][] plateau){
        System.out.println("de combien de degrée voulez-vous faire pivoter le district? " + "0 " + "90 " + "-90 " + "180");
        int angle = scanner.nextInt();
        if (angle == 90){
            boolean nord = district.isNord();
            boolean est = district.isEst();
            boolean sud = district.isSud();
            boolean ouest = district.isOuest();
            district.setNord(est);
            district.setOuest(nord);
            district.setSud(ouest);
            district.setEst(sud);

        }
        else if (angle == -90){
            boolean nord = district.isNord();
            boolean est = district.isEst();
            boolean sud = district.isSud();
            boolean ouest = district.isOuest();
            district.setNord(ouest);
            district.setOuest(sud);
            district.setSud(est);
            district.setEst(nord);

        }
        else if (angle == 180){
            boolean nord = district.isNord();
            boolean est = district.isEst();
            boolean sud = district.isSud();
            boolean ouest = district.isOuest();
            district.setNord(sud);
            district.setOuest(est);
            district.setSud(nord);
            district.setEst(ouest);
        }

        plateau[district.getLigne()][district.getColonne()] = district;

    }
    public void afficherDistricteNonRetourne(Tile[][] plateau, ArrayList<District> districtsPlateau){
        int l = 0;
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                District district = (District) plateau[i][j];
                districtsPlateau.add(district);
                if (district.getAlibi() == null) {
                    System.out.println(l + "-" + "District " + district.getAlibi());
                }
                else{
                    System.out.println(l + "-" + "District " + district.getAlibi().getPersonnage());
                }
                l++;
            }
        }
    }
}

