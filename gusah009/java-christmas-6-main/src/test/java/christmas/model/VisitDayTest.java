package christmas.model;

import christmas.exception.ChristmasException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDayTest {

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "32", "100", "10a", "a10", "a", "a1", "1a"})
    void should_throwException_whenInvalidInputDay(String invalidDay) {
        Assertions.assertThatThrownBy(() -> VisitDay.from(invalidDay))
                .isInstanceOf(ChristmasException.class)
                .hasMessageContaining("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }
}
