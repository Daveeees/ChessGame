package Model;

import java.util.ArrayList;

public class Reine extends Piece {
    private int ligne;
    private int colonne;
    private String image;

    public Reine(int ligne,int colonne, String image) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.image = image;
    }

    @Override
    public ArrayList<Case> getCasesAccessibles(int ligne, int colonne) {
        ArrayList<Case> casesAccessibles = new ArrayList<>();
        return casesAccessibles;
    }
}
