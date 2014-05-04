package teamC;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import java.util.ArrayList;

/**
 * The RdosTester class is the GUI for the Reflected Denial of Service
 * Tester application. It allows the user to enter a source and destination
 * IP address as well as a port number. It also contains a Transmit button
 * that initiates the test. The results are shown at the bottom of the panel
 * in a status bar.
 * 
 * @author Jamie M. Lane
 * @version 1.0
 * 
 * CMSC 495
 * Team C
 * Date Created: 25 April 2014
 * */
public class RdosTester extends JPanel implements ActionListener
{

    // transmit button
	private JButton button;
    
	// source IP address fields
	private JTextField srcIP1;
    private JTextField srcIP2;
    private JTextField srcIP3;
    private JTextField srcIP4;
    
    // destination IP address fields
    private JTextField dstIP1;
    private JTextField dstIP2;
    private JTextField dstIP3;
    private JTextField dstIP4;
    
    // destination port field
    private JTextField port;
    
    // gateway MAC field
    private JTextField gMac1;
    private JTextField gMac2;
    private JTextField gMac3;
    private JTextField gMac4;
    private JTextField gMac5;
    private JTextField gMac6;
    
    // selection of network interface
    private JComboBox networkInterfaceList;

    private JLabel statusBar;
    private Border raisedEtched;
    private String DOT = ".";
    private String message;
    
    private JPanel panel;
    private JPanel spacer;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel6;
    
    private int validSrcIP1;
    private int validSrcIP2;
    private int validSrcIP3;
    private int validSrcIP4;
    private int validDstIP1;
    private int validDstIP2;
    private int validDstIP3;
    private int validDstIP4;
    private int validPort;
    private String validMac;

	
    private PacketTransmitter transmit = new PacketTransmitter();

