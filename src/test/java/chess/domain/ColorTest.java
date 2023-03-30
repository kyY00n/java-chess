package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.model.player.Color;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

final class ColorTest {

    @ParameterizedTest(name = "{0} 색상은 {1} 색상으로 반환한다.")
    @CsvSource(value = {
            "WHITE,BLACK",
            "BLACK,WHITE"
    })
    void changeColor(final Color actual, final Color expected) {
        assertThat(Color.changeColor(actual)).isEqualTo(expected);
    }

}
