package lotto;

import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.WinningDetails;
import lotto.domain.WinningNumbers;
import lotto.domain.WinningSet;
import lotto.service.LottoService;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LottoServiceTest {

    private final LottoService lottoService = new LottoService();

    @Test
    void 정상적으로_집계() {
        WinningNumbers winningNumbers = new WinningNumbers("1,2,3,4,5,6");
        BonusNumber bonusNumber = new BonusNumber("7");
        WinningSet winningSet = new WinningSet(winningNumbers, bonusNumber);

        Lotto fifth = new Lotto(List.of(1, 2, 3, 8, 9, 10));
        Lotto fourth = new Lotto(List.of(1, 2, 3, 4, 8, 9));
        Lotto third = new Lotto(List.of(1, 2, 3, 4, 5, 8));
        Lotto second = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        Lotto first = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        List<Lotto> lottos = List.of(fifth, fourth, third, second, first);
        List<Integer> winningDetailsCounts = lottoService.evaluate(lottos, winningSet);

        assertThat(winningDetailsCounts.get(WinningDetails.FIRST.getIndex())).isEqualTo(1);
        assertThat(winningDetailsCounts.get(WinningDetails.FOURTH.getIndex())).isEqualTo(1);
        assertThat(winningDetailsCounts.get(WinningDetails.THIRD.getIndex())).isEqualTo(1);
        assertThat(winningDetailsCounts.get(WinningDetails.SECOND.getIndex())).isEqualTo(1);
        assertThat(winningDetailsCounts.get(WinningDetails.FIRST.getIndex())).isEqualTo(1);
    }
}
