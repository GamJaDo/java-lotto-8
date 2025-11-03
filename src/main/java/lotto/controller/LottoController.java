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

    private final LottoInputView lottoInputView = new LottoInputView();
    private final LottoOutputView lottoOutputView = new LottoOutputView();
    private final LottoService lottoService = new LottoService();

    public void run() {
        LottoPurchaseAmount lottoPurchaseAmount = lottoInputView.inputPurchaseAmount();
        int lottoPurchaseCount = lottoPurchaseAmount.getLottoPurchaseAmount() / 1000;
        lottoOutputView.printLottoPurchaseCount(lottoPurchaseCount);

        List<Lotto> lottos = lottoService.generateLotto(lottoPurchaseCount);
        lottoOutputView.printLottoNumber(lottos);

        WinningSet winningSet = inputWinningSet();
        List<Integer> winningDetailsCounts = lottoService.evaluate(lottos, winningSet);

        lottoOutputView.printWinningHistory(winningDetailsCounts);
        lottoOutputView.printRateOfReturn(winningDetailsCounts, lottoPurchaseAmount);
    }

    private WinningSet inputWinningSet() {
        WinningNumbers winningNumbers = lottoInputView.inputWinningNumbers();
        BonusNumber bonusNumber = lottoInputView.inputBonusNumber();
        return new WinningSet(winningNumbers, bonusNumber);
    }
}
