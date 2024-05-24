import javax.swing.*;
import java.awt.*;

public class Window extends JFrame { ;
    private static final int NUM_CELLS = 50;
    private static final int CELL_SIZE = 20;
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color DRAW_COLOR = Color.WHITE;
    // preferred window size of the application
    private static final Dimension WINDOW_SIZE = new Dimension(CELL_SIZE*NUM_CELLS,CELL_SIZE*NUM_CELLS);
    // default constructor for the window class
    Window() {
        // calling super to invoke extended JFrame construct
        super("Wave Function Collapse");
        // allowing exit to close the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(DRAW_COLOR);
                // scaling the draw width based on the found height of the JPanel
                initGrid(g);
            }
        };
        // set size and color of the window background
        mainPanel.setPreferredSize(WINDOW_SIZE);
        mainPanel.setBackground(BACKGROUND_COLOR);
        // add the main panel to the frame
        this.add(mainPanel);

    }

    public void initGrid(Graphics g) {
        // draw the grid lines
        for (int i = 0; i < WINDOW_SIZE.width; i++) {
            for (int j = 0; j < WINDOW_SIZE.height; j++) {
                g.drawRect(i*CELL_SIZE,j*CELL_SIZE, CELL_SIZE,CELL_SIZE);
            }
        }
    }

    public static void main(String[] args) {
        // use a new thread to handle the execution of the window
        SwingUtilities.invokeLater(()-> {
            // create a new window
            Window window = new Window();
            // set the window to be visible
            window.setVisible(true);
            // set the preferred size of the window
            window.setSize(WINDOW_SIZE);

        });
        // end of main
    }

}

