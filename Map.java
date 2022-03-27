import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;

public class Map extends JPanel{
    
    private HashMap<String, ArrayList<Integer>> coordinates;
    private JPanel mapPanel; // the panel that holds the map.
    private JLabel mapLabel; // the map itself

    public Map() {
        buildMapWindow();
        coordinates = new HashMap<>();
    }
    private void buildMapWindow()

    {
        setLayout(new BorderLayout());

        // Initialises all the panels.
        JPanel bottomPanel = new JPanel(new BorderLayout());
        mapPanel = new JPanel();


        // Displays the map of London and adds it to the mapPanel.
        try {
            BufferedImage bimg = ImageIO.read(new File("borough3.png")); // extracts the file containing the map of london
            ImageIcon londonMap = new ImageIcon(); //
            londonMap.setImage(bimg); // sets the image of the imageIcon to be the map of London.
            mapLabel = new JLabel(londonMap);
            mapPanel.add(mapLabel);

        } catch (IOException exc) {
            return;
        }

        // Sets the borders of the panels.
        mapPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        bottomPanel.setBorder(BorderFactory.createMatteBorder(1,0,0,0,Color.black));

        // Initialises the components.
        JButton statisticsButton = new JButton("Statistics");
        JButton previousButton = new JButton("Welcome");
        previousButton.addActionListener(e-> goBackToWelcome());
        statisticsButton.addActionListener(e-> goToStatistics());

        // Adds the components to the panels
        bottomPanel.add(statisticsButton, BorderLayout.EAST);
        bottomPanel.add(previousButton,BorderLayout.WEST);

        // Adds all the panels to the window
        add(mapPanel,BorderLayout.CENTER);
        add(bottomPanel,BorderLayout.SOUTH);

        // Sets the color of the background of the panels.
        mapPanel.setBackground(Color.WHITE);
        bottomPanel.setBackground(Color.WHITE);

        // coordinates();
        // displayHouseIcons(WelcomeWindow.lowerPrice, WelcomeWindow.upperPrice);
    }
    
    private void goBackToWelcome() {
        mainWindow.frame.remove(mainWindow.map);
        mainWindow.frame.add(mainWindow.welcome);
        mainWindow.frame.revalidate();
        mainWindow.frame.repaint();
    }
    
    private void goToStatistics() {
        mainWindow.stats = new Statistics();
        mainWindow.frame.remove(mainWindow.map);
        mainWindow.frame.add(mainWindow.stats);
        mainWindow.frame.revalidate();
        mainWindow.frame.repaint();
    }
}