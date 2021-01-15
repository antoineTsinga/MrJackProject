package com.company.Model;

import java.util.ArrayList;
import java.util.Stack;

public abstract class Action {
    public String getNom(){
        return "action";
    }
    public void setNom(String nomFace){

    }
    public void makeAction(Joueur joueur, Detective sherlock, Detective watson, Detective toby, Stack<Alibi> alibiRestant, Tile[][] plateau, ArrayList<District> districtsPlateau){
        System.out.println("faire Action");
    }
}
