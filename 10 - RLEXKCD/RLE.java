//Tara Moses
//CP3 Assignment 10: Run-Length Encoding XKCD
//April 8, 2014

import java.util.Scanner;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;
import java.io.PrintWriter;

public class RLE {
	public static File origPicture;
	public static File text;
	public static File newPicture;
	
	public static void textToNewPicture() throws Exception {
		Scanner filescan=new Scanner(text);
		BufferedImage image=ImageIO.read(origPicture);
		int width=image.getWidth();
		int height=image.getHeight();
		int[][] array=new int[height][width];
		String line=filescan.nextLine();
		int charCount=0;
		int rowCount=0;
		int numToPrint=0;
		int index=0;
		int numOfPixels=0; //0 represents white pixels
		
		//System.out.println("width: "+width);
		//System.out.println("height: "+height);
		
		//get file to array
		while (line.length()>1) {
			if (line.indexOf("w")!=-1) index=line.indexOf("w");
			numToPrint=0;
			if (line.indexOf("b")!=-1 && line.indexOf("b") < index) {
				index=line.indexOf("b");
				numToPrint=1; //1 represents black pixels
			}
			
			numOfPixels=Integer.parseInt(line.substring(0,index));
			for (int i=0;i<numOfPixels;i++) {
				array[rowCount][charCount] = numToPrint;
				charCount++;
				if (charCount == width) {
					rowCount++;
					charCount=0;
				}
			}
			line=line.substring(index+1);
		}
		
		//array to image
		BufferedImage newImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		
		for (int x=0;x<width;x++) {
			for (int y=0;y<height;y++) {
				if (array[y][x]==1) newImage.setRGB(x,y,0x000000); //black
				if (array[y][x]==0) newImage.setRGB(x,y,0xffffff); //white
			}
		}
		
		try {
			ImageIO.write(newImage, "png", newPicture);
		} catch (Exception e) {
			System.out.println("Array couldn't be turned into a picture, sorry :<");
		}
	}
	
	public static void origPictureToText() {
		try {
			BufferedImage image=ImageIO.read(origPicture);
			int width=image.getWidth();
			int height=image.getHeight();
			int[][] array=new int[height][width];
			int numToWrite=array[0][0];
			int charCount=0;
			String currentChar="w";
			
			if (numToWrite==0) currentChar="w";
			else currentChar="b";
			
			for (int x=0;x<width;x++) for (int y=0;y<height;y++) {
				Color c=new Color(image.getRGB(x,y),true);
				int r=c.getRed();
				if (r==0xff) array[y][x]=0; //white
				else array[y][x]=1; //black
			}
			
			PrintWriter writer=new PrintWriter(text);
			//get array to text file
			for (int y=0;y<height;y++) for (int x=0;x<width;x++) {
				if (numToWrite==array[y][x]) {
					charCount++;
				} else {
					//write stuff
					writer.write(charCount+currentChar);
					
					//now reassign everything
					numToWrite=array[y][x];
					charCount=1;
					if (numToWrite==0) currentChar="w";
					else currentChar="b";
				}
			}
			writer.write(charCount+currentChar);
			writer.close();
		} catch (Exception e) {
			System.out.println("Image didn't turn into array :<");
		}
	}
	
	public static void encode() {  //need to finish this
		
	}
	
	public static void main(String[] args) throws Exception {
		Scanner scan=new Scanner(System.in);
		text=new File("text.txt");
		newPicture=new File("newPic.png");
		
		System.out.print("Original picture: ");
		origPicture=new File(scan.nextLine());
		
		//get origpicture into text file
		origPictureToText();
		
		//get text file into newpicture
		textToNewPicture();
	}
}
