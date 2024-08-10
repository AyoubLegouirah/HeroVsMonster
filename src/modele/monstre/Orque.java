package modele.monstre;

import utilitaire.Des;

public class Orque extends Monstre implements IOr {
    public Orque(){
        super(0, 1);
    }

    public int getOr() {
        return Des.D6.roll();
    }
}
