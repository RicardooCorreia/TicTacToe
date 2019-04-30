package game;

import client.players.CMDPlayer;
import domain.classes.Board;
import domain.enums.PlaySymbolEnum;
import domain.interfaces.Player;

import java.util.Scanner;

public class GameController {

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
        while (!board.isFinished()) {
            Player nextPlayer = flagPlayer ? player1 : player2;
            flagPlayer = !flagPlayer;

            System.out.println(nextPlayer.getName() + " is next!");
            nextPlayer.doPlay(board, nextPlayer.getPlaySymbol());
            System.out.println(board.toString());
        }
    }
}
