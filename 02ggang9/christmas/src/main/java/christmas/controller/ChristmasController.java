package christmas.controller;

import christmas.domain.OrderSheet;
import christmas.domain.badge.Badge;
import christmas.domain.detail.BenefitDetail;
import christmas.domain.menu.Event;
import christmas.repository.ChristmasOrderMenuRepository;
import christmas.service.ChristmasService;
import christmas.view.ChristmasInputView;
import christmas.view.ChristmasOutputView;

public class ChristmasController {

    private final ChristmasInputView christmasInputView;
    private final ChristmasOutputView christmasOutputView;
    private final ChristmasService christmasService;
    private final ChristmasOrderMenuRepository christmasOrderMenuRepository;

    public ChristmasController(ChristmasInputView christmasInputView,
                               ChristmasOutputView christmasOutputView,
                               ChristmasService christmasService,
                               ChristmasOrderMenuRepository christmasOrderMenuRepository
    ) {
        this.christmasInputView = christmasInputView;
        this.christmasOutputView = christmasOutputView;
        this.christmasService = christmasService;
        this.christmasOrderMenuRepository = christmasOrderMenuRepository;
    }

    public void run() {
        // STEP1 : 예상 방문 날짜 입력받고 저장
        int dateOfVisit = getDateOfVisit();

        // STEP2 : 주문할 메뉴와 개수 입력 받고 저장
        getOrderMenusAndSave(dateOfVisit);

        // STEP3 : 이벤트 혜택 미리 보기 안내 메시지 출력
        printEventInformationMessage();

        // STEP4 : 사용자가 주문한 메뉴와 개수 출력
        OrderSheet findOrderSheet = findOrderSheet();
        printOrderMenusAndCounts(findOrderSheet);

        // STEP5 : 할인 전 총 주문 금액 출력
        Integer amountBeforeDiscount = getAmountBeforeDiscount();
        printTotalAmountBeforeDiscount(amountBeforeDiscount);

        // STEP6 : 증정 메뉴 출력
        BenefitDetail benefitDetail = new BenefitDetail();
        benefitDetail.setEventGoods(amountBeforeDiscount);
        printGiveMenu(benefitDetail.getEvent());

        // STEP 7 : 혜택 내역 출력
        benefitDetail.saveEventDetails(findOrderSheet);
        printBenefitDetails(benefitDetail);

        // STEP 8 : 총 혜택 금액 출력
        int totalBenefitPrice = benefitDetail.getTotalBenefitPrice();
        printTotalDiscountAmount(totalBenefitPrice);

        // STEP 9 : 할인 후 예상 결제 금액 출력
        printAfterDiscountAmount(benefitDetail, amountBeforeDiscount);

        // STEP 10 : 이벤트 배지 출력
        printEventBadge(christmasService.findBadge(totalBenefitPrice));
    }

    private void printOrderMenusAndCounts(OrderSheet orderSheet) {
        christmasOutputView.printOrderMenusAndCounts(orderSheet);
    }

    private OrderSheet findOrderSheet() {
        return christmasService.findOrderSheet();
    }

    private void getOrderMenusAndSave(int dateOfVisit) {
        OrderSheet orderSheet = getOrderSheet(dateOfVisit);
        saveOrderMenu(orderSheet);
    }

    private void printEventBadge(Badge badge) {
        christmasOutputView.printEventBadge(badge);
    }

    private void printAfterDiscountAmount(BenefitDetail benefitDetail, int beforeDiscountPrice) {
        christmasOutputView.printAfterDiscountAmount(benefitDetail, beforeDiscountPrice);
    }

    private void printTotalDiscountAmount(int totalDiscountAmount) {
        christmasOutputView.totalDiscountAmount(totalDiscountAmount);
    }

    private void printBenefitDetails(BenefitDetail benefitDetail) {
        christmasOutputView.printBenefitDetails(benefitDetail);
    }

    private void printGiveMenu(Event event) {
        christmasOutputView.printGiveMenu(event);
    }

    private void printTotalAmountBeforeDiscount(Integer amountBeforeDiscount) {
        christmasOutputView.printTotalAmountBeforeDiscount(amountBeforeDiscount);
    }

    private Integer getAmountBeforeDiscount() {
        return christmasService.calculateTotalAmount();
    }

    private void printEventInformationMessage() {
        christmasOutputView.printEventInformation();
    }

    private void saveOrderMenu(OrderSheet orderSheet) {
        christmasOrderMenuRepository.saveOrderSheet(orderSheet);
    }

    private OrderSheet getOrderSheet(int dateOfVisit) {
        return christmasInputView.getUserOrderMenu(dateOfVisit);
    }

    private int getDateOfVisit() {
        return christmasInputView.getUserDateOfVisit();
    }

}
