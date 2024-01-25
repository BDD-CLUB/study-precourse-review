package christmas.entity.discount;

import christmas.model.VisitDay;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DiscountPolicyTest {

    @ParameterizedTest
    @MethodSource
    void should_discountable_when_inputVisitDay(DiscountPolicy discountPolicy, boolean expected) {
        boolean isDiscountable = discountPolicy.isDiscountable();
        assertEquals(expected, isDiscountable);
    }

    static Stream<Arguments> should_discountable_when_inputVisitDay() {
        return Stream.of(
                Arguments.arguments(ChristmasDiscountPolicy.from(VisitDay.from("1")), true),
                Arguments.arguments(ChristmasDiscountPolicy.from(VisitDay.from("25")), true),
                Arguments.arguments(SpecialDiscountPolicy.from(VisitDay.from("10")), true),
                Arguments.arguments(SpecialDiscountPolicy.from(VisitDay.from("25")), true),
                Arguments.arguments(SpecialDiscountPolicy.from(VisitDay.from("31")), true),
                Arguments.arguments(WeekdayDiscountPolicy.from(VisitDay.from("4"), anyInt()), true),
                Arguments.arguments(WeekdayDiscountPolicy.from(VisitDay.from("13"), anyInt()), true),
                Arguments.arguments(WeekdayDiscountPolicy.from(VisitDay.from("28"), anyInt()), true),
                Arguments.arguments(WeekdayDiscountPolicy.from(VisitDay.from("31"), anyInt()), true),
                Arguments.arguments(WeekendDiscountPolicy.from(VisitDay.from("1"), anyInt()), true),
                Arguments.arguments(WeekendDiscountPolicy.from(VisitDay.from("22"), anyInt()), true),
                Arguments.arguments(WeekendDiscountPolicy.from(VisitDay.from("30"), anyInt()), true),
                Arguments.arguments(ChristmasDiscountPolicy.from(VisitDay.from("26")), false),
                Arguments.arguments(ChristmasDiscountPolicy.from(VisitDay.from("31")), false),
                Arguments.arguments(SpecialDiscountPolicy.from(VisitDay.from("18")), false),
                Arguments.arguments(SpecialDiscountPolicy.from(VisitDay.from("20")), false),
                Arguments.arguments(SpecialDiscountPolicy.from(VisitDay.from("30")), false),
                Arguments.arguments(WeekdayDiscountPolicy.from(VisitDay.from("1"), anyInt()), false),
                Arguments.arguments(WeekdayDiscountPolicy.from(VisitDay.from("22"), anyInt()), false),
                Arguments.arguments(WeekdayDiscountPolicy.from(VisitDay.from("30"), anyInt()), false),
                Arguments.arguments(WeekendDiscountPolicy.from(VisitDay.from("4"), anyInt()), false),
                Arguments.arguments(WeekendDiscountPolicy.from(VisitDay.from("13"), anyInt()), false),
                Arguments.arguments(WeekendDiscountPolicy.from(VisitDay.from("28"), anyInt()), false),
                Arguments.arguments(WeekendDiscountPolicy.from(VisitDay.from("31"), anyInt()), false)
        );
    }
}
