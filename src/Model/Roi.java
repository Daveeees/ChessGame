package Model;

import java.util.ArrayList;

public class Roi extends Piece {

    private int ligne;
    private int colonne;

    public Roi(int ligne,int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    @Override
    public ArrayList<Case> getCasesAccessibles(int ligne, int colonne) {
        ArrayList<Case> casesAccessibles = new ArrayList<>();
        return casesAccessibles;
    }
}
