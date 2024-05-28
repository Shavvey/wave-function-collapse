import java.awt.*;

public class Tile {
    // this should control the tiles color and rules set
    private boolean isCollapsed;
    // the number of options a cell can be at a given moment
    private TileType[] options;

    // enum type that describes the tile type, should have a few types + a rule set for the tiles
    public enum TileType {
        // creating different tile types
        // beach tiles can connect to other beach tiles, and ocean and grass tiles
        BEACH(Color.YELLOW),
        // ocean tiles can connect to other beach tiles, and beach tiles
        OCEAN(Color.BLUE),
        // grass tiles can connect to other grass tiles
        GRASS(Color.GREEN);

        final Color color;
        TileType(Color color){
            this.color = color;

        }
    }
    TileType getOptions(int index) {
        return options[index];
    }

    public boolean isCollapsed() {
        return isCollapsed;
    }

    Tile() {
        // tiles shouldn't be collapsed yet when constructing the tiles
        isCollapsed = false;
        this.options = TileType.values();
    }
}
