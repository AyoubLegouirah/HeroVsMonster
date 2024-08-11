package map;

import modele.hero.Hero;
import modele.hero.Humain;
import game.ItemType;
import modele.hero.Nain;
import modele.monstre.Dragonnet;
import modele.monstre.Loup;
import modele.monstre.Monstre;
import modele.monstre.Orque;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Bienvenue dans la forêt !");
        System.out.println("Tu vas être du côté des héros avec le Nain et l'Humain contre les monstres.");
        System.out.println("Choisis par qui tu veux commencer : l'Humain ('H') ou le Nain ('N') ?");

        Scanner sc = new Scanner(System.in);
        char choix = sc.next().charAt(0);

        Hero hero;

        if (choix == 'H') {
            hero = new Humain();
        } else if (choix == 'N') {
            hero = new Nain();
        } else {
            System.out.println("Choix invalide. Recommence.");
            main(args);
            return;
        }

        System.out.println("Tu as choisi : " + (hero instanceof Humain ? "l'Humain" : "le Nain"));

        // Liste des ennemis à affronter
        Monstre[] ennemis = {new Dragonnet(), new Loup(), new Orque()};

        for (Monstre ennemi : ennemis) {
            System.out.println("Un " + ennemi.getClass().getSimpleName() + " apparaît avec " + ennemi.getPv() + " PV !");
            while (hero.getPv() > 0 && ennemi.getPv() > 0) {
                System.out.println("Appuie sur 'F' pour attaquer !");
                char attaque = sc.next().charAt(0);
                if (attaque == 'F') {
                    hero.frapper(ennemi);
                    if (ennemi.getPv() > 0) {
                        System.out.println("Le " + ennemi.getClass().getSimpleName() + " a " + ennemi.getPv() + " PV restants.");
                    }
                }
                if (ennemi.getPv() > 0) {
                    ennemi.frapper(hero);
                    if (hero.getPv() > 0) {
                        System.out.println("Le " + ennemi.getClass().getSimpleName() + " t'a infligé des dégâts. Il te reste " + hero.getPv() + " PV.");
                    }
                }
            }

            if (hero.getPv() <= 0) {
                System.out.println("Tu es mort. Le jeu est terminé.");
                return;
            } else {
                if (ennemi instanceof Dragonnet) {
                    Dragonnet dragonnet = (Dragonnet) ennemi;
                    System.out.println("Le Dragonnet est vaincu ! Il libère " + dragonnet.getCuir() + " cuir et " + dragonnet.getOr() + " or.");
                    hero.getInventaire().ajouterItem(ItemType.CUIR, dragonnet.getCuir());
                    hero.getInventaire().ajouterItem(ItemType.OR, dragonnet.getOr());


                } else if (ennemi instanceof Loup) {
                    Loup loup = (Loup) ennemi;
                    System.out.println("Le Loup est vaincu ! Il libère " + loup.getCuir() + " cuir.");
                    hero.getInventaire().ajouterItem(ItemType.CUIR, loup.getCuir());
                } else if (ennemi instanceof Orque) {
                    Orque orque = (Orque) ennemi;
                    System.out.println("L'Orque est vaincu ! Il libère " + orque.getOr() + " or.");
                    hero.getInventaire().ajouterItem(ItemType.OR, orque.getOr());
                }

                // Faire en sorte de afficher l'inventaire
                /*
                System.out.println("Inventaire :");
                for (ItemType itemType : ItemType.values()) {
                    System.out.println(itemType + " : " + hero.getInventaire().getItem(itemType));
                }

                 */

                hero.restaurerPv();
                System.out.println("Tu te reposes et récupères des points de vie. Tu as maintenant " + hero.getPv() + " PV.");
            }
        }
        System.out.println("Félicitations ! Tu as vaincu tous les ennemis !");
    }
}
