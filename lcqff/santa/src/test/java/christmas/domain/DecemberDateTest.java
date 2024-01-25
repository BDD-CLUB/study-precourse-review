package christmas.domain;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.InputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DecemberDateTest {

    @Nested
    @DisplayName("날짜 입력 예외 처리")
    class DateValidationTest {
        @ValueSource(strings = {"-10", "0", "99"})
        @ParameterizedTest
        void 날짜는_1에서_31_범위여야_한다(Integer input) {
            assertThatThrownBy(() -> new DecemberDate(input))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}