//Tara Moses
//CP3 Assignment 11: Threading
//April 23, 2014

import java.lang.Thread;
import java.util.Scanner;

public class Threading {
	public static double sum=3;
	
	public static void main(String[] args) {
		FancyThread firstTerm=new FancyThread(2,true);
		firstTerm.start();
		System.out.println("after first term: "+sum);
		
		FancyThread secondTerm=new FancyThread(4,false);
		secondTerm.start();
		System.out.println("after second term: "+sum);
		
		FancyThread thirdTerm=new FancyThread(6,true);
		thirdTerm.start();
		System.out.println("after third term: "+sum);
		
		FancyThread fourthTerm=new FancyThread(8,false);
		fourthTerm.start();
		System.out.println("after fourth term: "+sum);
		
		/*Scanner scan=new Scanner(System.in);
		System.out.print("How many terms would you like? ");
		int numTerms=scan.nextInt();
		
		boolean positive=true;
		for (int i=0;i<numTerms;i++) {
			(new FancyThread(i*2,positive)).start();
			System.out.println("sum: "+sum);
			positive=!positive;
		}*/
	}
}
