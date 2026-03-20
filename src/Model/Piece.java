package Model;

import Model.Mouvements.DCA;

import java.util.ArrayList;

public abstract class Piece implements Cloneable{
    protected int ligne;
    protected int colonne;
    protected Joueur joueur;
    protected DCA dca;

    public Piece(int ligne,int colonne, Joueur joueur, DCA dca) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.joueur = joueur;
        this.dca = dca;
    }

    public int getLigne(){
        return ligne;
    }

    public  int getColonne(){
        return colonne;
    }

    public Joueur getJoueur(){
        return joueur;
    }

    public ArrayList<Case> getCasesAccessibles(int ligne, int colonne, Case[][] board) {
        ArrayList<Case> casesAccessibles;
        casesAccessibles = dca.getCasesAccessibles(ligne, colonne, board, joueur);
        return casesAccessibles;
    }

    @Override
    public Piece clone() {
        try {
            Piece clone = (Piece) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
