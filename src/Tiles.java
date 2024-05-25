import java.awt.*;

public class Tiles {
    // this should control the tiles color and rules set
    private boolean isCollapsed;
    private int tileSize;
    TileType type;


    // enum type that describes the tile type, should have a few types + a rule set for the tiles
    public enum TileType {

    }

    public boolean isCollapsed() {
        return isCollapsed;
    }

    Tiles(int size) {
        isCollapsed = false;
        // get size from params
        this.tileSize = size;
    }
}
