import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    // tick rate controls how fast the update and display loop will occur
    // NOTE: maybe this should be inside the main class???
    private final int tickRate;
    // used to display the state of the tile set as the algorithm continues
    TileSet tileSet;
    private static final int HEIGHT_OFFSET = 40;
    // in the future we should be able to pause the update and display loop
    private boolean isPaused;
    private static final Color GRID_COLOR = Color.WHITE;
    // tile set used for wave function collapse algorithm
    // preferred window size of the application

    // default constructor for the window class
    Window(TileSet tileSet, int tickRate) {
        // calling super to invoke extended JFrame constructor, which sets the title
        super("Wave Function Collapse");
        this.isPaused = true;
        this.tileSet = tileSet;
        this.tickRate = tickRate;
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

        JPanel buttonPanel = getjPanel();
        mainPanel.setBackground(Color.BLACK);
        // add the main panel to the frame
        this.add(mainPanel,BorderLayout.CENTER);
        this.add(buttonPanel,BorderLayout.PAGE_START);
        // pack the main panel to the frame
        this.pack();
        // set the window to be visible
        this.setVisible(true);
        // set the preferred size of the window
        this.setSize(WINDOW_SIZE);
        // setting the window to not be, resizable, for now..
        this.setResizable(false);
    }

    private JPanel getjPanel() {
        JPanel buttonPanel = new JPanel();
        JButton pauseButton = new JButton("Unpause");

        pauseButton.addActionListener(_ -> {
            if(isPaused) {
                isPaused = false;
                pauseButton.setText("Pause");
            } else {
                isPaused = true;
                pauseButton.setText("Unpause");
            }

        });
        buttonPanel.add(pauseButton);
        return buttonPanel;
    }

    private void delay(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception ex) {
            System.err.println("Error: could not sleep thread...");
        }

    }
    public void animate() {
        int delay = 1000 / tickRate;
        // continue animating if tileset is not complete and window is not paused
        if(!isPaused) {
            tileSet.update();
            repaint();
            // insert delay in update and render loop
            delay(delay);
        } else {
            delay(delay);
        }
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
                    g.drawImage(resizedImage,i * cellSize, j * cellSize, cellSize, cellSize,null);
                } else {
                    // if the tiles is not collapsed yet, just draw the grid where the cell is inside the window
                    g.drawRect(i * cellSize,j * cellSize, cellSize, cellSize);
                }
            }
        }
    }


}

