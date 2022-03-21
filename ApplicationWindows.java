import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.*;

 public class ApplicationWindows extends JFrame implements ActionListener
{
    private JButton exit, cal, save, clear, load, prev, next;
private JTextField text, text1, text2,  text3;
private int counter;
//ArrayList<String> splitLine = new ArrayList<String>();
String[] textLine = new String[3];
public  ApplicationWindows()
{

    JFrame myFrame  = new   JFrame("{Your Name} Item Orders Calculator");
    myFrame.setLayout(new BorderLayout(2,   2));

    JPanel panel =  new JPanel();
    panel.setLayout( new    GridLayout(0, 2));

    panel.add( new  JLabel("Item name: ", SwingConstants.RIGHT));
    text = new JTextField("", 25);
    panel.add( text );

    panel.add( new  JLabel("Number of: ", SwingConstants.RIGHT));
    text1   = new   JTextField("",  20);
    panel.add( text1 );


    panel.add( new  JLabel("Cost: ", SwingConstants.RIGHT));
    text2   = new   JTextField("",  20);
    panel.add( text2 );

    panel.add( new  JLabel("Amount owed: ", SwingConstants.RIGHT));
    text3   = new   JTextField("",  20);
    text3.setEnabled(false);
    panel.add( text3 );

    add(panel,BorderLayout.CENTER);


    JPanel panelS = new JPanel( new FlowLayout(FlowLayout.CENTER, 20,3)                                    );

    //  Create some buttons to place in the south   area
    cal = new JButton("Calculate");
    save    = new   JButton("Save");
    clear = new JButton("Clear");
    exit    = new   JButton("Exit");
    load    = new   JButton("Load");
    prev    = new   JButton("<prev");
    next    = new   JButton("next>");

    exit.addActionListener(this);
    clear.addActionListener(this);
    cal.addActionListener(this);
    save.addActionListener(this);
    load.addActionListener(this);
    prev.addActionListener(this);
    next.addActionListener(this);

    panelS.add(cal);
    panelS.add(save);
    panelS.add(clear);
    panelS.add(exit);
    panelS.add(load);
    panelS.add(prev);
    panelS.add(next);

    add(panelS, BorderLayout.SOUTH);
}

public void actionPerformed(ActionEvent e) 
   {
    if(e.getSource() == exit)
    {
        System.exit(0);
    }
    else if(e.getSource()   ==  cal)
    {

        double amount = Double.parseDouble(text1.getText()) *   Double.parseDouble(text2.getText());
        text3.setText(String.format(Locale.ENGLISH, "%.2f", amount));
    }
    else if(e.getSource()   ==  save)
    {
        boolean append  = true;
        try
        {
        BufferedWriter out = new BufferedWriter(new FileWriter("121Lab1.csv", append));

        out.write(String.format("%s,%s,%s,%s", text.getText(), text1.getText(), text2.getText(),    text3.getText()));
        out.newLine();
        out.flush();
        out.close();
    }
    catch (IOException ex)
    {
        JOptionPane.showMessageDialog(null, "Error");
    }
    counter++;

    }
    else if(e.getSource()   ==  clear)
    {
        text.setText("");
        text1.setText("");
        text2.setText("");
        text3.setText("");
    }
    else if(e.getSource()   ==  load)
    {

        try {
            String splitLine;

            BufferedReader  br  = new   BufferedReader( new FileReader("121Lab1.csv"));
            while ((splitLine = br.readLine())  !=  null)   
            {
            textLine = splitLine.split(",");

            text.setText(textLine[0]);
            text1.setText(textLine[1]);
            text2.setText(textLine[2]);
            text3.setText(textLine[3]);
            }
        } 
        catch (IOException exp) 
        {
            JOptionPane.showMessageDialog(null, "Error no file found.");
        }
    }
    else if(e.getSource()   ==  prev)
    {
        //Prev line
    }
    else if(e.getSource()   ==  next)
    {
        //Read next line
    }
}

public static   void main(String[] args)
{
    ApplicationWindows main = new ApplicationWindows();
    main.setTitle("Item Orders Calculator");
    main.setSize(450,   150);
    main.setLocationRelativeTo(null);
    main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    main.setVisible(true);
}
}