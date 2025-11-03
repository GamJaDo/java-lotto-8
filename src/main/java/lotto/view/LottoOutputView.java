package lotto.view;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import lotto.domain.Lotto;
import lotto.domain.LottoPurchaseAmount;
import lotto.domain.WinningDetails;

public class LottoOutputView {

    private final NumberFormat numberFormat = NumberFormat.getInstance(Locale.KOREA);

    public void printLottoPurchaseCount(int lottoPurchaseCount) {
        System.out.println("\n" + lottoPurchaseCount + "개를 구매했습니다.");
    }

    public void printLottoNumber(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            lotto.printLottoNumber();
        }
    }

    public void printWinningHistory(List<Integer> winningDetailsCounts) {
        System.out.println("\n당첨 통계");
        System.out.println("---");
        printWinningDetails(WinningDetails.FIFTH.getDescription(), WinningDetails.FIFTH,
            winningDetailsCounts);
        printWinningDetails(WinningDetails.FOURTH.getDescription(), WinningDetails.FOURTH,
            winningDetailsCounts);
        printWinningDetails(WinningDetails.THIRD.getDescription(), WinningDetails.THIRD,
            winningDetailsCounts);
        printWinningDetails(WinningDetails.SECOND.getDescription(), WinningDetails.SECOND,
            winningDetailsCounts);
        printWinningDetails(WinningDetails.FIRST.getDescription(), WinningDetails.FIRST,
            winningDetailsCounts);
    }

    private void printWinningDetails(String description, WinningDetails winningDetails,
        List<Integer> winningDetailsCounts) {
        String prizeMoney = numberFormat.format(winningDetails.getPrizeMoney());
        int count = winningDetailsCounts.get(winningDetails.getIndex());
        System.out.println(description + " (" + prizeMoney + "원) - " + count + "개");
    }

    public void printRateOfReturn(List<Integer> winningDetailsCounts,
        LottoPurchaseAmount lottoPurchaseAmount) {
        int totalPrize = 0;

        for (WinningDetails rank : WinningDetails.values()) {
            int count = winningDetailsCounts.get(rank.getIndex());
            totalPrize += count * rank.getPrizeMoney();
        }

        double rateOfReturn = (double) totalPrize / lottoPurchaseAmount.getLottoPurchaseAmount() * 100.0;
        System.out.printf("총 수익률은 %.1f%%입니다.", rateOfReturn);
    }
}
