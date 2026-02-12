package VueController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame {
    //création de la fenêtre
    public Window() {
        super("Jeu d'échecs");
        setSize(400, 400);
        //listener pour fermer la fenêtre
        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        addWindowListener(l);
    }
}