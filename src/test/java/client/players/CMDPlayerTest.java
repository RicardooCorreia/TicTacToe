package client.players;

import domain.classes.Board;
import domain.enums.PlaySymbolEnum;
import domain.enums.PlayerType;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class CMDPlayerTest {

    private CMDPlayer subject;

    @Mock
    private Board board;

    @Before
    public void setUp() {
        initMocks(this);
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
        subject = new CMDPlayer(mockScannerWith("-1"), PlaySymbolEnum.X, "Test subject");

        // When
        subject.doPlay(board, subject.getPlaySymbol());

        // Then
            // TODO - Test for System.out
    }

    @Test
    public void shouldWarnAboutDuplicatedMoves() throws IOException {
        // Given
        subject = new CMDPlayer(mockScannerWith("2\n2"), PlaySymbolEnum.X, "Test subject");

        // When
        subject.doPlay(board, subject.getPlaySymbol());
        subject.doPlay(board, subject.getPlaySymbol());

        // Then
            // TODO - Test for System.out
    }

    private InputStream inputStreamOf(String input) throws IOException {
        return IOUtils.toInputStream(input, "UTF-8");
    }

    private Scanner mockScannerWith(String input) throws IOException {
        return new Scanner(inputStreamOf(input));
    }
}