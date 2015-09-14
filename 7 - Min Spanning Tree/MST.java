//Tara Moses
//CP3 Assignment 7: Minimum Spanning Tree
//February 21, 2014

import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MST {
	public static List<Integer> visited=new ArrayList<Integer>();
	
	public static void print2DArray(int[][] a) {
		for (int i=0;i<a.length;i++) {
			System.out.print(i+") ");
			for (int j=0;j<a[0].length;j++) {
				System.out.print(a[i][j]+",");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		Scanner filescan=new Scanner(System.in);
		String line;
		int[][] network;
		String[][] strNetwork;
		int count;
		String[] strArray;
		
		System.out.print("Input file: ");
		String inFile=scan.next();
		
		//create scanner
		try {
			filescan=new Scanner(new File(inFile));
		} catch (Exception e) {
			System.out.println("OH NO: "+inFile+" can't be found.");
		}
		
		//read into 2D array
		line=filescan.nextLine();
		count=line.split(",").length;
			
		strNetwork=new String[count][count];
		network=new int[count][count];
		
		strNetwork[0]=line.split(",");
		for (int i=1;i<count;i++) {
			line=filescan.nextLine();
			strNetwork[i]=line.split(",");
		}
		
		for (int i=0;i<strNetwork.length;i++) for (int j=0;j<strNetwork[0].length;j++) {
			if (strNetwork[j][i].equals("-")) network[j][i]=999999; 
			else network[j][i]=Integer.parseInt(strNetwork[j][i]);
		}
		
		print2DArray(network);
	}
}
