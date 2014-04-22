package teamC;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class RdosTester extends JPanel implements ActionListener
{


    private JButton button;
    private JTextField srcIP1;
    private JTextField srcIP2;
    private JTextField srcIP3;
    private JTextField srcIP4;
    private JTextField dstIP1;
    private JTextField dstIP2;
    private JTextField dstIP3;
    private JTextField dstIP4;
    private JTextField port;
    private JLabel dot1;
    private JLabel dot2;
    private JLabel dot3;
    private JLabel dot4;
    private JLabel dot5;
    private JLabel dot6;
    private JLabel sourceLabel;
    private JLabel destinationLabel;
    private JLabel portLabel;
    private JLabel statusBar;
    private Border raisedEtched;
    private String DOT = ".";
    private String message;
    private JPanel panel;
    private JPanel spacer;


    public RdosTester() 
    {
    	// Initialize the source IP address labels and textfields
    	sourceLabel = new JLabel("Source IP Address:");
    	srcIP1 = new JTextField(3);
    	srcIP1.addActionListener(this);
    	srcIP2 = new JTextField(3);
    	srcIP2.addActionListener(this);
    	srcIP3 = new JTextField(3);
    	srcIP3.addActionListener(this);
    	srcIP4 = new JTextField(3);
    	srcIP4.addActionListener(this);
    	dot1 = new JLabel(DOT);
    	dot2 = new JLabel(DOT);
    	dot3 = new JLabel(DOT);
    	
    	// Initialize the destination IP address labels and textfields
    	destinationLabel = new JLabel("Destination IP Address:");
    	dstIP1 = new JTextField(3);
    	dstIP1.addActionListener(this);
    	dstIP2 = new JTextField(3);
    	dstIP2.addActionListener(this);
    	dstIP3 = new JTextField(3);
    	dstIP3.addActionListener(this);
    	dstIP4 = new JTextField(3);
    	dstIP4.addActionListener(this);
    	dot4 = new JLabel(DOT);
    	dot5 = new JLabel(DOT);
    	dot6 = new JLabel(DOT);    
    	
    	// Initialize the port label and textfield
    	portLabel = new JLabel("Port:");    	
    	port = new JTextField(6);
    	port.addActionListener(this);
    	
    	// Initialize the button
        button = new JButton("Transmit Packet");
        button.setToolTipText("Click this button to transmit the packet.");
        button.addActionListener(this);

        statusBar = new JLabel();
        raisedEtched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        statusBar.setBorder(raisedEtched);
        statusBar.setPreferredSize(new Dimension(340, 30));
        
        // Create the panels to hold the components. The spacer
        // panel is so the transmit button will be right aligned.
        spacer = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setPreferredSize(new Dimension(350, 125));
        
        //Add Components to the panel using the default FlowLayout.       
        panel.add(sourceLabel);
        panel.add(srcIP1);
        panel.add(dot1);
        panel.add(srcIP2);
        panel.add(dot2);
        panel.add(srcIP3);
        panel.add(dot3);
        panel.add(srcIP4);
        
        panel.add(destinationLabel);
        panel.add(dstIP1);
        panel.add(dot4);
        panel.add(dstIP2);
        panel.add(dot5);
        panel.add(dstIP3);
        panel.add(dot6);
        panel.add(dstIP4);
        
        panel.add(portLabel);
        panel.add(port);
        panel.add(spacer);
        panel.add(button);
        
        panel.add(statusBar);
        
        add(panel);
    }

    public void actionPerformed(ActionEvent e) 
    {
    	boolean valid = false;
    	if(e.getSource()==button)
    	{
	    	System.out.println("I pressed the button!");
	    	// Record the current time.
	    	long timer = System.currentTimeMillis();
	    	
	    	Packet originalPacket = new Packet(Integer.parseInt(srcIP1.getText()), Integer.parseInt(srcIP2.getText()),
	    			Integer.parseInt(srcIP3.getText()), Integer.parseInt(srcIP4.getText()), Integer.parseInt(dstIP1.getText()),
	    			Integer.parseInt(dstIP2.getText()), Integer.parseInt(dstIP3.getText()), Integer.parseInt(dstIP4.getText()),
	    			Integer.parseInt(port.getText()));

    	}
    	else
    	{	
    		System.out.println("I'm leaving a textfield");
    		valid = validateText(((JTextField)e.getSource()).getText());
    	}
    	
    }
    
   public boolean validateText(String string)
   {
	   char[] c = string.toCharArray();
	   for(int i=0; i < string.length(); i++)
	   {
		   if ( !Character.isDigit(c[i]))
		   {
			   message = "Only integer values are allowed.";
			   statusBar.setText(message);
			   statusBar.setForeground(Color.RED);
			   return false;
		   }
	   }
	   statusBar.setText("");
	   return true;
   }

    /**
     * Create the GUI and show it.  For thread safety, 
     * this method should be invoked from the 
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("Reflected Denial of Service Tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        RdosTester newContentPane = new RdosTester();
        newContentPane.setOpaque(true); //content panes must be opaque
        newContentPane.setPreferredSize(new Dimension(350, 125));
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        });
    }
}
