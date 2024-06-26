
public class Main {
    // constants that could be later used as command line arguments or something
    public final static int CELL_SIZE = 25;
    public final static int CELLS_PER_ROW = 40;
    public final static int TICK_RATE = 150;
    // create the tile set, the window will have a reference to this tile set in order to display it
    private static final TileSet tileSet = new TileSet(CELLS_PER_ROW, CELL_SIZE);
    public static void main(String[] args) {
        // window has a reference to the created tile set in order to draw its results to the screen
        // create a new window to run the wave function collapse algorithm
        Window window = new Window(tileSet,TICK_RATE);
        while(!tileSet.isComplete()) {
            //System.out.print("");
            window.animate();
        }
    }
}