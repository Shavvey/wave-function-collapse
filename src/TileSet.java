public class TileSet {
    private Tile[][] tiles;
    // constructor to create the tile set
    private int cellsPerRow;
    private int cellSize;
    TileSet(int cellsPerRow, int cellSize) {
        this.tiles = new Tile[cellsPerRow][cellsPerRow];
        this.cellsPerRow = cellsPerRow;
        this.cellSize = cellSize;
        for (int i = 0; i < cellsPerRow; ++i) {
            for (int j = 0; j < cellsPerRow; ++j) {
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
}
