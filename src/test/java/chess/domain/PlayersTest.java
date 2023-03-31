package chess.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.domain.dao.TurnDaoImpl;
import chess.domain.model.player.Color;
import chess.domain.model.piece.Pieces;
import chess.domain.model.player.Player;
import chess.domain.model.player.Players;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    Pieces whitePieces;
    Players players;

    @BeforeEach
    void setUp() {
        Pieces pieces = new Pieces();
        whitePieces = pieces.createWhitePieces();
        Pieces blackPieces = pieces.createBlackPieces();
        Player whitePlayer = Player.fromWhitePlayer(whitePieces);
        Player blackPlayer = Player.fromBlackPlayer(blackPieces);
        players = Players.of(whitePlayer, blackPlayer, new TurnDaoImpl().getCurrentTurn());
    }

    @Test
    @DisplayName("입력받은 색에 해당하는 pieces를 반환한다.")
    void getPiecesByColor() {
        // when, then
        Assertions.assertThat(players.getPiecesByColor(Color.WHITE).size())
                .isEqualTo(whitePieces.getPieces().size());
    }

    @Test
    @DisplayName("모든 킹이 살아 있지 않다면 false를 반환한다.")
    void everyKingAlive() {
        // when, then
        Assertions.assertThat(players.isEveryKingAlive())
                .isFalse();
    }

    @Test
    @DisplayName("킹이 모두 살아있는 상태면 예외를 발생시킨다.")
    void getWinnerColorName() {
        // when, then
        assertThrows(IllegalArgumentException.class, () ->
                players.getWinnerColorName());
    }

    @Test
    @DisplayName("현재 플레이어의 점수를 계산한다.")
    void calculateScore() {
        // when, then
        Assertions.assertThat(players.calculateScore().getValue())
                .isEqualTo(38);
    }
}
