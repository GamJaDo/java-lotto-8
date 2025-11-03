package lotto.domain;

public class LottoPurchaseAmount {

    private final int lottoPurchaseAmount;

    public LottoPurchaseAmount(String readLottoPurchaseAmount) {
        this.lottoPurchaseAmount = validate(readLottoPurchaseAmount);
    }

    private int validate(String readLottoPurchaseAmount) {
        validateDigits(readLottoPurchaseAmount);
        int lottoPurchaseAmount = Integer.parseInt(readLottoPurchaseAmount);
        validateUnit(lottoPurchaseAmount);
        return lottoPurchaseAmount;
    }

    private void validateDigits(String readLottoPurchaseAmount) {
        if (readLottoPurchaseAmount == null || !readLottoPurchaseAmount.matches("\\d+")) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해야합니다.");
        }
    }

    private void validateUnit(int lottoPurchaseAmount) {
        if (lottoPurchaseAmount % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위로 입력해야 합니다.");
        }
    }

    public int getLottoPurchaseAmount() {
        return lottoPurchaseAmount;
    }
}
