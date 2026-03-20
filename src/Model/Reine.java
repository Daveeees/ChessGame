package Model;

import Model.Mouvements.Diag;
import Model.Mouvements.HV;

public class Reine extends Piece {

    public Reine(int ligne,int colonne, Joueur joueur) {
        super(ligne, colonne, joueur, new HV(new Diag(null)));
    }

    @Override
    public Joueur getJoueur() {
        return joueur;
    }
}
