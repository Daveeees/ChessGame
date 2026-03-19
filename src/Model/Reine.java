package Model;

import java.util.ArrayList;

public class Reine extends Piece {

    public Reine(int ligne,int colonne, String image, Joueur joueur) {
        super(ligne, colonne, image, joueur, new HV(new Diag(null)));
    }

    @Override
    public Joueur getJoueur() {
        return joueur;
    }
    public String getImage(){
        return this.image;
    }
}
