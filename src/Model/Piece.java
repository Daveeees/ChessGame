package Model;

import java.util.ArrayList;

public abstract class Piece {
    private int ligne;
    private int colonne;
    private String image;

    public String getImage(){
        return image;
    }
    public abstract ArrayList<Case> getCasesAccessibles(int ligne, int colonne);
}
