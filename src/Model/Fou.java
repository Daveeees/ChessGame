package Model;

import Model.Mouvements.Diag;

public class Fou extends Piece{

    public Fou(int ligne,int colonne, String image, Joueur joueur) {
        super(ligne, colonne, image, joueur, new Diag(null));
    }

    @Override
    public Joueur getJoueur() {
        return joueur;
    }

    public String getImage() {
        return image;
    }
}
