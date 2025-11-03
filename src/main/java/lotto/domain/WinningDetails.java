package lotto.domain;

public enum WinningDetails {
    FIRST(0, 6, 2_000_000_000, false, "6개 일치"),
    SECOND(1, 5, 30_000_000, true, "5개 일치, 보너스 볼 일치"),
    THIRD(2, 5, 1_500_000, false, "5개 일치"),
    FOURTH(3, 4, 50_000, false, "4개 일치"),
    FIFTH(4, 3, 5_000, false, "3개 일치");

    private final int index;
    private final int numberMatchCount;
    private final int prizeMoney;
    private final boolean bonusNumberExists;
    private final String description;

    WinningDetails(int index, int numberMatchCount, int prizeMoney, boolean bonusNumberExists,
        String description) {
        this.index = index;
        this.numberMatchCount = numberMatchCount;
        this.prizeMoney = prizeMoney;
        this.bonusNumberExists = bonusNumberExists;
        this.description = description;
    }

    public static WinningDetails getMatchResult(int numberMatchCount, boolean bonusNumberExists) {
        for (WinningDetails winningDetails : values()) {
            if (winningDetails.isMatch(numberMatchCount, bonusNumberExists)) {
                return winningDetails;
            }
        }
        return null;
    }

    private boolean isMatch(int numberMatchCount, boolean bonusNumberExists) {
        if (numberMatchCount != this.numberMatchCount) {
            return false;
        }
        if (this == SECOND) {
            return bonusNumberExists;
        }
        if (this == THIRD) {
            return !bonusNumberExists;
        }
        return true;
    }

    public int getIndex() {
        return index;
    }

    public int getNumberMatchCount() {
        return numberMatchCount;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public boolean isBonusNumberExists() {
        return bonusNumberExists;
    }

    public String getDescription() {
        return description;
    }
}
