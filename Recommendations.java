import java.awt.*;
import javax.swing.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.Parent;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import java.util.ArrayList;


/**
 * Write a description of class Recommendations here.
 *
 * @author Sayaka, Janet, Apria and Jayden
 * @version 30.03.2022
 */
public class Recommendations extends JPanel
{
    // instance variables - replace the example below with your own
    private JPanel northPanel, southPanel, centrePanel;
    private JButton leftButton, rightButton, welcomeButton, mapButton, statisticsButton, recommendationsButton, goButton ;
    private JLabel intro, rankings;
    JComboBox comboBox;
    private ArrayList<AirbnbListing> listing;
    public String borough;
    private String[] boroughs = {"City of London","Barking and Dagenham", "Barnet", "Bexley", "Brent", "Bromley", "Camden", "Croydon", "Ealing", "Enfield", "Greenwich",
                                    "Hackney", "Hammersmith and Fulham", "Haringey", "Harrow", "Havering", "Hillingdon", "Hounslow", "Islington", "Kensington and Chelsea",
                                    "Kingston upon Thames", "Lambeth", "Lewisham", "Merton", "Newham", "Redbridge", "Richmond upon Thames", "Southwark", "Sutton",
                                    "Tower Hamlets", "Waltham Forest", "Wandsworth", "Westminster"};

    /**
     * Constructor for objects of class Recommendations
     */
    
    
    public Recommendations()
    {
      makeRecommendationsPanel();
    }
    
    public void makeRecommendationsPanel()
    {
        setLayout(new BorderLayout());
        northPanel = new JPanel(new GridLayout(0, 8));
        add(northPanel, BorderLayout.NORTH);
        northPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        southPanel = new JPanel(new FlowLayout());
        add(southPanel, BorderLayout.SOUTH);

        centrePanel = new JPanel(new GridLayout(4,1));
        add(centrePanel, BorderLayout.CENTER);
        
        
        
        //initialises navigation bar buttons
        leftButton = new JButton("<");
        welcomeButton = new JButton("Welcome");
        mapButton = new JButton("Map");
        statisticsButton = new JButton("Statistics");
        recommendationsButton = new JButton("Recommendations");
        rightButton = new JButton(">");
        goButton = new JButton("Go!");
        
        //popup window
        goButton.addActionListener(e-> showRankings());
        
        
        
        
        intro = new JLabel("<html>Welcome to our alternative property recommender.<br/>"
                            + "Feel free select a borough of your choosing <br/>"
                            + "Then receive our five most recommended alternative boroughs! <br/>"
                            + "All chosen based on previous expert opinion.<html>", SwingConstants.CENTER);
        intro.setFont(new Font("Verdana", Font.BOLD, 20));
        centrePanel.add(intro);
        
        comboBox = new JComboBox(boroughs);
        centrePanel.add(comboBox);
        comboBox.addActionListener(e-> getBorough());
        
        
        centrePanel.add(goButton);
    
        
        
        
        
        
        
        
        //adds all navigation buttons to the panel
        southPanel.add(leftButton, BorderLayout.CENTER);
        southPanel.add(welcomeButton, BorderLayout.CENTER);
        southPanel.add(mapButton, BorderLayout.CENTER);
        southPanel.add(statisticsButton, BorderLayout.CENTER);
        southPanel.add(recommendationsButton, BorderLayout.CENTER);
        southPanel.add(rightButton, BorderLayout.CENTER);
        
        //sets current page navigation button to be green
        recommendationsButton.setBackground(Color.GREEN);
        recommendationsButton.setOpaque(true);
        
        //changes page upon specific button press
        mapButton.addActionListener(e-> displayMap());
        welcomeButton.addActionListener(e-> displayWelcome());
        rightButton.addActionListener(e-> displayWelcome());
        leftButton.addActionListener(e-> displayStatistics());
        statisticsButton.addActionListener(e-> displayStatistics());
    }
    
    
    
    public void getBorough(){
        borough = String.valueOf(comboBox.getSelectedItem());
    }
    
    private void showRankings() {
        JFrame frame = new JFrame("Top 10");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rankings = new JLabel("<html>Top 5 Alternate Picks <br/>" +
                                "#1 Hillingdon <br/>" + "#2 Ealing <br/>" + "#3 Tower Hamlets <br/>" + "#4 Hackney <br/>" + "#5 Brent");
        frame.add(rankings);
        frame.setSize(640, 480);
        frame.setVisible(true);
    }
    
    private void displayMap() {
        mainWindow.map = new Map();
        mainWindow.frame.remove(mainWindow.recs);
        mainWindow.frame.add(mainWindow.map);
        mainWindow.frame.revalidate();
        mainWindow.frame.repaint();
    }
    
    private void displayWelcome() {
        mainWindow.welcome = new welcomePanel();
        mainWindow.frame.remove(mainWindow.recs);
        mainWindow.frame.add(mainWindow.welcome);
        mainWindow.frame.revalidate();
        mainWindow.frame.repaint();
    }
    
    private void displayStatistics() {
        mainWindow.stats = new Statistics();
        mainWindow.frame.remove(mainWindow.recs);
        mainWindow.frame.add(mainWindow.stats);
        mainWindow.frame.revalidate();
        mainWindow.frame.repaint();
    }
    


    
    }

