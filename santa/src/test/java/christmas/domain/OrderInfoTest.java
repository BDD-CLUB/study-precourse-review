package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class OrderInfoTest {

    @Nested
    @DisplayName("주문 예외 처리")
    class OrderInfoValidateTest {
        HashMap<Menu, Integer> menu = new HashMap<>();
        DecemberDate date = new DecemberDate(5);

        @Test
        void 음료만_주문할_수_없다() {
            menu.put(Menu.ZERO_COKE, 2);
            menu.put(Menu.CHAMPAGNE, 3);
            assertThatThrownBy(() -> new OrderInfo(date, menu)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 한번에_20개를_초과해_주문할_수_없다() {
            menu.put(Menu.T_BONE_STEAK,21);
            assertThatThrownBy(() -> new OrderInfo(date,menu)).isInstanceOf(IllegalArgumentException.class);
        }
    }

}