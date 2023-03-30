package chess.domain.factory;

import chess.domain.model.player.Color;
import chess.domain.model.piece.Piece;
import chess.domain.model.piece.Pieces;
import chess.domain.model.player.Player;
import chess.domain.model.player.Players;
import chess.domain.dao.PieceDao;
import chess.domain.dao.TurnDao;
import java.util.List;

public class InitPlayersFactory {

    public static Players initializeChessBoard(PieceDao pieceDao, TurnDao turnDao) {

        Pieces whitePieces = getDbWhitePieces(pieceDao);
        Pieces blackPieces = getDbBlackPieces(pieceDao);

        Player whitePlayer = Player.fromWhitePlayer(whitePieces);
        Player blackPlayer = Player.fromBlackPlayer(blackPieces);

        return Players.of(whitePlayer, blackPlayer, turnDao);
    }

    private static Pieces getDbWhitePieces(PieceDao dao) {
        List<Piece> dbWhitePieces = dao.findPieceByColor(Color.WHITE);
        if (dbWhitePieces.isEmpty()) {
            Pieces whitePieces = Pieces.createWhitePieces();
            insertAll(dao, whitePieces, Color.WHITE);
            return whitePieces;
        }
        return Pieces.from(dbWhitePieces);
    }

    private static Pieces getDbBlackPieces(PieceDao dao) {
        List<Piece> dbBlackPieces = dao.findPieceByColor(Color.BLACK);
        if (dbBlackPieces.isEmpty()) {
            Pieces blackPieces = Pieces.createBlackPieces();
            insertAll(dao, blackPieces, Color.BLACK);
            return blackPieces;
        }
        return Pieces.from(dbBlackPieces);
    }

    private static void insertAll(PieceDao dao, Pieces pieces, Color color) {
        for (Piece piece : pieces.getPieces()) {
            dao.create(piece, color);
        }
    }
}
