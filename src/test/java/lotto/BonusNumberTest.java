package lotto;

import lotto.domain.BonusNumber;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BonusNumberTest {

    @Test
    void 정상적인_보너스_번호를_입력() {
        BonusNumber bonusNumber = new BonusNumber("7");
        assertThat(bonusNumber.getBonusNumber()).isEqualTo(7);
    }

    @Test
    void 보너스_번호를_숫자로_입력하지_않은_경우_예외를_발생() {
        assertThatThrownBy(() -> new BonusNumber("r"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 보너스 번호는 숫자로 입력해야 합니다.");
    }

    @Test
    void 보너스_번호_입력시_범위를_벗어나는_숫자를_입력할_경우_예외를_발생() {
        assertThatThrownBy(() -> new BonusNumber("46"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 보너스 번호는 1부터 45 사이여야 합니다.");
    }
}
