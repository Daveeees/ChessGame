package Model;

import java.util.ArrayList;

public class MvtPion extends DCA {

    private static final Direction[] DIRECTIONS_BLANC = {
            Direction.HAUT,
            Direction.HAUT_DROITE,
            Direction.HAUT_GAUCHE
    };

    private static final Direction[] DIRECTIONS_NOIR = {
            Direction.BAS,
            Direction.BAS_DROITE,
            Direction.BAS_GAUCHE
    };

    public MvtPion(DCA base) {
        super(base);
    }

    @Override
    public ArrayList<Case> getMesCasesAccessibles(int ligne, int colonne, Case[][] board, Joueur joueur) {
        ArrayList<Case> cases = new ArrayList<>();

        if(joueur.getCouleur().equals("Blanc")) {



            int l = ligne + Direction.HAUT.dx;
            int c = colonne + Direction.HAUT.dy;

            if (board[l][c].getPiece() != null) {
                cases.add(board[l][c]);
                if(ligne == 6 && board[ligne - 1][c].getPiece() != null) {
                    cases.add(board[ligne - 1][c]);
                }
            }

            l = ligne + Direction.HAUT_DROITE.dx;
            c = colonne + Direction.HAUT_DROITE.dy;
            if (!board[l][c].getPiece().getJoueur().getCouleur().equals(joueur.getCouleur())) {
                cases.add(board[l][c]);
            }
            l = ligne + Direction.HAUT_GAUCHE.dx;
            c = colonne + Direction.HAUT_GAUCHE.dy;
            if(!board[l][c].getPiece().getJoueur().getCouleur().equals(joueur.getCouleur())){
                cases.add(board[l][c]);
            }
        }
        else if(joueur.getCouleur().equals("Noir")) {
            int l = ligne + Direction.BAS.dx;
            int c = colonne + Direction.BAS.dy;

            if (board[l][c].getPiece() != null) {
                cases.add(board[l][c]);
                if(ligne == 1 && board[ligne + 1][c].getPiece() != null) {
                    cases.add(board[ligne + 1][c]);
                }
            }

            l = ligne + Direction.BAS_DROITE.dx;
            c = colonne + Direction.BAS_DROITE.dy;
            if (!board[l][c].getPiece().getJoueur().getCouleur().equals(joueur.getCouleur())) {
                cases.add(board[l][c]);
            }
            l = ligne + Direction.BAS_GAUCHE.dx;
            c = colonne + Direction.BAS_GAUCHE.dy;
            if(!board[l][c].getPiece().getJoueur().getCouleur().equals(joueur.getCouleur())){
                cases.add(board[l][c]);
            }
        }

        return cases;
    }
}