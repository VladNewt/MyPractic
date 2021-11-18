package practic161121;

public enum Currency {
    EUR(978),
    USD(840);
    private int numCode;

    Currency(int numCode) {
        this.numCode = numCode;
    }

    public int numCode() {
        return numCode;
    }
}
