import VueController.Board;
import VueController.Window;

void main() {

    Window chessWindow = new  Window();

    Board board = new Board(chessWindow);

    chessWindow.setVisible(true);
}
