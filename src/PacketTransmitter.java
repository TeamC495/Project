package TeamC;

public class PacketTransmitter {
	
	// represents typical status response packet
	String statusResponse = 
			"00241dd16fb60800" 
	        + "27d4469c08004500" 
			+ "00c9000040004011" 
	        + "08b1c0a85818c0a8" 
			+ "580a6d386d3800b5" 
	        + "d94cffffffff696e" 
			+ "666f526573706f6e" 
	        + "73650a5c6d617850" 
			+ "696e675c3430305c" 
	        + "766f69705c315c67" 
			+ "5f68756d616e706c" 
	        + "61796572735c315c" 
			+ "675f6e6565647061" 
	        + "73735c305c707572" 
			+ "655c315c67616d65" 
	        + "747970655c305c73" 
			+ "765f6d6178636c69" 
	        + "656e74735c345c63" 
			+ "6c69656e74735c33" 
	        + "5c6d61706e616d65" 
			+ "5c6f615f646d345c" 
	        + "686f73746e616d65" 
			+ "5c4d79204f412073" 
	        + "65727665725c7072" 
			+ "6f746f636f6c5c37" 
	        + "315c6368616c6c65" 
			+ "6e67655c787878";
	
	// constructor
	public PacketTransmitter() {
		
	} // end constructor PacketTransmitter
	
	// sends packet to raw network interface
	// currently does nothing
	public void send(Packet transmitPacket) {
		
	} // end method send
	
	// receive packet from raw network interface
	// currently returns hardcoded packet
	public Packet receive() {
		
		Packet receivePacket = new Packet(statusResponse);
		
		return receivePacket;
	
	} // end method receive

} // end class PacketTransmitter
