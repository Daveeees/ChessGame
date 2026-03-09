package Model;

public class Case {
    private int ligne;
    private int colonne;
    private Piece piece;

    public Case(int ligne, int colonne, Piece piece){
        this.ligne = ligne;
        this.colonne = colonne;
        this.piece = piece;
    }

    public int getLigne() {
        return ligne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }
    public int getColonne() {
        return colonne;
    }
    public void setColonne(int colonne) {
        this.colonne = colonne;
    }
}
