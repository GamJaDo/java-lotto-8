package lotto;

public enum WinningDetails {
    FIRST(6, 2_000_000_000, false),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1_500_000, false),
    FOURTH(4, 50_000, false),
    FIFTH(3, 5_000, false);

    private final int numberMatchCount;
    private final int prizeMoney;
    private final boolean bonusNumberExists;

    WinningDetails(int numberMatchCount, int prizeMoney, boolean bonusNumberExists) {
        this.numberMatchCount = numberMatchCount;
        this.prizeMoney = prizeMoney;
        this.bonusNumberExists = bonusNumberExists;
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
}
