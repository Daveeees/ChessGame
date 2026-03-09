package Model;

import java.util.ArrayList;

public abstract class Piece {
    public abstract ArrayList<Case> getCasesAccessibles(int ligne, int colonne);
}
