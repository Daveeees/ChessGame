package VueController;

import javax.swing.*;
import java.awt.*;

public class Board {
    JPanel[][] tab = new JPanel[8][8];

    public Board(Window chessWindow) {

        // JPanel of the whole Window
        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());

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

                pi.add(tab[i][j]);
            }
        }
        jp.add(pi);
        chessWindow.add(jp);
    }
}
