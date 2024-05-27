import java.awt.*;

public class Tiles {
    // this should control the tiles color and rules set
    private boolean isCollapsed;
    private int tileSize;
    private TileType type;

    // enum type that describes the tile type, should have a few types + a rule set for the tiles
    public enum TileType {
        // creating different tile types
        BEACH(Color.YELLOW);
        final Color color;
        TileType(Color color){
            this.color = color;
        }
    }

    public boolean isCollapsed() {
        return isCollapsed;
    }

    Tiles(int size) {
        // tiles shouldn't be collapsed yet when constructing the tiles
        isCollapsed = false;
        // get size from params
        this.tileSize = size;
    }
}
