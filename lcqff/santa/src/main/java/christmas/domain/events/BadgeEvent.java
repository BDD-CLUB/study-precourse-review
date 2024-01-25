package christmas.domain.events;

public enum BadgeEvent {
    STAR("별", totalDiscount -> totalDiscount > 5_000),
    TREE("트리", totalDiscount -> totalDiscount > 10_000),
    SANTA("산타", totalDiscount -> totalDiscount > 20_000);

    private final String badgeName;
    private final BadgeAssignment assignBadge;

    BadgeEvent(String badgeName, BadgeAssignment assignBadge) {
        this.badgeName = badgeName;
        this.assignBadge = assignBadge;
    }

    public String getBadge(int totalDiscount) {
        if (assignBadge.assignBadge(totalDiscount)) {
            return badgeName;
        }
        return null;
    }

    interface BadgeAssignment {
        boolean assignBadge(int totalDiscount);
    }
}
