
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
	
	}}

