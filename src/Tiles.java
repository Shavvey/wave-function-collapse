import java.awt.*;

public class Tiles {
    // this should control the tiles color and rules set
    Color tileColor;
    boolean isCollapsed;
    private int tileSize;


    // enum type that describes the tile type, should have a few types + a rule set for the tiles
    public enum TileType {

    }

    Tiles(int size) {
        isCollapsed = false;
        // get size from params
        this.tileSize = size;
    }
}
