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
 * 			PacketTransmitter.java
 * 			->Analysis.java<-
 * 			
 * 			Demonstrate a reflected and amplified Denial-of-Service attack mechanism
 * 			exploiting a vulnerability in Open Arena server versions <= 0.8.5
 * 
 * Current Status: Calculates packet size ratio (received packet size/original packet size)
 * 
 * @author Brad Norman
 *           
 **/

package teamC;

public class Analysis {
	
	// variables
	int originalSize;
	int receivedSize;
	float ratio;
	
	// constructor
	Analysis (int receivedSize, int originalSize){
		
	this.receivedSize = receivedSize;
	this.originalSize = originalSize;
	ratioCalculator();	
	}
	
	// determine ratio
	void ratioCalculator(){
		
	ratio = ((float)(receivedSize))/((float)(originalSize));	
	}
	
	// get ratio as a percentage
	int getRatio (){
	
	return (int)(ratio * 100);	
	}
}

