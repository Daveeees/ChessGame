package Model.Mouvements;

import Model.Case;
import Model.Joueur;
import Model.Piece;
import Model.Pion;

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

            if (joueur.getDernierCoupAdverse() != null)
            {
                int src_ligne = joueur.getDernierCoupAdverse().getDepart().getX();
                int src_col = joueur.getDernierCoupAdverse().getDepart().getY();
                int dest_ligne = joueur.getDernierCoupAdverse().getArrivee().getX();
                int dest_col = joueur.getDernierCoupAdverse().getArrivee().getY();

                Piece dernierePiece = board[dest_ligne][dest_col].getPiece();

                boolean etaitPion = dernierePiece instanceof Pion;
                boolean estAdverse = dernierePiece != null && dernierePiece.getJoueur().getCouleur().equals("Noir");
                boolean avanceDeDeux = Math.abs(dest_ligne - src_ligne) == 2;
                boolean memeLigne = dest_ligne == ligne;
                boolean colonneAdjacente = Math.abs(dest_col - colonne) == 1;

                if (etaitPion && estAdverse && avanceDeDeux && memeLigne && colonneAdjacente)
                {
                    cases.add(board[src_ligne + 1][src_col]);
                }
            }

            int l = ligne + Direction.HAUT.dx;
            int c = colonne + Direction.HAUT.dy;

            if (estDansLePlateau(l,c) && board[l][c].getPiece() == null) {
                cases.add(board[l][c]);
                if(ligne == 6 && board[l - 1][c].getPiece() == null) {
                    cases.add(board[l - 1][c]);
                }
            }

            l = ligne + Direction.HAUT_DROITE.dx;
            c = colonne + Direction.HAUT_DROITE.dy;
            if (estDansLePlateau(l,c) && board[l][c].getPiece() != null && !board[l][c].getPiece().getJoueur().getCouleur().equals(joueur.getCouleur())) {
                cases.add(board[l][c]);
            }
            l = ligne + Direction.HAUT_GAUCHE.dx;
            c = colonne + Direction.HAUT_GAUCHE.dy;
            if( estDansLePlateau(l,c) && board[l][c].getPiece() != null && !board[l][c].getPiece().getJoueur().getCouleur().equals(joueur.getCouleur())){
                cases.add(board[l][c]);
            }
        }
        else if(joueur.getCouleur().equals("Noir")) {

            if (joueur.getDernierCoupAdverse() != null)
            {
                int src_ligne = joueur.getDernierCoupAdverse().getDepart().getX();
                int src_col = joueur.getDernierCoupAdverse().getDepart().getY();
                int dest_ligne = joueur.getDernierCoupAdverse().getArrivee().getX();
                int dest_col = joueur.getDernierCoupAdverse().getArrivee().getY();

                Piece dernierePiece = board[dest_ligne][dest_col].getPiece();

                boolean etaitPion = dernierePiece instanceof Pion;
                boolean estAdverse = dernierePiece != null && dernierePiece.getJoueur().getCouleur().equals("Blanc");
                boolean avanceDeDeux = Math.abs(dest_ligne - src_ligne) == 2;
                boolean memeLigne = dest_ligne == ligne;
                boolean colonneAdjacente = Math.abs(dest_col - colonne) == 1;

                if (etaitPion && estAdverse && avanceDeDeux && memeLigne && colonneAdjacente)
                {
                    cases.add(board[src_ligne - 1][src_col]);
                }
            }
            int l = ligne + Direction.BAS.dx;
            int c = colonne + Direction.BAS.dy;

            if (estDansLePlateau(l,c) && board[l][c].getPiece() == null) {
                cases.add(board[l][c]);
                if(ligne == 1 && board[l + 1][c].getPiece() == null) {
                    cases.add(board[l + 1][c]);
                }
            }

            l = ligne + Direction.BAS_DROITE.dx;
            c = colonne + Direction.BAS_DROITE.dy;
            if (estDansLePlateau(l,c) && board[l][c].getPiece() != null && !board[l][c].getPiece().getJoueur().getCouleur().equals(joueur.getCouleur())) {
                cases.add(board[l][c]);
            }
            l = ligne + Direction.BAS_GAUCHE.dx;
            c = colonne + Direction.BAS_GAUCHE.dy;
            if(estDansLePlateau(l,c) && board[l][c].getPiece() != null && !board[l][c].getPiece().getJoueur().getCouleur().equals(joueur.getCouleur())){
                cases.add(board[l][c]);
            }
        }

        return cases;
    }
}