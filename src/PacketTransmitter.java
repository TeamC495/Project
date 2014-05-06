/** 
 * Class: CMSC495 Spring 2014
 * Assignment: Capstone, Week 6, Sprint 1
 * 
 * Team Members: Brad Norman, Jamie Lane, Daniel Ross
 * 
 * Date: 26 April 2014 
 * 
 * File:	RdosTester.java (main application) 	
 * 			Packet.java
 * 			->PacketTransmitter.java<-
 * 			Analysis.java
 * 			
 * 			Demonstrate a reflected and amplified Denial-of-Service attack mechanism
 * 			exploiting a vulnerability in Open Arena server versions <= 0.8.5
 * 
 * Current Status: Prints transmit packet to console, prints and returns hard-coded receive packet
 * 
 * @author Daniel Ross
 *           
 **/

package teamC;

// required for possible IO Exception
import java.io.IOException;

// required for creating lists of interfaces, labels, etc
import java.util.ArrayList;
import java.util.List;

// for labeling packets received
import java.util.Date;

// required for packet capture
import org.jnetpcap.Pcap;

// required to get network interface(s)
import org.jnetpcap.PcapDLT;
import org.jnetpcap.PcapIf;

// required to format packet for transmission
// required to process received packets
import org.jnetpcap.packet.JMemoryPacket;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.JPacketHandler;
import org.jnetpcap.protocol.JProtocol;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Udp;

// required to filter packets
import org.jnetpcap.PcapBpfProgram;

// allows transmission and receipt of raw packets
public class PacketTransmitter {
	
	// init string for response packet
	private String statusResponse = "empty";	
	
	// For any error msgs
	private StringBuilder errbuf = new StringBuilder(); 
	
	// hold list of available network adapters
	private List<PcapIf>  networkInterfaces = new ArrayList<PcapIf>();
	
	// hold source MAC
	private byte[] sourceMacAddress;
	
	// max time to allow transmission and capture; in milliseconds
	private static final int TIMEOUT = 5 * 1000;
	
	// constructor; calls method to get available network interfaces
	public PacketTransmitter() {
		
		// enumerate network interfaces
		getNetworkInterfaces();
		
	} // end constructor PacketTransmitter
	
	// gets list of available network interfaces
	private void getNetworkInterfaces() {
		
		// count available interfaces while adding their handles to a list
		int numberOfInterfaces = Pcap.findAllDevs(networkInterfaces, errbuf);
		
		// generate error if no interfaces available
		if (numberOfInterfaces == Pcap.NOT_OK || networkInterfaces.isEmpty()) {
			System.err.printf("Can't read list of devices, error is %s", errbuf
			    .toString());
			return;
		}
		
		System.out.println("Network device found!");		
	
	} // end method getNetworkInterfaces
	
	// return an array of strings filled with network interface labels
	public ArrayList<String> getInterfaceLabels() {
		
		// create array to hold labels
		ArrayList<String> labelList = new ArrayList<String>();
		
		// init variable to hold number for each interface
		int i = 0;
		
		// for each device in list of network interfaces, add label to label list
		for (PcapIf device : networkInterfaces) 
		{
			String description = (device.getDescription() != null) ? device.getDescription()
			        : "No description available";
			
			labelList.add("#" + i + " " + description);
			
			System.out.println(labelList.get(i));
			
			i++;
		}
		
		// return populated label list
		return labelList;

	} // end method getInterfaceLabels	
	
