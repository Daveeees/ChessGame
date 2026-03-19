package Model;

import java.util.ArrayList;

public class Cavalier extends Piece{

    public Cavalier(int ligne,int colonne, String image, Joueur joueur) {
        super(ligne, colonne, image, joueur, new SautCheval(null));
    }

    @Override
    public Joueur getJoueur() {
        return joueur;
    }

    public String getImage() {
        return image;
    }
}
