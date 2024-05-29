import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class TileSet {
    private Tile[][] tiles;
    // constructor to create the tile set
    private final int cellsPerRow;
    private final int cellSize;
    TileSet(int cellsPerRow, int cellSize) {
        this.tiles = new Tile[cellsPerRow][cellsPerRow];
        this.cellsPerRow = cellsPerRow;
        this.cellSize = cellSize;
        for (int i = 0; i < cellsPerRow; ++i) {
            for (int j = 0; j < cellsPerRow; ++j) {
                // construct the new tiles
                tiles[i][j] = new Tile();
            }
        }
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
        // copy the tiles inside the arraylist
        ArrayList<Tile> tileList = new ArrayList<>();
        for (Tile[] t : tiles) {
            tileList.addAll(Arrays.asList(t));
        }
        // use lambda expression to the sort the two tiles
        tileList.sort((Tile t1, Tile t2) -> {
            int len1 = t1.entropy();
            int len2 = t2.entropy();
            // compare the number of possible options the two tiles still have
            // this is serves as the rudimentary measure of "entropy"
            return Integer.compare(len1, len2);
        });
        for (Tile t : tileList) {
            System.out.println("Compare val: " + t.entropy());
        }

    }
}
