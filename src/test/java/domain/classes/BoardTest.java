package domain.classes;

import domain.enums.PlaySymbolEnum;
import domain.exceptions.NotAdmissibleValuesException;
import domain.exceptions.TileAlreadyPlayed;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class BoardTest {
    private Board subject;


    @Test
    public void shouldMakePlay() throws NotAdmissibleValuesException, TileAlreadyPlayed {
        // Given
        subject = new Board();
        PlaySymbolEnum[] expectedTiles = new PlaySymbolEnum[9];
        Arrays.fill(expectedTiles, PlaySymbolEnum.NOTHING);
        expectedTiles[1] = PlaySymbolEnum.O;

        // When
        subject.play(1, PlaySymbolEnum.O);

        // Then
        assertArrayEquals(expectedTiles, subject.getTiles());
        assertEquals(subject.getTiles()[1], PlaySymbolEnum.O);
    }

    @Test(expected = NotAdmissibleValuesException.class)
    public void shouldNotAcceptValueAboveMax() throws NotAdmissibleValuesException, TileAlreadyPlayed {
        // Given
        subject = new Board();

        // When
        subject.play(10, PlaySymbolEnum.O);
    }

    @Test(expected = NotAdmissibleValuesException.class)
    public void shouldNotAcceptValueBelowMin() throws NotAdmissibleValuesException, TileAlreadyPlayed {
        // Given
        subject = new Board();

        // When
        subject.play(-1, PlaySymbolEnum.O);
    }

    @Test(expected = TileAlreadyPlayed.class)
    public void shouldNotAcceptTileWherePlayWasAlreadyMade() throws NotAdmissibleValuesException, TileAlreadyPlayed {
        // Given
        subject = new Board();
        subject.play(0, PlaySymbolEnum.O);

        // When
        subject.play(0, PlaySymbolEnum.X);
    }

    @Ignore("Not implemented yet!")
    @Test
    public void shouldNotAllowSameSymbolToMakeTwoMoves() {
    }

    @Test
    public void shouldGetWinner() {
        // Given
        subject = new Board();
        mockWinnerTiles(subject.getTiles());

        // When
        boolean isWin = subject.doWeHaveAWinner();

        // Then
        assertTrue(isWin);
    }

    @Test
    public void shouldNotGetWinner() {
        // Given
        subject = new Board();
        mockNotWinnerTiles(subject.getTiles());

        // When
        boolean isWin = subject.doWeHaveAWinner();

        // Then
        assertFalse(isWin);
    }

    @Test
    public void shouldReturnToString() {
        // Given
        subject = new Board();

        // Then
        assertNotNull(subject.toString());
    }

    private void mockNotWinnerTiles(PlaySymbolEnum[] tiles) {
        Arrays.fill(tiles, PlaySymbolEnum.NOTHING);
        tiles[0] = PlaySymbolEnum.X;
        tiles[1] = PlaySymbolEnum.X;
        tiles[3] = PlaySymbolEnum.X;
    }

    private void mockWinnerTiles(PlaySymbolEnum[] tiles) {
        Arrays.fill(tiles, PlaySymbolEnum.NOTHING);
        tiles[0] = PlaySymbolEnum.X;
        tiles[1] = PlaySymbolEnum.X;
        tiles[2] = PlaySymbolEnum.X;
    }
}