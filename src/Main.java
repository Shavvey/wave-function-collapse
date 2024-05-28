import javax.swing.*;
public class Main {
    public static int CELL_SIZE = 50;
    public static int CELLS_PER_ROW = 10;
    // create a new window to run the wave function collapse algorithm
    private static Window window;
    // create the tile set, the window will have a reference to this tile set in order to display it
    private static final TileSet tileSet = new TileSet(10,50);
    public static void main(String[] args) {
        // create a new window
        window = new Window(tileSet);
        Tile tile = tileSet.getTile(0, 0);
        tile.setCollapsed(true);
        Tile.TileType[] options = {Tile.TileType.DOWN};
        tile.setOptions(options);
        // repaint the screen
        window.repaint();
    }
}