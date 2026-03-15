package Model;

import java.util.ArrayList;

public class Fou extends Piece{

    private int ligne;
    private int colonne;
    private String image;
    private Joueur joueur;

    public Fou(int ligne,int colonne, String image, Joueur joueur) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.image = image;
        this.joueur = joueur;
    }

    @Override
    public Joueur getJoueur() {
        return joueur;
    }

    public String getImage() {
        return image;
    }

    @Override
    public ArrayList<Case> getCasesAccessibles(int ligne, int colonne) {
        ArrayList<Case> casesAccessibles = new ArrayList<>();
        return casesAccessibles;
    }
}
