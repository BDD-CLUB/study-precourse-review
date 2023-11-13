package christmas.controller;

import christmas.domain.Customer;
import christmas.domain.DecemberDate;
import christmas.model.Menu;
import christmas.domain.discounts.DecemberDiscount;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.HashMap;

public class PromotionController {
    private final InputView inputView;
    private final OutputView outputView;

    public PromotionController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Customer customer = customerInput();
        outputView.printChristmasPromotion(customer);

        DecemberDiscount decemberDiscount = new DecemberDiscount(customer);
        printResult(decemberDiscount, customer.calculateTotalPrice());

        int totalDiscount = decemberDiscount.getTotalDiscount();
        assignBadge(customer, totalDiscount);

    }

    private void assignBadge(Customer customer, int totalDiscount) {
        customer.assignBadge(totalDiscount);
        outputView.printBadge(customer.getBadgeName());
    }

    private void printResult(DecemberDiscount decemberDiscount, int totalPrice) {
        boolean WillPresentation = decemberDiscount.willPresentEvent();
        outputView.printPresentationMenu(WillPresentation);
        outputView.printDiscountDetails(decemberDiscount, totalPrice);
    }

    private Customer customerInput() {
        outputView.printHelloMessage();
        DecemberDate visitDate = inputView.inputDate();
        HashMap<Menu, Integer> menu = inputView.inputMenu();
        return new Customer(visitDate, menu);
    }
}
