package christmas.view;

import static christmas.utils.ErrorMessage.INVALID_ORDER_ERROR;
import static christmas.utils.ErrorMessage.OUT_OF_RANGE_DATE_ERROR;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.DecemberDate;
import christmas.domain.Menu;
import christmas.utils.ChristmasException;
import christmas.utils.ErrorMessage;
import java.util.HashMap;
import java.util.List;

public class InputView {

    private static final String VISIT_DATE_INPUT_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String MENU_INPUT_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public DecemberDate inputDate() {
        System.out.println(VISIT_DATE_INPUT_MESSAGE);
        while (true) {
            try {
                int visitDate = parseDigit(Console.readLine(), OUT_OF_RANGE_DATE_ERROR);
                return new DecemberDate(visitDate);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public HashMap<Menu, Integer> inputMenu() {
        System.out.println(MENU_INPUT_MESSAGE);
        while (true) {
            try {
                List<String> inputs = List.of(Console.readLine().split(","));
                List<Menu> menus = inputs.stream().map(this::getMenus).toList();
                List<Integer> amounts = inputs.stream().map(this::getAmounts).toList();
                isDuplicated(menus);
                return creatMenuMap(menus, amounts);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Menu getMenus(String input) {
        String[] menuAndAmount = input.split("-");
        ValidateFormat(menuAndAmount.length);
        return Menu.findMenu(menuAndAmount[0].trim());
    }

    private Integer getAmounts(String input) {
        String[] menuAndAmount = input.split("-");
        int amount = parseDigit(menuAndAmount[1],INVALID_ORDER_ERROR);
        ValidateAmount(amount);
        return amount;
    }

    private void isDuplicated(List<Menu> menus) {
        if (menus.size() != menus.stream().distinct().count()) {
            throw new ChristmasException(INVALID_ORDER_ERROR);
        }
    }

    private HashMap<Menu, Integer> creatMenuMap(List<Menu> menus, List<Integer> amounts) {
        HashMap<Menu, Integer> orderedMenu = new HashMap<>();
        for (int i = 0; i < menus.size(); i++) {
            orderedMenu.put(menus.get(i), amounts.get(i));
        }
        return orderedMenu;
    }

    private void ValidateAmount(int amount) {
        if (amount < 1) {
            throw new ChristmasException(INVALID_ORDER_ERROR);
        }
    }

    private void ValidateFormat(int formatLength) {
        if (formatLength != 2) {
            throw new ChristmasException(INVALID_ORDER_ERROR);
        }
    }

    private int parseDigit(String input, ErrorMessage message) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new ChristmasException(message);
        }
    }
}
