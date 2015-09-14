//Tara Moses
//CP3 Assignment 9: Huffman Encoding
//March 18, 2014

/**
 * Helpful links!!
 * 
 * http://www.siggraph.org/education/materials/HyperGraph/video/mpeg/mpegfaq/huffman_tutorial.html
 * http://docs.oracle.com/javase/7/docs/api/java/util/PriorityQueue.html
 */

import java.util.Scanner;
import java.util.PriorityQueue;
import java.io.File;
import java.io.PrintWriter;

public class Huffman {
	public static PriorityQueue<HuffNode> list;
	public static PriorityQueue<HuffNode> listCopy;
	public static HuffTree tree;
	public static File originalFile; 
	public static File encodedFile;
	public static File freqFile=new File("frequencies.txt");
	
	public static HuffNode getNodeWithChar(char c) {
		for (HuffNode node : list) {
			if (node.value==c) return node;
		}
		return null;
	}
	
	public static void encode() {
		Scanner scan=new Scanner(System.in);
		BitInputStream bitIn;
		String line;
		//String byteString;
		char[] chars;
		
		try {
			scan=new Scanner(originalFile);
		} catch (Exception e) {}
		
		try {
			PrintWriter writer=new PrintWriter(encodedFile);
			
			//get all the 0s and 1s
			while (scan.hasNextLine()) {
				line=scan.nextLine();
				chars=line.toCharArray();
				
				//for (char c : chars) byteString+=tree.getCodeOfChar(c);
				for (char c : chars)  writer.write(tree.getCodeOfChar(c));
				writer.write("\n");
			}
			writer.close();
			
			bitIn=new BitInputStream("out.txt");
			System.out.println(bitIn.read());
			bitIn.close();
		} catch (Exception e) {}
	}
	
	public static void saveFrequencies() {
		try {
			PrintWriter writer=new PrintWriter(freqFile);
			while (listCopy.size()>0) {
				HuffNode node=listCopy.poll();
				writer.write(node.value+": "+node.freq+"\n");
			}
			writer.close();
			
			System.out.println("The frequencies have been written.");
		} catch (Exception e) {}
	}
	
	public static void main(String[] args) {
		//variables
		Scanner scan=new Scanner(System.in);
		Scanner filescan=new Scanner(System.in); //redefine this later
		char[] chars;
		String line;
		HuffNode newNode;
		list=new PriorityQueue<HuffNode>();
		PriorityQueue<HuffNode> listCopy;
		
		//input and output file names
		System.out.print("Input file: ");
		originalFile=new File(scan.nextLine());
		System.out.print("Output file: ");
		encodedFile=new File(scan.nextLine());
		
		//go through file and get char frequencies
		try {
			filescan=new Scanner(originalFile);
		} catch (Exception e) {}
		
		while (filescan.hasNextLine()) {
			line=filescan.nextLine();
			chars=line.toCharArray();
			
			for (char c : chars) {
				newNode=getNodeWithChar(c);
				if (newNode==null) { //if char is not already in priorityqueue
					newNode=new HuffNode(1, c); //create it
					list.add(newNode); //add it
				} else {
					list.remove(newNode);
					newNode.freq++;
					list.add(newNode);
				}
			}
		}
		
		listCopy=list;
		tree=new HuffTree(list);
		
		//encode file :D
		encode();
		System.out.println("The encoded data has been saved.");
		
		//now print frequencies to a new file called "frequencies.txt"
		saveFrequencies();
	}
}
