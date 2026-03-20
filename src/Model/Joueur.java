package Model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Joueur {
    private String couleur;
    private final Jeu jeu;
    private ArrayList<Piece> pieces;

    public Joueur(String couleur, Jeu jeu) {
        this.couleur = couleur;
        this.jeu = jeu;
        this.pieces = new ArrayList<>();
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

    public Boolean coupValide(Coup c, Case[][] board){
        Case caseDepart = board[c.getDepart().getX()][c.getDepart().getY()];
        Case caseArrivee = board[c.getArrivee().getX()][c.getArrivee().getY()];
        ArrayList<Case> casesPossibles = null;
        casesPossibles = caseDepart.getPiece().getCasesAccessibles(c.getDepart().getX(), c.getDepart().getY(), board);
        return jeu.getTypeCase(c.getDepart().getX(),c.getDepart().getY()).getJoueur().equals(this) && casesPossibles != null && casesPossibles.contains(caseArrivee);
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

    public ArrayList<Piece> getPieces(){
        return pieces;
    }

    public ArrayList<Case> getCasesPossibles (){
        ArrayList<Piece> pieces = getPieces();
        ArrayList<Case> casesPossibles = new ArrayList<>();

        for(Piece p : pieces){
            casesPossibles.addAll(p.getCasesAccessibles(p.getLigne(),p.getColonne(), jeu.getBoard()));
        }

        return casesPossibles;
    }

    public Case getCaseRoi(){
        Case caseRoi = null;
        for(Piece p: getPieces()){
            if (p instanceof Roi){
                caseRoi = jeu.getBoard()[p.getLigne()][p.getColonne()];
            }
        }
        return caseRoi;
    }
}
