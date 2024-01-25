package christmas.model.benefits;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BadgeTest {

    @Test
    void should_sortedDesc_when_compareBadge() {
        Badge[] badges = Badge.values();
        Arrays.sort(badges, new Badge.BadgeComparator());
        assertEquals(Badge.산타, badges[0]);
        assertEquals(Badge.트리, badges[1]);
        assertEquals(Badge.별, badges[2]);
    }

}
