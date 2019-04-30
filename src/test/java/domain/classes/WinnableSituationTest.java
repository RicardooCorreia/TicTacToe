package domain.classes;

import org.junit.Test;

import static org.junit.Assert.*;

public class WinnableSituationTest {

    WinnableSituation subject;

    @Test
    public void testGetters() {
        // Given
        subject = new WinnableSituation(1, 2, 3);

        // When
        int tile1 = subject.getTile1();
        int tile2 = subject.getTile2();
        int tile3 = subject.getTile3();

        // Then
        assertEquals(tile1, 1);
        assertEquals(tile2, 2);
        assertEquals(tile3, 3);
    }
}