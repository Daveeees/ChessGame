package VueController;

import Model.Jeu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

public class Board extends JFrame implements Observer {
    JPanel[][] tab = new JPanel[8][8];

    public Board(Jeu jeu) {

        super("Jeu d'échecs");
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

        //Jpanel of  the chess board
        JPanel pi = new JPanel(new GridLayout(8, 8));

        //Adding the board cases
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tab[i][j] = new JPanel();

                if ((i + j) % 2 == 0) {
                    tab[i][j].setBackground(Color.WHITE);
                } else {
                    tab[i][j].setBackground(Color.BLACK);
                }

                JPanel square = tab[i][j];
                square.addMouseListener(new MouseAdapter(){
                    public void mouseClicked(MouseEvent e){
                        square.setBackground(Color.RED);
                    }
                });

                tab[i][j] = square;
                pi.add(tab[i][j]);
            }
        }
        this.add(pi);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}


