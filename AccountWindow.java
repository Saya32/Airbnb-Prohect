import java.awt.*;
import java.util.HashMap;
import java.util.regex.Pattern;
import javax.swing.*;

/*
This class stands for the first window that the user sees when launching the application. In this window the user has to create an account and then log in to the system with that account in order to be able to use the application.
It inherits from the class Windows which itself inherits from JPanel because each instance of AccountWindow class is itself a JPanel contained into the main window (GUI.mainFrame in the class GUI) , and containing other panels.
This statement is valid for all the classes standing for windows oif the application : MapWindow, StatisticsWindow , ApplicationWindow and PropertiesWinow
 */

public class AccountWindow extends Windows {

    private HashMap<String,String> credentials; //  Hash Map that maps a username to its corresponding password after an account has been created.
    private int tries; // number of times the user can try to log in.
    private JTextField CAUsernameField,LIUsernameField; // the text fields of the "Create account" section in which the user an input a username and a password.
    private JPasswordField CAPasswordField,LIPasswordField; // the text fields of the "Log in" section in which the user an input a username and a password.
    private JButton logOut,logIn,welcome; // the buttons to log in and log out.
    private JPanel northPanel; // the panel holding the createAccount , logIn and logOut buttons.
    private JLabel usernameLabel; // a JLabel containing the username of the account on which the user is currently logged in.

    public AccountWindow() {
        credentials = new HashMap<>();
        makeFrame();
    }

    // Builds the window displayed to the user.

