package VueController;

import Model.*;
import Model.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Board extends JFrame implements Observer {
    JPanel[][] tab = new JPanel[8][8];
    final private Jeu jeu;

    public Board(Jeu jeu) {

        super("Jeu d'échecs");
        this.jeu = jeu;
        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        addWindowListener(l);
        setSize(800, 800);
        initBoard();
        setVisible(true);
    }

    public String getCheminImage(Case caseCourante, int ligne){
        String cheminImage = "chessPieces/";

        if (caseCourante.getPiece().getJoueur().getCouleur().equals("Noir")){
            cheminImage += "PiecesNoires/";
        }
        else if (caseCourante.getPiece().getJoueur().getCouleur().equals("Blanc")){
            cheminImage += "PiecesBlanches/";
        }

        if(caseCourante.getPiece() instanceof Pion){
            cheminImage += "P.png";
        }
        else if(caseCourante.getPiece() instanceof Fou){
            cheminImage += "B.png";
        }
        else if(caseCourante.getPiece() instanceof Tour){
            cheminImage += "R.png";
        }
        else if(caseCourante.getPiece() instanceof Roi){
            cheminImage += "K.png";
        }
        else if(caseCourante.getPiece() instanceof Reine){
            cheminImage += "Q.png";
        }
        else if(caseCourante.getPiece() instanceof Cavalier){
            cheminImage += "N.png";
        }

        return cheminImage;

    }

    public void initBoard() {

        //Jpanel of  the chess board
        JPanel pi = new JPanel(new GridLayout(8, 8));

        //Adding the board cases
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                // création de la case et sa couleur
                tab[i][j] = new JPanel();
                if ((i + j) % 2 == 0) {
                    tab[i][j].setBackground(Color.WHITE);
                } else {
                    tab[i][j].setBackground(Color.DARK_GRAY);
                }

                // création d'un label qui contiendra la pièce pour pour le panel
                JLabel pieceCase = new JLabel();
                if(jeu.getTypeCase(i,j) != null){
                    String cheminImage = getCheminImage(jeu.getBoard()[i][j], i);
                    ImageIcon icon = new ImageIcon(cheminImage);
                    pieceCase.setIcon(icon);
                }

                Point p = new Point(i,j);

                JPanel square = tab[i][j];
                square.addMouseListener(new MouseAdapter(){
                    public void mouseClicked(MouseEvent e){
                        jeu.gererPointSelectionne(p);
                    }
                });


                tab[i][j] = square;
                tab[i][j].add(pieceCase);
                pi.add(tab[i][j]);
            }
        }
        this.add(pi);
    }

    public void selectionPiece(){
        ArrayList<Case> casesPossibles = jeu.getCasesPossibles();

        int colonneSelect = jeu.getPointSelectionne().getY();
        int ligneSelect = jeu.getPointSelectionne().getX();
        if (tab[ligneSelect][colonneSelect].getBackground() != Color.RED) {
            tab[ligneSelect][colonneSelect].setBackground(Color.RED);
        }
        else {
            if ((ligneSelect + colonneSelect) % 2 == 0) {
                tab[ligneSelect][colonneSelect].setBackground(Color.WHITE);
            } else {
                tab[ligneSelect][colonneSelect].setBackground(Color.DARK_GRAY);
            }
        }

        for (Case c : casesPossibles) {
            tab[c.getLigne()][c.getColonne()].setBackground(Color.RED);
        }
    }

    public void refreshBoard(){
        for(int i=0; i<8; i++){
            for(int j=0; j<8;j++){
                if (tab[i][j].getBackground() == Color.RED) {
                    if ((i + j) % 2 == 0) {
                        tab[i][j].setBackground(Color.WHITE);
                    } else {
                        tab[i][j].setBackground(Color.DARK_GRAY);
                    }
                }
                tab[i][j].removeAll();
                JLabel pieceCase = new JLabel();
                if(jeu.getTypeCase(i,j) != null){
                    String cheminImage = getCheminImage(jeu.getBoard()[i][j], i);
                    ImageIcon icon = new ImageIcon(cheminImage);
                    pieceCase.setIcon(icon);
                    //System.out.println(jeu.getTypeCase(i,j).getImage());
                }
                tab[i][j].add(pieceCase);

                tab[i][j].revalidate();
                tab[i][j].repaint();
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(jeu.getPointSelectionne() != null){
            selectionPiece();
        }
        else{
            refreshBoard();
            if(jeu.isPartieGagnee()){
                JOptionPane.showMessageDialog(this,
                        "Échec et mat !","Fin de partie",JOptionPane.INFORMATION_MESSAGE);
            }
            else if(jeu.isPat()){
                JOptionPane.showMessageDialog(this,
                        "Pat !","Fin de partie",JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }
}


