package Model;

import java.util.ArrayList;

public class Roi extends Piece {

    public Roi(int ligne,int colonne,  String image, Joueur joueur) {
        super(ligne, colonne, image, joueur, new Pas(null));
    }

    @Override
    public Joueur getJoueur(){
        return joueur;
    }
    public String  getImage() {
        return image;
    }
}
