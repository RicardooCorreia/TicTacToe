package runner;

import client.players.CMDPlayer;
import domain.classes.Board;
import domain.enums.PlaySymbolEnum;
import domain.interfaces.Player;

import java.util.Scanner;

public class GameRunner {

    public static void main(String[] args) {
        Board board;
        Player player1, player2;

        // TODO - Change to loop

        board = new Board();
        Scanner scanner = new Scanner(System.in);

        player1 = new CMDPlayer(scanner, PlaySymbolEnum.X, "Player 1");
        player2 = new CMDPlayer(scanner, PlaySymbolEnum.O, "Player 2");

        boolean flagPlayer = true;
        System.out.println(board.toString());
        Player nextPlayer = null;
        while (!board.isFinished()) {
            nextPlayer = flagPlayer ? player1 : player2;
            flagPlayer = !flagPlayer;

            System.out.println(nextPlayer.getName() + " is next!");
            nextPlayer.doPlay(board, nextPlayer.getPlaySymbol());
            System.out.println(board.toString());
        }

        assert nextPlayer != null;
        System.out.println(nextPlayer.getName() + " wins!");
    }
}
