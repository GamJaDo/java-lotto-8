package lotto.controller;

import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoPurchaseAmount;
import lotto.domain.WinningNumbers;
import lotto.domain.WinningSet;
import lotto.service.LottoService;
import lotto.view.LottoInputView;
import lotto.view.LottoOutputView;

public class LottoController {

    public static final int LOTTO_PRICE = 1000;

    private final LottoInputView lottoInputView = new LottoInputView();
    private final LottoOutputView lottoOutputView = new LottoOutputView();
    private final LottoService lottoService = new LottoService();

    public void run() {
        LottoPurchaseAmount lottoPurchaseAmount = inputPurchaseAmountTry();
        int lottoPurchaseCount = lottoPurchaseAmount.getLottoPurchaseAmount() / LOTTO_PRICE;
        lottoOutputView.printLottoPurchaseCount(lottoPurchaseCount);

        List<Lotto> lottos = lottoService.generateLotto(lottoPurchaseCount);
        lottoOutputView.printLottoNumber(lottos);

        WinningSet winningSet = inputWinningSet();
        List<Integer> winningDetailsCounts = lottoService.evaluate(lottos, winningSet);

        lottoOutputView.printWinningHistory(winningDetailsCounts);
        lottoOutputView.printRateOfReturn(winningDetailsCounts, lottoPurchaseAmount);
    }

    private WinningSet inputWinningSet() {
        WinningNumbers winningNumbers = inputWinningNumbersTry();
        BonusNumber bonusNumber = inputBonusNumberTry();
        return new WinningSet(winningNumbers, bonusNumber);
    }

    private LottoPurchaseAmount inputPurchaseAmountTry() {
        while (true) {
            try {
                return lottoInputView.inputPurchaseAmount();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private WinningNumbers inputWinningNumbersTry() {
        while (true) {
            try {
                return lottoInputView.inputWinningNumbers();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private BonusNumber inputBonusNumberTry() {
        while (true) {
            try {
                return lottoInputView.inputBonusNumber();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
