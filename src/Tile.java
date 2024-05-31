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
        // two different types of edges, type A: one without a connecting line, and type B: one with a connecting line
        BLANK("images/blank.png", "AAAA"),
        UP("images/up.png","BABB"),
        DOWN("images/down.png","BBAB"),
        lEFT("images/left.png","BBBA"),
        RIGHT("images/right.png","ABBB");
        final String edges;
        final BufferedImage image;
        // edges should be arranged LEFT UP DOWN RIGHT -> such that they can
        // be reversed to compare RIGHT DOWN UP LEFT, comparing connecting edges of neighboring tiles
        TileType(String filePath, String edges) {
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
        public String getEdges() {
            return edges;
        }
        public String getReverseEdges() {
            // get the reverse of the string edges
            return new StringBuilder(edges).reverse().toString();
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

    public void setOptions(List<TileType> options) {
        this.options = options;
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
