import Model.Jeu;
import VueController.Board;

public class Main {
    public static void main(String[] args) {
        Jeu j = new Jeu();
        j.initBoardModel();
        Board board = new Board(j);
        j.addObserver(board);

        Thread t = new Thread(j);
        t.start();
    }
}