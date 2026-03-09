package Model;

import java.util.ArrayList;

public class Pion extends Piece {

    private int ligne;
    private int colonne;

    public Pion(int ligne,int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    @Override
    public ArrayList<Case> getCasesAccessibles(int ligne, int colonne) {
        ArrayList<Case> casesAccessibles = new ArrayList<>();
        return casesAccessibles;
    }
}
