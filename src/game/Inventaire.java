package game;

import modele.monstre.Monstre;

import java.util.HashMap;
import java.util.Map;

public class Inventaire {

    private Map<ItemType, Integer> contenus = new HashMap<>(); // Initialisation de la Map

    public void ajouterItem(ItemType type, Integer quantite) {
        contenus.merge(type, quantite, Integer::sum);
    }

    public int getItem(ItemType type) {
        return contenus.getOrDefault(type, 0);
    }

    public void ajouterItem(Monstre m) {
    }
}
