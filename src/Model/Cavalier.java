package Model;

import Model.Mouvements.SautCheval;

public class Cavalier extends Piece{

    public Cavalier(int ligne,int colonne, Joueur joueur) {
        super(ligne, colonne,joueur, new SautCheval(null));
    }

    @Override
    public Joueur getJoueur() {
        return joueur;
    }
}