	// corrects packet checksums, sends packet to raw network interface
	// captures and filters packets from raw network interface
	public void send(RdosPacket transmitPacketContents, int networkInterface) {
		
		// select specified network interface
		PcapIf device = networkInterfaces.get(networkInterface);
		
		// get source MAC address
		try {
			
			// Use device's MAC address as the source address
			sourceMacAddress = device.getHardwareAddress();
		} 
		
		// catch exception thrown if no network devices available
		catch (IOException e) {
			
			System.err.println("Unable to get network device");
		
		}  
		
		// create JPacket for actual transmission
		JPacket transmitPacket = new JMemoryPacket(JProtocol.ETHERNET_ID, transmitPacketContents.toString());
		
		// get ethernet header from packet
		Ethernet ethernet = transmitPacket.getHeader(new Ethernet());
		
        // set source MAC address of transmitPacket
		ethernet.source(sourceMacAddress);
        
		// fix ethernet checksum
        ethernet.checksum(ethernet.calculateChecksum());
		
		// get IP header from packet
		Ip4 ip = transmitPacket.getHeader(new Ip4());
		
		// fix IP checksum
		ip.checksum(ip.calculateChecksum());		
		
		// get UDP header from packet
		Udp udp = transmitPacket.getHeader(new Udp());
		
		// fix UDP checksum
		udp.checksum(udp.calculateChecksum());
		
		// update packet state with checksums and changes
		transmitPacket.scan(Ethernet.ID);
		
		System.out.println(transmitPacket.toString());
		
		// set capture filter using port info from UDP header
		String filterString = "port " + String.valueOf(udp.destination() + " && udp");
		
		// configure network stream for capture and transmission
		int snaplen = 64 * 1024; // capture complete packets
		int flags = Pcap.MODE_PROMISCUOUS; // capture all packets, even packets not addressed to this machine
		int dataLinkType = PcapDLT.CONST_EN10MB; // ethernet 10/100/1000 MB
		int optimize = 0;         // 0 = false; do not optimize capture
		int netmask = 0xFFFFFF00; // 255.255.255.0; only capture packets available in this subnet
		
		// declare a filter "program"; will be compiled
		PcapBpfProgram program = new PcapBpfProgram();
		
		// compile filter; "program" is initialized by this function
		if (Pcap.compileNoPcap(snaplen, dataLinkType, program, filterString, optimize, netmask) != Pcap.OK) {
			System.err.println("unable to compile filter");
			return;
		}
		
		// create packet handler to process received packets
		JPacketHandler<String> jpacketHandler = createPacketHandler();
		
		// begin packet capture on network
		Pcap pcap = Pcap.openLive(device.getName(), snaplen, flags, TIMEOUT, errbuf);		

		// apply filter
		if (pcap.setFilter(program) != Pcap.OK) {
			System.err.println(pcap.getErr());
			return;		
		}
				
		// transmit packet
		if (pcap.sendPacket(transmitPacket) != Pcap.OK) {
			System.err.println(pcap.getErr());
		}	
		
		// get packets; up to 3; send them to handler
		pcap.dispatch(3, jpacketHandler, null);
		
		// close network connection
		pcap.close();
		
	} // end method send
	
	// processes captured packets
	private JPacketHandler<String> createPacketHandler() {
		
		// create object that will display size and time information for captured packets
		// passes receive packets to formatter method
		JPacketHandler<String> jpacketHandler = new JPacketHandler<String>() {

			// required method for JPacketHandler, actually processes captured packet
			public void nextPacket(JPacket receivePacket, String user) {

				// display packet info
				System.out.printf("Received packet at %s caplen=%-4d\n",
				    new Date(receivePacket.getCaptureHeader().timestampInMillis()), // timestamp
				    receivePacket.getCaptureHeader().caplen()  // Length actually captured
				    );
				
				// pass packet to formatter method
				formatReceivePacket(receivePacket);						
			
			} // end method nextPacket
		
		}; // end jpacketHandler declaration
		
		// return packet handling object
		return jpacketHandler;
	
	} // end method createPacketHandler
	
	// format received packets for instantiation as RdosPacket object
	private void formatReceivePacket(JPacket receivePacket) {
		
		// dump receive packet to console for test
		System.out.println(receivePacket.toString());
		
		// get number of bytes in packet
		int responseSize = receivePacket.size();
		
		// create array to hold string representation of hex from packet bytes
		String[] temp = new String[responseSize];
		
		// populate array with bytes
		for(int i = 0; i < responseSize; i++) {
			temp[i] = Integer.toHexString(0x100 | receivePacket.getUByte(i)).substring(1); 
		}
		
		// concatenate string array elements
		StringBuilder packetBuilder = new StringBuilder();
		for(String element : temp) {
		    packetBuilder.append(element);
		}
		
		// fill class variable with string representing packet
		statusResponse = packetBuilder.toString();
		
	} // end method formatReceivePacket
	
	// receive packet from raw network interface
	// currently prints and returns hard-coded packet
	public RdosPacket receive() {
		
		// create RdosPacket object using contents of captured packet
		RdosPacket receivePacket = new RdosPacket(statusResponse);
		
		// System.err.println("\nReceive packet:\n" + receivePacket.toString() + "\n");
		
		// return object
		return receivePacket;
	
	} // end method receive

} // end class PacketTransmitter
