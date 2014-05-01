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

import java.util.ArrayList;

public class PacketTransmitter2 {
	
	// represents typical status response packet
	String statusResponse = 
			      "4500039b00004000401105dfc0a85818c0a8580a6d386d3803876789ffffffff"
				+ "737461747573526573706f6e73650a5c675f6c6d735f6d6f64655c305c656c69"
				+ "6d696e6174696f6e5f726f756e6474696d655c3132305c675f766f746547616d"
				+ "6574797065735c2f302f312f332f342f352f362f372f382f392f31302f31312f"
				+ "31322f5c675f646f5761726d75705c315c766964656f666c6167735c375c675f"
				+ "6d617847616d65436c69656e74735c305c675f64656c61674869747363616e5c"
				+ "315c675f766f74654d696e467261676c696d69745c305c675f766f74654d6178"
				+ "467261676c696d69745c305c675f766f74654d696e54696d656c696d69745c30"
				+ "5c675f766f74654d617854696d656c696d69745c313030305c675f616c6c6f77"
				+ "766f74655c315c636170747572656c696d69745c385c626f745f6d696e706c61"
				+ "796572735c345c73765f6670735c32355c73765f6d6178636c69656e74735c38"
				+ "5c675f67616d65747970655c305c74696d656c696d69745c31355c667261676c"
				+ "696d69745c31355c646d666c6167735c305c73765f666c6f6f6450726f746563"
				+ "745c315c73765f6d617850696e675c313030305c73765f6d696e50696e675c30"
				+ "5c73765f6d6178526174655c32353030305c73765f6d696e526174655c305c73"
				+ "765f686f73746e616d655c4f412054657374205365727665725c76657273696f"
				+ "6e5c696f71332b6f6120312e33365f53564e313931304d206c696e75782d7838"
				+ "365f36342044656320323520323031315c70726f746f636f6c5c37315c6d6170"
				+ "6e616d655c616767726573736f725c73765f70726976617465436c69656e7473"
				+ "5c305c73765f616c6c6f77446f776e6c6f61645c315c73765f6d617374657231"
				+ "5c64706d61737465722e64656174686d61736b2e6e65745c67616d656e616d65"
				+ "5c626173656f615c656c696d666c6167735c305c766f7465666c6167735c3736"
				+ "375c675f6e656564706173735c305c675f6f62656c69736b5265737061776e44"
				+ "656c61795c31305c675f656e61626c65447573745c305c675f656e61626c6542"
				+ "72656174685c305c675f726f636b6574735c305c675f696e7374616e74676962"
				+ "5c305c675f616c74457863656c6c656e745c305c675f74696d657374616d705c"
				+ "323031342d30342d32362031343a34343a35330a302030202248656164637261"
				+ "7368220a33203020224d757269656c6c65220a3820302022477269736d220a32"
				+ "203020225361726765220a3020313620226772696667726966220a";
	
		// constructor
	public PacketTransmitter2() {		
		
		System.out.println("Network device found!");		
			
	} // end constructor PacketTransmitter
	
	public ArrayList<String> getInterfaceLabels(){
		
		ArrayList<String> labelList = new ArrayList<String>();
		
		labelList.add("Microsoft");
		labelList.add("RealTek GigE 1");
		labelList.add("Microsoft");
				
		return labelList;

	} // end method getInterfaceLabels
	
	// sends packet to raw network interface
	// currently prints packet to console
	public void send(RdosPacket transmitPacket, int interfaceNumber) {
		
		System.out.println("\nTransmitting packet on interface " + interfaceNumber +  ":\n" + transmitPacket.toString() + "\n");
		
	} // end method send
	
	// receive packet from raw network interface
	// currently prints and returns hard-coded packet
	public RdosPacket receive() {
		
		RdosPacket receivePacket = new RdosPacket(statusResponse);
		
		System.out.println("\nReceive packet:\n" + receivePacket.toString() + "\n");
		
		return receivePacket;
	
	} // end method receive

} // end class PacketTransmitter