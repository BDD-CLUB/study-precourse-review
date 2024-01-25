package christmas.repository;

import christmas.domain.OrderSheet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ChristmasOrderMenuRepository {

    private static final Map<Long, OrderSheet> orderSheetDatabase = new HashMap<>();
    private static Long orderSheetId = 0L;

    public void saveOrderSheet(OrderSheet orderSheet) {
        orderSheetDatabase.put(orderSheetId, orderSheet);
        orderSheetId++;
    }

    public OrderSheet findOrderSheetById(Long id) {
        return orderSheetDatabase.get(id);
    }
}
