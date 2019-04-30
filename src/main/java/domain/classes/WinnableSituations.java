package domain.classes;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

public class WinnableSituations {
    private static final Set<WinnableSituation> SITUATIONS = ImmutableSet.of(
            // Vertical
            new WinnableSituation(1, 2, 3),
            new WinnableSituation(4, 5, 6),
            new WinnableSituation(7, 8, 9),

            // Diagonal
            new WinnableSituation(1, 5, 9),
            new WinnableSituation(3, 5, 7),

            // Horizontal
            new WinnableSituation(1, 4, 7),
            new WinnableSituation(2, 5, 8),
            new WinnableSituation(3, 6, 9)
    );

    public static Set<WinnableSituation> getSituations() {
        return SITUATIONS;
    }
}
