package lotto;

import lotto.domain.LottoPurchaseAmount;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoPurchaseAmountTest {

    @Test
    void 정상적인_금액을_입력() {
        LottoPurchaseAmount lottoPurchaseAmount = new LottoPurchaseAmount("8000");
        assertThat(lottoPurchaseAmount.getLottoPurchaseAmount()).isEqualTo(8000);
    }

    @Test
    void 구입금액을_숫자로_입력하지_않은_경우_예외를_발생() {
        assertThatThrownBy(() -> new LottoPurchaseAmount("rrrr"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 숫자를 입력해야합니다.");
    }

    @Test
    void 구입금액_입력시_1000원_단위로_입력하지_않은_경우_예외를_발생() {
        assertThatThrownBy(() -> new LottoPurchaseAmount("9900"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 구입 금액은 1,000원 단위로 입력해야 합니다.");
    }
}
