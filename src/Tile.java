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
    protected List<TileType> options;

    public int entropy() {
        return options.size();

    }

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
    // mod that can handle negative numbers
    static public int mod(int a, int b) {
        return (a % b + b) % b;
    }

    // use this function to collapse tiles
    public void collapse(String edges, TileSet.Direction dir) {
        // LEFT DOWN UP RIGHT (0,1,2,3)
        List<TileType> valid = new ArrayList<>();
        switch (dir) {
            // look at right-center and left-neighbour edges to see if they match
            case LEFT -> {
                // loop through options, left and right need to match
                for (TileType opt : options) {
                    String nEdges = opt.getEdges();
                    if(edges.charAt(0) == nEdges.charAt(3)) {
                        valid.add(opt);
                    }
                }
                this.setOptions(valid);
            }
            case DOWN -> {
                for (TileType opt : options) {
                    String nEdges = opt.getEdges();
                    if(edges.charAt(1) == nEdges.charAt(2)) {
                        valid.add(opt);
                    }
                }
                this.setOptions(valid);
            }
            case UP -> {
                for (TileType opt : options) {
                    String nEdges = opt.getEdges();
                    if(edges.charAt(2) == nEdges.charAt(1)) {
                        valid.add(opt);
                    }
                }
                this.setOptions(valid);
            }
            case RIGHT -> {
                for (TileType opt : options) {
                    String nEdges = opt.getEdges();
                    if(edges.charAt(3) == nEdges.charAt(0)) {
                        valid.add(opt);
                    }
                }
                this.setOptions(valid);
            }
        }

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
