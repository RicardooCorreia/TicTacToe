package domain.enums;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTypeTest {

    @Test
    public void testPlayerType() {
        // Given
        PlayerType playerType = PlayerType.BOT_RANDOM;

        // Then
        assertNotNull(playerType);
    }
}