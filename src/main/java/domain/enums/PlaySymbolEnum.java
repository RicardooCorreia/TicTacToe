package domain.enums;

public enum PlaySymbolEnum {
    X('X'),
    O('O');

    private char symbol;

    PlaySymbolEnum(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(this.symbol);
    }
}
