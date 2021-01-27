package com.company.Controller;

import com.company.Model.*;

import java.util.*;

import static com.company.Controller.Main.play;


public class Partie {
    private Tile[][] plateau;
    private Stack<Alibi> alibiRestante;
    private ArrayList<JetonAction> jetonsAction;
    private ArrayList<Detective> detectives;
    private Enqueteur enqueteur;
    private MrJack mrJack;
    static Scanner scanner = new Scanner(System.in);

    public Partie() {
    }

    public void lancerPartie() {
        initialisationPlateau();
        initialisationJeton();
        initialisationJoueur();
    }

    private void initialisationJoueur() {
        if (alibiRestante == null) alibiRestante = new Stack<>();
        if (enqueteur == null) enqueteur = new Enqueteur();
        if (mrJack == null) mrJack = new MrJack();

        System.out.println("veillez saisir votre nom joueur 1");
        String nomJoueur1 = scanner.next();
        System.out.println("veillez saisir votre nom joueur 2");
        String nomJoueur2 = scanner.next();

        Stack<String> nomJoueursL = new Stack<>();
        nomJoueursL.add(nomJoueur1);
        nomJoueursL.add(nomJoueur2);
        Collections.shuffle(nomJoueursL);

        enqueteur.setNom(nomJoueursL.remove(0));
        enqueteur.setType("enqueteur");
        enqueteur.setNbSuspectRestant(9);//tout le monde est suspect au depart
        mrJack.setNom(nomJoueursL.remove(0));
        mrJack.setType("mrJack");

        System.out.println("Celui qui jouera le role de l'Enqueteur est " + enqueteur.getNom());
        System.out.println("Celui qui jouera le role de MrJack est " + mrJack.getNom());

        alibiRestante = createStackAlibi();
        Alibi yu = alibiRestante.get(0);
        String vu = yu.getPersonnage();
        mrJack.setIdentite(alibiRestante.remove(0));
        System.out.println("Seulement MrJack peut voir cette information, veuillez regarder tout à gauche pour découvrir votre identité                                           " + " l'identité de MrJack est "+ vu);
    }

    private void initialisationJeton() {
        if (jetonsAction == null) jetonsAction = new ArrayList<>();
        Stack<Boolean> booleans = new Stack<>();
        booleans.add(true);
        booleans.add(true);
        booleans.add(false);
        booleans.add(false);
        Collections.shuffle(booleans);
        jetonsAction.add(new JetonAction(new ActionAlibi("Alibi"), new ActionSherlock("Sherlock"), booleans.remove(0)));
        jetonsAction.add(new JetonAction(new ActionRotation("Rotation"), new ActionJoker("Joker"), booleans.remove(0)));
        jetonsAction.add(new JetonAction(new ActionRotation("Rotation"), new ActionEchange("Echange"), booleans.remove(0)));
        jetonsAction.add(new JetonAction(new ActionWatson("Watson"), new ActionToby("Toby"), booleans.remove(0)));
        Collections.shuffle(jetonsAction);
    }

    private void initialisationPlateau() {
        if (plateau == null) plateau = new Tile[5][5];
        if (detectives == null) detectives = new ArrayList<>();

        List<District> districts = createDistricts();
        for (int i = 0; i < 5; i++) {//on parcours les lignes
            for (int j = 0; j < 5; j++) {//on parcours les colonnes
                if (i == 0) {//le haut du plateau
                    plateau[i][j] = new Detective();
                }
                else if (i == 1) {//ligne de Sherlock et Watson
                    if (j == 0) {//colonne de Sherlock
                        Detective sherlock = new Detective("Sherlock", i, j);
                        detectives.add(sherlock);
                        plateau[i][j] = sherlock;
                    } else if (j == 4) {//colonne de Watson
                        Detective watson = new Detective("watson", i, j);
                        detectives.add(watson);
                        plateau[i][j] = watson;
                    } else {
                        District district = districts.remove(0);
                        district.setLigne(i);
                        district.setColonne(j);
                        plateau[i][j] = district;
                    }
                }
                else if (i == 4) {//linge de Toby et bas du plateau'
                    if (j == 2) {//colonne de Toby
                        Detective toby = new Detective("toby", i, j);
                        detectives.add(toby);
                        plateau[i][j] = toby;
                    }
                    else {
                        plateau[i][j] = new Detective();
                    }
                }
                else {
                    if (j > 0 && j < 4) {
                        District district = districts.remove(0);
                        district.setLigne(i);
                        district.setColonne(j);
                        plateau[i][j] = district;
                    } else {
                        plateau[i][j] = new Detective();
                    }
                }
            }
        }
    }

