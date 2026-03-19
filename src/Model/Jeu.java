package Model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Jeu extends Observable implements Runnable{

    private int ligneChoise;
    private int colonneChoisie;
    private Piece piece;
    private Case[][] board = new Case[8][8];
    private Joueur joueurBlanc = new Joueur("Blanc",this);
    private Joueur joueurNoir = new Joueur("Noir",this);
    private Joueur joueurEnCours = joueurBlanc;
    private Boolean partieTerminee = false;
    private Coup nextC;
    private Point pointSelectionne = null;


    public Joueur joueurSuivant(){
        if(joueurEnCours.getCouleur().equals("Blanc")){
            joueurEnCours = joueurNoir;
        }
        else{
            joueurEnCours = joueurBlanc;
        }
        return joueurEnCours;
    }

    // Fonction servant à créer un coup
    // Si pas de case déjà enregistree: enregistrer cette case (elle servira de point de départ)
    // Si une case déjà enregistrée: créer un coup avec la case choisie en tant que point d'arrivee
    // et la case déjà présente en tant que point de départ
    public void gererPointSelectionne(Point p){
        if (board[p.getX()][p.getY()].getPiece() != null || pointSelectionne != null){
            if(pointSelectionne == null){
                pointSelectionne = p;
            }
            else{
                Coup c = new Coup(pointSelectionne,p);
                setCoup(c);
                pointSelectionne = null;
            }
            setChanged();
            notifyObservers();
        }
    }

    public Point getPointSelectionne(){
        return pointSelectionne;
    }

    public void initBoardModel(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(i == 1){
                    Pion pN = new Pion(i,j,"chessPieces/bP.png", joueurNoir);
                    joueurNoir.ajouterPiece(pN);
                    board[i][j] = new Case(i,j, pN);
                }
                // autre pieces
                else if(i == 0){
                    if(j == 0 || j == 7){
                        Tour tN = new Tour(i,j, "chessPieces/bR.png", joueurNoir);
                        joueurNoir.ajouterPiece(tN);
                        board[i][j] = new Case(i,j, tN);
                    }
                    else if(j == 1 || j == 6){
                        Cavalier cN = new Cavalier(i,j, "chessPieces/bN.png", joueurNoir, new SautCheval(null));
                        joueurNoir.ajouterPiece(cN);
                        board[i][j] = new Case(i,j, cN);
                    }
                    else if(j == 2 || j == 5){
                        Fou fN = new Fou(i,j, "chessPieces/bB.png", joueurNoir);
                        joueurNoir.ajouterPiece(fN);
                        board[i][j] = new Case(i,j,fN);
                    }
                    else if(j == 3){
                        Reine reineN = new Reine(i,j, "chessPieces/bQ.png", joueurNoir);
                        joueurNoir.ajouterPiece(reineN);
                        board[i][j] = new Case(i,j,reineN);
                    }
                    else if(j == 4){
                        Roi roiN = new Roi(i,j, "chessPieces/bK.png", joueurNoir);
                        joueurNoir.ajouterPiece(roiN);
                        board[i][j] = new Case(i,j,roiN);
                    }
                }

                // blancs
                // pions
                else if(i == 6){
                    Pion pB = new Pion(i,j,"chessPieces/wP.png", joueurBlanc);
                    joueurBlanc.ajouterPiece(pB);
                    board[i][j] = new Case(i,j, pB);
                }
                // autres pieces
                else if (i == 7){
                    if(j == 0 || j == 7){
                        Tour tB = new Tour(i,j, "chessPieces/wR.png", joueurBlanc);
                        joueurBlanc.ajouterPiece(tB);
                        board[i][j] = new Case(i,j, tB);
                    }
                    else if(j == 1 || j == 6){
                        Cavalier cB = new Cavalier(i,j, "chessPieces/wN.png", joueurBlanc,new SautCheval(null));
                        joueurBlanc.ajouterPiece(cB);
                        board[i][j] = new Case(i,j, cB);
                    }
                    else if(j == 2 || j == 5){
                        Fou fB = new Fou(i,j, "chessPieces/wB.png", joueurBlanc);
                        joueurBlanc.ajouterPiece(fB);
                        board[i][j] = new Case(i,j,fB);
                    }
                    else if(j == 3){
                        Reine reineB = new Reine(i,j, "chessPieces/wQ.png", joueurBlanc);
                        joueurBlanc.ajouterPiece(reineB);
                        board[i][j] = new Case(i,j,reineB);
                    }
                    else if(j == 4){
                        Roi roiB = new Roi(i,j, "chessPieces/wK.png", joueurBlanc);
                        joueurBlanc.ajouterPiece(roiB);
                        board[i][j] = new Case(i,j,roiB);
                    }
                }

                // cases vides
                else{
                    board[i][j] = new Case(i,j,null);
                }
            }
        }
    }

    public Piece getTypeCase(int ligne,int colonne){
        return board[ligne][colonne].getPiece();
    }

    public Coup getNextCoup(){
        return nextC;
    }

    public void setCoup(Coup c){
        synchronized (this) {
         nextC = c;
         this.notify();
        }
    }
    public void appliquerCoup(Coup c){
        Case caseDepart = board[c.getDepart().getX()][c.getDepart().getY()];
        Case caseArrivee = board[c.getArrivee().getX()][c.getArrivee().getY()];
        if(caseDepart.getPiece() != null){
            Piece pieceABouger = caseDepart.getPiece();
            caseDepart.setPiece(null);
            caseArrivee.setPiece(pieceABouger);
        }
        setChanged();
        notifyObservers();
    }

    public void resetNextC(){
        nextC = null;
    }

    public Case[][] getBoard(){
        return board;
    }

    public void jouerPartie() throws InterruptedException {

        while(!partieTerminee){
            Coup c = joueurEnCours.getCoup();
            appliquerCoup(c);
            joueurSuivant();

        }
    }
    public void run(){
        try {
            jouerPartie();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



}

