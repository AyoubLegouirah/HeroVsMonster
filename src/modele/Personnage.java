package modele;

import modele.hero.Nain;
import modele.monstre.IOr;
import utilitaire.Des;

public abstract class Personnage {
    private int endurance;
    private int force;
    private int pv;


    public Personnage(int bonusEndurance, int bonusForce) {
        this.endurance = Des.D6.roll(4,3) + bonusEndurance;
        this.force = Des.D6.roll(4,3) + bonusForce;
        this.pv = Des.D6.roll(4,3);
    }


    public int getEndurance() {
        return endurance;
    }

    public int getForce() {
        return force;
    }

    public int getPv() {
        return pv;
    }

    private void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    private void setForce(int force) {
        this.force =  force;
    }

    private void setPv(int pv) {
        if (endurance < 5) {
            this.pv = endurance - 1;
        }
        else if (endurance < 10) {
            this.pv = endurance;
        }
        else if (endurance < 15) {
            this.pv = endurance + 1;
        }
        else {
            this.pv = endurance + 2;
        }
    }

    public void takeDamage(int damage) {
        this.pv -= damage;
    }

    public void frapper(Personnage cible) {
        int degats = Des.D4.roll(1,1);
        if(force < 5 ){
            cible.takeDamage(degats - 1);
        }
        else if(force < 10 ){
            cible.takeDamage(degats);
        }
        else if(force < 15 ){
            cible.takeDamage(degats + 1);
        }
        else {
            cible.takeDamage(degats + 2);;
        }
    }


/*
    public void mortPersonnage(Personnage personnage){
        if (personnage.pv <= 0){
            System.out.println("Le personnage est mort.");
        }
    }

 */






    // La force et l’endurance sont calculées à la création du personnage en lançant,
    // pour chacune d’elles, quatre dé 6 faces et en n’en reprenant que les 3 meilleurs.
    // Les points de vie sont déterminés par l’endurance additionnée avec le modificateur1
    // basé sur l’endurance.





}
