package Model;

import java.util.ArrayList;

public class Pion extends Piece {

    private int ligne;
    private int colonne;
    private String image;

    public Pion(int ligne,int colonne, String image) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.image = image;
    }

    public String  getImage() {
        return image;
    }

    @Override
    public ArrayList<Case> getCasesAccessibles(int ligne, int colonne) {
        ArrayList<Case> casesAccessibles = new ArrayList<>();
        return casesAccessibles;
    }
}
