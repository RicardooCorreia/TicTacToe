package domain.classes;

public class WinnableSituation {

    private int tile1, tile2, tile3;

    WinnableSituation(int tile1, int tile2, int tile3) {
        this.tile1 = tile1;
        this.tile2 = tile2;
        this.tile3 = tile3;
    }

    public int getTile1() {
        return tile1;
    }

    public int getTile2() {
        return tile2;
    }

    public int getTile3() {
        return tile3;
    }
}
