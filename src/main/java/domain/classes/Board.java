package domain.classes;

import com.google.common.annotations.VisibleForTesting;
import domain.enums.PlaySymbolEnum;
import domain.exceptions.NotAdmissibleValuesException;
import domain.exceptions.TileAlreadyPlayed;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class Board {
    private final PlaySymbolEnum[] tiles;
    private PlaySymbolEnum lastPlay;
    private boolean isFinished = false;

    private static final int MIN_POSITION = 0;
    private static final int MAX_POSITION = 9;

    public PlaySymbolEnum[] getTiles() {
        return tiles;
    }

    public Board() {
        this.tiles = new PlaySymbolEnum[9];
        Arrays.fill(tiles, PlaySymbolEnum.NOTHING);
    }

    // TODO: should finished when out of moves

    public boolean isFinished() {
        return isFinished;
    }

    public boolean play(int x, PlaySymbolEnum symbol) throws NotAdmissibleValuesException, TileAlreadyPlayed {
        if (!isFinished) {
            checkValuesAdmissible(x);
            checkIfFreeTile(x);
            // TODO - check if same player don't play twice

            tiles[x] = symbol;
            lastPlay = symbol;

            return doWeHaveAWinner();
        } else {
            return false;
        }
    }

    @VisibleForTesting
    protected boolean doWeHaveAWinner() {
        isFinished = WinnableSituations.getSituations().stream().anyMatch(this::checkSituation) || isOutOfMoves();
        return isFinished;
    }

    private boolean isOutOfMoves() {
        return Stream.of(tiles)
                .filter(playSymbolEnum -> !playSymbolEnum.equals(PlaySymbolEnum.NOTHING))
                .count() == MAX_POSITION;
    }

    @VisibleForTesting
    protected boolean checkSituation(WinnableSituation winnableSituation) {
        int tile1Index = winnableSituation.getTile1() - 1;
        int tile2Index = winnableSituation.getTile2() - 1;
        int tile3Index = winnableSituation.getTile3() - 1;

        return Objects.nonNull(tiles[tile1Index])
                && !tiles[tile1Index].equals(PlaySymbolEnum.NOTHING)
                && tiles[tile1Index].equals(tiles[tile2Index])
                && tiles[tile1Index].equals(tiles[tile3Index]);
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
}