    private Stack<Alibi> createStackAlibi() {
        Stack<Alibi> alibiStack = new Stack();
        alibiStack.push(new Alibi("Insp. Lestrade", 0));
        alibiStack.push(new Alibi("Miss Stoalthy", 1));
        alibiStack.push(new Alibi("Jeremy Bert", 1));
        alibiStack.push(new Alibi("John Pizer", 1));
        alibiStack.push(new Alibi("John Smith", 1));
        alibiStack.push(new Alibi("Sgt Goodlet", 0));
        alibiStack.push(new Alibi("William Gull", 1));
        alibiStack.push(new Alibi("Madame", 2));
        alibiStack.push(new Alibi("Joseph Lane", 1));

        Collections.shuffle(alibiStack);
        return alibiStack;

    }

    private ArrayList<District> createDistricts() {
        //generation de la pile d'alibis
        Stack StackAlibi = createStackAlibi();

        //orientation des districtes qui sera rondu aleatoire
        ArrayList<Boolean> orientation = new ArrayList<>();
        orientation.add(false);
        orientation.add(true);
        orientation.add(true);
        orientation.add(true);

        //liste des districtes contenant en premières positions ceux dont la position initiale est fixé par les detectives
        ArrayList<District> districts = new ArrayList<>();
        Boolean[] orientationDistrictSherlock = {true, true, true, false};
        Boolean[] orientationDistrictWatson = {true, false, true, true};
        Boolean[] orientationDistrictToby = {true, true, false, true};
        List<Boolean> OrientationDistrictSherlockL = Arrays.asList(orientationDistrictSherlock);
        List<Boolean> OrientationDistrictWatsonL = Arrays.asList(orientationDistrictWatson);
        List<Boolean> OrientationDistrictTobyL = Arrays.asList(orientationDistrictToby);

        //pour remplir la liste des districtes on va supposer que le plateau de disrictes est numeroté de 1 à 9, en commençant par remplir les lignes
        districts.add(new District((Alibi) StackAlibi.remove(0), OrientationDistrictSherlockL)); //Sherlock en position 1

        Collections.shuffle(orientation);
        districts.add(new District((Alibi) StackAlibi.remove(0), orientation));

        districts.add(new District((Alibi) StackAlibi.remove(0), OrientationDistrictWatsonL)); //Watson en position 3

        for(int k = 1; k<=4; k++){
            Collections.shuffle(orientation);
            districts.add(new District((Alibi) StackAlibi.remove(0), orientation));
        }
        districts.add(new District((Alibi) StackAlibi.remove(0), OrientationDistrictTobyL)); //Toby en position 8

        Collections.shuffle(orientation);
        districts.add(new District((Alibi) StackAlibi.remove(0), orientation));

        return districts;
    }

    public boolean partieGagnee() {
        return enqueteur.partiGagnee() | mrJack.partiGagnee();
    }

    public Enqueteur getEnqueteur() {
        return enqueteur;
    }

    public void setEnqueteur(Enqueteur enqueteur) {
        this.enqueteur = enqueteur;
    }

    public MrJack getMrJack() {
        return mrJack;
    }

    public void setMrJack(MrJack mrJack) {
        this.mrJack = mrJack;
    }

    public boolean gagnerEnMemeTemps(){
        return enqueteur.partiGagnee() && mrJack.partiGagnee();
    }

    public boolean personneNaGagne(){return !enqueteur.partiGagnee() && !mrJack.partiGagnee();}

    public boolean mrJackEnVue(){
        return enqueteur.mrJackVue(mrJack.getIdentite());
    }

    public void finPartie() {
        System.out.println("La partie est terminée, voulez-vous en recommencer ? (y/n)");
        String choix = scanner.next();
        switch (choix){
            case "y" :
                play();
            case "n":
                System.out.println("merci d'avoir joué");
        }
    }

    public void jouerTour(int tour) {
        //PREMIERE ETAPE – LA TRAQUE
        afficherPlateau();
        traque(tour);

        //DEUXIEME ETAPE - APPEL A TEMOIN
        afficherPlateau();
        appelATemoin();

        System.out.println("mrJack à " + mrJack.getNbSablier() + "sablier(s)" + '\n' + "il reste" + enqueteur.getNbSuspectRestant() + "suspect(s) à l'enqueteur");
    }

