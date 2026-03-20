package Model;

import Model.Mouvements.Diag;

public class Fou extends Piece{

    public Fou(int ligne,int colonne, Joueur joueur) {
        super(ligne, colonne, joueur, new Diag(null));
    }

    @Override
    public Joueur getJoueur() {
        return joueur;
    }
}
