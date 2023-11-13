package christmas.repository;

import christmas.domain.OrderSheet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ChristmasOrderMenuRepository {

    private static final Map<Long, OrderSheet> orderSheetDatabase = new HashMap<>();
    private final Map<String, Integer> orderMenuDatabase = new HashMap<>();

    private static Long orderSheetId = 0L;

    public void saveOrderMenus(List<String> orderMenus, List<String> orderMenusCounts) {

        for (int i = 0; i < orderMenus.size(); i++) {
            try {
                orderMenuDatabase.put(orderMenus.get(i), Integer.parseInt(orderMenusCounts.get(i)));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }

    }

    public Set<Map.Entry<String, Integer>> findAllMenusAndCounts() {
        return orderMenuDatabase.entrySet();
    }

    public int findDessertCount() {
        Set<Map.Entry<String, Integer>> entries = orderMenuDatabase.entrySet();
        int result = 0;
        for (Map.Entry<String, Integer> entry : entries) {
            String key = entry.getKey();
            if (key.matches("초코케이크|아이스크림")) {
                result += entry.getValue();
            }
        }
        return result;
    }

    public int findMainCount() {
        Set<Map.Entry<String, Integer>> entries = orderMenuDatabase.entrySet();
        int result = 0;
        for (Map.Entry<String, Integer> entry : entries) {
            String key = entry.getKey();
            if (key.matches("티본스테이크|바비큐립|해산물파스타|크리스마스파스타")) {
                result += entry.getValue();
            }
        }
        return result;
    }

    public void saveOrderSheet(OrderSheet orderSheet) {
        orderSheetDatabase.put(orderSheetId, orderSheet);
        orderSheetId++;
    }

    public OrderSheet findOrderSheetById(Long id) {
        return orderSheetDatabase.get(id);
    }
}
