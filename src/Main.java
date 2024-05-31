import javax.swing.*;
public class Main {
    // constants that could be later used as command line arguments or something
    public static int CELL_SIZE = 50;
    public static int CELLS_PER_ROW = 10;
    // create a new window to run the wave function collapse algorithm
    private static Window window;
    // create the tile set, the window will have a reference to this tile set in order to display it
    private static final TileSet tileSet = new TileSet(CELLS_PER_ROW, CELL_SIZE);
    public static void main(String[] args) {
        // window has a reference to the created tile set in order to draw its results to the screen
        window = new Window(tileSet);
        tileSet.update();
        window.repaint();
    }
}