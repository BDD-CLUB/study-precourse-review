package christmas;

import christmas.controller.PromotionController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class AppConfig {

    public static PromotionController promotionController() {
        return new PromotionController(inputView(), outputView());

    }

    private static InputView inputView() {
        return new InputView();
    }

    private static OutputView outputView() {
        return new OutputView();
    }

}
