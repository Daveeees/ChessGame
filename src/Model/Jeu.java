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

    public void initBoardModel(){
        for(int i=0;i<=8;i++){
            for(int j=0;j<=8;j++){
                if(i == 1){
                    Pion pN = new Pion(i,j,"chessPieces/bP.svg");
                    board[i][j] = new Case(i,j, pN);
                }
                // autre pieces
                else if(i == 0){
                    if(j == 0 || j == 7){
                        Tour tN = new Tour(i,j, "chessPieces/bR.svg");
                        board[i][j] = new Case(i,j, tN);
                    }
                    else if(j == 1 || j == 6){
                        Cavalier cN = new Cavalier(i,j, "chessPieces/bN.svg");
                        board[i][j] = new Case(i,j, cN);
                    }
                    else if(j == 2 || j == 5){
                        Fou fN = new Fou(i,j, "chessPieces/bB.svg");
                        board[i][j] = new Case(i,j,fN);
                    }
                    else if(j == 3){
                        Reine reineN = new Reine(i,j, "chessPieces/bQ.svg");
                        board[i][j] = new Case(i,j,reineN);
                    }
                    else if(j == 4){
                        Roi roiN = new Roi(i,j, "chessPieces/bK.svg");
                        board[i][j] = new Case(i,j,roiN);
                    }
                }

                // blancs
                // pions
                else if(i == 6){
                    Pion pB = new Pion(i,j,"chessPieces/wP.svg");
                    board[i][j] = new Case(i,j, pB);
                }
                // autres pieces
                else if (i == 7){
                    if(j == 0 || j == 7){
                        Tour tB = new Tour(i,j, "chessPieces/wR.svg");
                        board[i][j] = new Case(i,j, tB);
                    }
                    else if(j == 1 || j == 6){
                        Cavalier cB = new Cavalier(i,j, "chessPieces/wN.svg");
                        board[i][j] = new Case(i,j, cB);
                    }
                    else if(j == 2 || j == 5){
                        Fou fB = new Fou(i,j, "chessPieces/wB.svg");
                        board[i][j] = new Case(i,j,fB);
                    }
                    else if(j == 3){
                        Reine reineB = new Reine(i,j, "chessPieces/wQ.svg");
                        board[i][j] = new Case(i,j,reineB);
                    }
                    else if(j == 4){
                        Roi roiB = new Roi(i,j, "chessPieces/wK.svg");
                        board[i][j] = new Case(i,j,roiB);
                    }
                }

                // cases vides
                else{
                    board[i][j] = new Case(i,j,null);
                }
            }
        }

        setChanged();
        notifyObservers();
    }

    public Piece getTypeCase(int ligne,int colonne){
        return board[ligne][colonne].getPiece();
    }


}

