package domain.interfaces;

import domain.enums.PlaySymbolEnum;
import domain.classes.Board;

public interface Player {

    int doPlay(Board board, PlaySymbolEnum symbol);
}
