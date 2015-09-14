//Tara Moses
//CP3 Assignment 2: ArrayList Practice
//January 17, 2014

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

public class SortWords {
	public static void addWords(String wordToAdd, List<String> al) {
		al.add(wordToAdd);
	}
	
	/**
	 * sorts words lexicographically (sp?)
	 * using a binary sorting algorithm.
	 * @param al the (array?)list of words to sort
	 **/
	public static void sortWords(List<String> al) {
		int swapsPerPass;
		String first;
		String second;
		
		do {
			swapsPerPass=0;
			for (int i=1;i<al.size();i++) {
				first=al.get(i-1);
				second=al.get(i);
				if (first.compareTo(second)>0) {
					al.set(i-1,second);
					al.set(i,first);
					swapsPerPass++;
				}
			}
		} while (swapsPerPass!=0);
	}
	
	/**
	 * prints out all the words in the arraylist
	 * @param al the arraylist containing words to be printed
	 **/
	public static void printWords(List<String> al) {
		for (int i=0;i<al.size();i++) {
			System.out.println(i+1+". "+al.get(i));
		}
	}
	
	/**
	 * searches for a word and returns either true or false
	 * depending on whether it's found or not.
	 * @param wordToSearchFor the word to search for. (Did I mention I'm a master of description?)
	 * @param al the arraylist that may or may not contain the word
	 * @return true if word is found in list, false if not
	 **/
	public static boolean searchFor(String wordToSearchFor, List<String> al) {
		int midIndex=al.size()/2;
		int min=0;
		int max=al.size()-1;
		
		while (min<=max) {
			midIndex=(min+max)/2;
			if (al.get(midIndex).compareTo(wordToSearchFor)<0) min=midIndex+1;
			else if (al.get(midIndex).compareTo(wordToSearchFor)>0) max=midIndex-1;
			else return true;
		}	
		return false;	
	}
	
	/**
	 * the main function. contains a menu!!
	 **/
	public static void main(String[] args) {
		List<String> fancyAL=new ArrayList(1);
		Scanner scan=new Scanner(System.in);
		Scanner filescan=null;
		int option=0;
		String currentWord="";
		
		//read in words from file
		try {
			filescan=new Scanner(new File("random_words.txt"));
		} catch (Exception e) {
			System.out.println("I guess the file wasn't found!");
		}
		while (filescan.hasNext()) {
			currentWord=filescan.next();
			fancyAL.add(currentWord);
		}
		
		//this is the menu!
		System.out.println("1. Add a word");
		System.out.println("2. Search for a word");
		System.out.println("3. Print all words");
		System.out.println("4. Sort all words");
		System.out.println("5. Quit");
		
		while (option!=5) {
			System.out.print("\nChoose an option (1/2/3/4/5): ");
			option=scan.nextInt();
			while (option>5 || option<1) {
				System.out.print("That's not an option. Choose again: ");
				option=scan.nextInt();
			}
			
			if (option==1) {  //add a word
				System.out.print("\nWhat word would you like to add? ");
				String word=scan.next();
				addWords(word, fancyAL);
				System.out.println(word+" has been added.");
			} else if (option==2) {  //search for a word
				System.out.print("\nWhat word would you like to search for? ");
				String word=scan.next();
				if (searchFor(word, fancyAL)) System.out.println(word+" was found!");
				else System.out.println(word+" wasn't found.");
			} else if (option==3) {  //print all of the words
				System.out.println("The words are as follows: \n");
				printWords(fancyAL);
			} else if (option==4) {  //SORT EVERYTHING WHOOHOO
				sortWords(fancyAL);
				System.out.println("The words have been sorted lexicographically.");
			} else System.out.println("Farewell!\n");
		}
	}
}
