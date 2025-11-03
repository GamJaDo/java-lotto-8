package lotto.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.WinningDetails;
import lotto.domain.WinningNumbers;
import lotto.domain.WinningSet;

public class LottoService {

    private static final int WINNING_DETAILS_SIZE = 5;

    public List<Lotto> generateLotto(int lottoPurchaseCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoPurchaseCount; i++) {
            lottos.add(new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6)));
        }
        return lottos;
    }

    public List<Integer> evaluate(List<Lotto> lottos, WinningSet winningSet) {
        List<Integer> winningDetailsCounts = WinningDetailsCountsReset();

        for (Lotto lotto : lottos) {
            progressLotto(lotto, winningSet, winningDetailsCounts);
        }
        return winningDetailsCounts;
    }

    private List<Integer> WinningDetailsCountsReset() {
        List<Integer> winningDetailsCounts = new ArrayList<>();
        for (int i = 0; i < WINNING_DETAILS_SIZE; i++) {
            winningDetailsCounts.add(0);
        }
        return winningDetailsCounts;
    }

    private void progressLotto(Lotto lotto, WinningSet winningSet, List<Integer> winningDetailsCounts) {
        WinningNumbers winningNumbers = winningSet.getWinningNumbers();
        BonusNumber bonusNumber = winningSet.getBonusNumber();

        int numberMatchCount = matchCount(lotto.getNumbers(), winningNumbers.getWinningNumbers());
        boolean bonusNumberExists = lotto.getNumbers().contains(bonusNumber.getBonusNumber());

        WinningDetails winningDetails = decisionRank(numberMatchCount, bonusNumberExists);
        if (winningDetails == null) {
            return;
        }
        int index = winningDetails.getIndex();
        winningDetailsCounts.set(index, winningDetailsCounts.get(index) + 1);
    }

    private static int matchCount(List<Integer> lottoNumbers, List<Integer> winningNumbers) {
        Set<Integer> winningNumbersSet = new HashSet<>(winningNumbers);
        int count = 0;
        for (int lottoNumber : lottoNumbers) {
            if (winningNumbersSet.contains(lottoNumber)) {
                count += 1;
            }
        }
        return count;
    }

    private static WinningDetails decisionRank(int numberMatchCount, boolean bonusNumberExists) {
        return WinningDetails.getMatchResult(numberMatchCount, bonusNumberExists);
    }
}
