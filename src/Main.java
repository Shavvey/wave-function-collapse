import javax.swing.*;
public class Main {
    // create a new window to run the wave function collapse algorithm
    private static Window window;
    private static final TileSet tileSet = new TileSet(10,50);
    public static void main(String[] args) {
        // use a new thread to handle the execution of the window
        SwingUtilities.invokeLater(() -> {
            // create a new window
            window = new Window(tileSet);
        });
        // update the tile set used for the wave function collapse algorithm
        }
}