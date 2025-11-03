package lotto;

import lotto.domain.WinningNumbers;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningNumbersTest {

    @Test
    void 정상적인_당첨_번호를_입력() {
        WinningNumbers winningNumbers = new WinningNumbers("1,2,3,4,5,6");
        assertThat(winningNumbers.getWinningNumbers()).containsExactly(1,2,3,4,5,6);
    }

    @Test
    void 당첨_번호를_구분자_쉼표가_없을시_예외_발생() {
        assertThatThrownBy(() -> new WinningNumbers("1/2/3/4/5/6"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 구분자 쉼표(,)가 없습니다.");
    }

    @Test
    void 당첨_번호가_여섯개가_아닌_경우_예외_발생() {
        assertThatThrownBy(() -> new WinningNumbers("1,2,3,4,5,6,7"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 당첨 번호는 6개여야 합니다.");
    }

    @Test
    void 당첨_번호_입력시_범위를_벗어나는_숫자를_입력할_경우_예외를_발생() {
        assertThatThrownBy(() -> new WinningNumbers("1,2,3,4,5,46"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 당첨 번호는 1부터 45 사이여야 합니다.");
    }

    @Test
    void 당첨_번호_입력시_중복되는_숫자를_입력할_경우_예외를_발생() {
        assertThatThrownBy(() -> new WinningNumbers("1,1,2,3,4,5"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 당첨 번호들 중 중복된 번호가 있습니다.");
    }

    @Test
    void 당첨_번호를_숫자로_입력하지_않은_경우_예외를_발생() {
        assertThatThrownBy(() -> new WinningNumbers("1,2,3,4,5,r"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 당첨 번호는 숫자로 입력해야 합니다.");
    }
}
