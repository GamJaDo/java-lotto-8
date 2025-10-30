package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
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
    }
}
