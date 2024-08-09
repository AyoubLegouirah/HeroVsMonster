package modele.monstre;

import utilitaire.Des;

public class Dragonnet extends Monstre  {
    public Dragonnet(){
        super(1,0);

        int nbrCuir = Des.D4.roll(1,1);

        if(Monstre.pv <= 0){
            nbrCuir--;
            System.out.println("Dragonnet a donner " + nbrCuir + " cuir()");
        }


    }
}
