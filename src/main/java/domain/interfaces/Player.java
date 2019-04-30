package domain.interfaces;

import domain.enums.PlaySymbolEnum;
import domain.classes.Board;
import domain.enums.PlayerType;

public interface Player {

    int doPlay(Board board, PlaySymbolEnum symbol);
    PlayerType getPlayerType();
    PlaySymbolEnum getPlaySymbol();
    String getName();
}
