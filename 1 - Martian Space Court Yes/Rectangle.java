//Tara Moses
//Class for CP3 Assignment 1: Rectangle
//January 10, 2014

import java.util.Arrays;

/**
 * This object is the rectangle that we're trying
 * to make into a court! Alt is the altitude at which
 * we're trying to level the court. ImageArray is the
 * array of the image that contains the rectangle. Cost
 * is the total cost of the project, topLeftX and topLeftY 
 * are the coordinates of the top-left corner of the rectangle
 * (relative to the image). RecArray is the 2D array of the 
 * rectangle.
 * 
 * @author Tara Moses
 */
public class Rectangle {
	int alt;
	int cost;
	int[][] imageArray;
	int[][] originalArray;
	int[][] recArray;
	int topLeftX;
	int topLeftY;
	
	public Rectangle(int tempX, int tempY, int[][] array) {
		topLeftX=tempX;
		topLeftY=tempY;
		imageArray=array;
		originalArray=new int[512][512];
		
		for (int i=0;i<imageArray.length;i++) {
			System.arraycopy(imageArray[i],0,originalArray[i],0,imageArray[i].length);
		}
		
		cost=0;
		recArray=getRectangleArray();
		alt=getMostFrequentAltitude();
	}
	
	/**
	 * Levels out the court. Uses {@link #setPixelAltitude(int startImageX, int startImageY)}
	 * to move dirt around for one pixel. Keeps doing that for each spot until it's leveled
	 * to the right altitude (alt).
	 * 
	 * @return array of the updated image (with all the dirt moved around)
	 */
	public int[][] levelOutCourt() {		
		for (int i=0;i<32;i++) for (int j=0;j<64;j++) { //for all the unpixels in rectangle array 
			while (recArray[j][i]!=alt) {
				setPixelAltitude(i+topLeftX,j+topLeftY,alt);
			}
		}
		//check();
		
		int beforePixel=0;
		int afterPixel=0;
		
		for (int x=0;x<512;x++) for (int y=0;y<512;y++) {
			beforePixel=originalArray[y][x];
			afterPixel=imageArray[y][x];
			
			cost+=Math.abs(beforePixel-afterPixel);
		}
		System.out.println("total cost: "+cost);
		
		return imageArray;
	}
	
	/**Takes a pixel in the rectangle and moves dirt around so that the pixel
	 * has the required altitude. 
	 * 
	 * @param startImageX x-coordinate of pixel inside rectangle with not-okay altitude
	 * @param startImageY y-coordinate of pixel inside rectangle with not-okay altitude
	 */
	public void setPixelAltitude(int startImageX, int startImageY, int desiredAltitude) {
		int newImageX=-1;
		int newImageY=-1;
		boolean transportingDirtOutOfRectangle=(imageArray[startImageY][startImageX]>desiredAltitude)?true:false;

		for (int x=0;x<512 && newImageX==-1;x++) for (int y=0;y<512 && newImageY==-1;y++) {
			if (slopeIsOkayToMoveDirt(x,y,transportingDirtOutOfRectangle) && pointIsNotInRectangle(x,y)) {
				newImageX=x;
				newImageY=y;
			}
		}

		//now actually move stuff :3
		if (transportingDirtOutOfRectangle) {
			this.imageArray[startImageY][startImageX]--;
			this.imageArray[newImageY][newImageX]++;
			recArray=getRectangleArray();
		} else {
			this.imageArray[startImageY][startImageX]++;
			this.imageArray[newImageY][newImageX]--;
			recArray=getRectangleArray();
		}
	}
	
	/**
	 * Checks whether a pixel outside the rectangle is okay to receive or give dirt
	 * away, depending on the pixel inside the rectangle that needs to change its
	 * altitude.
	 * 
	 * @param endImageX x-coordinate of pixel outside rectangle (where dirt ENDS up coming from or going to)
	 * @param endImageY y-coordinate of pixel outside rectangle (where dirt ENDS up coming from or going to)
	 * @param transportingDirtOutOfRectangle true if we're trying to transport dirt out of the rectangle, false otherwise
	 * @return true if slope won't be too steep should dirt leave or be placed in that pixel, false if it causes a problem
	 */
	public boolean slopeIsOkayToMoveDirt(int endImageX, int endImageY, boolean transportingDirtOutOfRectangle) {		
		//handle edge wrapping here
		int north=(endImageY==0)?imageArray[511][endImageX]:imageArray[endImageY-1][endImageX];
		int south=(endImageY==511)?imageArray[0][endImageX]:imageArray[endImageY+1][endImageX];
		int east=(endImageX==511)?imageArray[endImageY][0]:imageArray[endImageY][endImageX+1];
		int west=(endImageX==0)?imageArray[endImageY][511]:imageArray[endImageY][endImageX-1];
		int current=imageArray[endImageY][endImageX];

		if (transportingDirtOutOfRectangle) {
			if (current-north>1 || current-south>1 || current-east>1 || current-west>1) return false;
		} else {
			if (north-current>1 || south-current>1 || east-current>1 || west-current>1) return false;
		}
		
		return true;
	}
	
