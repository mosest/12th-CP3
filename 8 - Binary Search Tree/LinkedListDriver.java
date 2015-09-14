//Tara Moses
//Assignment 14: Linked List
//October 18, 2013

import java.util.Scanner;

public class LinkedListDriver
{
	public static void main(String[] args)
	{
		Scanner scan=new Scanner(System.in);
		BinarySearchTree tree=new BinarySearchTree();
		int option=0;
		
		System.out.println("1. Add a value to list");
		System.out.println("2. Delete a value from list");
		System.out.println("3. Search for a value in list");
		System.out.println("4. Print the list");
		System.out.println("5. Exit");
		
		while (option<5)
		{
			System.out.print("\nChoose an option (1/2/3/4/5): ");
			option=scan.nextInt();
			
			if (option==1)
			{
				System.out.print("Value to add: ");
				int addThis=scan.nextInt();
				tree.add(addThis);
				System.out.println(addThis+" was added.");
			}
			else if (option==2)
			{
				System.out.print("Value to delete: ");
				int deleteThis=scan.nextInt();
				if (tree.search(deleteThis))
				{
					tree.delete(deleteThis);
					System.out.println(deleteThis+" was deleted.");
				}
				else System.out.println(deleteThis+" is not in list.");
			}
			else if (option==3)
			{
				System.out.print("Value to search for: ");
				int searchForThis=scan.nextInt();
				if (tree.search(searchForThis)) System.out.println(searchForThis+" is in list.");
				else System.out.println(searchForThis+" is not in list.");
			}
			else if (option==4)
			{
				System.out.println("List is as follows:");
				tree.print();
			}
		}
		System.out.println("Goodbye.\n");
	}
}
