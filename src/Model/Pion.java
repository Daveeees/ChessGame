package Model;

import java.util.ArrayList;

public class Pion extends Piece {

    private int ligne;
    private int colonne;
    private String image;
    private Joueur joueur;

    public Pion(int ligne,int colonne, String image, Joueur joueur) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.image = image;
        this.joueur = joueur;
    }

    public String  getImage() {
        return image;
    }

    @Override
    public Joueur getJoueur() {
        return joueur;
    }

    @Override
    public ArrayList<Case> getCasesAccessibles(int ligne, int colonne, Case[][] board) {
        ArrayList<Case> casesAccessibles = new ArrayList<>();
        return casesAccessibles;
    }
}
