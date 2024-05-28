import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Comparator;
import java.io.File;

public class Tile implements Comparator<Tile> {
    // this should control the tiles color and rules set
    private boolean isCollapsed;
    // the number of options a cell can be at a given moment
    private TileType[] options;

    @Override
    public int compare(Tile t1, Tile t2) {
        int len1 = t1.options.length;
        int len2 = t2.options.length;
        // compare the number of possible options the two tiles still have
        // this is serves as the rudimentary measure of "entropy"
        return Integer.compare(len1, len2);
    }


    // enum type that describes the tile type, should have a few types + a rule set for the tiles
    public enum TileType {
        BLANK("images/blank.png"),
        UP("images/up.png"),
        DOWN("images/down.png"),
        lEFT("images/left.png"),
        RIGHT("images/right.png");
        final BufferedImage image;
        TileType(String filePath) {
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
