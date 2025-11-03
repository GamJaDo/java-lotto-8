package lotto;

import lotto.domain.WinningDetails;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WinningDetailsTest {

    @Test
    void 당첨_번호가_여섯개_일치할_경우() {
        assertThat(WinningDetails.getMatchResult(6, false))
            .isEqualTo(WinningDetails.FIRST);
    }

    @Test
    void 당첨_번호가_다섯개와_보너스_번호가_일치할_경우() {
        assertThat(WinningDetails.getMatchResult(5, true))
            .isEqualTo(WinningDetails.SECOND);
    }

    @Test
    void 당첨_번호가_다섯개_일치할_경우() {
        assertThat(WinningDetails.getMatchResult(5, false))
            .isEqualTo(WinningDetails.THIRD);
    }

    @Test
    void 당첨_번호가_네개_일치할_경우() {
        assertThat(WinningDetails.getMatchResult(4, false))
            .isEqualTo(WinningDetails.FOURTH);
    }

    @Test
    void 당첨_번호가_세개_일치할_경우() {
        assertThat(WinningDetails.getMatchResult(3, false))
            .isEqualTo(WinningDetails.FIFTH);
    }

    @Test
    void 당첨_번호가_한개_두개_일치하는게_없을_경우() {
        assertThat(WinningDetails.getMatchResult(1, false))
            .isNull();
        assertThat(WinningDetails.getMatchResult(2, false))
            .isNull();
        assertThat(WinningDetails.getMatchResult(0, false))
            .isNull();
    }
}
