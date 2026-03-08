package Model;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class Jeu extends Observable{

    private int ligneChoise;
    private int colonneChoisie;

    public void appliquerCoup(Coup c){
        ligneChoise = c.getLigne();
        colonneChoisie = c.getColonne();

        setChanged();
        notifyObservers();
    }

    public int getLigneChoisie() {
        return ligneChoise;
    }

    public int getColonneChoisie() {
        return colonneChoisie;
    }

}
