import javax.swing.*;
public class Main {
    // create a new window to run the wave function collapse algorithm
    private static Window window;
    // create the tile set
    private static final TileSet tileSet = new TileSet(10,50);
    public static void main(String[] args) {
        // create a new window
        window = new Window(tileSet);
        Tile tile = tileSet.getTile(1, 1);
        tile.setCollapsed(true);
        Tile.TileType[] options = {Tile.TileType.BEACH};
        tile.setOptions(options);
        // repaint the screen
        window.repaint();
    }
}