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

    public Boolean coupValide(Coup c){
        return jeu.getTypeCase(c.getDepart().getX(),c.getDepart().getY()).getJoueur().equals(this);
    }

    public Coup getCoup() throws InterruptedException {
        synchronized (jeu){
            while (jeu.getNextCoup() == null || !coupValide(jeu.getNextCoup())) {
                jeu.wait();
            }

            Coup c = jeu.getNextCoup();
            jeu.resetNextC(); // On vide le coup pour le tour suivant
            return c;
        }
    }

}
