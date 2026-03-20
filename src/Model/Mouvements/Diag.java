package Model.Mouvements;

import Model.Case;
import Model.Joueur;
import Model.Piece;

import java.util.ArrayList;

public class Diag extends DCA {

    private static final Direction[] DIRECTIONS = {
            Direction.HAUT_GAUCHE,
            Direction.HAUT_DROITE,
            Direction.BAS_GAUCHE,
            Direction.BAS_DROITE
    };

    public Diag(DCA base) {
        super(base);
    }

    @Override
    public ArrayList<Case> getMesCasesAccessibles(int ligne, int colonne, Case[][] board, Joueur joueur) {
        ArrayList<Case> cases = new ArrayList<>();

        for (Direction dir : DIRECTIONS) {
            int l = ligne + dir.dx;
            int c = colonne + dir.dy;
            boolean flag = true;
            while (estDansLePlateau(l, c) &&  flag) {
                Piece pieceActuelle = board[l][c].getPiece();

                if (pieceActuelle == null) {
                    cases.add(board[l][c]);
                } else if (!pieceActuelle.getJoueur().getCouleur().equals(joueur.getCouleur())) {
                    cases.add(board[l][c]);
                    flag = false;
                } else {
                    flag = false;
                }

                l += dir.dx;
                c += dir.dy;
            }
        }

        return cases;
    }
}