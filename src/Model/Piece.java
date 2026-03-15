package Model;

import java.util.ArrayList;

public abstract class Piece {
    private int ligne;
    private int colonne;
    private String image;
    private Joueur joueur;

    public String getImage(){
        return image;
    }

    public Joueur getJoueur(){
        return joueur;
    }

    public abstract ArrayList<Case> getCasesAccessibles(int ligne, int colonne);
}
