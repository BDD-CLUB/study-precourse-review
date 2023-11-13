package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import christmas.model.Menu;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuTest {

    @Test
    void 메뉴판에_없는_메뉴는_주문할_수_없다() {
         String nonExistingMenu = "떡볶이";
        assertThatThrownBy(() -> Menu.findMenu(nonExistingMenu))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ValueSource(strings = {"양송이수프", "타파스", "시저샐러드", "티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타", "초코케이크", "아이스크림", "제로콜라", "레드와인", "샴페인"})
    @ParameterizedTest
    void 메뉴판에_있는_메뉴는_주문_가능하다(String menu) {
        assertDoesNotThrow(()-> Menu.findMenu(menu));
    }

}