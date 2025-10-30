package lotto;

public class BonusNumber {

    private final int bonusNumber;

    public BonusNumber(int bonusNumber) {
        validate(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validate(int bonusNumber) {
        validateNumberComposition(bonusNumber);
        validateNumberRange(bonusNumber);
    }

    private void validateNumberComposition(int bonusNumber) {
        if (!String.valueOf(bonusNumber).matches("\\d+")) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자로 입력해야 합니다.");
        }
    }

    private void validateNumberRange(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이여야 합니다.");
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
