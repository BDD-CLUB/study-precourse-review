package lotto.controller;

import static lotto.model.Lotto.generateLotto;

import java.util.List;
import java.util.stream.IntStream;
import lotto.dto.LottosResult;
import lotto.dto.TotalResults;
import lotto.generator.NumberGenerator;
import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.User;
import lotto.model.WinningLottos;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private static final int DIVIDE_NUMBER = 1000;

    private final InputView inputView;
    private final OutputView outputView;
    private final NumberGenerator numberGenerator;

    public LottoController(InputView inputView, OutputView outputView, NumberGenerator numberGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.numberGenerator = numberGenerator;
    }

    public void run() {
        int amounts = inputView.inputAmount();
        int lottoCount = getLottoCount(amounts);

        Lottos lottos = generateLottos(lottoCount);
        outputView.printLottos(LottosResult.from(lottos));

        User user = createUser();
        WinningLottos winningLottos = new WinningLottos(lottos.getWinningLottos(user));
        outputView.printResults(TotalResults.of(winningLottos, getRateOfReturn(amounts, winningLottos)));
    }

    private int getLottoCount(int amounts) {
        return amounts / DIVIDE_NUMBER;
    }

    private Lottos generateLottos(int lottoCount) {
        List<Lotto> lottos = IntStream.range(0, lottoCount)
                .mapToObj(i -> generateLotto(numberGenerator))
                .toList();
        return new Lottos(lottos);
    }

    private User createUser() {
        while (true) {
            try {
                List<Integer> numbers = inputView.inputWinningNumbers();
                Lotto lotto = new Lotto(numbers);
                int bonusNumber = inputView.inputBonusNumber();
                return new User(lotto, bonusNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String getRateOfReturn(int userAmounts, WinningLottos winningLottos) {
        int totalAmounts = winningLottos.getTotalAmounts();
        double rateOfReturn = (double) totalAmounts / userAmounts * 100;
        return String.valueOf(Math.round(rateOfReturn * 100.0) / 100.0);
    }
}
