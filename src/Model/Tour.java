package Model;

import Model.Mouvements.HV;

public class Tour extends Piece{

    public Tour(int ligne,int colonne, Joueur joueur) {
        super(ligne, colonne, joueur, new HV(null));
    }

    @Override
    public Joueur getJoueur(){
        return joueur;
    }
}
