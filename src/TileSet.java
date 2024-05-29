
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class TileSet {
    private Tile[][] tiles;
    // implementing a comparator for the
    private final static Comparator<Tile> compare = (t1, t2) -> {
        int len1 = t1.entropy();
        int len2 = t2.entropy();
        // compare the number of possible options the two tiles still have
        // this is serves as the rudimentary measure of "entropy"
        return Integer.compare(len1, len2);
    };
    // construct a random seed to pull random number for
    // used in the get random tile method
    private static final Random rand = new Random();


    // constructor to create the tile set
    private final int cellsPerRow;
    private final int cellSize;
    TileSet(int cellsPerRow, int cellSize) {
        this.tiles = new Tile[cellsPerRow][cellsPerRow];
        this.cellsPerRow = cellsPerRow;
        this.cellSize = cellSize;
        for (int i = 0; i < cellsPerRow; ++i) {
            for (int j = 0; j < cellsPerRow; ++j) {
                // construct the new tiles, keep track of where they are in the tileset using i,j
                tiles[i][j] = new Tile(i,j);
            }
        }
    }

    // get a random tile from the tile set
    public Tile getRandom() {
        int len = tiles.length;
        // get indices into 2d array
        int idx1 = rand.nextInt(len);
        int idx2 = rand.nextInt(len);
        // return a tile randomly indexed from the array
        return tiles[idx1][idx2];

    }

    public int getCellsPerRow() {
        return cellsPerRow;
    }

    public int getCellSize() {
        return cellSize;
    }

    Tile getTile(int x, int y) {
        return tiles[x][y];
    }
    public void update() {
        // copy the tiles inside the arraylist, with a capacity for the entire tiles array
        ArrayList<Tile> tileList = new ArrayList<>(tiles.length);
        for (Tile[] tiles : tiles) {
            for(Tile tile : tiles) {
                if(!tile.isCollapsed()) {
                    // add all non collapsed tiles to the arraylist
                    tileList.add(tile);
                }
            }
        }
        // sort the non-collapsed tiles by the amount of entropy the tiles contain
        tileList.sort(compare);
        for (Tile t : tileList) {
            System.out.println("Compare val: " + t.entropy());
        }


    }
}
