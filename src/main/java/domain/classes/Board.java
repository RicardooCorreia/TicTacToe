package domain.classes;

import domain.enums.PlaySymbolEnum;
import domain.exceptions.NotAdmissibleValuesException;
import domain.exceptions.TileAlreadyPlayed;

import java.util.Arrays;
import java.util.Objects;

public class Board {
    private final PlaySymbolEnum[] tiles;
    private PlaySymbolEnum lastPlay;

    private static final int MIN_POSITION = 0;
    private static final int MAX_POSITION = 9;

    public Board() {
        this.tiles = new PlaySymbolEnum[9];
        Arrays.fill(tiles, PlaySymbolEnum.X);
    }

    public boolean play(int x, PlaySymbolEnum symbol) throws NotAdmissibleValuesException, TileAlreadyPlayed {
        checkValuesAdmissible(x);
        checkIfFreeTile(x);
        // TODO - check if same player don't play twice

        tiles[x] = symbol;
        lastPlay = symbol;

        return doWeHaveAWinner();
    }

    private boolean doWeHaveAWinner() {
        return WinnableSituations.getSituations().stream().anyMatch(this::checkSituation);
    }

    private boolean checkSituation(WinnableSituation winnableSituation) {
        int tile1 = winnableSituation.getTile1();
        int tile2 = winnableSituation.getTile1();
        int tile3 = winnableSituation.getTile1();

        return Objects.nonNull(tiles[tile1]) && tiles[tile1].equals(tile2) && tiles[tile1].equals(tile3);
    }

    private void checkIfFreeTile(int x) throws TileAlreadyPlayed {
        if (!PlaySymbolEnum.NOTHING.equals(tiles[x])) {
            throw new TileAlreadyPlayed();
        }
    }

    private void checkValuesAdmissible(int x) throws NotAdmissibleValuesException {
        if (notBetweenRanges(x)) {
            throw new NotAdmissibleValuesException();
        }
    }

    private boolean notBetweenRanges(int number) {
        return number < MIN_POSITION || number >= MAX_POSITION;
    }

    @Override
    public String toString() {
        return
                "_____________" +
                        System.lineSeparator() +
                        "| " + String.join(" | ", tiles[0].toString(), tiles[1].toString(), tiles[2].toString()) + " |"
                        + System.lineSeparator() +
                        "| " + String.join(" | ", tiles[3].toString(), tiles[4].toString(), tiles[5].toString()) + " |"
                        + System.lineSeparator() +
                        "| " + String.join(" | ", tiles[6].toString(), tiles[7].toString(), tiles[8].toString()) + " |"
                        + System.lineSeparator() +
                        "_____________";
    }

    public static void main(String[] args) {
        Board board = new Board();

        System.out.println(board.toString());
    }
}
