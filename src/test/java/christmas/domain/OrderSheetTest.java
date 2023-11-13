package christmas.domain;

import christmas.domain.menu.OrderMenu;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderSheetTest {

    @Test
    @DisplayName("생성자 호출 시 주문 메뉴가 저장되어야 한다.")
    void 생성자_호출_시_주문_메뉴가_저장되어야_한다() {
        List<String> orderMenus = List.of("타파스", "티본스테이크");
        List<Integer> orderCounts = List.of(3, 4);

        OrderSheet orderSheet = new OrderSheet(orderMenus, orderCounts, 3);
        Map<OrderMenu, Integer> findOrders = orderSheet.getOrders();

        assertThat(findOrders.containsKey(OrderMenu.fromMenuName(orderMenus.get(0)))).isTrue();
        assertThat(findOrders.containsKey(OrderMenu.fromMenuName(orderMenus.get(1)))).isTrue();
        assertThat(findOrders.get(OrderMenu.fromMenuName(orderMenus.get(0)))).isEqualTo(3);
        assertThat(findOrders.get(OrderMenu.fromMenuName(orderMenus.get(1)))).isEqualTo(4);
    }

    @Test
    @DisplayName("전체 요금 계산을 성공해야 한다.")
    void 전체_요금_계산을_성공해야_한다() {
        List<String> orderMenus = List.of("타파스", "티본스테이크");
        List<Integer> orderCounts = List.of(3, 4);

        OrderSheet orderSheet = new OrderSheet(orderMenus, orderCounts, 3);
        Map<OrderMenu, Integer> findOrders = orderSheet.getOrders();
        int expectedSum = findOrders.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().calculatePrice(entry.getValue()))
                .sum();

        assertThat(orderSheet.calculateTotalPrice()).isEqualTo(expectedSum);
    }

}