    private void makeFrame()
    {
        // Creates all the panels and sets their layouts.
        setLayout(new BorderLayout());
        JPanel bottomPanel = new JPanel(new BorderLayout());
        northPanel = new JPanel(new GridLayout(1,3));
        JPanel centerPanel = new JPanel(new GridLayout(1,0));
        JPanel leftPanel = new JPanel(new GridLayout(0,1));
        JPanel rightPanel = new JPanel(new GridLayout(0,1));

        // Sets the color of the background of the panels.
        leftPanel.setBackground(Color.WHITE);
        rightPanel.setBackground(Color.WHITE);
        bottomPanel.setBackground(Color.WHITE);
        northPanel.setBackground(Color.WHITE);

        // Sets the size of the panels.
        leftPanel.setPreferredSize(new Dimension(300,300));
        rightPanel.setPreferredSize(new Dimension(300,300));
        northPanel.setPreferredSize(new Dimension(750,50));
        bottomPanel.setPreferredSize(new Dimension(750,50));

        // Creates the text and password fields.
        CAUsernameField = new JTextField();
        CAPasswordField = new JPasswordField();
        LIUsernameField = new JTextField();
        LIPasswordField = new JPasswordField();

        // Initialises and configures all the buttons.
        JButton createAccount = new JButton("Create an account");
        logIn = new JButton("Log in");
        logOut = new JButton("Log out");
        welcome = new JButton("Welcome"); // This button enables the user to go to the welcome window at any point after having logged in with an account. This button is enabled while the user is logged in with an account.
        welcome.setEnabled(false);
        welcome.addActionListener(e-> goToWelcomeScreen());
        createAccount.addActionListener(e-> createNewAccount(CAUsernameField.getText(),CAPasswordField.getPassword()));
        logIn.addActionListener(e-> logIn(LIUsernameField.getText(),LIPasswordField.getPassword(),logIn));
        logOut.addActionListener(e-> logOut());

        // Adds the components to the panels. The left and right panels are both subpanels of the central panel.

        // The left panel stands for the "Create account " section.
        leftPanel.add(new JLabel("   Don't have any account ? Create one here "));
        leftPanel.add(new JLabel("     Username"));
        leftPanel.add(CAUsernameField);
        leftPanel.add(new JLabel("     Password"));
        leftPanel.add(CAPasswordField);
        leftPanel.add(new JLabel(""));

        // The right panel stands for the "Log in" section.
        rightPanel.add(new JLabel("   If you have an account , please log in here"));
        rightPanel.add(new JLabel("    Username"));
        rightPanel.add(LIUsernameField);
        rightPanel.add(new JLabel("     Password"));
        rightPanel.add(LIPasswordField);
        rightPanel.add(new JLabel(""));

        centerPanel.add(leftPanel);
        centerPanel.add(rightPanel);
        northPanel.add(createAccount);
        northPanel.add(new JLabel(""));
        northPanel.add(logIn);
        usernameLabel = new JLabel();
        bottomPanel.add(usernameLabel,BorderLayout.WEST);
        bottomPanel.add(welcome,BorderLayout.EAST);


        // Sets the borders between the panels.
        leftPanel.setBorder(BorderFactory.createMatteBorder(1,0,1,1,Color.black));
        rightPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,0,Color.black));

        // Adds all the panels to the main panel.
        add(bottomPanel,BorderLayout.SOUTH);
        add(northPanel,BorderLayout.NORTH);
        add(centerPanel,BorderLayout.CENTER);

    }
    /* Allows the user to go to the welcome screen.
    Is executed when the welcome button is pressed. */

    private void goToWelcomeScreen()
    {

        GUI.awn = new ApplicationWindow(); // The generated welcome window is assigned to the welcome window field of the GUI class, which is the welcome window that the user will see all along.
        GUI.mainFrame.remove(GUI.aw);
        GUI.mainFrame.add(GUI.awn);
        GUI.mainFrame.setPreferredSize(new Dimension(1200,1000));
        GUI.mainFrame.pack();

    }

    // Allows the user to create a new account.
    private void createNewAccount(String username, char[] password)
    {

        if(checkCorrectness(password) && username.length() > 0) // Checks if both the username and the password meet the requirements. If yes , the account is successfully created.
        {
            credentials.put(username,String.valueOf(password));
            JOptionPane.showMessageDialog(GUI.mainFrame, "Your account has been successfully created. You can now log in into Airbnb",
                "Warning", JOptionPane.INFORMATION_MESSAGE);
            CAUsernameField.setText("");
            CAPasswordField.setText("");
        }

        // the user has put that a password that doesn't meet all the requirements
        else
        {
            JOptionPane.showMessageDialog(GUI.mainFrame, "Please make sure that :" + "\n" +
                "- the password is not less than 8 characters long " + "\n"
                + "- the password contains at least 1 uppercase character " + "\n"
                + "- the password contains at least 1 lowercase character" + "\n"
                + "- the password contains at least 1 digit (number between 1 and 9)" + "\n"
                + "- the username is not empty",
                "Airbnb", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Allows the user to log in after having created an account.
    private void logIn(String username, char[] password, JButton button)
    {
        if (LIUsernameField.getText().length() == 0 || LIPasswordField.getPassword().length ==0) // if the user doesn't input a password or a username , the log in process fails.
        {
            JOptionPane.showMessageDialog(GUI.mainFrame, "There was a problem :" + "\n" +
                "Either your username or your password is blank",
                "Airbnb", JOptionPane.WARNING_MESSAGE);
            tries++;
        }

        else if (checkValidityCredentials(username, password))  // checks if the user's username and password are correct and match.
        {
            JOptionPane.showMessageDialog(GUI.mainFrame, "You successfully logged in",
                "Airbnb", JOptionPane.INFORMATION_MESSAGE);

            exchangeButtons(logIn,logOut);
            LIUsernameField.setText("");
            LIPasswordField.setText("");
            usernameLabel.setText("Logged in as : " + username);
            welcome.setEnabled(true);
            goToWelcomeScreen();

        }

        else {
            JOptionPane.showMessageDialog(GUI.mainFrame, "There was a problem :" + "\n" +
                "We couldn't find an account with that username or your password is incorrect",
                "Airbnb", JOptionPane.WARNING_MESSAGE);
            tries++;

        }

        if (tries == 5) // If the number of tries equals the limit , then the user cannot log in for 2 minutes.
        {
            JOptionPane.showMessageDialog(GUI.mainFrame,"You have exceeded the number of tries to log in. From now , you will be unable to log in for 2 minutes. ", "Airbnb",JOptionPane.WARNING_MESSAGE );
            button.setEnabled(false);
            Timer timer = new Timer(120000, e -> {
                        tries = 0;
                        button.setEnabled(true);
                    });
            timer.setRepeats(false);
            timer.start();
        }


    }

    // Checks if the inputted password meets the requirements : having at least one capital letter , lowercase letter and digit ; and that its length is greater than 8.
    private boolean checkCorrectness(char[] pw)
    {
        String password = new String(pw);
        return Pattern.compile( "[0-9]" ).matcher( password ).find() &&  Pattern.compile( "[a-z]" ).matcher( password ).find() &&  Pattern.compile( "[A-Z]" ).matcher( password ).find() && password.length() >= 8;

    }

    // Checks both if an account exists with the inputted username and if that username matches the inputted password.
    private boolean checkValidityCredentials(String username, char[] pw)
    {
        String password =  new String(pw);
        String storedPassword = String.valueOf(credentials.get(username));
        return credentials.keySet().contains(username) && storedPassword.equals(password);

    }

    // Logs out the user of the system.
    private void logOut()
    {
        exchangeButtons(logOut,logIn);
        usernameLabel.setText("");
        welcome.setEnabled(false);
    }

    // Removes one button and replaces it by another one. This function is used in this class to display or not the log in and log out buttons when the user logs in and logs out.
    private void exchangeButtons(JButton button1, JButton button2)
    {
        northPanel.remove(button1);
        northPanel.add(button2);
        northPanel.revalidate();
        northPanel.repaint();
    }

}