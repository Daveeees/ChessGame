package Model.Mouvements;

import Model.Case;
import Model.Joueur;
import Model.Piece;

import java.util.ArrayList;

public abstract class DCA {

    private final DCA base;

    public DCA(DCA base) {
        this.base = base;
    }


    public ArrayList<Case> getCasesAccessibles(int ligne, int colonne, Case[][] board, Joueur joueur) {
        ArrayList<Case> cases = getMesCasesAccessibles(ligne, colonne, board, joueur);
        if (base != null) {
            cases.addAll(base.getCasesAccessibles(ligne, colonne, board, joueur));
        }
        return cases;
    }

    public abstract ArrayList<Case> getMesCasesAccessibles(int ligne, int colonne, Case[][] board, Joueur joueur);

    public boolean estDansLePlateau(int ligne, int colonne) {
        return ligne >= 0 && ligne < 8 && colonne >= 0 && colonne < 8;
    }

    public boolean estAccessible(int ligne, int colonne, Case[][] board, Joueur joueur) {
        if (!estDansLePlateau(ligne, colonne)) return false;
        Piece p = board[ligne][colonne].getPiece();
        return p == null || !p.getJoueur().getCouleur().equals(joueur.getCouleur());
    }
}