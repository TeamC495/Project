package teamC;

// models an IP packet
public class Packet {
	
	// source address
	String srcIp;
	
	// destination address
	String dstIp;
	
	// destination port
	String dstPort;
	
	// IP header
	String ipHeader;
	
	// UDP payload of IP packet (includes UDP header)
	String udpPayload;	
	
	// complete IP packet
	String completePacket;
	
	// size of packet in bytes
	int packetSize;
	
	// base getInfo request packet, IP
	String packetBase = "ffffffffffff001fbc01b4db08004500002b60a100008011c166c0a85812ffffffff6d386d3b001762c3ffffffff676574696e666f20787878000000";
	
	// constructor requiring complete packet as String representing hex
	public Packet(String completePacket)
	{
		this.completePacket = completePacket;
		packetSizeCalc();
	
	}	// end Packet constructor

	// constructor requiring source and destination address and port info, creates packet suitable for transmission
	public Packet(int srcIP1, int srcIP2, int srcIP3, int srcIP4, int dstIP1,
			int dstIP2, int dstIP3, int dstIP4, int port)
	{
		this.srcIp = Integer.toHexString(srcIP1) + Integer.toHexString(srcIP2) + Integer.toHexString(srcIP3) + Integer.toHexString(srcIP4);
		this.dstIp = Integer.toHexString(dstIP1) + Integer.toHexString(dstIP2) + Integer.toHexString(dstIP3) + Integer.toHexString(dstIP4);
		this.dstPort = Integer.toHexString(port);
		ipHeaderMaker(srcIp, dstIp);
		udpPayloadMaker(dstPort);
		combiner();
		packetSizeCalc();
		
	}  // end Packet constructor
	
	private void packetSizeCalc() {
		
		packetSize = completePacket.length() / 2;
		
	}  // end method packetSizeCalc;
	
	// create IP header	
	private void ipHeaderMaker(String srcIp, String dstIp)
	{
		// call API to create header in IPv4 format
		ipHeader = srcIp + dstIp;
	
	}  // end method ipHeaderMaker
	
	// create UDP payload
	private void udpPayloadMaker(String dstPort)
	{
		String udpPayloadTemplate = dstPort;
		// int portOffset = 4;
		
		//overwrite contents of udpPayloadTemplate at portOffset with dstPort;
		
		udpPayload =  udpPayloadTemplate;
	
	}  // end method udppayloadMaker
	
	// combine IP header and UDP payload to make a complete packet
	private void combiner()
	{
		completePacket = ipHeader + udpPayload;
	
	}  // end method combiner
	
	// return packet size
	public int getPacketSize() {
		
		return packetSize;
	
	} // end method getPacketSize
	
	// returns completePacket as string representation of packet object 	
	public String toString()
	{
		return completePacket;
	
	}  // end method toString

} // end class Packet