import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private static int tickRate;
    // creates a square matrix with the same row and column size, using the var below
    private static final int NUM_CELLS_PER_ROW = 10;
    private static final int CELL_SIZE = 60;
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color GRID_COLOR = Color.WHITE;
    private static final int HEIGHT_OFFSET = 40;
    // in the future we should be able to pause the update and display loop
    private static boolean isPaused = false;
    // tile set used for wave function collapse algorithm
    // preferred window size of the application
    private static final Dimension WINDOW_SIZE =
            new Dimension(CELL_SIZE * NUM_CELLS_PER_ROW,CELL_SIZE * NUM_CELLS_PER_ROW + HEIGHT_OFFSET);
    // default constructor for the window class
    Window() {
        // calling super to invoke extended JFrame constructor, which sets the title
        super("Wave Function Collapse");
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
        mainPanel.setBackground(BACKGROUND_COLOR);
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
        g.setColor(GRID_COLOR);
        // some hacky stuff to ignore the window bar, when drawing the grids
        int width = (int) (WINDOW_SIZE.getWidth()/CELL_SIZE);
        int height = (int) ((WINDOW_SIZE.getHeight() - HEIGHT_OFFSET)/CELL_SIZE);

        // draw the grid lines
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                g.drawRect(i * CELL_SIZE,j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    // animation loop the window should implement to control the display of the wave function collapse algorithm
    public void update(Graphics g) {

    }


}

