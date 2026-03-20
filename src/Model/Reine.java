package Model;

import Model.Mouvements.Diag;
import Model.Mouvements.HV;

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
