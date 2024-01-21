import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    // preferred window size of the application
    private static final Dimension WINDOW_SIZE = new Dimension(1000,1000);
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
                int drawWidth = this.getWidth();
                int drawHeight = this.getHeight();
                // scaling the draw width based on the found height of the JPanel
                int drawScale = (drawHeight + drawWidth)/20;
            }
        };
        mainPanel.setPreferredSize(WINDOW_SIZE);
        mainPanel.setBackground(Color.WHITE);

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

