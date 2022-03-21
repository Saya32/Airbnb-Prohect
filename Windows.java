import javax.swing.*;
import java.util.ArrayList;

// Super class called Windows from which each window of the application inherits. This class extends JPanel as well.
public class Windows extends JPanel{

    // the database containing all the Airbnb properties of London and their information.
    protected final ArrayList<AirbnbListing> database;
    // List of all the boroughs of London.
    protected static final String[] BOROUGHS  = {"Kingston upon Thames","Croydon","Bromley","Hounslow","Ealing","Havering",
            "Hillingdon","Harrow","Brent","Barnet", "Enfield", "Waltham Forest", "Redbridge", "Sutton", "Lambeth", "Southwark",
            "Lewisham", "Greenwich", "Bexley", "Richmond upon Thames", "Merton", "Wandsworth","Hammersmith and Fulham",
            "Kensington and Chelsea","City of London","Westminster","Camden","Tower Hamlets", "Islington", "Hackney","Haringey",
            "Newham","Barking and Dagenham"};

    public Windows()
    {
        AirbnbDataLoader dl = new AirbnbDataLoader();
        database = dl.load();
    }

}