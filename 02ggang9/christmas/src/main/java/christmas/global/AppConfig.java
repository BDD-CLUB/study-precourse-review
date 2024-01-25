package christmas.global;

import christmas.controller.ChristmasController;
import christmas.repository.ChristmasOrderMenuRepository;
import christmas.service.ChristmasService;
import christmas.view.ChristmasInputView;
import christmas.view.ChristmasOutputView;

public class AppConfig {

    public static ChristmasController christmasController() {
        return new ChristmasController(
                getChristmasInputView(),
                getChristmasOutputView(),
                getChristmasService(),
                getChristmasOrderMenuRepository()
        );
    }

    private static ChristmasInputView getChristmasInputView() {
        return new ChristmasInputView();
    }

    private static ChristmasOutputView getChristmasOutputView() {
        return new ChristmasOutputView();
    }

    private static ChristmasService getChristmasService() {
        return new ChristmasService(getChristmasOrderMenuRepository());
    }

    private static ChristmasOrderMenuRepository getChristmasOrderMenuRepository() {
        return new ChristmasOrderMenuRepository();
    }


}
