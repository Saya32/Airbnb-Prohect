import java.awt.*;
import javax.swing.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.Parent;

/**
 * Write a description of class Recommendations here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Recommendations extends JPanel
{
    // instance variables - replace the example below with your own
    private JPanel northPanel, southPanel, centrePanel;
    private JButton leftButton, rightButton, welcomeButton, mapButton, statisticsButton, recommendationsButton ;

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

        centrePanel = new JPanel();
        add(centrePanel, BorderLayout.CENTER);
        
        
        //initialises navigation bar buttons
        leftButton = new JButton("<");
        welcomeButton = new JButton("Welcome");
        mapButton = new JButton("Map");
        statisticsButton = new JButton("Statistics");
        recommendationsButton = new JButton("Recommendations");
        rightButton = new JButton(">");
        
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

