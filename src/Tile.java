import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Comparator;
import java.io.File;

public class Tile {
    // this should control the tiles color and rules set
    private boolean isCollapsed;
    // the number of options a cell can be at a given moment
    private TileType[] options;

    public int entropy() {
        return options.length;
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
        return options[index];
    }

    public boolean isCollapsed() {
        return isCollapsed;
    }

    public void setCollapsed(boolean collapsed) {
        isCollapsed = collapsed;
    }

    public void setOptions(TileType[] options) {
        this.options = options;
    }

    Tile() {
        // tiles shouldn't be collapsed yet when constructing the tiles
        isCollapsed = false;
        this.options = TileType.values();
    }
}
