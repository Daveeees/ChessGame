package Model;

import java.util.ArrayList;
import java.util.Observable;

public class Jeu extends Observable implements Runnable{

    private int ligneChoise;
    private int colonneChoisie;
    private Piece piece;
    private Case[][] board = new Case[8][8];
    private Joueur joueurBlanc = new Joueur("Blanc",this);
    private Joueur joueurNoir = new Joueur("Noir",this);
    private Joueur joueurEnCours = joueurBlanc;
    private Boolean partieGagnee = false;
    private Coup nextC;
    private Point pointSelectionne = null;
    private ArrayList<Case> casesPossibles = null;
    private Boolean pat = false;

    public void setCasesPossibles() {
        Piece pieceSelectionnee = board[pointSelectionne.getX()][pointSelectionne.getY()].getPiece();
        casesPossibles = pieceSelectionnee.getCasesAccessibles(pointSelectionne.getX(),pointSelectionne.getY(),getBoard());
        casesPossibles.removeIf(c -> !joueurEnCours.coupValide(new Coup(pointSelectionne, new Point(c.getLigne(), c.getColonne())), this.getBoard()));

    }

    public ArrayList<Case> getCasesPossibles() {
        return casesPossibles;
    }

    public boolean isPartieGagnee() {
        return partieGagnee;
    }

    public boolean isPat(){
        return pat;
    }
    public Joueur joueurSuivant(){
        if(joueurEnCours.getCouleur().equals("Blanc")){
            joueurEnCours = joueurNoir;
            if(joueurBlanc.getDernierCoup() != null){
                joueurEnCours.setDernierCoupAdverse(joueurBlanc.getDernierCoup());
            }

        }
        else{
            joueurEnCours = joueurBlanc;
            if(joueurNoir.getDernierCoup() != null){
                joueurEnCours.setDernierCoupAdverse(joueurNoir.getDernierCoup());
            }
        }
        return joueurEnCours;
    }

    public Joueur getJoueurSuivant(){
        if(joueurEnCours.getCouleur().equals("Blanc")){
            return joueurNoir;
        }
        else{
            return joueurBlanc;
        }
    }

    // Fonction servant à créer un coup
    // Si pas de case déjà enregistree: enregistrer cette case (elle servira de point de départ)
    // Si une case déjà enregistrée: créer un coup avec la case choisie en tant que point d'arrivee
    // et la case déjà présente en tant que point de départ
    public void gererPointSelectionne(Point p){
        if (board[p.getX()][p.getY()].getPiece() != null || pointSelectionne != null){
            if(pointSelectionne == null){
                pointSelectionne = p;
                setCasesPossibles();
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
                    Pion pN = new Pion(i,j, joueurNoir);
                    joueurNoir.ajouterPiece(pN);
                    board[i][j] = new Case(i,j, pN);
                }
                // autre pieces
                else if(i == 0){
                    if(j == 0 || j == 7){
                        Tour tN = new Tour(i,j, joueurNoir);
                        joueurNoir.ajouterPiece(tN);
                        board[i][j] = new Case(i,j, tN);
                    }
                    else if(j == 1 || j == 6){
                        Cavalier cN = new Cavalier(i,j, joueurNoir);
                        joueurNoir.ajouterPiece(cN);
                        board[i][j] = new Case(i,j, cN);
                    }
                    else if(j == 2 || j == 5){
                        Fou fN = new Fou(i,j, joueurNoir);
                        joueurNoir.ajouterPiece(fN);
                        board[i][j] = new Case(i,j,fN);
                    }
                    else if(j == 3){
                        Reine reineN = new Reine(i,j, joueurNoir);
                        joueurNoir.ajouterPiece(reineN);
                        board[i][j] = new Case(i,j,reineN);
                    }
                    else if(j == 4){
                        Roi roiN = new Roi(i,j, joueurNoir);
                        joueurNoir.ajouterPiece(roiN);
                        board[i][j] = new Case(i,j,roiN);
                    }
                }

                // blancs
                // pions
                else if(i == 6){
                    Pion pB = new Pion(i,j, joueurBlanc);
                    joueurBlanc.ajouterPiece(pB);
                    board[i][j] = new Case(i,j, pB);
                }
                // autres pieces
                else if (i == 7){
                    if(j == 0 || j == 7){
                        Tour tB = new Tour(i,j, joueurBlanc);
                        joueurBlanc.ajouterPiece(tB);
                        board[i][j] = new Case(i,j, tB);
                    }
                    else if(j == 1 || j == 6){
                        Cavalier cB = new Cavalier(i,j, joueurBlanc);
                        joueurBlanc.ajouterPiece(cB);
                        board[i][j] = new Case(i,j, cB);
                    }
                    else if(j == 2 || j == 5){
                        Fou fB = new Fou(i,j,joueurBlanc);
                        joueurBlanc.ajouterPiece(fB);
                        board[i][j] = new Case(i,j,fB);
                    }
                    else if(j == 3){
                        Reine reineB = new Reine(i,j, joueurBlanc);
                        joueurBlanc.ajouterPiece(reineB);
                        board[i][j] = new Case(i,j,reineB);
                    }
                    else if(j == 4){
                        Roi roiB = new Roi(i,j, joueurBlanc);
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
        int dx = c.getDepart().getX();
        int dy = c.getDepart().getY();
        int ax = c.getArrivee().getX();
        int ay = c.getArrivee().getY();
        if(board[dx][dy].getPiece() != null){
            Piece pieceABouger = board[dx][dy].getPiece();
            boolean estPion = pieceABouger instanceof Pion;
            boolean estDiagonal = (dy != ay);
            boolean caseArriveeVide = (board[ax][ay].getPiece() == null);
            if (estPion && estDiagonal && caseArriveeVide) {
                Piece pionCapture = board[dx][ay].getPiece();
                if (pionCapture != null) {
                    pionCapture.getJoueur().retirerPiece(pionCapture);
                    board[dx][ay].setPiece(null);
                }
            }

            board[dx][dy].setPiece(null);
            board[ax][ay].setPiece(pieceABouger);
        }
        joueurEnCours.setDernierCoup(c);
        setChanged();
        notifyObservers();
    }

    public void resetNextC(){
        nextC = null;
    }

    public Case[][] getBoard(){
        return this.board;
    }

    public void jouerPartie() throws InterruptedException {

        while(!partieGagnee && !pat){
            Coup c = joueurEnCours.getCoup();
            appliquerCoup(c);
            joueurSuivant();
            if(joueurEnCours.estEnEchecEtMat(board)){
                partieGagnee = true;
            }
            else if(joueurEnCours.estEnPat()){
                pat = true;
            }
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

