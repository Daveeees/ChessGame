package Model;

import java.util.ArrayList;

public class Joueur {
    private String couleur;
    private final Jeu jeu;
    private ArrayList<Piece> pieces = new ArrayList<Piece>();

    public Joueur(String couleur, Jeu jeu) {
        this.couleur = couleur;
        this.jeu = jeu;
    }

    public void ajouterPiece(Piece p){
        pieces.add(p);
    }

    public void retirerPiece(Piece p){
        pieces.remove(p);
    }

    public String getCouleur(){
        return couleur;
    }

    public void setCouleur(String col){
        couleur = col;
    }

    public boolean estEnEchec(Case[][] board) {
        int roiL = -1, roiC = -1;
        for (int l = 0; l < 8; l++) {
            for (int c = 0; c < 8; c++) {
                Piece p = board[l][c].getPiece();
                if (p instanceof Roi &&
                        p.getJoueur().getCouleur().equals(getCouleur())) {
                    roiL = l;
                    roiC = c;
                }
            }
        }

        Case caseRoi = board[roiL][roiC];
        for (int l = 0; l < 8; l++) {
            for (int c = 0; c < 8; c++) {
                Piece p = board[l][c].getPiece();
                if (p != null && !p.getJoueur().getCouleur().equals(getCouleur())) {
                    if (p.getCasesAccessibles(l, c, board).contains(caseRoi))
                        return true;
                }
            }
        }
        return false;
    }

    public Case[][] simulerCoup(Coup coup, Case[][] board) {
        Case[][] copie = new Case[8][8];
        for (int l = 0; l < 8; l++) {
            for (int c = 0; c < 8; c++) {
                copie[l][c] = new Case(l, c, board[l][c].getPiece());
            }
        }
        int dx = coup.getDepart().getX();
        int dy = coup.getDepart().getY();
        int ax = coup.getArrivee().getX();
        int ay = coup.getArrivee().getY();
        Piece pieceABouger = copie[dx][dy].getPiece();
        copie[dx][dy].setPiece(null);
        copie[ax][ay].setPiece(pieceABouger);
        return copie;

    }

    public boolean estEnEchecEtMat(Case[][] board) {
        if (!estEnEchec(board)){
            return false;
        }
        for (int l = 0; l < 8; l++) {
            for (int c = 0; c < 8; c++) {
                Piece p = board[l][c].getPiece();
                if (p != null && p.getJoueur().getCouleur().equals(getCouleur())) {

                    for (Case caseTest : p.getCasesAccessibles(l, c, board)) {
                        Coup coup = new Coup(new Point(l, c), new Point(caseTest.getLigne(), caseTest.getColonne()));
                        if (!estEnEchec(simulerCoup(coup, board))) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public Boolean estEnPat(){
        for(Piece p : pieces){
            for(Case c : p.getCasesAccessibles(p.ligne,p.colonne, jeu.getBoard())){
              if(coupValide(new Coup(new Point(p.ligne,p.colonne), new Point(c.getLigne(),c.getColonne())),jeu.getBoard())){
                  return false;
              }
            }
        }
        return !estEnEchec(jeu.getBoard());
    }

    public Boolean coupValide(Coup c, Case[][] board){
        Case caseDepart = board[c.getDepart().getX()][c.getDepart().getY()];
        Case caseArrivee = board[c.getArrivee().getX()][c.getArrivee().getY()];
        ArrayList<Case> casesPossibles = caseDepart.getPiece().getCasesAccessibles(c.getDepart().getX(), c.getDepart().getY(), board);
        return jeu.getTypeCase(c.getDepart().getX(),c.getDepart().getY()).getJoueur().equals(this) && casesPossibles != null && casesPossibles.contains(caseArrivee) && !estEnEchec(simulerCoup(c, board));
    }

    public Coup getCoup() throws InterruptedException {
        synchronized (jeu){
            while (jeu.getNextCoup() == null || !coupValide(jeu.getNextCoup(), jeu.getBoard()) || jeu.getNextCoup().getDepart().equals(jeu.getNextCoup().getArrivee())) {
                jeu.wait();
            }

            Coup c = jeu.getNextCoup();
            jeu.resetNextC(); // On vide le coup pour le tour suivant
            return c;
        }
    }

}
