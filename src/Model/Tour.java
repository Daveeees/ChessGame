package Model;

import Model.Mouvements.HV;

public class Tour extends Piece{

    public Tour(int ligne,int colonne, String image, Joueur joueur) {
        super(ligne, colonne, image, joueur, new HV(null));
    }

    public String getImage() {
        return image;
    }

    @Override
    public Joueur getJoueur(){
        return joueur;
    }
}
