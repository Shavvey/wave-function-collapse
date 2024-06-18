import javax.imageio.ImageIO;
import java.awt.Image;
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
    // a string representation of all the edges
    final String edges;
    final Image image;
    // edges should be arranged LEFT UP DOWN RIGHT
    //
    TileType(String filePath, String edges) {
        this.edges = edges;
        Image img = null;
        // use try catch loop to load in image
        try {
            // read the file path provided by enum constructor and scale according to the cell of each cell
            img = ImageIO.read(new File(filePath)).getScaledInstance(Main.CELL_SIZE, Main.CELL_SIZE, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            System.err.println("Error: could not load the image");
        }
        this.image = img;
    }
    public String getEdges() {
        return edges;
    }
}