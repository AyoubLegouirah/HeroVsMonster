import modele.hero.Hero;
import modele.hero.Humain;
import modele.hero.Nain;
import modele.monstre.Dragonnet;
import modele.monstre.Loup;
import modele.monstre.Monstre;
import modele.monstre.Orque;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Bienvenue dans la forets");
        System.out.println("Tu vas etre du coté des heros avec le nain et l'humain contre les monstres");
        System.out.println("Choisi par qui tu veux commencer l'humain 'H' ou le nain 'N' " );

        Scanner sc = new Scanner(System.in);
        char choix = sc.next().charAt(0);

        Hero hero;

        if (choix == 'H'){
            hero = new Humain();
        }else if (choix == 'N') {
            hero = new Nain();
        }else{
            System.out.println("Choix invalide. Recommence");
            main(args);
            return;
        }
        // À partir de ce point, tu peux utiliser 'hero' comme ton héros dans le jeu
        System.out.println("Vous avez choisi : " + (hero instanceof Humain ? "Humain" : "Nain"));


        // TODO : Creer une boucle ou le hero va affronter des mechants (dans l'ordre : Dragonnet, loup, Monstre) et apres avoir utiliser la methode frapper du hero on change de hero

        Monstre[] ennemis = {new Dragonnet(), new Loup(), new Orque()};

        for (int i = 0; i < ennemis.length; i++){
            Monstre ennemi = ennemis[i];
            System.out.println("Un " + ennemi.getClass().getSimpleName() + " apparaît avec " + ennemi.getPv() + " PV!");
            while(hero.getPv() > 0 && ennemi.getPv() > 0) {
                System.out.println("Il va t'attaquer ! Appuie sur 'F' pour attaquer en premier ! ");
                char attaque = sc.next().charAt(0);
                if (attaque == 'F'){
                    hero.frapper(ennemi);
                    if (ennemi.getPv() > 0) {
                        System.out.println("Le " + ennemi.getClass().getSimpleName() + " a " + ennemi.getPv() + " PV");
                    }
                }
                if (ennemi.getPv() > 0){
                    ennemi.frapper(hero);
                    if (hero.getPv() > 0) {
                        System.out.println("Le " + ennemi.getClass().getSimpleName() + " t'as infligé des dégats. Tu as " + hero.getPv() + " PV");
                    }
                }
            }
            if (hero.getPv() <= 0) {
                System.out.println("Tu es mort. Le jeu est terminé.");
                return;
            } else {
                // Libération du butin spécifique selon le type d'ennemi
                if (ennemi instanceof Dragonnet) {
                    Dragonnet dragonnet = (Dragonnet) ennemi;
                    System.out.println("Le Dragonnet est vaincu ! Il libère : " + dragonnet.getCuir() + " cuir et " + dragonnet.getOr() + " or.");
                } else if (ennemi instanceof Loup) {
                    Loup loup = (Loup) ennemi;
                    System.out.println("Le Loup est vaincu ! Il libère : " + loup.getCuir() + " cuir.");
                } else if (ennemi instanceof Orque) {
                    Orque orque = (Orque) ennemi;
                    System.out.println("L'Orque est vaincu ! Il libère : " + orque.getOr() + " or.");
                }

                // Le héros se repose et restaure ses PV après le combat
                hero.restaurerPv();
                System.out.println("Tu te reposes et récupères des points de vie. Tu as maintenant " + hero.getPv() + " PV.");
            }
        }
        System.out.println("Félicitations ! Tu as vaincu tous les ennemis !");

    }




    }
