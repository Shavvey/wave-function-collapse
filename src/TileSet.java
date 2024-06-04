
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class TileSet {
    private Tile[][] tiles;
    private int numCollapsed;
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
    public boolean isComplete() {
        return (numCollapsed == (cellsPerRow*cellsPerRow));
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
    // method to propagate information to a collapse tile
    public void propagate(Tile tile) {
        // get the first tile option, if collapsed, this should be
        Tile.TileType opt = tile.getOptions(0);
        // LEFT DOWN UP RIGHT
        // look at each of the possible types of neighboring tiles and collapse down impossible combinations
        int cx = tile.getX();
        int cy = tile.getY();
        // get edges but reverse them
        String cEdges = opt.getReverseEdges();
        for (int dx = -1; dx <= 1; ++dx) {
            for (int dy = -1; dy <= 1; ++dy) {
                // check to see if we are at the center, which we do not want to check
                boolean center = (dx == 0 && dy == 0);
                if (!center) {
                    // get x and y indices for each neighboring tile
                    int x = Tile.mod(cx + dx, this.cellsPerRow);
                    int y = Tile.mod(cy + dy, this.cellsPerRow);
                    Tile t = tiles[x][y];
                    int size = t.entropy();
                    for (int e = 0; e < size; ++e) {
                        // get the possible edges
                        Tile.TileType pos = t.getOptions(e);
                        String edges = pos.getEdges();
                        int len = edges.length();
                        for (int i = 0; i < len; i++) {
                            char te = edges.charAt(i);
                            char ce = cEdges.charAt(i);
                            // collapse possibility if center and tile edge do not match
                            if (ce != te && ce == 'a') {
                                t.options.remove(pos);
                            }
                        }
                    }
                }
            }
        }
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
        Tile minTile = tileList.getFirst();
        int minEntropy = minTile.entropy();
        int minCount = 0;
        for (Tile t : tileList) {
            if(t.entropy() == minEntropy) {
                minCount++;
            }
        }

        int randVal = rand.nextInt(minCount);
        Tile tile = tileList.get(randVal);
        // collapse the first tile and then update the neighboring tiles
        final int len = tile.entropy();
        // pick a random options from the original set of options
        Tile.TileType opt = tile.getOptions(rand.nextInt(len));
        tile.setOptions(List.of(opt));
        tile.setCollapsed(true);
        // tally the amount of tiles currently collapsed
        numCollapsed++;
        // propagate tile information
        propagate(tile);
    }
}
