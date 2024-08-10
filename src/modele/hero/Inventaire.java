package modele.hero;

import java.util.Map;

public class Inventaire {

    private Map<ItemType,Integer> contenus;

    public void ajouterItem(ItemType type, Integer quantite) {
        contenus.merge(type, quantite, Integer::sum);
    }

    public int getItem(ItemType type) {
        return contenus.getOrDefault(type, 0);
    }

}
