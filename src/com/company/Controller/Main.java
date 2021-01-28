package com.company.Controller;

import com.company.Model.Partie;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
	// write your code here
        play();
    }
    public static void play(){
        Partie partie = new Partie();
        partie.lancerPartie();
        int tour = 1;
        while(!partie.partieGagnee()){
            if (tour >= 9){
                break;
            }
            partie.jouerTour(tour);
            tour++;
        }

        if (partie.gagnerEnMemeTemps() && tour >= 9){// Si les deux gagnent en même temps à la fin du 8e tour
            if(partie.mrJackEnVue()){//l'enqueteur gagne si Mr Jack est en vue
                System.out.println("l'enqueteur à gagné" + '\n' + partie.getEnqueteur().getNom() + " " + "félicitation");
                finPartie(partie.getMrJack().getIdentite().getPersonnage());
            }
            // Mr jack gagne dans le cas contraire
            else System.out.println("Mr Jack à gagné" + '\n' + partie.getMrJack().getNom() + " " + "félicitation");
            finPartie(partie.getMrJack().getIdentite().getPersonnage());
        }

        else if (partie.gagnerEnMemeTemps() && tour <= 8){// Si les deux atteignent leurs objectif en même temps avant la fin du tour
            while (!partie.mrJackEnVue()) {// La course poursuite
                if (tour >= 9){//Mr Jack gagne dès que le 8e tour se termine et qu'il est resté caché
                    System.out.println("Mr Jack à gagné" + '\n' + partie.getMrJack().getNom() + " " + "félicitation");
                    finPartie(partie.getMrJack().getIdentite().getPersonnage());
                }
                partie.jouerTour(tour);
                tour++;
            }
            //L’Enquêteur gagne dès qu’il termine un tour avec Mr. Jack visible
            System.out.println("l'enqueteur à gagnée" + '\n' + partie.getEnqueteur().getNom() + " " + "félicitation");
            finPartie(partie.getMrJack().getIdentite().getPersonnage());

        }

        else if (partie.personneNaGagne()){//Si aucun des deux joueurs n’a atteint son objectif à la fin du tour 8, Jack est vainqueur
            System.out.println("Mr Jack à gagné" + '\n' + partie.getMrJack().getNom() + " " + "félicitation");
            finPartie(partie.getMrJack().getIdentite().getPersonnage());
        }

        if (partie.getMrJack().partiGagnee()){
            System.out.println("Mr Jack à gagné" + '\n' + partie.getMrJack().getNom() + " " + "félicitation");
            finPartie(partie.getMrJack().getIdentite().getPersonnage());
        }

        else if (partie.getEnqueteur().partiGagnee()){
            System.out.println("l'enqueteur à gagné" + '\n' + partie.getEnqueteur().getNom() + " " + "félicitation");
            finPartie(partie.getMrJack().getIdentite().getPersonnage());
        }

    }
    public static void finPartie(String identiteMrJack) {
        System.out.println(" Mr Jack est " + identiteMrJack);
        System.out.println("La partie est terminée, voulez-vous recommencer ? (y/n)");
        String choix = scanner.next();
        switch (choix){
            case "y" :
                play();
            case "n":
                System.out.println("merci d'avoir joué");
        }
    }
}












