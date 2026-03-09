package Model;

import java.util.ArrayList;

public class Reine extends Piece {
    private int ligne;
    private int colonne;

    public Reine(int ligne,int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    @Override
    public ArrayList<Case> getCasesAccessibles(int ligne, int colonne) {
        ArrayList<Case> casesAccessibles = new ArrayList<>();
        return casesAccessibles;
    }
}
