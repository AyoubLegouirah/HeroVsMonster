package map;

import java.util.Random;
import java.util.Scanner;

public class Map {

        // Met à jour la visibilité autour du pion
        public static void updateVisibility(boolean[][] visible, int x, int y, int taillePlateau) {
            visible[x][y] = true;
            if(x > 0) {
                visible[x-1][y] = true;
            }
            if(x < taillePlateau - 1) {
                visible[x+1][y] = true;
            }
            if(y > 0) {
                visible[x][y-1] = true;
            }
            if(y < taillePlateau - 1) {
                visible[x][y+1] = true;
            }
        }

        public static void main(String[] args) {
            //Outils
            Random random = new Random();
            Scanner sc = new Scanner(System.in);

            //Constantes
            final int NOMBRE_MECHANT = 3;
            final int TAILLE_PLATEAU = 15;
            final int NOMBRE_MURS = 5;


            //COLORS
            final String ANSI_RESET = "\u001B[0m";
            final String ANSI_RED = "\u001B[31m";
            final String ANSI_YELLOW = "\u001B[33m";
            final String ANSI_GREEN = "\u001B[32m";
            final String ANSI_BLUE = "\u001B[34m";
            final String ANSI_BLACK = "\u001B[30m";


            //Variables
            char[][] plateau = new char[TAILLE_PLATEAU][TAILLE_PLATEAU];
            boolean[][] visible = new boolean[TAILLE_PLATEAU][TAILLE_PLATEAU];
            boolean gameWin = false;
            int xPion = random.nextInt(TAILLE_PLATEAU);
            int yPion = random.nextInt(TAILLE_PLATEAU);
            int nbrMonstrePlaces = 0;
            int compteurMonstre = 0;
            int nbrMursPlaces = 0;
            int cmptDeMouvements = 0;


            //Remplissage du tableau
            for(int i = 0; i < TAILLE_PLATEAU; i++){
                for(int j = 0; j < TAILLE_PLATEAU; j++){
                    plateau[i][j] = '_';
                    visible[i][j] = false;
                }
            }

            //Placement du pion sur le plateau
            plateau[xPion][yPion] = '*';
            visible[xPion][yPion] = true;

            //Placement des pièces
            while (nbrMonstrePlaces < NOMBRE_MECHANT){
                int xPiece = random.nextInt(TAILLE_PLATEAU);
                int yPiece = random.nextInt(TAILLE_PLATEAU);

                if(plateau[xPiece][yPiece] == '_'){
                    plateau[xPiece][yPiece] = '$';
                    nbrMonstrePlaces++;
                }
            }

            //Placement des murs
            while (nbrMursPlaces < NOMBRE_MURS){
                int xMur = random.nextInt(TAILLE_PLATEAU);
                int yMur = random.nextInt(TAILLE_PLATEAU);

                if(plateau[xMur][yMur] == '_'){
                    plateau[xMur][yMur] = '#';
                    nbrMursPlaces++;
                }
            }

            System.out.println();

            while(!gameWin){

                System.out.println("Choisissez une direction pour déplacer le pion (g, d, h, b).");
                System.out.println();

                //Affichage du plateau de jeu
                String printPlateau = "";
                for(int i = 0; i < TAILLE_PLATEAU; i++){
                    for(int j = 0; j < TAILLE_PLATEAU; j++){
                        if(visible[i][j]){
                            switch (plateau[i][j]){
                                //Si la case est le pion
                                case '*' -> {
                                    printPlateau += ANSI_BLUE + plateau[i][j] + ANSI_RESET + " ";
                                }
                                //Si la case est une piece
                                case '$' -> {
                                    printPlateau += ANSI_YELLOW + plateau[i][j] + ANSI_RESET + " ";
                                }
                                //Si la case est un mur
                                case '#' -> {
                                    printPlateau += ANSI_RED + plateau[i][j] + ANSI_RESET + " ";
                                }
                                //Si la case est neutre
                                default -> {
                                    printPlateau += ANSI_BLACK + plateau[i][j] +ANSI_RESET + " ";
                                }
                            }
                        } else {
                            //Si la case n'est pas visible
                            printPlateau +=ANSI_GREEN + "?"+ ANSI_RESET +" ";
                        }
                    }
                    printPlateau+="\n";
                }
                System.out.println(printPlateau);

                //Affichage du nombre de pièces
                System.out.println("Pièces ramassées : " + compteurMonstre);

                //Demande le choix du joueur
                char choixJoueur = sc.next().charAt(0);

                //Action selon le choix du joueur
                switch(choixJoueur) {
                    case 'd' -> {
                        int newY = (yPion + 1) % TAILLE_PLATEAU;
                        if(plateau[xPion][newY] == '#') {
                            System.out.println("Mouvement impossible, il y a un mur.");
                        } else {
                            if(plateau[xPion][newY] == '$'){
                                compteurMonstre++;
                            }
                            plateau[xPion][yPion] = '_';
                            yPion = newY;
                            plateau[xPion][yPion] = '*';
                            updateVisibility(visible, xPion, yPion, TAILLE_PLATEAU);
                            cmptDeMouvements++;
                        }
                    }

                    case 'g' -> {
                        int newY = (yPion - 1 + TAILLE_PLATEAU) % TAILLE_PLATEAU;
                        if(plateau[xPion][newY] == '#') {
                            System.out.println("Mouvement impossible, il y a un mur.");
                        } else {
                            if(plateau[xPion][newY] == '$'){
                                compteurMonstre++;
                            }
                            plateau[xPion][yPion] = '_';
                            yPion = newY;
                            plateau[xPion][yPion] = '*';
                            updateVisibility(visible, xPion, yPion, TAILLE_PLATEAU);
                            cmptDeMouvements++;
                        }
                    }

                    case 'h' -> {
                        int newX = (xPion - 1 + TAILLE_PLATEAU) % TAILLE_PLATEAU;
                        if(plateau[newX][yPion] == '#') {
                            System.out.println("Mouvement impossible, il y a un mur.");
                        } else {
                            if(plateau[newX][yPion] == '$'){
                                compteurMonstre++;
                            }
                            plateau[xPion][yPion] = '_';
                            xPion = newX;
                            plateau[xPion][yPion] = '*';
                            updateVisibility(visible, xPion, yPion, TAILLE_PLATEAU);
                            cmptDeMouvements++;
                        }
                    }

                    case 'b' -> {
                        int newX = (xPion + 1) % TAILLE_PLATEAU;
                        if(plateau[newX][yPion] == '#') {
                            System.out.println("Mouvement impossible, il y a un mur.");
                        } else {
                            if(plateau[newX][yPion] == '$'){
                                compteurMonstre++;
                            }
                            plateau[xPion][yPion] = '_';
                            xPion = newX;
                            plateau[xPion][yPion] = '*';
                            updateVisibility(visible, xPion, yPion, TAILLE_PLATEAU);
                            cmptDeMouvements++;
                        }
                    }

                    //Si le choix n'existe pas
                    default -> {
                        System.out.println("Cette direction n'existe pas, choisissez une direction (g, d, h, b) ou o pour arrêter");
                    }
                }

                //Si le joueur a ramassé toutes les pièces, la partie est terminée
                if(compteurMonstre == NOMBRE_MECHANT){
                    gameWin = true;
                }
            }

            //Affichage du nombre de pièces
            System.out.println("Pièces ramassées : " + compteurMonstre);
            System.out.println("Partie Gagnée !!!");
            System.out.println();
            System.out.println("Vous avez finis la partie en : " + cmptDeMouvements + " mouvements");
        }

        //Test First Commit


    }


