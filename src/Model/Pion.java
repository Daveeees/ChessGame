package Model;

import Model.Mouvements.MvtPion;

public class Pion extends Piece {

    public Pion(int ligne,int colonne, String image, Joueur joueur) {
        super(ligne, colonne, image, joueur, new MvtPion(null));
    }

    public String  getImage() {
        return image;
    }

    @Override
    public Joueur getJoueur() {
        return joueur;
    }
}
