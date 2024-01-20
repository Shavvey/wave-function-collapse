import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private static Dimension WINDOW_SIZE = new Dimension(500,500);
    // default constructor
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
                int drawScale = (drawHeight + drawHeight)/20;
            }
        };
        mainPanel.setPreferredSize(WINDOW_SIZE);
        mainPanel.setBackground(Color.WHITE);

    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> {
            // create a new window
            Window window = new Window();
            // set the window to be visible
            window.setVisible(true);
        });
    }

}

