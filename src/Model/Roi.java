package Model;

import Model.Mouvements.Pas;

public class Roi extends Piece {

    public Roi(int ligne,int colonne, Joueur joueur) {
        super(ligne, colonne, joueur, new Pas(null));
    }

    @Override
    public Joueur getJoueur(){
        return joueur;
    }
}
