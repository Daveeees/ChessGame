package Model;

import java.util.ArrayList;

public class Cavalier extends Piece{

    private int ligne;
    private int colonne;
    private String image;
    private Joueur joueur;
    private DCA dca;

    public Cavalier(int ligne,int colonne, String image, Joueur joueur, DCA dca) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.image = image;
        this.joueur = joueur;
        this.dca = dca;

    }

    @Override
    public Joueur getJoueur() {
        return joueur;
    }

    public String getImage() {
        return image;
    }

    public ArrayList<Case> getCasesAccessibles(int ligne, int colonne, Case[][] board) {
        ArrayList<Case> casesAccessibles = new ArrayList<>();
        casesAccessibles = dca.getCasesAccessibles(ligne, colonne, board, joueur);
        return casesAccessibles;
    }
}
