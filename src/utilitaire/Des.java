package utilitaire;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public enum Des {
    D4(4),
    D6(6);

    private int nbFaces;

    private Des(int value) {
        this.nbFaces = value;
    }

    public int getNbFaces() {
        return nbFaces;
    }

    public int roll() {
        Random rand = new Random();
        return rand.nextInt(1, getNbFaces());
    }

    public int roll(int nbLance){
        int sum = 0;
        for (int i = 0; i < nbLance; i++) {
            sum += roll();
        }
        return sum;
    }

    public int roll(int nbLance, int nbToKeep) {
        List<Integer> rolls = new ArrayList<>();
        for (int i = 0; i < nbLance; i++) {
            rolls.add(roll());
        }

        return rolls.stream()
                .sorted(Comparator.reverseOrder())
                .limit(nbToKeep)
                .mapToInt(it -> it)
                .sum();
    }

}









