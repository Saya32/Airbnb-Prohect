import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.*;

/*
This class stands for the window displaying the list of properties in a specific borough matching the price range selected by the customer.
Whenever a house icon is clicked , an instance of that class is created. The user can choose to sort the list by host name, review or price.
 */

public class PropertiesWindow extends Windows{
    private ArrayList<AirbnbListing> matchingProperties; // the list of properties displayed in the window.
    private JComboBox sort; // contains the buttons to sort the list.
    private String borough; // the borough in which the displayed properties are located.
    private JFrame propertiesFrame; // the window holding the propertiesPanel.
    private JPanel propertiesPanel; // the panel holding the list of properties.
    private JScrollPane sp; // a scroll pane that allows the user to easily scroll through the properties.

    public PropertiesWindow(String borough)
    {
        this.borough = borough;
        listOfProperties(ApplicationWindow.lowerPrice,ApplicationWindow.upperPrice);
        buildInitialWindow();
    }

    // Stores ,in the matchingProperties list, all the properties located in the borough and matching the price range selected by the customer.
    private void listOfProperties( int fromPrice, int toPrice) {
         matchingProperties = new ArrayList<>();

        for (AirbnbListing p : database) {
            int price = p.getPrice();
            String neighbourhood= p.getNeighbourhood();

            if ((neighbourhood.equals(borough)) && (price <= toPrice) && (price >= fromPrice)) {

                matchingProperties.add(p);
            }

        }

    }


    // Sorts the list called matching properties by host name.
    private void sortByHostName()
    {
        if(sort.getSelectedItem().equals("Host name")) {
            matchingProperties.sort(Comparator.comparing(AirbnbListing::getHost_name));
            buildSortedWindow();
        }
    }

    // Sorts the list called matching properties by number of reviews.
    private void sortByNumberOfReviews()
    {
        if(sort.getSelectedItem().equals("Number of reviews")) {
            matchingProperties.sort(Comparator.comparing(AirbnbListing::getNumberOfReviews));
            buildSortedWindow();

        }
    }

    // Sorts the list called matching properties by price.
    private void sortByPrice()
    {
        if(sort.getSelectedItem().equals("Price")) {
            matchingProperties.sort(Comparator.comparing(AirbnbListing::getPrice));
            buildSortedWindow();

        }
    }

    // Builds the window that appears when an house icon is clicked.
    private void buildInitialWindow()
    {
       propertiesFrame = new JFrame(borough);
        String[] sortingWays = {"Sort by" ,"Host name", "Number of reviews", "Price"};
        sort = new JComboBox<>(sortingWays);
        sort.addActionListener(e-> {sortByHostName(); sortByNumberOfReviews(); sortByPrice(); });
        propertiesFrame.setPreferredSize(new Dimension(1200,800));
        buildPanel();
        sp = new JScrollPane(propertiesPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.getVerticalScrollBar().setUnitIncrement(20);
        propertiesFrame.getContentPane().add(sp);
        propertiesFrame.setVisible(true);
        propertiesFrame.pack();
    }

    // Builds the window displaying the sorted list of properties.
    private void buildSortedWindow()
    {
       propertiesFrame.remove(sp);
       buildPanel();
       sp = new JScrollPane(propertiesPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
       sp.getVerticalScrollBar().setUnitIncrement(20);
       propertiesFrame.getContentPane().add(sp);
       propertiesFrame.revalidate();
       propertiesFrame.repaint();
    }


    // Builds the panel showing the matching properties list. This panel is contained within the main window.
    private void buildPanel()
    {
        propertiesPanel = new JPanel(new GridLayout(matchingProperties.size()+1, 1, 0, 10));
        propertiesPanel.setBackground(Color.WHITE);
        propertiesPanel.add(sort);
        int propertyNumber = 1;
        for (AirbnbListing p : matchingProperties) {
            int price = p.getPrice();
            int reviews = p.getNumberOfReviews();
            int nights = p.getMinimumNights();
            String host = p.getHost_name();
            JLabel label = new JLabel("Property " + propertyNumber + ":    " + "Host Name : " + host + "   " + "Price : " + price + "   " + "Number of reviews : " + reviews + "   " + "Minimhum number of nights : " + nights);
            /*
             Each element of the list is a property and is stored into the variable label.
             Each time one of the properties is clicked the description of the clicked property is displayed in another window.
             */
            label.addMouseListener(
                    new MouseAdapter()
                    {
                        public void mousePressed(MouseEvent mouseEvent)
                        {
                            JFrame descriptionFrame = new JFrame();
                            descriptionFrame.getContentPane().add(new JLabel(p.getName()));
                            descriptionFrame.setVisible(true);
                            descriptionFrame.pack();

                        }
                    });
            propertiesPanel.add(label);
            propertyNumber++;
        }

    }



}
