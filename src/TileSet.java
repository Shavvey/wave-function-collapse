public class TileSet {
    private Tiles[][] tileSet;
    // constructor to create the tile set
    public int cellsPerRow;
    TileSet(int cellsPerRow) {
        tileSet = new Tiles[cellsPerRow][cellsPerRow];
        this.cellsPerRow = cellsPerRow;
    }
}
