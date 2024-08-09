package modele.monstre;

import utilitaire.Des;

public class Loup extends Monstre implements ICuir {
    public Loup() {
        super(0, 0);

    }

    public int getCuir() {
        return Des.D4.roll();
    }
}
