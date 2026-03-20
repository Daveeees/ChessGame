package Model.Mouvements;

import Model.Case;
import Model.Joueur;

import java.util.ArrayList;

public class Pas extends DCA {

    private static final Direction[] DIRECTIONS = Direction.values();

    public Pas(DCA base) {
        super(base);
    }

    @Override
    public ArrayList<Case> getMesCasesAccessibles(int ligne, int colonne, Case[][] board, Joueur joueur) {
        ArrayList<Case> cases = new ArrayList<>();

        for (Direction dir : DIRECTIONS) {
            int l = ligne + dir.dx;
            int c = colonne + dir.dy;

            if (estAccessible(l, c, board, joueur)) {
                cases.add(board[l][c]);
            }
        }

        return cases;
    }
}