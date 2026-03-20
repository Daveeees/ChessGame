package Model;

import Model.Mouvements.MvtPion;

public class Pion extends Piece {

    public Pion(int ligne,int colonne, Joueur joueur) {
        super(ligne, colonne, joueur, new MvtPion(null));
    }

    @Override
    public Joueur getJoueur() {
        return joueur;
    }
}
