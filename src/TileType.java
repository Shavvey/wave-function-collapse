import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// enum type that describes the tile type, should have a few types + a rule set for the tiles
public enum TileType {
    // two different types of edges, type A: one without a connecting line, and type B: one with a connecting line
    BLANK("images/blank.png", "AAAA"),
    UP("images/up.png","BBAB"),
    DOWN("images/down.png","BABB"),
    LEFT("images/left.png","BBBA"),
    RIGHT("images/right.png","ABBB");
    final String edges;
    final BufferedImage image;
    // edges should be arranged LEFT UP DOWN RIGHT
    //
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