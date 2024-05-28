public class TileSet {
    public Tile[][] tiles;
    // constructor to create the tile set
    public int cellsPerRow;
    public int cellSize;
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
}
