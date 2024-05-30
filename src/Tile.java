import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Tile {
    // row index
    private final int x;
    // column index
    private final int y;
    // should index into the tileset using tileset[x][y]
    // this should control the tiles color and rules set
    private boolean isCollapsed;
    // the number of options a cell can be at a given moment
    private List<TileType> options;

    public int entropy() {
        return options.size();

    }


    // enum type that describes the tile type, should have a few types + a rule set for the tiles
    public enum TileType {
        BLANK("images/blank.png", new int[]{0, 0, 0, 0}),
        UP("images/up.png",new int[]{1, 1, 1, 0}),
        DOWN("images/down.png",new int[]{1, 0, 1, 1}),
        lEFT("images/left.png",new int[]{1, 1, 0, 1}),
        RIGHT("images/right.png",new int[]{0, 1, 1, 1});
        final int[] edges;
        final BufferedImage image;
        TileType(String filePath, int[] edges) {
            this.edges = edges;
            BufferedImage img = null;
            // use try catch loop to load in image
            try {
                img = ImageIO.read(new File(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.image = img;
        }
    }
    TileType getOptions(int index) {
        return options.get(index);
    }

    public boolean isCollapsed() {
        return isCollapsed;
    }

    public void setCollapsed(boolean collapsed) {
        // set the boolean collapsed value
        isCollapsed = collapsed;
    }

    public void setOptions(TileType[] options) {
        this.options = List.of(options);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    Tile(int x, int y) {
        // tiles shouldn't be collapsed yet when constructing the tiles
        isCollapsed = false;
        this.options = new ArrayList<>(TileType.values().length);
        options.addAll(List.of(TileType.values()));
        // keeping track of these indices will be useful later on
        this.x = x;
        this.y = y;
    }
}
