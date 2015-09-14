//Tara Moses
//CP3 Assignment 5: Find the Shortest Path
//February 5, 2014

import java.util.Scanner;

public class FindShortPath {	
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		
		System.out.print("What image would you like to use? ");
		String inFile=scan.next();
		
		Map waterMap=new Map(inFile);
		System.out.println("the map is "+waterMap.width+" pixels wide and "+waterMap.height+" pixels high.");
		
		waterMap.findAPath();
		
		System.out.print("\nOutput file name: ");
		String outFile=scan.next();
		
		waterMap.mapToImage(outFile);
		System.out.println("File has been saved as '"+outFile+"'.");
	}
}
