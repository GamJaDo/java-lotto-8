package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;

public class Application {

    private static final int WINNING_DETAILS_SIZE = 5;
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;
    private static final int THIRD_INDEX = 2;
    private static final int FOURTH_INDEX = 3;
    private static final int FIFTH_INDEX = 4;

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

        System.out.println("\n당첨 번호를 입력해 주세요.");
        String inputWinningNumbers = Console.readLine();
        WinningNumbers winningNumbers = new WinningNumbers(inputWinningNumbers);

        System.out.println("\n보너스 번호를 입력해 주세요.");
        int inputBonusNumber = Integer.parseInt(Console.readLine());
        BonusNumber bonusNumber = new BonusNumber(inputBonusNumber);

        int[] winningDetails = new int[WINNING_DETAILS_SIZE];
        for (Lotto lotto : lottos) {
            int numberMatchCount = matchCount(lotto.getNumbers(), winningNumbers.getWinningNumbers());
            boolean bonusNumberExists = lotto.getNumbers().contains(bonusNumber.getBonusNumber());

            WinningDetails rank = decisionRank(numberMatchCount, bonusNumberExists);
            if (rank == null) {
                continue;
            }
            increaseRankCount(winningDetails, rank);
        }

        printWinningHistory(winningDetails);
        printRateOfReturn(winningDetails, lottoPurchaseAmount);
    }

    private static int matchCount(List<Integer> lottoNumbers, List<Integer> winningNumbers) {
        int count = 0;
        for (int lottoNumber : lottoNumbers) {
            if (winningNumbers.contains(lottoNumber)) {
                count += 1;
            }
        }

        return count;
    }

    private static WinningDetails decisionRank(int numberMatchCount, boolean bonusNumberExists) {
        if (numberMatchCount == 6) {
            return WinningDetails.FIRST;
        }
        if (numberMatchCount == 5 && bonusNumberExists) {
            return WinningDetails.SECOND;
        }
        if (numberMatchCount == 5) {
            return WinningDetails.THIRD;
        }
        if (numberMatchCount == 4) {
            return WinningDetails.FOURTH;
        }
        if (numberMatchCount == 3) {
            return WinningDetails.FIFTH;
        }

        return null;
    }

    private static void increaseRankCount(int[] winningDetails, WinningDetails rank) {
        if (rank == WinningDetails.FIRST) {
            winningDetails[FIRST_INDEX] += 1;
            return;
        }
        if (rank == WinningDetails.SECOND) {
            winningDetails[SECOND_INDEX] += 1;
            return;
        }
        if (rank == WinningDetails.THIRD) {
            winningDetails[THIRD_INDEX] += 1;
            return;
        }
        if (rank == WinningDetails.FOURTH) {
            winningDetails[FOURTH_INDEX] += 1;
            return;
        }
        if (rank == WinningDetails.FIFTH) {
            winningDetails[FIFTH_INDEX] += 1;
        }
    }

    private static void printWinningHistory(int[] winningDetails) {
        System.out.println("\n당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 (5,000원) - " + winningDetails[WinningDetails.FIFTH.ordinal()] + "개");
        System.out.println("4개 일치 (50,000원) - " + winningDetails[WinningDetails.FOURTH.ordinal()] + "개");
        System.out.println("5개 일치 (1,500,000원) - " + winningDetails[WinningDetails.THIRD.ordinal()] + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + winningDetails[WinningDetails.SECOND.ordinal()] + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + winningDetails[WinningDetails.FIRST.ordinal()] + "개");
    }

    private static void printRateOfReturn(int[] winningDetails, int lottoPurchaseAmount) {
        int totalPrize = 0;

        totalPrize += winningDetails[FIRST_INDEX] * WinningDetails.FIRST.getPrizeMoney();
        totalPrize += winningDetails[SECOND_INDEX] * WinningDetails.SECOND.getPrizeMoney();
        totalPrize += winningDetails[THIRD_INDEX] * WinningDetails.THIRD.getPrizeMoney();
        totalPrize += winningDetails[FOURTH_INDEX] * WinningDetails.FOURTH.getPrizeMoney();
        totalPrize += winningDetails[FIFTH_INDEX] * WinningDetails.FIFTH.getPrizeMoney();

        double rateOfReturn = (double) totalPrize/lottoPurchaseAmount * 100.0;
        System.out.printf("총 수익률은 %.1f%%입니다.", rateOfReturn);
    }
}
