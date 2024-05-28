import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    // tick rate controls how fast the update and display loop will occur
    // NOTE: maybe this should be inside the main class???
    private static int tickRate;
    // used to display the state of the tile set as the algorithm continues
    TileSet tileSet;
    private static final int HEIGHT_OFFSET = 40;
    // in the future we should be able to pause the update and display loop
    private static boolean isPaused = false;
    private static final  Color COLLAPSED_COLOR = Color.BLACK;
    private static final Color GRID_COLOR = Color.WHITE;
    // tile set used for wave function collapse algorithm
    // preferred window size of the application

    // default constructor for the window class
    Window(TileSet tileSet) {
        // calling super to invoke extended JFrame constructor, which sets the title
        super("Wave Function Collapse");
        this.tileSet = tileSet;
        final Dimension WINDOW_SIZE = new Dimension(tileSet.cellsPerRow * tileSet.cellSize, tileSet.cellsPerRow*tileSet.cellSize + HEIGHT_OFFSET);
        // allowing exit to close the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // set the draw color to init the grid
                // scaling the draw width based on the found height of the JPanel
                initGrid(g);
            }
        };
        mainPanel.setBackground(Color.BLACK);
        // add the main panel to the frame
        this.add(mainPanel);
        // pack the main panel to the frame
        this.pack();
        // set the window to be visible
        this.setVisible(true);
        // set the preferred size of the window
        this.setSize(WINDOW_SIZE);
        // setting the window to not be, resizable, for now..
        this.setResizable(false);
    }
    // getter and setter for tick rate, which controls animation speed
    public static int getTickRate() {
        return tickRate;
    }

    public static void setTickRate(int tickRate) {
        Window.tickRate = tickRate;
    }

    public static boolean isPaused() {
        return isPaused;
    }

    public void initGrid(Graphics g) {
        // some hacky stuff to ignore the window bar, when drawing the grids
        int width = tileSet.cellsPerRow;
        int height =  tileSet.cellsPerRow;
        int cellSize = tileSet.cellSize;
        g.setColor(GRID_COLOR);
        // draw the grid lines
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                g.drawRect(i * cellSize,j * cellSize, cellSize, cellSize);
            }
        }
    }

    // animation loop the window should implement to control the display of the wave function collapse algorithm
    public void update(Graphics g) {
        // some hacky stuff to ignore the window bar, when drawing the grids
        int width = tileSet.cellsPerRow;
        int height =  tileSet.cellsPerRow;
        int cellSize = tileSet.cellSize;
        // draw the grid lines
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Tile tile = tileSet.tiles[i][j];
                boolean collapsed = tileSet.tiles[i][j].isCollapsed();
                if (collapsed) {
                    // get the color that from the TileType enum
                    g.setColor(tile.getOptions(0).color);
                } else {
                    g.setColor(COLLAPSED_COLOR);
                }

                g.fillRect(i * cellSize,j * cellSize, cellSize, cellSize);
            }
        }
    }


}

