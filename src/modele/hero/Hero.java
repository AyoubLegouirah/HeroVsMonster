package modele.hero;

import modele.Personnage;

public abstract class Hero extends Personnage {

    public Hero(int bonusEndurance, int bonusForce) {
        super(bonusEndurance, bonusForce);
    }

    private Inventaire inventaire = new Inventaire()  {

    };
    public Inventaire getInventaire() {
        return inventaire;
    }

    // Après chaque combat les héros se reposent et restaurent leurs points de vie et affronte
    // le monstre suivant jusqu’à leur mort3.

    int repos =  10;
    public void restaurerPv() {
        this.pv += repos;
        if (this.pv < 0) {
            this.pv = 0;
        }
    }








}