	/**
	 * Checks whether a pixel is in the rectangle.
	 * 
	 * @param imageX the x-coordinate (relative to the image, not the rectangle) of the pixel in question
	 * @param imageY the y-coordinate (relative to the image, not the rectangle) of the pixel in question
	 * @return true if pixel is not in rectangle, false otherwise
	 */
	public boolean pointIsNotInRectangle(int imageX, int imageY) {
		if (topLeftX+32<imageX || imageX<topLeftX || topLeftY+64<imageY || imageY<topLeftY) return true;
		return false;
	}
	
	/**
	 * Returns the 2D array for the 32 x 64 pixel rectangle. Uses the top-left coordinates
	 * specified when Rectangle was created in the driver.
	 * 
	 * @return 2D array of rectangle (soon-to-be court!)
	 */
	public int[][] getRectangleArray() {
		int[][] rectangleArray=new int[64][32];
		for (int i=topLeftY;i<topLeftY+64;i++) {
			System.arraycopy(imageArray[i], topLeftX, rectangleArray[i-topLeftY], 0, 32);
		}
		return rectangleArray;
	}
	
	/**
	 * Checks whether any pixels have slopes too steep. Also checks to make sure
	 * that the rectangle is uniform. Prints out things to tell Tara if things
	 * are going wrong. Not really necessary for anything except troubleshooting, haha.
	 */
	public void check() {
		int[] altitudes=new int[256];
		
		System.out.println("\n\nBEGINNING TROUBLESHOOTING: ");
		
		//make sure all pixels in image are okay with their surroundings
		for (int i=0;i<512;i++) for (int j=0;j<512;j++) {
			int north=(j==0)?imageArray[511][i]:imageArray[j-1][i];
			int south=(j==511)?imageArray[0][i]:imageArray[j+1][i];
			int east=(i==511)?imageArray[j][0]:imageArray[j][i+1];
			int west=(i==0)?imageArray[j][511]:imageArray[j][i-1];
			int current=imageArray[j][i];
			
			if (Math.abs(current-north)>2 || Math.abs(current-south)>2 || Math.abs(current-east)>2 || Math.abs(current-west)>2) System.out.println("("+i+","+j+") with value "+current+" is too high/low for its surroundings!");
		}
		
		//make sure all pixels in rectangle are the same altitude
		for (int x=0;x<32;x++) for (int y=0;y<64;y++) {
			int unpixelValue=recArray[y][x];
			altitudes[unpixelValue]++;	
		}

		for (int i=0;i<256;i++) {
			if (altitudes[i]==2048) System.out.println("RECTANGLE IS UNIFORM AWW YEAH");
			else if (altitudes[i]!=0) System.out.println(altitudes[i]+" pixels share the amplitude "+i+", :'(");
		}
	}
	
	/**
	 * Gets the most frequent altitude in the rectangle so that all pixels 
	 * that aren't that altitude can be changed. Saves the most frequent 
	 * altitude it finds into alt, a classwide variable.
	 */ 
	public int getMostFrequentAltitude() {
		int[] altitudes=new int[256];	//0 to 255
		int frequentAlt=0;
		int numPixels=-1;
		int prev1=0;
		int prev2=0;
		int next1=0;
		int next2=0;
		int count=1;
		int unpixelValue=0;
		
		//get all altitudes in rectangle whoohoo
		for (int x=0;x<32;x++) for (int y=0;y<64;y++) {
			unpixelValue=recArray[y][x];
			altitudes[unpixelValue]++;	
		}
		
		//find largest number in altitudes array. if there's
		//more than one altitude with the same # of pixels, 
		//choose the one that yields the least cost
		for (int i=0;i<256;i++) {
			if (numPixels<altitudes[i]) {
				numPixels=altitudes[i];
				frequentAlt=i;
			}
		}
		return frequentAlt;
	}
}
