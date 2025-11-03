package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.BonusNumber;
import lotto.domain.LottoPurchaseAmount;
import lotto.domain.WinningNumbers;

public class LottoInputView {

    public LottoPurchaseAmount inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String readLottoPurchaseAmount = Console.readLine();
        return new LottoPurchaseAmount(readLottoPurchaseAmount);
    }

    public WinningNumbers inputWinningNumbers() {
        System.out.println("\n당첨 번호를 입력해 주세요.");
        String readWinningNumbers = Console.readLine();
        return new WinningNumbers(readWinningNumbers);
    }

    public BonusNumber inputBonusNumber() {
        System.out.println("\n보너스 번호를 입력해 주세요.");
        String readBonusNumber = Console.readLine();
        return new BonusNumber(readBonusNumber);
    }
}
