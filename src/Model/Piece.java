package Model;

import java.util.ArrayList;

public abstract class Piece {
    protected int ligne;
    protected int colonne;
    protected String image;
    protected Joueur joueur;
    protected DCA dca;

    public Piece(int ligne,int colonne, String image, Joueur joueur, DCA dca) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.image = image;
        this.joueur = joueur;
        this.dca = dca;
    }

    public String getImage(){
        return image;
    }

    public Joueur getJoueur(){
        return joueur;
    }

    public ArrayList<Case> getCasesAccessibles(int ligne, int colonne, Case[][] board) {
        ArrayList<Case> casesAccessibles;
        casesAccessibles = dca.getCasesAccessibles(ligne, colonne, board, joueur);
        return casesAccessibles;
    }
}
