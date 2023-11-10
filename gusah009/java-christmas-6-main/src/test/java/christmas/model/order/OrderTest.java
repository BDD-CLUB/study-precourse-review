package christmas.model.order;

import christmas.entity.menu.Menu;
import christmas.exception.ChristmasException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


class OrderTest {

    @ParameterizedTest
    @ValueSource(strings = {"정현모-1", "해산물파스타-2,레드와인-1,정현모-1", "해산물파스타-2,레드와인-1,정현모-1,아이스크림-1"})
    void should_throwException_when_notExistMenu(String notExistMenus) {
        assertThatThrownBy(() -> Order.from(notExistMenus))
                .isInstanceOf(ChristmasException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-21", "해산물파스타-11,레드와인-10", "해산물파스타-6,레드와인-5,아이스크림-5,티본스테이크-5", "해산물파스타-0"})
    void should_throwException_when_invalidTotalCountMenus(String menus) {
        assertThatThrownBy(() -> Order.from(menus))
                .isInstanceOf(ChristmasException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-1,해산물파스타-1", "해산물파스타-1,레드와인-10,해산물파스타-2", "해산물파스타-1,레드와인-5,아이스크림-5,해산물파스타-5"})
    void should_throwException_when_duplicateMenus(String menus) {
        assertThatThrownBy(() -> Order.from(menus))
                .isInstanceOf(ChristmasException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타 - 21", "해산물파스타- 11", "해산물파스타 -6, 레드와인-5,아이스크림-5", "해산물파스타,0,티본스테이크,5"})
    void should_throwException_when_invalidFormatMenus(String menus) {
        assertThatThrownBy(() -> Order.from(menus))
                .isInstanceOf(ChristmasException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"레드와인-1", "제로콜라-1,레드와인-10", "제로콜라-1,레드와인-5,샴페인-5"})
    void should_throwException_when_onlyDrinkMenus(String menus) {
        assertThatThrownBy(() -> Order.from(menus))
                .isInstanceOf(ChristmasException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"타파스-1,제로콜라-1", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1", "해산물파스타-2,레드와인-1,초코케이크-1", "초코케이크-1", "초코케이크-20"})
    void should_getSuccess_when_validInputMenus(String validInputMenus) {
        assertDoesNotThrow(() -> Order.from(validInputMenus));
    }

    @Test
    void should_getTotalPriceCorrectly() {
        Order order = new Order(Map.of(
                Menu.from("해산물파스타"), Order.MenuCount.from("2"),
                Menu.from("레드와인"), Order.MenuCount.from("1"),
                Menu.from("초코케이크"), Order.MenuCount.from("1")));
        assertThat(order.getTotalPrice()).isEqualTo(70000 + 60000 + 15000); // 145000
    }
}
