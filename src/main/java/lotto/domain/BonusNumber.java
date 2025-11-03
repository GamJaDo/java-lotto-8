package lotto.domain;

public class BonusNumber {

    public static final int RANDOM_NUMBER_MINIMUM_VALUE = 1;
    public static final int RANDOM_NUMBER_MAXIMUM_VALUE = 45;

    private final int bonusNumber;

    public BonusNumber(String readBonusNumber) {
        this.bonusNumber = validate(readBonusNumber);
    }

    private int validate(String readBonusNumber) {
        validateBlank(readBonusNumber);
        validateNumberComposition(readBonusNumber);
        int bonusNumber = Integer.parseInt(readBonusNumber);
        validateNumberRange(bonusNumber);
        return bonusNumber;
    }

    private void validateBlank(String readLottoPurchaseAmount) {
        if (readLottoPurchaseAmount.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호를 입력해야 합니다.");
        }
    }

    private void validateNumberComposition(String readBonusNumber) {
        if (!readBonusNumber.matches("\\d+")) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자로 입력해야 합니다.");
        }
    }

    private void validateNumberRange(int bonusNumber) {
        if (bonusNumber < RANDOM_NUMBER_MINIMUM_VALUE || bonusNumber > RANDOM_NUMBER_MAXIMUM_VALUE) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이여야 합니다.");
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
