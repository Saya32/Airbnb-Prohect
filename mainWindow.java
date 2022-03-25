import java.awt.*;
import javax.swing.*;

/**
 * Creates the main window that the program is held on.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class mainWindow
{
    // instance variables
    private JFrame frame;
    private welcomePanel welcome;

    /**
     * Create the main window and show it on screen.
     */
    public mainWindow()
    {
        makeFrame();
    }

    /**
     * Creates the main window and starts the user on the welcome panel.
     */
    private void makeFrame()
    {
        frame = new JFrame("Airbnb viewer");
        frame.setPreferredSize(new Dimension(1000, 800));
        welcome = new welcomePanel();
        frame.add(welcome);
        frame.setVisible(true);
        frame.pack();
    }
    
}