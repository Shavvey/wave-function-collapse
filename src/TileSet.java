public class TileSet {
    private Tiles[][] tileSet;
    // constructor to create the tile set
    public int cellsPerRow;
    TileSet(int cellsPerRow) {
        tileSet = new Tiles[cellsPerRow][cellsPerRow];
        this.cellsPerRow = cellsPerRow;
        for (int i = 0; i < cellsPerRow; ++i) {
            for (int j = 0; j < cellsPerRow; ++j) {
                // iterate through all the cells of the tile set
            }
        }
    }
}
