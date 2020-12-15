package bowling.model.state.finishedState;

import bowling.model.Pins;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SpareTest {
    @Test
    void of_정상() {
        assertThat(Spare.of(Pins.from(0), Pins.from(10)));
    }

    @ParameterizedTest
    @MethodSource("spareParams")
    void of_Spare의_조건에_맞지_않는_경우(Pins previous, Pins totalScore) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Spare.of(previous, totalScore))
                .withMessage("Spare의 조건에 맞지 않습니다.");
    }

    @Test
    void isFinished() {
        assertThat(Spare.of(Pins.from(8), Pins.from(10)).isFinished()).isTrue();
    }

    @Test
    void bowling() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Spare.of(Pins.from(8), Pins.from(10)).bowling(1))
                .withMessage("해당 프레임에서는 더 이상 던질 수 없습니다.");
    }

    private static Stream<Arguments> spareParams() {
        return Stream.of(
                Arguments.of(Pins.from(10), Pins.from(10)),
                Arguments.of(Pins.from(1), Pins.from(9))
        );
    }
}