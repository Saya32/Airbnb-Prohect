import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;

/**
 * This class displays a Map on the main panel and it should ideakky show markers of the houses 
 * depending on the price range selected by the users.
 * 
 * @author: Sayaka, Janet, Apria and Jayden
 * @version 22.03.2022
 */
public class Map extends JPanel{
   
    private HashMap<String, ArrayList<Integer>> coordinates;
    private JPanel mapPanel; // the panel that holds the map.
    private JLabel mapLabel; // the map itself
    private JButton leftButton, rightButton, welcomeButton, mapButton, statisticsButton, recommendationsButton ; // navigation bar buttons
    private JPanel southPanel; //border panels
    private ArrayList<AirbnbListing> listing;
    private welcomePanel welcome;
    private static final String[] BOROUGHS  = {"Barking and Dagenham","Bromley","Ealing","Havering",
            "Hillingdon","Harrow","Brent","Barnet", "Enfield", "Redbridge", "Sutton", "Lambeth", "Southwark",
            "Greenwich","Lewisham","Hounslow", "Bexley","Kingston upon Thames", "Merton", "Wandsworth","Hammersmith and Fulham",
            "Kensington and Chelsea","City of London","Westminster","Camden","Tower Hamlets", "Islington", "Hackney","Haringey",
            "Newham","Croydon","Waltham Forest", "Richmond upon Thames"};

    /**
     * Constructor for objects of class Map
     */     
    public Map() {
        buildMapWindow();
        coordinates = new HashMap<>();
        AirbnbDataLoader loader = new AirbnbDataLoader();
        listing = loader.load();
    }
    
    /**
     * Creates the actual map window
     */
    private void buildMapWindow()

    {
        setLayout(new BorderLayout());

        // Initialises all the panels.
        JPanel bottomPanel = new JPanel(new BorderLayout());
        mapPanel = new JPanel();
        southPanel = new JPanel(new FlowLayout());
        add(southPanel, BorderLayout.SOUTH);


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

        //initialises navigation bar buttons
        leftButton = new JButton("<");
        welcomeButton = new JButton("Welcome");
        mapButton = new JButton("Map");
        statisticsButton = new JButton("Statistics");
        recommendationsButton = new JButton("Recommendations");
        rightButton = new JButton(">");
        
        // Adds the components to the panels
        //adds all navigation buttons to the panel
        southPanel.add(leftButton, BorderLayout.CENTER);
        southPanel.add(welcomeButton, BorderLayout.CENTER);
        southPanel.add(mapButton, BorderLayout.CENTER);
        southPanel.add(statisticsButton, BorderLayout.CENTER);
        southPanel.add(recommendationsButton, BorderLayout.CENTER);
        southPanel.add(rightButton, BorderLayout.CENTER);

        // Adds all the panels to the window
        add(mapPanel,BorderLayout.CENTER);

        // Sets the color of the background of the panels.
        mapPanel.setBackground(Color.WHITE);
        bottomPanel.setBackground(Color.WHITE);
        
        //changes page upon specific button press
        recommendationsButton.addActionListener(e-> displayRecs());
        welcomeButton.addActionListener(e-> displayWelcome());
        rightButton.addActionListener(e-> displayStatistics());
        leftButton.addActionListener(e-> displayWelcome());
        statisticsButton.addActionListener(e-> displayStatistics());
        
        //sets current page navigation button to be green
        mapButton.setBackground(Color.GREEN);
        mapButton.setOpaque(true);

        // coordinates();
        // displayHouseIcons(WelcomeWindow.lowerPrice, WelcomeWindow.upperPrice);
    }
   
    
    
    /**
     * Decides what color marker should be displayed on the map.
     */
    private String whatColor(){
        int numPropertiesInRange = 0;
        for(AirbnbListing property : listing){
           
            if(welcome.getFromChoice() <= property.getPrice() && welcome.getToChoice() >= property.getPrice()){
                numPropertiesInRange++;
            }
       
        }
       
        if(numPropertiesInRange < 100){
            return "red";
        }
        else if(numPropertiesInRange >= 100 && numPropertiesInRange <= 1000 ){
            return "yellow";
        }
        else{
            return "green";
        }

    }
    
    private void displayWelcome() {
        mainWindow.welcome = new welcomePanel();
        mainWindow.frame.remove(mainWindow.map);
        mainWindow.frame.add(mainWindow.welcome);
        mainWindow.frame.revalidate();
        mainWindow.frame.repaint();
    }
    
    private void displayStatistics() {
        mainWindow.stats = new Statistics();
        mainWindow.frame.remove(mainWindow.map);
        mainWindow.frame.add(mainWindow.stats);
        mainWindow.frame.revalidate();
        mainWindow.frame.repaint();
    }
    
    private void displayRecs() {
        mainWindow.recs = new Recommendations();
        mainWindow.frame.remove(mainWindow.map);
        mainWindow.frame.add(mainWindow.recs);
        mainWindow.frame.setVisible(true);
        mainWindow.frame.pack();
    }
   
    /**
     * sets the postition of the markers on the map
     */
    private void placeMarkers(){
   
   
    }
   
    /**
     * displays the markers corretly
     */
    private void displayMarkers(){
   
   
    }
   

   
}