    // Constructor initializes gui components and layout.
    public RdosTester() 
    {
    	// Initialize the source IP address labels and textfields

    	srcIP1 = new JTextField(3);
        srcIP1.setText("192");
    	srcIP1.addActionListener(this);
    	srcIP2 = new JTextField(3);
        srcIP2.setText("168");
    	srcIP2.addActionListener(this);
    	srcIP3 = new JTextField(3);
        srcIP3.setText("");
    	srcIP3.addActionListener(this);
    	srcIP4 = new JTextField(3);
        srcIP4.setText("");
    	srcIP4.addActionListener(this);

    	
    	// Initialize the destination IP address labels and textfields

    	dstIP1 = new JTextField(3);
        dstIP1.setText("5");
    	dstIP1.addActionListener(this);
    	dstIP2 = new JTextField(3);
        dstIP2.setText("101");
    	dstIP2.addActionListener(this);
    	dstIP3 = new JTextField(3);
        dstIP3.setText("146");
    	dstIP3.addActionListener(this);
    	dstIP4 = new JTextField(3);
        dstIP4.setText("67");
    	dstIP4.addActionListener(this);

    	
    	// Initialize the port label and textfield
    	port = new JTextField(6);
        port.setText("27960");
    	port.addActionListener(this);
    	
    	// Initialize MAC fields and add action listeners
    	gMac1 = new JTextField(2);
    	gMac1.addActionListener(this);
    	gMac2 = new JTextField(2);
    	gMac2.addActionListener(this);
    	gMac3 = new JTextField(2);
    	gMac3.addActionListener(this);
    	gMac4 = new JTextField(2);
    	gMac4.addActionListener(this);    	
    	gMac5 = new JTextField(2);
    	gMac5.addActionListener(this);
    	gMac6 = new JTextField(2);
    	gMac6.addActionListener(this);
    	
    	// Initialize the network interface combobox
    	// Get the available network interfaces
    	ArrayList<String> networkInterfaces = transmit.getInterfaceLabels();
        networkInterfaceList = new JComboBox(networkInterfaces.toArray());
    	
    	// Initialize the button
        button = new JButton("Transmit Packet");
        button.setToolTipText("Click this button to transmit the packet.");
        button.addActionListener(this);

        // Initialize the status bar
        statusBar = new JLabel();
        raisedEtched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        statusBar.setBorder(raisedEtched);
        statusBar.setPreferredSize(new Dimension(375, 30));
        
        // Create the panels to hold the components. The spacer
        // panel is so the transmit button will be right aligned.
        spacer = new JPanel(new FlowLayout(FlowLayout.LEFT, 300, 40));
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setPreferredSize(new Dimension(400, 40));
        
        //Add Components to the panel using the default FlowLayout.       
        panel.add(new JLabel("Source IP Address:        "));
        panel.add(srcIP1);
        panel.add(new JLabel(DOT));
        panel.add(srcIP2);
        panel.add(new JLabel(DOT));
        panel.add(srcIP3);
        panel.add(new JLabel(DOT));
        panel.add(srcIP4);
        
        //spacer = new JPanel(new FlowLayout(FlowLayout.LEFT, 300, 0));
        panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel1.setPreferredSize(new Dimension(400, 40));
        
        panel1.add(new JLabel("Destination IP Address:"));
        panel1.add(dstIP1);
        panel1.add(new JLabel(DOT));
        panel1.add(dstIP2);
        panel1.add(new JLabel(DOT));
        panel1.add(dstIP3);
        panel1.add(new JLabel(DOT));
        panel1.add(dstIP4);    

        
        panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel2.setPreferredSize(new Dimension(400, 40));
        
        panel2.add(new JLabel("Gateway MAC Address:"));
        panel2.add(gMac1);
        panel2.add(new JLabel(DOT));
        panel2.add(gMac2);
        panel2.add(new JLabel(DOT));
        panel2.add(gMac3);
        panel2.add(new JLabel(DOT));
        panel2.add(gMac4);
        panel2.add(new JLabel(DOT));
        panel2.add(gMac5);
        panel2.add(new JLabel(DOT));
        panel2.add(gMac6);
        
        panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel3.setPreferredSize(new Dimension(400, 40));
               
        panel3.add(new JLabel("Port:"));
        panel3.add(port);
        
        panel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel4.setPreferredSize(new Dimension(400, 40));
        
        panel4.add(new JLabel("Network Interface:"));
        panel4.add(networkInterfaceList);
        
        panel5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel5.setPreferredSize(new Dimension(400, 40));
        
        panel5.add(button);
        panel5.add(spacer);
        
        panel6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel6.setPreferredSize(new Dimension(400, 40));
        
        panel6.add(statusBar);
        
        add(panel);
        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);
        add(panel5);
        add(panel6);
    }

    /*
     * The actionPerformed method takes action depending upon
     * which component in the gui is selected by the user. Currently
     * the Transmit button is the only component with an action
     * tied to it. 
     **/
    
        
    public void actionPerformed(ActionEvent e) 
    {
              
    	if(e.getSource() == button)
    	{	    	          
                //clear error message
                message = "";
                statusBar.setText(message);
                
                try {

                    message = "Please enter a valid Source IP Address";
                    statusBar.setForeground(Color.RED);

                    //convert entered source IP address text to ints
                    validSrcIP1 = Integer.parseInt(srcIP1.getText());
                    validSrcIP2 = Integer.parseInt(srcIP2.getText());
                    validSrcIP3 = Integer.parseInt(srcIP3.getText());
                    validSrcIP4 = Integer.parseInt(srcIP4.getText());

                    //display error message if entered source IP address is not valid
                    if (validSrcIP1 < 0 || validSrcIP1 > 255 || validSrcIP2 < 0 || validSrcIP2 > 255 || validSrcIP3 < 0 || validSrcIP3 > 255
                            || validSrcIP4 < 0 || validSrcIP4 > 255){

                        statusBar.setText(message);
                        return;
                    }
                }
                //display error message if entered source IP address is not numeric
                catch (NumberFormatException c) {

                    statusBar.setText(message);		
                    return;
                }
              
                try {

                    message = "Please enter a valid Destination IP Address";
                    statusBar.setForeground(Color.RED);

                    //convert entered destination IP address text to ints
                    validDstIP1 = Integer.parseInt(dstIP1.getText());
                    validDstIP2 = Integer.parseInt(dstIP2.getText());
                    validDstIP3 = Integer.parseInt(dstIP3.getText());
                    validDstIP4 = Integer.parseInt(dstIP4.getText());

                    //display error message if entered destination IP address is not valid
                    if (validDstIP1 < 0 || validDstIP1 > 255 || validDstIP2 < 0 || validDstIP2 > 255 || validDstIP3 < 0 || validDstIP3 > 255
                            || validDstIP4 < 0 || validDstIP4 > 255){

                        statusBar.setText(message);
                        return;
                    }
                }
                //display error message if entered destination IP address is not numeric
                catch (NumberFormatException c) {

                    statusBar.setText(message);		
                    return;
                }
                
                try {

                    message = "Please enter a port number between 0 and 65535";
                    statusBar.setForeground(Color.RED);
                    
                    //convert entered port text to an int
                    validPort = Integer.parseInt(port.getText());
                   
                    //display error message if entered port is not valid
                    if (validPort < 0 || validPort > 65535){

                        statusBar.setText(message);
                        return;
                    }
                }
                //display error message if entered port is not numeric
                catch (NumberFormatException c) {

                    statusBar.setText(message);		
                    return;
                }
                
                try {

                    message = "Please enter a complete MAC address";
                    statusBar.setForeground(Color.RED);
                    
                    //convert entered port text to an int
                    validMac = gMac1.getText() + gMac2.getText() + gMac3.getText() + gMac4.getText() + gMac5.getText() + gMac6.getText();
                   
                    //display error message if entered port is not valid
                    if (validMac.length() != 12){

                        statusBar.setText(message);
                        return;
                    }
                }
                
                //display error message if entered MAC is incomplete
                catch (NullPointerException c) {

                    statusBar.setText(message);		
                    return;
                }
                
    		// Create the original packet. 
	    	RdosPacket originalPacket = new RdosPacket(validSrcIP1, validSrcIP2,
	    			validSrcIP3, validSrcIP4, validDstIP1,
	    			validDstIP2, validDstIP3, validDstIP4,
	    			validPort, validMac);


	    	
	    	//TODO use this to populate the combo box
	    	transmit.send(originalPacket, networkInterfaceList.getSelectedIndex());

	    	// Get the received packet
	    	RdosPacket receivedPacket = transmit.receive();

	    	// Create an analysis object
	    	Analysis analysis = new Analysis(receivedPacket.getPacketSize(), originalPacket.getPacketSize());
	    	int percentage = analysis.getRatio();
//	    	System.out.println("Received size: " + receivedPacket.getPacketSize());	    	
//	    	System.out.println("Original size: " + originalPacket.getPacketSize());

	    	message = "Received Packet to Original Packet Ratio is " + 
	    			Integer.toString(percentage) + "%";
			statusBar.setText(message);
			statusBar.setForeground(Color.GREEN);
    	}
        
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
        newContentPane.setPreferredSize(new Dimension(400, 325));
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