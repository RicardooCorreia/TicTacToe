package client.players;

import domain.classes.Board;
import domain.enums.PlaySymbolEnum;
import domain.enums.PlayerType;
import domain.exceptions.NotAdmissibleValuesException;
import domain.exceptions.TileAlreadyPlayed;
import domain.interfaces.Player;

import java.util.Scanner;

public class CMDPlayer implements Player {
    private final Scanner scanner;
    private final PlaySymbolEnum playSymbol;
    private final String name;

    public CMDPlayer(Scanner scanner, PlaySymbolEnum playSymbolEnum, String name) {
        this.scanner = scanner;
        this.playSymbol = playSymbolEnum;
        this.name = name;
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
                System.out.println("Please make a move between 1 and 9!");
            } catch (TileAlreadyPlayed tileAlreadyPlayed) {
                System.out.println("Tile already played, pick another!");
            }
        }
        return play;
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
