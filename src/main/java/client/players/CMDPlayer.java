package client.players;

import com.google.common.annotations.VisibleForTesting;
import domain.classes.Board;
import domain.enums.PlaySymbolEnum;
import domain.enums.PlayerType;
import domain.exceptions.NotAdmissibleValuesException;
import domain.exceptions.TileAlreadyPlayed;
import domain.interfaces.Player;

import java.util.Scanner;

public class CMDPlayer implements Player {
    private static final String INVALID_VALUES_MESSAGE = "Please make a move between 1 and 9!";
    private static final String TILE_ALREADY_PLAYED_MESSAGE = "Tile already played, pick another!";

    private final Scanner scanner;
    private final PlaySymbolEnum playSymbol;
    private final String name;

    public CMDPlayer(Scanner scanner, PlaySymbolEnum playSymbolEnum, String name) {
        this.scanner = scanner;
        this.playSymbol = playSymbolEnum;
        this.name = name;
    }

    @VisibleForTesting
    protected static String getInvalidValuesMessage() {
        return INVALID_VALUES_MESSAGE;
    }

    @VisibleForTesting
    protected static String getTileAlreadyPlayedMessage() {
        return TILE_ALREADY_PLAYED_MESSAGE;
    }

    @Override
    public int doPlay(Board board, PlaySymbolEnum symbol) {
        boolean played = false;
        int play = -1;
        while (!played) {
            try {
                play = scanner.nextInt() - 1;
                board.play(play, this.playSymbol);
                played = true;
            } catch (NotAdmissibleValuesException e) {
                print(INVALID_VALUES_MESSAGE);
            } catch (TileAlreadyPlayed tileAlreadyPlayed) {
                print(TILE_ALREADY_PLAYED_MESSAGE);
            }
        }
        return play;
    }

    @VisibleForTesting
    protected void print(String message) {
        System.out.println(message);
    }

    @Override
    public PlayerType getPlayerType() {
        return PlayerType.CMD_UI;
    }

    @Override
    public PlaySymbolEnum getPlaySymbol() {
        return this.playSymbol;
    }

    @Override
    public String getName() {
        return name;
    }
}
