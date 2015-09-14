//Tara Moses
//CP3 Assignment 5: Find the Shortest Path
//February 5, 2014

import java.util.Scanner;

public class MarsColonization {	
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		
		Map marsMap=new Map("mars.png");
		System.out.println("the map is "+marsMap.width+" pixels wide and "+marsMap.height+" pixels high. Printing cloud.");
		
		Pixel hamsterdam=marsMap.getPixel(123,456);
		Pixel caterbery=marsMap.getPixel(256,256);
		Pixel newnewyork=marsMap.getPixel(30,30);
		Pixel ummyeah=marsMap.getPixel(10,10);
		Pixel nyanilly=marsMap.getPixel(182,226);
		Pixel forkbombcity=marsMap.getPixel(350,350);
		Pixel woosterborough=marsMap.getPixel(300,145);
		Pixel lordconradlakecity=marsMap.getPixel(500,500);
		Pixel cloudsdale=marsMap.getPixel(400,345);
		
		marsMap.findAPath(hamsterdam, cloudsdale);
		marsMap.colorPath();
		
		System.out.print("\nOutput file name: ");
		String outFile=scan.next();
		
		marsMap.mapToImage(outFile);
		System.out.println("File has been saved as '"+outFile+"'.");
	}
}
