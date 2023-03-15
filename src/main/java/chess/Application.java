package chess;

import chess.controller.ChessGameController;
import chess.ui.InputView;

public class Application {
    public static void main(String[] args) {
        if (!InputView.getStartCommand()) {
            return;
        }
        new ChessGameController().execute();
    }
}
