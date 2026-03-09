package Model;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class Jeu extends Observable{

    private int ligneChoise;
    private int colonneChoisie;
    private Piece piece;
    private Case[][] board;

    public void communiquerCoup(Coup c){
        ligneChoise = c.getLigne();
        colonneChoisie = c.getColonne();

        setChanged();
        notifyObservers();
    }

    public int getLigneChoisie() {
        return ligneChoise;
    }

    public int getColonneChoisie() {
        return colonneChoisie;
    }

    public void initPiece(Case c){

        // noirs

        // pions
        if(c.getLigne() == 1){
            piece = "pion noir";
        }
        // autre pieces
        else if(c.getLigne() == 0){
            if(c.getColonne() == 0 || c.getColonne() == 7){
                piece = "tour noire";
            }
            else if(c.getColonne() == 1 || c.getColonne() == 6){
                piece = "cavalier noir";
            }
            else if(c.getColonne() == 2 || c.getColonne() == 5){
                piece = "fou noir";
            }
            else if(c.getColonne() == 3){
                piece = "reine noire";
            }
            else if(c.getColonne() == 4){
                piece = "roi noir";
            }
        }

        // blancs
        // pions
        else if(c.getLigne() == 6){
            piece = "pion blanc";
        }
        // autres pieces
        else if (c.getLigne() == 7){
            if(c.getColonne() == 0 || c.getColonne() == 7){
                piece = "tour blanche";
            }
            else if(c.getColonne() == 1 || c.getColonne() == 6){
                piece = "cavalier blanc";
            }
            else if(c.getColonne() == 2 || c.getColonne() == 5){
                piece = "fou blanc";
            }
            else if(c.getColonne() == 3){
                piece = "reine blanche";
            }
            else if(c.getColonne() == 4){
                piece = "roi blanc";
            }
        }
        else{
            piece = "vide";
        }

    }

}
