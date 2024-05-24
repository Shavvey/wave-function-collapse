import javax.swing.*;
public class Main {
    // create a new window to run the wave function collapse algorithm
    private static Window window;
    public static void main(String[] args) {
        // use a new thread to handle the execution of the window
        SwingUtilities.invokeLater(() -> {
            // create a new window
            window = new Window();
        });
    }
}