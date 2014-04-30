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
    private int validSrcIP1;
    private int validSrcIP2;
    private int validSrcIP3;
    private int validSrcIP4;
    private int validDstIP1;
    private int validDstIP2;
    private int validDstIP3;
    private int validDstIP4;
    private int validPort;

    // Constructor initializes gui components and layout.
    public RdosTester() 
    {
    	// Initialize the source IP address labels and textfields
    	sourceLabel = new JLabel("Source IP Address:");
    	srcIP1 = new JTextField(3);
        srcIP1.setText("127");
    	srcIP1.addActionListener(this);
    	srcIP2 = new JTextField(3);
        srcIP2.setText("0");
    	srcIP2.addActionListener(this);
    	srcIP3 = new JTextField(3);
        srcIP3.setText("0");
    	srcIP3.addActionListener(this);
    	srcIP4 = new JTextField(3);
        srcIP4.setText("1");
    	srcIP4.addActionListener(this);
    	dot1 = new JLabel(DOT);
    	dot2 = new JLabel(DOT);
    	dot3 = new JLabel(DOT);
    	
    	// Initialize the destination IP address labels and textfields
    	destinationLabel = new JLabel("Destination IP Address:");
    	dstIP1 = new JTextField(3);
        dstIP1.setText("127");
    	dstIP1.addActionListener(this);
    	dstIP2 = new JTextField(3);
        dstIP2.setText("0");
    	dstIP2.addActionListener(this);
    	dstIP3 = new JTextField(3);
        dstIP3.setText("0");
    	dstIP3.addActionListener(this);
    	dstIP4 = new JTextField(3);
        dstIP4.setText("1");
    	dstIP4.addActionListener(this);
    	dot4 = new JLabel(DOT);
    	dot5 = new JLabel(DOT);
    	dot6 = new JLabel(DOT);    
    	
    	// Initialize the port label and textfield
    	portLabel = new JLabel("Port:");    	
    	port = new JTextField(6);
        port.setText("27960");
    	port.addActionListener(this);
    	
    	// Initialize the button
        button = new JButton("Transmit Packet");
        button.setToolTipText("Click this button to transmit the packet.");
        button.addActionListener(this);

        // Intialize the status bar
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
                
    		// Create the original packet. 
	    	RdosPacket originalPacket = new RdosPacket(validSrcIP1, validSrcIP2,
	    			validSrcIP3, validSrcIP4, validDstIP1,
	    			validDstIP2, validDstIP3, validDstIP4,
	    			validPort);

	    	// Create a PacketTransmitter and send the original packet.
	    	PacketTransmitter transmit = new PacketTransmitter();
	    	transmit.send(originalPacket);

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