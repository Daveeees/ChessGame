import VueController.Board;
import VueController.Window;

public class Main {
    public static void main(String[] args) {
        Window chessWindow = new Window();
        Board board = new Board(chessWindow);
        chessWindow.setVisible(true);
    }
}