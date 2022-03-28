import java.awt.*;
import javax.swing.*;

/**
 * The first panel that the user will see when they begin the program.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class welcomePanel extends JPanel
{
    private JPanel northPanel, southPanel, centrePanel, fromPanel, toPanel;
    private JButton leftButton, rightButton;
    private JLabel intro, fromLabel, toLabel;
    private JComboBox fromBox, toBox;
    private String[] from, to;
    private int fromChoice, toChoice;

    /**
     * Creates the welcome panel and shows it on screen.
     */
    public welcomePanel()
    {
        makeWelcomePanel();
    }

    /**
     * Creates the contents of the welcome panel.
     */
    public void makeWelcomePanel()
    {
        // Makes the layout and panels.

        setLayout(new BorderLayout());

        northPanel = new JPanel(new GridLayout(0, 8));
        add(northPanel, BorderLayout.NORTH);
        northPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        southPanel = new JPanel(new BorderLayout());
        add(southPanel, BorderLayout.SOUTH);

        centrePanel = new JPanel();
        add(centrePanel, BorderLayout.CENTER);

        fromPanel = new JPanel(new BorderLayout());
        northPanel.add(fromPanel);

        toPanel = new JPanel(new BorderLayout());
        northPanel.add(toPanel);

        // adds the welcome message.
        intro = new JLabel("<html>Welcome to the AirBnB property viewer.<br/>"
            + "Use the drop-down menus at the top to select a price range "
            + "and then click <br/> the arrows at the bottom to navigate between different"
            + " parts of the program.</html>", SwingConstants.CENTER);
        intro.setFont(new Font("Verdana", Font.BOLD, 17));
        centrePanel.add(intro);

        fromLabel = new JLabel("From: ");
        fromPanel.add(fromLabel, BorderLayout.WEST);

        toLabel = new JLabel("To: ");
        toPanel.add(toLabel, BorderLayout.WEST);

        // creates the drop-down menus with the list of prices.
        priceList();
        fromBox = new JComboBox<>(from);
        fromPanel.add(fromBox);
        toBox = new JComboBox<>(to);
        toPanel.add(toBox);

        // adds the buttons at the bottom to navigate between panels.
        leftButton = new JButton("<");
        southPanel.add(leftButton, BorderLayout.WEST);
        rightButton = new JButton(">");
        southPanel.add(rightButton, BorderLayout.EAST);

        // allows the user to click the buttons if the price range is valid.
        fromBox.addActionListener(e -> checkValid(fromBox, toBox));
        toBox.addActionListener(e -> checkValid(fromBox, toBox));

        // add button functionality here to send the user to the next panels.
        rightButton.addActionListener(e-> displayMap());
    }

    /**
     * Creates an array of prices to add to the drop-down menu by going up in
     * fifties.
     */
    private void priceList()
    {
        from = new String[51];
        to = new String[51];
        //starting price
        int value = 0;
        for (int i = 0; i < 51; i++) {
            from[i] = Integer.toString(value);
            to[i] = Integer.toString(value);
            value = value + 50;
        }
    }

    /**
     * Checks if the price range the user has selected is valid. If so, allow
     * the user to use the navigation buttons.
     */
    private void checkValid(JComboBox from, JComboBox to)
    {
        if (Integer.parseInt(from.getSelectedItem().toString()) < Integer.parseInt(to.getSelectedItem().toString()))
        {
            fromChoice = Integer.parseInt(from.getSelectedItem().toString());
            toChoice = Integer.parseInt(to.getSelectedItem().toString());
            leftButton.setEnabled(true);
            rightButton.setEnabled(true);
        } else {
            leftButton.setEnabled(false);
            rightButton.setEnabled(false);
            showInvalidRangeMessage();
        }
    }

    /**
     * Shows an error message if the user has inputted an invalid price range.
     */
    private void showInvalidRangeMessage()
    {
        JOptionPane.showMessageDialog(null, "The price range you have selected is"
        + " invalid. Please try again.");
    }


    private void displayMap() {
        mainWindow.map = new Map();
        mainWindow.frame.remove(mainWindow.welcome);
        mainWindow.frame.add(mainWindow.map);
        mainWindow.frame.revalidate();
        mainWindow.frame.repaint();
    }

    public int getFromChoice(){
        return fromChoice;
    }

    public int getToChoice(){
        return toChoice;
    }
}