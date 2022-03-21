import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;

/*
 * This class stands for the second window that the user sees , called welcome window. On this window, the user chooses a price range.
 * By pressing a button the user will be brought to the map window , displaying a map of London showing the properties matching the selected price range.
 */
public class ApplicationWindow extends Windows {
    private String[] fromPrices, toPrices;
    public static int upperPrice, lowerPrice; // Variables that store the upper and lower prices chosen by the customer.
    private JComboBox from,to;

    /*
     * Builds the welcome window.
     */
    public WelcomeWindow() {

        makeWelcomeWindow();
    }


    private void makeWelcomeWindow() {
        setLayout(new BorderLayout());

        // Creates all the panels.
        JPanel northPanel = new JPanel(new GridLayout(1, 4, 0, 0));
        JPanel northButtonPanel = new JPanel(new BorderLayout()); // this panel holds the fromPanel and the toPanel.
        JPanel fromPanel = new JPanel(); // this panel holds the JComboBox called from that allows the user to choose a lower price.
        JPanel toPanel = new JPanel(); // this panel holds the JComboBox called from that allows the user to choose a upper price.
        JPanel southPanel = new JPanel(new BorderLayout()); // this panel holds the buttons to go back to the account window and to go to the map window.
        JPanel centerPanel = new JPanel(new BorderLayout()); // this panel holds both welcomeMessagePanel and iconPanel.
        JPanel welcomeMessagePanel = new JPanel(new BorderLayout()); // this panel holds the welcome message.
        JPanel iconPanel = new JPanel(new BorderLayout()); // this panel holds the Airbnb icon.

        // Sets the color of the background of the panels.
        northPanel.setBackground(Color.WHITE);
        fromPanel.setBackground(Color.WHITE);
        toPanel.setBackground(Color.WHITE);
        southPanel.setBackground(Color.WHITE);
        welcomeMessagePanel.setBackground(Color.WHITE);
        iconPanel.setBackground(Color.WHITE);

        // Sets the borders and the sizes of the panels.
        northPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        southPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.black));
        northPanel.setPreferredSize(new Dimension(1200, 30));
        southPanel.setPreferredSize(new Dimension(1200, 30));
        centerPanel.setPreferredSize(new Dimension(1200,1740));
        welcomeMessagePanel.setPreferredSize(new Dimension(1200,150));
        iconPanel.setPreferredSize(new Dimension(1200,1590));
        northButtonPanel.setPreferredSize(new Dimension(50, 30));

        // Displays the Airbnb icon and adds it to the iconPanel.
        try
        {
            BufferedImage ar = ImageIO.read(new File("airbnb.png")); // extracts the file containing the Airbnb icon
            ImageIcon airbnb = new ImageIcon();
            airbnb.setImage(ar); // sets the image of the image icon to be the airbnb image.
            JLabel logo = new JLabel(airbnb);
            iconPanel.add(logo,BorderLayout.CENTER);

        }
        catch(IOException i) {
            return;
        }


        // Initialises all the components.
        priceButtons(); // loads the arrays of prices to be shown into the JComboBoxes when the user wants to select a price range.
        from = new JComboBox<>(fromPrices);
        to = new JComboBox<>(toPrices);
        JButton previous = new JButton("<"); // if pressed , the user goes back to the account window.
        JButton next = new JButton(">");
        previous.addActionListener(e-> goToAccountWindow());
        next.addActionListener(e-> displayImage()); // this button is enabled only if the user selects a valid price range and when pressed it brings the user to the map window.

        // On those two JComboBoxes , whenever a price is selected , the price range is checked to be valid. If it is, the selected prices are assigned to the lower price and upper price variables.
        from.addActionListener(e -> validRange(from, to, next));
        to.addActionListener(e_ -> validRange(from, to, next));
        JLabel introduction = new JLabel("<html>" +"  Welcome to the Airbnb application. Please select a price range with the buttons on the top right" + "<br>"
                + "When you did so, just press on the > button and you can enjoy Airbnb experience !" + "<br>"
                + "If at any point , you get lost on how to use the app , feel free to press on the guide button on the top left of the window." +"</html>");
        introduction.setFont(new Font("Calibri", Font.ITALIC,24));
        JButton help = new JButton("Guide");
        help.addActionListener(e-> help());


        // Adds the components to the panels.
        fromPanel.add(new JLabel("From : "));
        fromPanel.add(from);
        toPanel.add(new JLabel("To : "));
        toPanel.add(to);
        northButtonPanel.add(fromPanel, BorderLayout.CENTER);
        northButtonPanel.add(toPanel, BorderLayout.EAST);
        northPanel.add(help);
        northPanel.add(new JLabel(""));
        northPanel.add(new JLabel(""));
        northPanel.add(northButtonPanel);
        southPanel.add(previous, BorderLayout.WEST);
        southPanel.add(next, BorderLayout.EAST);
        welcomeMessagePanel.add(introduction,BorderLayout.CENTER);
        centerPanel.add(welcomeMessagePanel,BorderLayout.NORTH);
        centerPanel.add(iconPanel,BorderLayout.CENTER);

        // Adds all the panels to the window.
        add(northPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

    }


    //Computes all the prices , that the user can choose , displayed into the JComboBoxes. There is a gap of 50 between each price.
    private void priceButtons() {
        fromPrices = new String[141]; // array storing the prices for the "from" JComboBox.
        toPrices = new String[141]; // array storing the prices for the "to" JComboBox.
        int price = 0;
        for (int i = 0; i < 141; i++) {
            fromPrices[i] = "" + price;
            toPrices[i] = "" + price;
            price += 50;
        }
    }

    // Checks if the price range selected by the user is valid. If it is not the inputted button is disabled.
    private void validRange(JComboBox a, JComboBox b, JButton c) {
        if (Integer.parseInt(a.getSelectedItem().toString()) > Integer.parseInt(b.getSelectedItem().toString())) {
            c.setEnabled(false);
            JOptionPane.showMessageDialog(GUI.mainFrame, "Please select a valid price range", "Warning", JOptionPane.WARNING_MESSAGE);


        } else {
            c.setEnabled(true);
            lowerPrice = Integer.parseInt(from.getSelectedItem().toString());
            upperPrice = Integer.parseInt(to.getSelectedItem().toString());

        }
    }

    // Displays the map of London showing the properties matching the price range selected by the customer.
    private void displayImage() {
        GUI.mw = new MapWindow();
        GUI.mainFrame.remove(GUI.ww);
        GUI.mainFrame.add(GUI.mw);
        GUI.mainFrame.revalidate();
        GUI.mainFrame.repaint();

    }

    // Goes back to the account window.
    private void goToAccountWindow()
    {
        GUI.mainFrame.remove(GUI.ww);
        GUI.mainFrame.add(GUI.aw);
        GUI.mainFrame.setPreferredSize(new Dimension(750,400));
        GUI.mainFrame.pack();
    }

    // Displays instructions to help the user use the app inside a new window when the guide button is clicked.
    private void help()
    {
        JFrame helpFrame = new JFrame("Guide to use Airbnb");
        helpFrame.getContentPane().add(new JLabel("<html>"+ "On the welcome window , once you have selected a price range and clicked the > button , a map of London with house icons is displayed" + "<br>" +
                "Press on any of them and the properties located in the borough matching the price range you selected will appear to you." + "<br>"
                +" Press on any of those properties and its description will be displayed in a new window. " + "<br>"
                + "From the map window , press on the statistics button to go to the statistics window. You will be shown a serie of figures on the airbnb properties." + "<br>"
                + "Once on the statistics window , just press the > and < buttons to go through the different statistics"
                + "At any point you can scroll through the different windows ,using the buttons located on the bottom of each window , change the price range you initially selected and begin a new search" + "<br>" +
                "You can also go back to the window from where you logged in if you want to log out or log in using a different account" +"<br>"
                + "I hope that this guide was useful and enjoy using Airbnb !" + "</html>"));
        helpFrame.getContentPane().setBackground(Color.WHITE);
        helpFrame.setVisible(true);
        helpFrame.pack();
    }
}



