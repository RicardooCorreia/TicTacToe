package client.players;

import domain.classes.Board;
import domain.enums.PlaySymbolEnum;
import domain.enums.PlayerType;
import org.apache.maven.surefire.shade.org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class CMDPlayerTest {

    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private CMDPlayer subject;

    @Mock
    private Board board;

    @Before
    public void setUp() {
        initMocks(this);
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void shouldMakeAPlay() throws IOException {
        // Given
        subject = new CMDPlayer(mockScannerWith("5"), PlaySymbolEnum.X, "Test subject");

        // When
        int play = subject.doPlay(board, subject.getPlaySymbol());

        // Then
        assertEquals(play, 4);
    }

    @Test
    public void shouldReturnCMDPlayerType() throws IOException {
        // Given
        subject = new CMDPlayer(mockScannerWith("5"), PlaySymbolEnum.X, "Test subject");

        // When
        PlayerType playerType = subject.getPlayerType();

        // Then
        assertEquals(playerType, PlayerType.CMD_UI);
    }

    @Test
    public void shouldReturnPlaySymbol() throws IOException {
        // Given
        subject = new CMDPlayer(mockScannerWith("5"), PlaySymbolEnum.X, "Test subject");

        // When
        PlaySymbolEnum playSymbolEnum = subject.getPlaySymbol();

        // Then
        assertEquals(playSymbolEnum, PlaySymbolEnum.X);
    }

    @Test
    public void shouldReturnPlayerName() throws IOException {
        // Given
        String name = "Test Subject";
        subject = new CMDPlayer(mockScannerWith("5"), PlaySymbolEnum.X, name);

        // When
        String returnedName = subject.getName();

        // Then
        assertEquals(returnedName, name);

    }

    @Test
    public void shouldWarnAboutInvalidValues() throws IOException {
        // Given
        subject = new CMDPlayer(mockScannerWith("99 2"), PlaySymbolEnum.X, "Test subject");

        // We need a real board to make the test and not a mocked one
        Board notMockedboard = new Board();

        // When
        subject.doPlay(notMockedboard, subject.getPlaySymbol());

        // Then
        assertEquals(CMDPlayer.getInvalidValuesMessage(), outContent.toString().trim());
    }

    @Test
    public void shouldWarnAboutDuplicatedMoves() throws IOException {
        // Given
        subject = new CMDPlayer(mockScannerWith("2 2 3"), PlaySymbolEnum.X, "Test subject");

        // We need a real board to make the test and not a mocked one
        Board notMockedboard = new Board();

        // When
        subject.doPlay(notMockedboard, subject.getPlaySymbol());
        subject.doPlay(notMockedboard, subject.getPlaySymbol());

        // Then
        assertEquals(CMDPlayer.getTileAlreadyPlayedMessage(), outContent.toString().trim());
    }

    private InputStream inputStreamOf(String input) throws IOException {
        return IOUtils.toInputStream(input, "UTF-8");
    }

    private Scanner mockScannerWith(String input) throws IOException {
        return new Scanner(inputStreamOf(input));
    }
}