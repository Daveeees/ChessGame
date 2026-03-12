package VueController;

import Model.Case;
import Model.Jeu;
import Model.Coup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

public class Board extends JFrame implements Observer {
    JPanel[][] tab = new JPanel[8][8];
    private Jeu jeu;

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

    public void initBoard() {
        // init board model
        jeu.initBoardModel();

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
                    ImageIcon icon = new ImageIcon(jeu.getTypeCase(i,j).getImage());
                    pieceCase.setIcon(icon);
                    System.out.println(jeu.getTypeCase(i,j).getImage());
                }

                Coup coup = new Coup(i,j);

                JPanel square = tab[i][j];
                square.addMouseListener(new MouseAdapter(){
                    public void mouseClicked(MouseEvent e){
                        jeu.communiquerCoup(coup);
                    }
                });


                tab[i][j] = square;
                tab[i][j].add(pieceCase);
                pi.add(tab[i][j]);
            }
        }
        this.add(pi);
    }

    public void refreshRouge(){
        int colSelect = jeu.getColonneChoisie();
        int ligneSelect = jeu.getLigneChoisie();

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if (j == colSelect && i == ligneSelect){
                    if (tab[i][j].getBackground() != Color.RED) {
                        tab[i][j].setBackground(Color.RED);
                    }
                    else {
                        if ((i + j) % 2 == 0) {
                            tab[i][j].setBackground(Color.WHITE);
                        } else {
                            tab[i][j].setBackground(Color.DARK_GRAY);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
       refreshRouge();
    }
}


