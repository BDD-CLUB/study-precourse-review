package christmas.controller;

import christmas.model.VisitDay;
import christmas.model.benefits.EventPreviewInfo;
import christmas.model.order.Order;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        inputView.printIntroduce();
        VisitDay visitDay = inputView.readVisitDay();
        Order order = inputView.readOrder();
        EventPreviewInfo eventPreviewInfo = EventPreviewInfo.from(order, visitDay);

        outputView.printGuideMessage(visitDay);
        outputView.printMenu(order);
        outputView.printEventPreviewInfo(eventPreviewInfo);
    }
}
