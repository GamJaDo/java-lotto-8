package lotto.domain;

public class WinningSet {

    private final WinningNumbers winningNumbers;
    private final BonusNumber bonusNumber;

    public WinningSet(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public WinningNumbers getWinningNumbers() {
        return winningNumbers;
    }

    public BonusNumber getBonusNumber() {
        return bonusNumber;
    }
}
