//Tara Moses
//CP3 Assignment 1: Martian Squirrel Beach Volleyball
//January 6, 2013

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;
import java.util.Scanner;

public class MarsCourt {
	/**Prints a 2D array.
	 * 
	 * @param array the array to print.
	 */
	public static void print2DArray(int[][] array) {
		for (int i=0;i<array[0].length;i++) {
			for (int j=0;j<array.length;j++) {
				System.out.print(array[j][i]+",");
			}
			System.out.println();
		}
	}
	
	/**Takes a 2D array and makes it into a picture. Saves it!
	 * 
	 * @param array the 2D int array that needs to be made into a picture
	 * @param filename the filename to save the new image as
	 */
	public static void arrayToImage(int[][] array, String filename) {
		int width=array[0].length;
		int height=array.length;
		BufferedImage newImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		
		for (int x=0;x<width;x++) {
			for (int y=0;y<height;y++) {
				int unpixelValue=array[y][x];
				newImage.setRGB(x,y,0x010101*array[y][x]);
			}
		}
		
		try {
			File outFile=new File(filename);
			ImageIO.write(newImage, "png", outFile);
		} catch (Exception e) {
			System.out.println("It didn't work.");
		}
	}
	
	/**Takes in a picture and returns a 2D array.
	 * 
	 * @param filename the filename of the picture to change into a 2D array
	 * @return the array full of ints that each stand for a shade of grey
	 */
	public static int[][] imageToArray(String filename) {
		File inFile=new File(filename);
		
		try {
			BufferedImage image=ImageIO.read(inFile);
			int width=image.getWidth();
			int height=image.getHeight();
			int[][] array=new int[height][width];
			
			for (int x=0;x<width;x++) for (int y=0;y<height;y++) {
				Color c=new Color(image.getRGB(x,y),true);
				int r=c.getRed();
				array[y][x]=r;
			}
			return array;
		} catch (Exception e) {
			System.out.println("Something went wrong D:");
			return new int[0][0];
		}
	}
	
	/**
	 * Main function. I don't know if I have to javadoc this, but here goes.
	 * Creates a rectangle and then levels it.
	 */
	public static void main(String[] args) throws Exception {
		
		Scanner scan=new Scanner(System.in);
		System.out.print("File name for finished product (please end in .png): ");
		String filename=scan.next();
		
		int[][] terrain=imageToArray("Original Terrain.png"); //start out with original
		
		/**
		 * find ideal spot for rectangle
		 * (you need an algorithm dawg)
		 **/
		 
		//do fancy stuff! switch dirt around :3
		Rectangle courtArea=new Rectangle(15,8,terrain);
		
		int[][] finishedCourt=courtArea.levelOutCourt();
		arrayToImage(finishedCourt, filename);
	}
}