    private void traque(int tour) {
        System.out.println("LA TRAQUE");
        if (tour % 2 == 1) {//L'enqueteur commence le tour
            ArrayList<JetonAction> jetonsAction2 = new ArrayList<>(jetonsAction);//on travail sur un copie de la collection jetonsAction
            int compteur = 1;
            for (int i = 1; i <= 2; i++) {

                String nom1 = enqueteur.getNom();
                if(compteur==1) System.out.println(nom1 + " lance les jetons...");

                afficheFacesVisible(jetonsAction2);
                System.out.println(nom1 + " veillez choisir un jeton en entrant le numéro assosié");
                int numJetonEnq = scanner.nextInt();
                JetonAction jetonActionEnq = jetonsAction2.get(numJetonEnq);


                //Realiser l'action de la face visible
                action(jetonActionEnq, enqueteur);
                jetonsAction2.remove(numJetonEnq);//enlever ce jeton des jetons à afficher
                afficherPlateau();
                if (compteur == 1) {//on ne le fait qu'une fois
                    for (int j = 1; j <= 2; j++) {//MrJack doit choisir deux jetons

                        String nom2 = mrJack.getNom();

                        afficheFacesVisible(jetonsAction2);
                        System.out.println(nom2 + " veillez choisir un jeton en entrant le numéro assosié");
                        int numJetonJack = scanner.nextInt();
                        JetonAction jetonActionJack = jetonsAction2.get(numJetonJack);
                        action(jetonActionJack, mrJack);
                        jetonsAction2.remove(numJetonJack);
                        afficherPlateau();
                    }
                }
                compteur++;//va faire échouer le teste plus haut
            }

        }
        else {//MrJack commence le tour
            retourneJetons(jetonsAction);
            ArrayList<JetonAction> jetonsAction2 = new ArrayList<>(jetonsAction);//on travail sur un copie de la collection jetonsAction
            int compteur = 1;
            for (int i = 1; i <= 2; i++) {
                String nom1 = mrJack.getNom();
                if (compteur == 1 )System.out.println(nom1 + " retourne les jetons...");
                afficheFacesVisible(jetonsAction2);
                if(compteur==1) System.out.println(nom1 + " veillez choisir un jeton en entrant le numéro assosié");
                int numJetonJack = scanner.nextInt();

                //Realiser l'action de la face visible
                JetonAction jetonActionJack = jetonsAction2.get(numJetonJack);
                action(jetonActionJack, mrJack);
                jetonsAction2.remove(numJetonJack);//enlever ce jeton des jetons à afficher
                afficherPlateau();

                if (compteur == 1) {//on ne le fait qu'une fois
                    for (int j = 1; j <= 2; j++) {//L'enqueteur doit choisir deux jetons
                        String nom2 = enqueteur.getNom();
                        afficheFacesVisible(jetonsAction2);
                        System.out.println(nom2 + " veillez choisir un jeton en entrant le numéro assosié");
                        int numJetonEnq = scanner.nextInt();
                        JetonAction jetonActionEnq = jetonsAction2.get(numJetonEnq);
                        action(jetonActionEnq, enqueteur);
                        jetonsAction2.remove(numJetonEnq);
                        afficherPlateau();
                    }
                    compteur++;//va faire échouer le teste plus haut
                }

            }

        }
    }

