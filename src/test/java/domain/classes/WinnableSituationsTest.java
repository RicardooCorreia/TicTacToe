package domain.classes;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class WinnableSituationsTest {

    @Test
    public void shouldReturnEightWinningSituations() {
        // Given
        Set<WinnableSituation> situations = WinnableSituations.getSituations();

        // Then
        assertNotNull(situations);
        assertEquals(situations.size(), 8);
    }

    @Test
    public void instanceTest() {
        // Given
        WinnableSituations subject = new WinnableSituations();

        // Then
        assertNotNull(subject);
    }
}