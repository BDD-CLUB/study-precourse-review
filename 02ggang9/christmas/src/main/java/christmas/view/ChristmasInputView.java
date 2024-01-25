package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.OrderSheet;
import christmas.domain.menu.OrderMenu;
import christmas.global.InputMessage;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import static christmas.global.ErrorMessage.*;
import static christmas.global.InputMessage.*;

public class ChristmasInputView {

    public int getUserDateOfVisit() {
        System.out.println(ORDER_DATE_OF_VISIT.getMessage());

        while (true) {
            String expectedVisitDate = Console.readLine();

            try {
                return checkDateOfVisitValidation(expectedVisitDate);
            } catch (IllegalArgumentException e) {
                System.out.println(INVALID_DATE_OF_VISIT.getMessage());
            }

        }
    }

    private int checkDateOfVisitValidation(String expectedVisitDate) {
        String regex = "(?:[1-9]|1[0-9]|2[0-9]|3[01])";
        Pattern pattern = Pattern.compile(regex);

        if (pattern.matcher(expectedVisitDate).matches()) {
            return Integer.parseInt(expectedVisitDate);
        }

        throw new IllegalArgumentException();
    }

    public OrderSheet getUserOrderMenu(int dateOfVisit) {
        System.out.println(ORDER_MENU_AND_COUNT.getMessage());

        while (true) {
            try {
                String menuAndCount = Console.readLine();

                List<String> menus = getMenus(menuAndCount);
                List<Integer> counts = getCounts(menuAndCount);
                checkValidation(menus, counts);

                return new OrderSheet(menus, counts, dateOfVisit);
            } catch (IllegalArgumentException e) {
                System.out.println(INVALID_ORDER.getMessage());
            }
        }
    }

    private void checkValidation(List<String> menus, List<Integer> counts) {
        checkOrderMenuIsValid(menus);
        checkOrderCountsIsValid(counts);
        checkDuplicateMenu(menus);
    }

    private void checkDuplicateMenu(List<String> menus) {
        if (menus.size() != Set.of(menus).size()) {
            return;
        }

        throw new IllegalArgumentException();
    }

    private void checkOrderCountsIsValid(List<Integer> counts) {
        counts.forEach(this::checkCountValid);
    }

    private void checkCountValid(Integer count) {
        if (count > 0) {
            return;
        }

        throw new IllegalArgumentException();
    }

    private List<String> getMenus(String menuAndCount) {
        try {
            return Arrays.stream(menuAndCount.split(","))
                    .toList()
                    .stream()
                    .map(s -> s.split("-")[0])
                    .toList();
        } catch (RuntimeException e) {
            throw new IllegalArgumentException();
        }
    }

    private List<Integer> getCounts(String menuAndCount) {
        try {
            return Arrays.stream(menuAndCount.split(","))
                    .toList()
                    .stream()
                    .map(s -> s.split("-")[1])
                    .map(Integer::parseInt)
                    .toList();
        } catch (RuntimeException e) {
            throw new IllegalArgumentException();
        }
    }

    private void checkOrderMenuIsValid(List<String> menus) {
        menus.forEach(OrderMenu::fromMenuName);
    }
}
