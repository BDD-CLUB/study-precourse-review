package christmas.entity.discount;

import christmas.model.VisitDay;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static christmas.entity.discount.DiscountPolicy.CHRISTMAS_DISCOUNT_POLICY;
import static christmas.entity.discount.DiscountPolicy.SPECIAL_DISCOUNT_POLICY;
import static christmas.entity.discount.DiscountPolicy.WEEKDAY_DISCOUNT_POLICY;
import static christmas.entity.discount.DiscountPolicy.WEEKEND_DISCOUNT_POLICY;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DiscountPolicyTest {

    @ParameterizedTest
    @MethodSource
    void should_discountable_when_inputVisitDay(DiscountPolicy discountPolicy, int visitDay, boolean expected) {
        boolean isDiscountable = discountPolicy.isDiscountable(VisitDay.from(String.valueOf(visitDay)));
        assertEquals(expected, isDiscountable);
    }

    static Stream<Arguments> should_discountable_when_inputVisitDay() {
        return Stream.of(
                Arguments.arguments(CHRISTMAS_DISCOUNT_POLICY, 1, true),
                Arguments.arguments(CHRISTMAS_DISCOUNT_POLICY, 25, true),
                Arguments.arguments(SPECIAL_DISCOUNT_POLICY, 10, true),
                Arguments.arguments(SPECIAL_DISCOUNT_POLICY, 25, true),
                Arguments.arguments(SPECIAL_DISCOUNT_POLICY, 31, true),
                Arguments.arguments(WEEKDAY_DISCOUNT_POLICY, 4, true),
                Arguments.arguments(WEEKDAY_DISCOUNT_POLICY, 13, true),
                Arguments.arguments(WEEKDAY_DISCOUNT_POLICY, 28, true),
                Arguments.arguments(WEEKDAY_DISCOUNT_POLICY, 31, true),
                Arguments.arguments(WEEKEND_DISCOUNT_POLICY, 1, true),
                Arguments.arguments(WEEKEND_DISCOUNT_POLICY, 22, true),
                Arguments.arguments(WEEKEND_DISCOUNT_POLICY, 30, true),
                Arguments.arguments(CHRISTMAS_DISCOUNT_POLICY, 26, false),
                Arguments.arguments(CHRISTMAS_DISCOUNT_POLICY, 31, false),
                Arguments.arguments(SPECIAL_DISCOUNT_POLICY, 18, false),
                Arguments.arguments(SPECIAL_DISCOUNT_POLICY, 20, false),
                Arguments.arguments(SPECIAL_DISCOUNT_POLICY, 30, false),
                Arguments.arguments(WEEKDAY_DISCOUNT_POLICY, 1, false),
                Arguments.arguments(WEEKDAY_DISCOUNT_POLICY, 22, false),
                Arguments.arguments(WEEKDAY_DISCOUNT_POLICY, 30, false),
                Arguments.arguments(WEEKEND_DISCOUNT_POLICY, 4, false),
                Arguments.arguments(WEEKEND_DISCOUNT_POLICY, 13, false),
                Arguments.arguments(WEEKEND_DISCOUNT_POLICY, 28, false),
                Arguments.arguments(WEEKEND_DISCOUNT_POLICY, 31, false)
        );
    }
}
