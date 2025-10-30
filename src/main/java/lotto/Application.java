package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        System.out.println("구입금액을 입력해 주세요.");
        String inputLottoPurchaseAmount = Console.readLine();
        if (!inputLottoPurchaseAmount.matches("\\d+")){
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해야합니다.");
        }

        int lottoPurchaseAmount = Integer.parseInt(inputLottoPurchaseAmount);
        if (lottoPurchaseAmount%1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위로 입력해야 합니다.");
        }

        int lottoPurchaseCount = lottoPurchaseAmount/1000;
        System.out.println("\n" + lottoPurchaseCount + "개를 구매했습니다.");

        List<Lotto> lottos = new ArrayList<>();
        for (int i=0; i<lottoPurchaseCount; i++) {
            Lotto lotto = new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6));
            lottos.add(lotto);
        }
        for (Lotto lotto : lottos) {
            lotto.printLottoNumber();
        }

        System.out.println("당첨 번호를 입력해 주세요.");
        String inputWinningNumbers = Console.readLine();
        validateHasComma(inputWinningNumbers);

        List<Integer> winningNumbers = Arrays.stream(inputWinningNumbers.split(","))
            .map(Integer::parseInt)
            .toList();
        validateWinningNumbers(winningNumbers);

    }

    public static void validateWinningNumbers(List<Integer> winningNumbers) {
        validateNumberComposition(winningNumbers);
        validateNumberCount(winningNumbers);
        validateNumberRange(winningNumbers);
        validateNoDuplicate(winningNumbers);
    }

    public static void validateHasComma(String inputWinningNumbers) {
        if (!inputWinningNumbers.contains(",")) {
            throw new IllegalArgumentException("[ERROR] 구분자 쉼표(,)가 없습니다.");
        }
    }

    public static void validateNumberComposition(List<Integer> winningNumbers) {
        for (Integer winningNumber : winningNumbers) {
            if (!String.valueOf(winningNumber).matches("\\d+")) {
                throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자로 입력해야 합니다.");
            }
        }
    }

    public static void validateNumberCount(List<Integer> winningNumbers) {
        if (winningNumbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }
    }

    public static void validateNumberRange(List<Integer> winningNumbers) {
        for (Integer winningNumber: winningNumbers) {
            if (winningNumber < 1 || winningNumber > 45) {
                throw new IllegalArgumentException("[ERROR] 당첨 번호는 1부터 45 사이여야 합니다.");
            }
        }
    }

    public static void validateNoDuplicate(List<Integer> winningNumbers) {
        if (winningNumbers.size() != winningNumbers.stream().distinct().count()) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호들 중 중복된 번호가 있습니다.");
        }
    }


}
