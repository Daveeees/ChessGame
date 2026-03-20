package Model.Mouvements;

import Model.Case;
import Model.Joueur;

import java.util.ArrayList;

public class SautCheval extends DCA {

    private static final int[][] SAUTS = {
            {-2, -1}, {-2,  1},
            {-1, -2}, {-1,  2},
            { 1, -2}, {1,  2},
            { 2, -1}, { 2,  1}
    };

    public SautCheval(DCA base) {
        super(base);
    }

    @Override
    public ArrayList<Case> getMesCasesAccessibles(int ligne, int colonne, Case[][] board, Joueur joueur) {
        ArrayList<Case> cases = new ArrayList<>();

        for (int[] saut : SAUTS) {
            int l = ligne + saut[0];
            int c = colonne + saut[1];

            if (estAccessible(l, c, board, joueur)) {
                cases.add(board[l][c]);
            }
        }

        return cases;
    }
}