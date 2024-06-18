
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class TileSet {
    private final Tile[][] tiles;
    private int numCollapsed;
    ArrayList<Tile> tileList;
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
    // use this to apply direction
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
        // copy the tiles inside the arraylist, with a capacity for the entire tiles array
        tileList = new ArrayList<>(tiles.length);
        for (Tile[] tiles : tiles) {
            for(Tile tile : tiles) {
                if(!tile.isCollapsed()) {
                    // add all non collapsed tiles to the arraylist
                    tileList.add(tile);
                }
            }
        }

    }
    // boolean to check whether the full tileset has been collapsed
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
        String edges = tile.getOptions(0).getEdges();
        // LEFT DOWN UP RIGHT
        // look at each of the possible types of neighboring tiles and collapse down impossible combinations
        int cx = tile.getX();
        int cy = tile.getY();
        // get edges but reverse them
        for (Direction dir : Direction.values()) {
            int dx = dir.x;
            int dy = dir.y;
            // get x and y indices for each neighboring tile
            int x = Tile.mod(cx + dx, this.cellsPerRow);
            int y = Tile.mod(cy + dy, this.cellsPerRow);
            // get the neighboring tiles
            Tile neighbor = tiles[x][y];
            neighbor.collapse(edges,dir);
            // deal with an edge case where no valid tile exist, in which case use a blank tile
            if(neighbor.entropy() == 0) {
                neighbor.setOptions(List.of(TileType.BLANK));
            }

        }

    }

    public void update() {
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
        // return a random option that is currently available and then collapse the tile
        TileType opt = tile.getOptions(rand.nextInt(0,len));
        tile.setOptions(List.of(opt));
        tile.setCollapsed(true);
        // remove collapsed tile from tileList
        tileList.remove(tile);
        // tally the amount of tiles currently collapsed
        numCollapsed++;
        // propagate tile information
        propagate(tile);
    }
}
