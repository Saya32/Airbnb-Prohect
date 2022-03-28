import java.awt.*;
import javax.swing.*;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Creates the main window that the program is held on.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class mainWindow extends Application
{
    // instance variables
    public static JFrame frame;
    public static welcomePanel welcome;
    public static Map map;
    public static Statistics stats;

    /**
     * Create the main window and show it on screen.
     */
    @Override
    public void start(Stage stage)
    {
        makeFrame();
    }
    
    public static void main(String[] args)
    {
        mainWindow main = new mainWindow();
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
