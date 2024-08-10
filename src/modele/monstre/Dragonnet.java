package modele.monstre;

import utilitaire.Des;

public class Dragonnet extends Monstre implements ICuir, IOr {
    public Dragonnet(){
        super(1,0);
    }

    public int getCuir() {
        return Des.D4.roll();
    }

    public int getOr() {
        return Des.D6.roll();
    }

}
