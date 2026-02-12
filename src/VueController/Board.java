package VueController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.*;

public class Board {
    JPanel[][] tab = new JPanel[8][8];

    public Board(Window chessWindow) {

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
        chessWindow.add(pi);
    }
}


