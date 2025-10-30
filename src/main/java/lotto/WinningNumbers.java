package lotto;

import java.util.Arrays;
import java.util.List;

public class WinningNumbers {

    private final List<Integer> winningNumbers;

    public WinningNumbers(String inputWinningNumbers) {
        validateHasComma(inputWinningNumbers);
        List<Integer> winningNumbers = Arrays.stream(inputWinningNumbers.split(","))
            .map(Integer::parseInt)
            .toList();
        validate(winningNumbers);

        this.winningNumbers = winningNumbers;
    }

    private void validateHasComma(String inputWinningNumbers) {
        if (!inputWinningNumbers.contains(",")) {
            throw new IllegalArgumentException("[ERROR] 구분자 쉼표(,)가 없습니다.");
        }
    }

    private void validate(List<Integer> winningNumbers) {
        validateNumberComposition(winningNumbers);
        validateNumberCount(winningNumbers);
        validateNumberRange(winningNumbers);
        validateNoDuplicate(winningNumbers);
    }

    private void validateNumberComposition(List<Integer> winningNumbers) {
        for (Integer winningNumber : winningNumbers) {
            if (!String.valueOf(winningNumber).matches("\\d+")) {
                throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자로 입력해야 합니다.");
            }
        }
    }

    private void validateNumberCount(List<Integer> winningNumbers) {
        if (winningNumbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }
    }

    private void validateNumberRange(List<Integer> winningNumbers) {
        for (Integer winningNumber: winningNumbers) {
            if (winningNumber < 1 || winningNumber > 45) {
                throw new IllegalArgumentException("[ERROR] 당첨 번호는 1부터 45 사이여야 합니다.");
            }
        }
    }

    private void validateNoDuplicate(List<Integer> winningNumbers) {
        if (winningNumbers.size() != winningNumbers.stream().distinct().count()) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호들 중 중복된 번호가 있습니다.");
        }
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }
}
