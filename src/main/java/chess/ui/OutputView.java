package chess.ui;

import chess.domain.Pieces;
import chess.domain.dto.PiecesResponse;

import static chess.domain.Pieces.FIRST_FILE_OF_BLACK;
import static chess.domain.Pieces.FIRST_FILE_OF_WHITE;
import static chess.domain.Pieces.FIRST_RANK;
import static chess.domain.Pieces.LAST_RANK;

public final class OutputView {

    private static final String START_CHESS_GAME = "체스 게임을 시작합니다.";
    private static final String INPUT_GAME_START_COMMAND = "게임 시작은 start, 종료는 end 명령을 입력하세요.";
    private static final String EMPTY_POSITION = ".";

    public static void printStartGame() {
        System.out.println(START_CHESS_GAME);
        System.out.println(INPUT_GAME_START_COMMAND);
    }

    public static void printInitializedChessBoard(PiecesResponse piecesResponse) {
        for (int rank = FIRST_FILE_OF_BLACK; rank >= FIRST_FILE_OF_WHITE; rank--) {
            printChessBoardByRankAndFile(piecesResponse, rank);
        }
        System.out.println(System.lineSeparator() + "end");
    }

    private static void printChessBoardByRankAndFile(PiecesResponse piecesResponse, int rank) {
        for (int file = LAST_RANK; file >= FIRST_RANK; file--) {
            printChessBoard(piecesResponse, rank, file);
        }
        System.out.println();
    }

    private static void printChessBoard(PiecesResponse piecesResponse, int rank, int file) {
        if (piecesResponse.isExistPosition(rank, file)) {
            System.out.print(piecesResponse.findNameByRankAndFile(rank, file));
            return;
        }
        System.out.print(EMPTY_POSITION);
    }
}
