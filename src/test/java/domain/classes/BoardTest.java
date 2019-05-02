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
        assertTrue(subject.isFinished());
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
        assertFalse(subject.isFinished());
        assertFalse(isWin);
    }

    @Test
    public void shouldNotMakePlayWhenIsAlreadyAWinner() throws NotAdmissibleValuesException, TileAlreadyPlayed {
        // Given
        subject = new Board();
        mockWinnerTiles(subject.getTiles());
        subject.doWeHaveAWinner();

        PlaySymbolEnum[] winnerTiles = subject.getTiles().clone();

        // When
        boolean result = subject.play(8, PlaySymbolEnum.X);

        // Then
        assertTrue(subject.isFinished());
        assertFalse(result);
        assertArrayEquals(winnerTiles, subject.getTiles());
    }

    @Test
    public void shouldNotMakePlayWhenGameOutOfMoves() throws NotAdmissibleValuesException, TileAlreadyPlayed {
        // Given
        subject = new Board();
        mockAllTiles(subject.getTiles());
        subject.doWeHaveAWinner();

        PlaySymbolEnum[] allTiles = subject.getTiles().clone();

        // When
        boolean result = subject.play(2, PlaySymbolEnum.X);

        // Then
        assertTrue(subject.isFinished());
        assertFalse(result);
        assertArrayEquals(allTiles, subject.getTiles());
    }

    // TODO: should finished when out of moves

    @Test
    public void shouldReturnToString() {
        // Given
        subject = new Board();

        // Then
        assertNotNull(subject.toString());
    }

    @Test
    public void testNullTiles() {
        // Given
        subject = new Board();
        mockNullTiles(subject.getTiles());

        // When
        boolean result = subject.doWeHaveAWinner();

        // Then
        assertFalse(result);
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

    private void mockAllTiles(PlaySymbolEnum[] tiles) {
        Arrays.fill(tiles, PlaySymbolEnum.NOTHING);
        // O X X
        tiles[0] = PlaySymbolEnum.O;
        tiles[1] = PlaySymbolEnum.X;
        tiles[2] = PlaySymbolEnum.X;

        // X X O
        tiles[3] = PlaySymbolEnum.X;
        tiles[4] = PlaySymbolEnum.X;
        tiles[5] = PlaySymbolEnum.O;

        // O O X
        tiles[6] = PlaySymbolEnum.O;
        tiles[7] = PlaySymbolEnum.O;
        tiles[8] = PlaySymbolEnum.X;
    }

    private void mockNullTiles(PlaySymbolEnum[] tiles) {
        Arrays.fill(tiles, null);
    }
}