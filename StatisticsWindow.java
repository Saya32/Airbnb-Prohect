import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;


/* This class stands for the last window of the application.
   Each instance of that class displays general statistics computed from the database.
   The user can scroll through those statistics.
 */

public class StatisticsWindow extends Windows {

   private ArrayList<String> statistics;
   private int currentStat;
   private JLabel stat;

    public StatisticsWindow()
    {
        buildStatistics();
        currentStat = 0;
        makeStatisticsFrame();

    }

    // Returns the average number of reviews per property.
    private int averageNumberOfReviewsPerProperty()
    {
        int totalNumberOfReviews = 0;

        for(AirbnbListing property : database)
        {
            totalNumberOfReviews+=property.getNumberOfReviews();
        }
        return totalNumberOfReviews/database.size();
    }

    // Returns the total number of properties that are available for at least one day in the year.
    private int numberOfAvailableProperties()
    {
        int totalNumberOfAvailableProperties =0;
        for(AirbnbListing property  : database)
        {
            if(property.getAvailability365()>0) {
                totalNumberOfAvailableProperties ++;

            }
        }
        return totalNumberOfAvailableProperties;
    }

    // Returns the total number of entire home/apartments.
    private int numberOfEntireHomeApts()
    {
        int numberOfEntireHomeApts=0;
        for(AirbnbListing property: database)
        {
            if(property.getRoom_type().equals("Entire home/apt"))
            {
                numberOfEntireHomeApts++;
            }
        }
        return   numberOfEntireHomeApts;
    }

    // Returns the neighborhood with the most expensive property.
    private String getPriciestNeighborhood()
    {
        int maxPrice =0;
        String neighborhood = "";
        for(AirbnbListing property : database)
        {
            int price = property.getPrice() * property.getMinimumNights(); // we assume that the customer stays the minimum number of nights into the property.

            if(price>maxPrice)
            {
                maxPrice=price;
                neighborhood = property.getNeighbourhood();
            }
        }
        return neighborhood;
    }

    // Counts the number of properties for a given borough. Used in getMostPopulatedNeighborhood() method.
    private int countProperties(String borough)
    {
        int count =0;
        for(AirbnbListing p : database)
        {
            if(p.getNeighbourhood().equals(borough))
            {
                count++;

            }
        }
        return count;
    }

    // Returns the neighborhood with the most properties.
    private String getMostPopulatedNeighborhood()
    {
        int maxNumberOfProperties =0;
        String mostPopulatedNeighborhood ="";
        for (String n : BOROUGHS)
        {
           if(countProperties(n)> maxNumberOfProperties)
           {
               maxNumberOfProperties = countProperties(n);
               mostPopulatedNeighborhood = n;
           }
        }
        return mostPopulatedNeighborhood;
    }

    // Returns the most common room type.
    private String mostCommonRoomType()
    {
        /* We go through the database and find all the room types.
         Each time there is a property having a room type , the counter associated to that room type increments. */
        HashMap<String,Integer> roomTypes = new HashMap<>();
        String mostCommonNeighborhood = "";
        for(AirbnbListing p : database)
        {
            String rt = p.getRoom_type();
            if(!roomTypes.keySet().contains(rt))

            {
               roomTypes.put(rt,0);
            }
            else
            {
                int count = roomTypes.get(rt);
                roomTypes.put(rt,++count);
            }
        }
        int maxNumberOfProperties =0;

        // We go through the keys of the hashmap and find the room type associated to the biggest count.
        for(String key: roomTypes.keySet())
        {
            if(roomTypes.get(key) > maxNumberOfProperties)
            {
                maxNumberOfProperties = roomTypes.get(key);
                mostCommonNeighborhood = key;
            }
        }
        return mostCommonNeighborhood;

    }

    // Returns the average price per night.
    private int averagePricePerNight()
    {
        int total =0;
        for(AirbnbListing p : database)
        {
           total+=p.getPrice();
        }
        return total/database.size();
    }

    // Returns then id and name of the host with the most properties.
    private String hostWithMostProperties()
    {
        int max =0;
        String host = "";
        for(AirbnbListing p : database)
        {
            String hostID = p.getHost_id();
            String hostName = p.getHost_name();
            if(p.getCalculatedHostListingsCount() > max)
            {
                max = p.getCalculatedHostListingsCount();
                host = "ID: " + hostID + "  " + "Name : " + hostName;

            }

        }
        return host;
    }


    // Stores all the statistics to a list in order to be displayed on the window.
    private void buildStatistics()
    {
        statistics = new ArrayList<>();
        statistics.add( "Average number of reviews per property: "+  Integer.toString(averageNumberOfReviewsPerProperty()));
        statistics.add("Total number of available properties: " + Integer.toString(numberOfAvailableProperties()));
        statistics.add("Number of entire apartments: " + Integer.toString(numberOfEntireHomeApts()));
        statistics.add("Priciest neighborhood : " + getPriciestNeighborhood());
        statistics.add("Neighborhood with the most properties : " + getMostPopulatedNeighborhood());
        statistics.add("Most common room type : " + mostCommonRoomType());
        statistics.add("Average price per night : " + averagePricePerNight());
        statistics.add("Host with the most properties : " + hostWithMostProperties());


    }

    // Builds the statistics window.
    private void makeStatisticsFrame()
    {
        setLayout(new BorderLayout());

        // Initialises all the components.
        JButton previousStat = new JButton("<");
        previousStat.addActionListener(e-> getPreviousStat());
        JButton nextStat = new JButton(">");
        nextStat.addActionListener(e-> goToNextStat());
        stat = new JLabel(statistics.get(currentStat)); // current stat displayed on the window. Initialised to the first statistic of the list.
        stat.setFont(new Font("Calibri", Font.BOLD, 30));


        // Initialises all the panels.
        JPanel statisticsPanel = new JPanel(new GridBagLayout());
        JPanel bottomPanel = new JPanel();
        JPanel previousPanel = new JPanel(new BorderLayout());
        JPanel nextPanel = new JPanel(new BorderLayout());
        JButton mapPanel = new JButton("Map");
        mapPanel.addActionListener(e-> goBackToMapPanel());
        previousPanel.add(previousStat,BorderLayout.CENTER);
        nextPanel.add(nextStat,BorderLayout.CENTER);


        // Sets the color of the background of the panels.
        statisticsPanel.setBackground(Color.WHITE);
        bottomPanel.setBackground(Color.WHITE);
        previousPanel.setBackground(Color.WHITE);
        nextPanel.setBackground(Color.WHITE);

        // Adds the components to the panels and the panels to the window.
        statisticsPanel.add(stat);
        bottomPanel.add(mapPanel, BorderLayout.WEST);
        add(statisticsPanel,BorderLayout.CENTER);
        bottomPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.black));
        add(previousPanel,BorderLayout.WEST);
        add(nextPanel,BorderLayout.EAST);
        add(bottomPanel,BorderLayout.SOUTH);


    }

    // Displays the following stat on the screen.
    private void goToNextStat()
    {

       stat.setText(statistics.get(++currentStat % statistics.size()));

    }

    // Displays the preceding stat on the screen.
    private void getPreviousStat()
    {
        --currentStat;
       currentStat%=statistics.size();
        if(currentStat < 0)
        {
            currentStat = statistics.size()-1;
        }
        stat.setText(statistics.get(currentStat));
    }

    // Goes back to the map panel.
    private void goBackToMapPanel()
    {
        GUI.mainFrame.remove(GUI.sw);
        GUI.mainFrame.add(GUI.mw);
        GUI.mainFrame.revalidate();
        GUI.mainFrame.repaint();
    }


}
