package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {

        System.out.println("구입금액을 입력해 주세요.");
        String inputLottoPurchaseAmount = Console.readLine();
        if (!inputLottoPurchaseAmount.matches("\\d+")){
            throw new IllegalArgumentException("숫자를 입력해야합니다.");
        }

        int lottoPurchaseAmount = Integer.parseInt(inputLottoPurchaseAmount);
        if (lottoPurchaseAmount%1000 != 0) {
            throw new IllegalArgumentException("구입 금액은 1,000원 단위로 입력해야 합니다.");
        }

        
    }
}
