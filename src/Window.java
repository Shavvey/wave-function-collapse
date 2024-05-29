import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

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
        int cellsPerRow = tileSet.getCellsPerRow();
        int cellSize = tileSet.getCellSize();
        final Dimension WINDOW_SIZE = new Dimension(cellsPerRow * cellSize, cellsPerRow * cellSize + HEIGHT_OFFSET);
        // allowing exit to close the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(GRID_COLOR);
                super.paintComponent(g);
                // call draw method
                drawTileSet(g);
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


    // animation loop the window should implement to control the display of the wave function collapse algorithm
    public void drawTileSet(Graphics g) {
        // some hacky stuff to ignore the window bar, when drawing the grids
        int width = tileSet.getCellsPerRow();
        int height = tileSet.getCellsPerRow();
        int cellSize = tileSet.getCellSize();
        // draw the grid lines
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Tile tile = tileSet.getTile(i,j);
                boolean collapsed = tile.isCollapsed();
                if (collapsed) {
                    // just get the first option for now
                    Tile.TileType option = tile.getOptions(0);
                    // NOTE: maybe we should do this inside the constructor of the TileType enum
                    Image resizedImage = option.image.getScaledInstance(cellSize,cellSize,Image.SCALE_DEFAULT);
                    g.drawImage(resizedImage,0, 0, cellSize, cellSize,null);
                } else {
                    // if the tiles is not collapsed yet, just draw the grid where the cell is inside the window
                    g.drawRect(i * cellSize,j * cellSize, cellSize, cellSize);
                }
            }
        }
    }


}

