
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;

/* This class stands for the window displaying the map of London filled with house icons at each borough.
   The house icons are displayed if there are any properties both matching the price range selected by the customer and located in that borough.
 */
public class MapWindow extends Windows {
    private JPanel mapPanel; // the panel that holds the map.
    private JLabel mapLabel; // the map itself
    private HashMap<String, ArrayList<Integer>> boroughsCoordinates; // a hash map that associates each borough to coordinates where the house icon associated to that borough will be placed.

    public MapWindow() {
        boroughsCoordinates = new HashMap<>();
        buildMapWindow();

    }

    // Builds the map window.
    private void buildMapWindow()

    {
        setLayout(new BorderLayout());

        // Initialises all the panels.
        JPanel bottomPanel = new JPanel(new BorderLayout());
        mapPanel = new JPanel();


        // Displays the map of London and adds it to the mapPanel.
        try {
            BufferedImage bimg = ImageIO.read(new File("london-borough-map.jpg")); // extracts the file containing the map of london
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

        coordinates();
        displayHouseIcons(WelcomeWindow.lowerPrice, WelcomeWindow.upperPrice);
    }


    /*
     For each borough, displays the house icon on the map taking into account the price range selected by the customer.
     Adjusts the size of the house icon depending on the number of properties located into the borough matching the price range.
      */
    private void displayHouseIcons(int fromPrice, int toPrice) {

        for (String borough : BOROUGHS) {
            int numberOfProperties = numberOfProperties(borough, fromPrice, toPrice);
            // Width and height of the displayed house icon
            int houseWidth = 0;
            int houseHeight = 0;
            String url = ""; // link to the file corresponding to the house icon to display.

            if (numberOfProperties < 500 && numberOfProperties > 0) {
                houseWidth = houseHeight = 20;
                url = "houseicon3.png";
            } else if (numberOfProperties >= 500 && numberOfProperties < 3000) {
                houseWidth = houseHeight = 30;
                url = "houseicon2.png";
            } else if(numberOfProperties >= 3000){
                houseWidth = houseHeight = 50;
                url = "houseicon.png";
            }

            ImageIcon houseIcon = new ImageIcon(url);
            JButton houseButton= new JButton();
            houseButton.setIcon( houseIcon);
            houseButton.addActionListener(e-> new PropertiesWindow(borough));
            houseButton.setBounds(boroughsCoordinates.get(borough).get(0), boroughsCoordinates.get(borough).get(1), houseWidth, houseHeight); // sets the coordinates and the dimensions of the button.
            mapLabel.add(houseButton);
            mapPanel.add(mapLabel);
        }

    }

    // Computes the number of properties located in the borough matching the given price range.
    private int numberOfProperties(String borough, int fromPrice, int toPrice) {
        int propertiesCounter = 0;
        for (AirbnbListing property : database) {
            int price = property.getPrice();
            String n = property.getNeighbourhood();

            if ((n.equals(borough)) && (price <= toPrice) && (price >= fromPrice)) {
                propertiesCounter++;

            }
        }
        return propertiesCounter;

    }

    // Fills in the hash map called boroughsCoordinates. The second parameter of each line corresponds to a list storing the x and y coordinates to which each house icon will be placed.
    private void coordinates() {

        boroughsCoordinates.put(BOROUGHS[0], new ArrayList<>(Arrays.asList(265, 648)));
        boroughsCoordinates.put(BOROUGHS[1], new ArrayList<>(Arrays.asList(492, 721)));
        boroughsCoordinates.put(BOROUGHS[2], new ArrayList<>(Arrays.asList(700, 700)));
        boroughsCoordinates.put(BOROUGHS[3], new ArrayList<>(Arrays.asList(150, 460)));
        boroughsCoordinates.put(BOROUGHS[4], new ArrayList<>(Arrays.asList(222, 313)));
        boroughsCoordinates.put(BOROUGHS[5], new ArrayList<>(Arrays.asList(872, 178)));
        boroughsCoordinates.put(BOROUGHS[6], new ArrayList<>(Arrays.asList(106, 424)));
        boroughsCoordinates.put(BOROUGHS[7], new ArrayList<>(Arrays.asList(245, 161)));
        boroughsCoordinates.put(BOROUGHS[8], new ArrayList<>(Arrays.asList(344, 288)));
        boroughsCoordinates.put(BOROUGHS[9], new ArrayList<>(Arrays.asList(371, 116)));
        boroughsCoordinates.put(BOROUGHS[10], new ArrayList<>(Arrays.asList(509, 55)));
        boroughsCoordinates.put(BOROUGHS[11], new ArrayList<>(Arrays.asList(616, 133)));
        boroughsCoordinates.put(BOROUGHS[12], new ArrayList<>(Arrays.asList(759, 171)));
        boroughsCoordinates.put(BOROUGHS[13], new ArrayList<>(Arrays.asList(460, 625)));
        boroughsCoordinates.put(BOROUGHS[14], new ArrayList<>(Arrays.asList(470, 480)));
        boroughsCoordinates.put(BOROUGHS[15], new ArrayList<>(Arrays.asList(515, 431)));
        boroughsCoordinates.put(BOROUGHS[16], new ArrayList<>(Arrays.asList(587, 425)));
        boroughsCoordinates.put(BOROUGHS[17], new ArrayList<>(Arrays.asList(673, 440)));
        boroughsCoordinates.put(BOROUGHS[18], new ArrayList<>(Arrays.asList(772, 500)));
        boroughsCoordinates.put(BOROUGHS[19], new ArrayList<>(Arrays.asList(220, 520)));
        boroughsCoordinates.put(BOROUGHS[20], new ArrayList<>(Arrays.asList(440, 550)));
        boroughsCoordinates.put(BOROUGHS[21], new ArrayList<>(Arrays.asList(432, 483)));
        boroughsCoordinates.put(BOROUGHS[22], new ArrayList<>(Arrays.asList(381, 408)));
        boroughsCoordinates.put(BOROUGHS[23], new ArrayList<>(Arrays.asList(384, 348)));
        boroughsCoordinates.put(BOROUGHS[24], new ArrayList<>(Arrays.asList(524, 355)));
        boroughsCoordinates.put(BOROUGHS[25], new ArrayList<>(Arrays.asList(393, 300)));
        boroughsCoordinates.put(BOROUGHS[26], new ArrayList<>(Arrays.asList(445, 290)));
        boroughsCoordinates.put(BOROUGHS[27], new ArrayList<>(Arrays.asList(593, 345)));
        boroughsCoordinates.put(BOROUGHS[28], new ArrayList<>(Arrays.asList(460, 225)));
        boroughsCoordinates.put(BOROUGHS[29], new ArrayList<>(Arrays.asList(518, 220)));
        boroughsCoordinates.put(BOROUGHS[30], new ArrayList<>(Arrays.asList(520, 197)));
        boroughsCoordinates.put(BOROUGHS[31], new ArrayList<>(Arrays.asList(661, 282)));
        boroughsCoordinates.put(BOROUGHS[32], new ArrayList<>(Arrays.asList(812, 267)));


    }



    // Allows the user to go back to the welcome window.
    private void goBackToWelcome()
    {
        GUI.mainFrame.remove(GUI.mw);
        GUI.mainFrame.add(GUI.ww);
        GUI.mainFrame.revalidate();
        GUI.mainFrame.repaint();
    }

    // Allows the user to go to the statistics window.
    private void goToStatistics()
    {
        GUI.sw = new StatisticsWindow();
        GUI.mainFrame.remove(GUI.mw);
        GUI.mainFrame.add(GUI.sw);
        GUI.mainFrame.revalidate();
        GUI.mainFrame.repaint();

    }
}