    private void appelATemoin() {
        System.out.println("APPEL A TEMOIN");
        if (enqueteur.getAlibiEnVue()==null) enqueteur.setAlibiEnVue(new Stack<>());
        suspectEnVue();//on met à jour la liste Alibi en vue qui représente les personnages associés aux districtes visible par tous les detectives
        if (enqueteur.mrJackVue(mrJack.getIdentite())) {//le cas MrJack en vue
            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 3; j++) {//partie du tableau reservée aux districtes
                    if (!districtEnVue((District) plateau[i][j], enqueteur.getAlibiEnVue())) {//on teste si l'element en position i,j n'est pas dans la liste des suspect en vue, si c'est pas le cas on le retourne
                        ((District) plateau[i][j]).setAlibi(null);
                        int nbSuspectRestant = enqueteur.getNbSuspectRestant();
                        nbSuspectRestant--;
                        enqueteur.setNbSuspectRestant(nbSuspectRestant);
                    }
                }
            }
        }
        else {//le cas MrJack n'est pas en vue
            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 3; j++) {
                    if (districtEnVue((District) plateau[i][j], enqueteur.getAlibiEnVue())) { //les personnage en vue sont innocentés
                        ((District) plateau[i][j]).setAlibi(null);
                        int nbSuspectRestant = enqueteur.getNbSuspectRestant();
                        nbSuspectRestant--;
                        enqueteur.setNbSuspectRestant(nbSuspectRestant);
                    }
                }
            }
            int nbSablier = mrJack.getNbSablier();
            nbSablier++;
            mrJack.setNbSablier(nbSablier); //on mais à jour le nombre de sablier de mrJack
        }
        enqueteur.setAlibiEnVue(new Stack<>());// A la fin du tour on remet à vide la pile d'alibi en vue
    }

    private boolean districtEnVue(District district, Stack<District> alibiEnVue) {//on verifie si le personnage sur le districte est en dans la liste des alibis en vue pour le retourner eventuellement par la suite
        if (district.getAlibi()!=null) {
            String personnage = district.getAlibi().getPersonnage();
            for (District district1 : alibiEnVue) {
                if (district1.getAlibi() != null){
                    String case1 = district1.getAlibi().getPersonnage();
                    if (personnage.equals(case1)) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    private void retourneJetons(ArrayList<JetonAction> jetonsAction) {
        for (JetonAction jetonAction : jetonsAction) {//on retourne les jetons
            jetonAction.setRectoIsVisible(!jetonAction.getRectoIsVisible());//en envoyant le contraire de RestoIsVisible
        }
    }

    private void afficheFacesVisible(ArrayList<JetonAction> jetonsAction) {//afficher la face visible du jeton
        int l = 0;
        for (JetonAction jetonAction : jetonsAction) {
            if ((jetonAction.getRectoIsVisible())) {

                System.out.println(l + "-" + (jetonAction.getRecto()).getNom());
            } else {
                System.out.println(l + "-" + (jetonAction.getVerso()).getNom());
            }
            l++;
        }
    }

    private void suspectEnVue() {//permet de determiner tout les personnages en vue
        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
                if (!((1 <= i && i <= 3) && (1 <= j && j <= 3))) {//on parcour les bords du tableau reservés aux detectives
                    Detective detective = (Detective) plateau[i][j];
                    ArrayList<String> detectivesL = new ArrayList<>() ;
                    detectivesL.add("Sherlock");
                    detectivesL.add("watson");
                    detectivesL.add("toby");
                    if(detectivesL.contains(detective.getNom())) {//s'il s'agit d'un detective et non d'une case vide
                        if (i == 0) {//le detective est en au nord (en "haut" du plateau)
                            boolean routeTrouvee = true;
                            int k = 1;
                            while ( k <= 3 && routeTrouvee) {//on verifie si il y a une route du nord vers le sud
                                District district = (District) plateau[k][j];
                                boolean cheminEntre = district.isNord();
                                boolean cheminSortie = district.isSud();
                                if (cheminEntre ){//Si l'entree est ouvert alors il y un debut de chemin
                                    Stack<District> AlibiEnVue = enqueteur.getAlibiEnVue();
                                    AlibiEnVue.add(district); //on ajoute le district dans la liste
                                    enqueteur.setAlibiEnVue(AlibiEnVue); //on remet à jour
                                }
                                k++;
                                routeTrouvee = cheminEntre && cheminSortie; //metter à jour la valeur
                            }
                        }
                        if (i == 4) {//le detective est en bas
                            boolean routeTrouvee = true;
                            int k = 3;
                            while ( k >= 1 && routeTrouvee) {//on verifie si il y a un route du sud vers le nord
                                District district = (District) plateau[k][j];
                                boolean cheminEntre = district.isSud();
                                boolean cheminSortie = district.isNord();
                                if (cheminEntre){//Si l'entree et la sortie sont ouverts alors il y a un chemin
                                    Stack<District> alibiEnVue = enqueteur.getAlibiEnVue();
                                    alibiEnVue.add(district);
                                    enqueteur.setAlibiEnVue(alibiEnVue);
                                }
                                k--;
                                routeTrouvee = cheminEntre && cheminSortie; //metter à jour la valeur
                            }
                        }
                        if (j == 0) {//le detective est à gauche
                            boolean routeTrouvee = true;
                            int k = 1;
                            while ( k <= 3 && routeTrouvee) {//on verifie si il y a un route de l'ouest vers l'est
                                District district = (District) plateau[i][k];
                                boolean cheminEntre = district.isOuest();
                                boolean cheminSortie = district.isEst();
                                if (cheminEntre){//Si l'entree et la sortie sont ouverts alors il y a un chemin
                                    Stack<District> AlibiEnVue = enqueteur.getAlibiEnVue();
                                    AlibiEnVue.add(district);
                                    enqueteur.setAlibiEnVue(AlibiEnVue);
                                }
                                k++;
                                routeTrouvee = cheminEntre && cheminSortie; //mettre à jour la valeur
                            }
                        }
                        if (j == 4) {
                            boolean routeTrouvee = true;
                            int k = 3;
                            while ( k >= 1 && routeTrouvee) {
                                District district = (District) plateau[i][k];
                                boolean cheminEntre = district.isEst();
                                boolean cheminSortie = district.isOuest();
                                if (cheminEntre){
                                    Stack<District> AlibiEnVue = enqueteur.getAlibiEnVue();
                                    AlibiEnVue.add(district);
                                    enqueteur.setAlibiEnVue(AlibiEnVue);
                                }
                                k--;
                                routeTrouvee = cheminEntre && cheminSortie;
                            }
                        }
                    }
                }
            }
        }
    }

    private void action(JetonAction jetonAction, Joueur joueur){
        //cette attribut permet d'avoir la liste des districtes non retournés sur le plateau
        ArrayList<District> districtsPlateau = new ArrayList<>();
        if (jetonAction.getRectoIsVisible()) {
            Action recto = jetonAction.getRecto();
            recto.makeAction(joueur, detectives.get(0), detectives.get(1), detectives.get(2), alibiRestante, plateau, districtsPlateau);
        }
        else{
            Action verso = jetonAction.getVerso();
            verso.makeAction(joueur, detectives.get(0), detectives.get(1), detectives.get(2), alibiRestante, plateau, districtsPlateau);
        }
    }

    private void afficherPlateau(){
        for (int i = 0; i<=4; i++){
            for(int j =0; j<=4; j++) {
                if (!((1 <= i && i <= 3) && (1 <= j && j <= 3))) {
                    Detective detective = (Detective) plateau[i][j];
                    if(j==4){ // lorsqu'on affiche les chaine de caractère formatées on doit revenir car j = 4, donc le bord du tableau
                        if(detective.getNom() != null) {
                            String str = detective.getNom();
                            for(Detective detective1 : detectives){ //on verifie s'il n'y pas d'autres détectives au même endroit
                                if ( detective1.getLigne() == i && detective1.getColonne() == j && !detective1.getNom().equals(detective.getNom())){
                                    str = str + " " + detective1.getNom();
                                }
                            }
                            System.out.printf("|-%19s|\n", str);
                        }
                        else {
                            String str = "";
                            for(Detective detective1 : detectives){ //on verifie s'il n'y pas d'autres détectives au même endroi
                                if ( detective1.getLigne() == i && detective1.getColonne() == j && !detective1.getNom().equals(detective.getNom())){
                                    str = str + " " + detective1.getNom();
                                }
                            }
                            System.out.printf("|%-19s|\n", str);
                        }
                    }
                    else {
                        if(detective.getNom() != null) {
                            String str = detective.getNom();
                            for(Detective detective1 : detectives){ //on verifie s'il n'y pas d'autres détectives au même endroi
                                if ( detective1.getLigne() == i && detective1.getColonne() == j && !detective1.getNom().equals(detective.getNom())){
                                    str = str + " " + detective1.getNom();
                                }
                            }
                            System.out.printf("|%-19s|", str);
                        }
                        else {
                            String str = "";
                            for(Detective detective1 : detectives){ //on verifie s'il n'y pas d'autres détectives au même endroi
                                if ( detective1.getLigne() == i && detective1.getColonne() == j && !detective1.getNom().equals(detective.getNom())){
                                    str = str + " " + detective1.getNom();
                                }
                            }
                            System.out.printf("|%-19s|", str);
                        }
                    }
                }
                else {
                    District district = (District) plateau[i][j];
                    if (district.getAlibi() != null){
                        String str = district.getAlibi().getPersonnage() + donneOrientation(district);
                        System.out.printf("|%-18s|", str );
                    }
                    else {
                        System.out.printf("|%-18s|", donneOrientation(district));
                    }
                }
            }
        }
    }

    private String donneOrientation(District district){
        boolean nord = district.isNord();
        boolean sud = district.isSud();
        boolean est = district.isEst();
        boolean ouest = district.isOuest();
        String orientation = "";
        if (ouest) orientation = orientation + "◄";
        if (nord) orientation = orientation + "▲";
        if(sud) orientation = orientation + "▼";
        if(est) orientation = orientation + "►";
        return orientation;
    }

}
