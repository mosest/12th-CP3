//Tara Moses
//CP3 Assignment 3: Timing Sorts
//January 21, 2014

import java.util.Scanner;

public class TimingSorts {
	public static int QScompareCount=0;
	public static int QSswapCount=0;
	public static int HScompareCount=0;
	public static int HSswapCount=0;
	public static int MScompareCount=0;
	public static int MSswapCount=0;
	
	/**
	 * prints contents of an array
	 * @param array the array to print contents of
	 **/
	public static void printArray(int[] array) {
		for (int i=0;i<array.length;i++) {
			System.out.println(i+". "+array[i]);
		}
	}
	
	/**
	 * sorts an array of numbers using the selection method.
	 * Switches lowest number with first number in unsorted part
	 * of array.
	 * @param nums the array to sort
	 * @return sorted array
	 **/
	public static int[] selectionSort(int[] nums) {
		//http://en.wikipedia.org/wiki/Selection_sort
		int swapCount=0;
		int compareCount=0;
		int passCount=0;
		int low=nums[0];
		int current=nums[0];
		int lowIndex=0;
		int switchWithLow=nums[0];
		long time1;
		long time2;
		long duration;
		
		time1=System.nanoTime();
		for (int a=0;a<nums.length;a++) {
			low=nums[passCount];
			current=nums[passCount];
			lowIndex=passCount;
			switchWithLow=nums[passCount];
			for (int i=passCount;i<nums.length;i++) {
				current=nums[i];
				if (current<low) {
					low=current;
					lowIndex=i;
				}
				compareCount++;
			}
			nums[passCount]=low;
			nums[lowIndex]=switchWithLow;
			swapCount++;
			passCount++;
		}
		time2=System.nanoTime();
		duration=time2-time1;
		
		System.out.println("comparisons: "+compareCount);
		System.out.println("swaps: "+swapCount);
		System.out.println("duration: "+duration+" nanoseconds");
		
		return nums;
	}
	
	/**
	 * sorts an array of numbers using the insertion method.
	 * takes a number and moves it back in the list where it belongs.
	 * shifts everything else forward.
	 * @param nums the array to sort
	 * @return sorted array
	 **/
	public static int[] insertionSort(int[] nums) {
		//http://en.wikipedia.org/wiki/Insertion_sort
		int swapCount=0;
		int compareCount=0;
		int current;
		int temp;
		int index;
		long time1;
		long time2;
		long duration;
		
		time1=System.nanoTime();
		for (int i=1;i<nums.length;i++) { //first item is always sorted, so start at second item :3
			current=nums[i];
			index=i;
			
			//go back in list and find where current belongs
			if (nums[0]>current) index=0;
			for (int j=0;j<i;j++) {
				if (nums[j]<=current) index=j+1;
				compareCount++;
			}
			if (index!=i) {
				temp=nums[index];
				nums[index]=current;
				swapCount++;
				
				//shift everything with greater index up one :D
				for (int k=i;k>index;k--) nums[k]=nums[k-1];
				nums[index+1]=temp;
			}
		}
		time2=System.nanoTime();
		duration=time2-time1;
		
		System.out.println("comparisons: "+compareCount);
		System.out.println("swaps: "+swapCount);
		System.out.println("duration: "+duration+" nanoseconds");
		
		return nums;
	}
	
	/**
	 * sorts an array of numbers using the quicksort method.
	 * Uses a dynamic pointer and a stationary pivot to 
	 * sort array by splitting it into >=pivot, pivot, and <=pivot
	 * @param nums the array to sort
	 * @return sorted array
	 **/
	public static int[] quickSort(int[] nums) {
		//http://en.wikipedia.org/wiki/Quick_sort
		long time1=System.nanoTime();		
		helperQS(nums, 0, nums.length);
		long time2=System.nanoTime();
		long duration=time2-time1;
		
		System.out.println("comparisons: "+QScompareCount);
		System.out.println("swaps: "+QSswapCount);
		System.out.println("duration: "+duration+" nanoseconds");
		return nums;
	}
	
	/**
	 * recursive method to quicksort
	 * @param nums the array to sort
	 * @param start the index of the first item in the range to sort
	 * @param end the index of the last item in the range to sort
	 **/
	public static void helperQS(int[] nums, int start, int end) {
		int pointerIndex=start;
		int pivotIndex=end-1;
		int temp;
		int current;
		
		if (end-start>1) {
			for (int i=pointerIndex;i<end;i++) {
				current=nums[i];
				if (current<nums[pivotIndex]) {
					temp=nums[pointerIndex];
					nums[pointerIndex]=current;
					nums[i]=temp;
									
					pointerIndex++; //incrementation!! :D
					QSswapCount++;
				}
				QScompareCount++;
			}
			temp=nums[pointerIndex];
			nums[pointerIndex]=nums[pivotIndex];
			nums[pivotIndex]=temp;
			QSswapCount++;
			
			helperQS(nums, start, pointerIndex);
			helperQS(nums, pointerIndex, end);
		}
	}
	
	
	/**
	 * sorts an array of numbers using the intelligent design sort method!
	 * (it's already sorted by The Sorter. Praise The Sorter!)
	 * @param nums the array that has already been sorted by The Sorter
	 * @return the sorted array
	 **/
	public static int[] esotericSort(int[] nums) {
		//http://www.dangermouse.net/esoteric/
		//I guess we're doing intelligent design sort! 
		int compareCount=0;
		int swapCount=0;
		long time1=System.nanoTime();
		long time2=System.nanoTime();
		long duration=time2-time1;
		
		System.out.println("comparisons: "+compareCount);
		System.out.println("swaps: "+swapCount);
		System.out.println("duration: "+duration+" nanoseconds");
		return nums;
	}
	
	/**
	 * sorts array using heapsort method.
	 * takes an array, makes it into a heap, and then takes 
	 * numbers off the heap.
	 * @param nums array to sort
	 * @return sorted array
	 **/
	public static int[] heapSort(int[] nums) {
		//http://en.wikipedia.org/wiki/Heapsort
		int biggerChildIndex;
		int swapCount=0;
		int compareCount=0;
		int parentIndex;
		int topIndex=0;
		int bottomIndex;
		int temp;
		int end=nums.length-1;
		long time1;
		long time2;
		long duration;
		
		time1=System.nanoTime();
		nums=heapify(nums);
		
		for (int i=0;i<nums.length;i++) {
			bottomIndex=end;
			parentIndex=0;
			
			//switch first and last one i guess
			temp=nums[topIndex];
			nums[topIndex]=nums[bottomIndex];
			nums[bottomIndex]=temp;
			end--;
			HSswapCount++;
			
			//trickle down last one :D
			while (parentIndex*2+1<=end) {  //if parent has a child (left one at least)
				biggerChildIndex=parentIndex*2+1;  //bigger child is left child
				HScompareCount+=2;
				
				if (parentIndex*2+2<=end && nums[biggerChildIndex]<nums[biggerChildIndex+1]) {
					biggerChildIndex=parentIndex*2+2;	//if right child exists and is bigger			
				}										//then biggerchild=right child
				if (nums[parentIndex]<nums[biggerChildIndex]) {
					//swap parent with biggerchild
					temp=nums[parentIndex];
					nums[parentIndex]=nums[biggerChildIndex];
					nums[biggerChildIndex]=temp;
					HSswapCount++;
					parentIndex=biggerChildIndex;
				} else break;
			}
		}
		time2=System.nanoTime();
		duration=time2-time1;
		
		System.out.println("comparisons: "+HScompareCount);
		System.out.println("swaps: "+HSswapCount);
		System.out.println("duration: "+duration+" nanoseconds");
		return nums;
	}
	
	/**
	 * takes in an array and makes it a heap.
	 * @param array array to heapify
	 * @return heapified version of array
	 **/
	public static int[] heapify(int[] array) {
		int childIndex;
		int parentIndex;
		int temp;
		int[] newArray=new int[array.length];
		
		newArray[0]=array[0];
		for (int i=1;i<array.length;i++) {
			 childIndex=i;
			 newArray[i]=array[i];
			 
			 while (childIndex>0) {
				parentIndex=(childIndex-1)/2;
				if (newArray[parentIndex]<newArray[childIndex]) {
					//swap parent and child
					temp=newArray[parentIndex];
					newArray[parentIndex]=newArray[childIndex];
					newArray[childIndex]=temp;
					HSswapCount++;
				}
				childIndex=parentIndex;
				HScompareCount++;
			 }
		}
		return newArray;
	}
	
	/**
	 * sorts array using mergesort method
	 * splits array in half over and over
	 * again; then merges pieces together in sorted order
	 * until array is put together again.
	 * @param nums array to sort
	 * @return sorted array
	 **/
	public static int[] mergeSort(int[] nums) {
		//http://en.wikipedia.org/wiki/Merge_sort
		long time1=System.nanoTime();
		nums=helperMergeSort(nums);
		long time2=System.nanoTime();
		long duration=time2-time1;
		
		System.out.println("comparisons: "+MScompareCount);
		System.out.println("swaps: "+MSswapCount);
		System.out.println("duration: "+duration+" nanoseconds");
		
		return nums;
	}
	
	/**
	 * recursive method that breaks array into pieces
	 * @param a array to break up
	 * @return array that has been broken up, sorted, and put together
	 **/
	public static int[] helperMergeSort(int[] a) {
		int mid=(a.length)/2;
		int[] front=new int[mid];
		int[] back=new int[a.length-front.length];
		
		System.arraycopy(a,0,front,0,front.length);
		System.arraycopy(a,mid,back,0,back.length);
			
		if (front.length>0 || back.length>0) {	
			if (front.length>1) {
				front=helperMergeSort(front);
			}
			if (back.length>1) {
				back=helperMergeSort(back);
			}
			
			a=merge(front,back);
		}
		return a;
	}
	
	/**
	 * takes two arrays and merges them together in sorted order
	 * @param a first array to merge with second
	 * @param b second array to merge with first
	 * @return merged array
	 **/
	public static int[] merge(int[] a, int[] b) {
		int aIndex=0;
		int bIndex=0;
		int currentA;
		int currentB;
		int[] newArray=new int[a.length+b.length];
		
		for (int i=0;i<newArray.length;i++) {
			if (aIndex<a.length) currentA=a[aIndex];
			else currentA=101;
			if (bIndex<b.length) currentB=b[bIndex];
			else currentB=101;
			
			if (currentA<currentB) {
				newArray[i]=currentA;
				aIndex++;
				MSswapCount++;
			} else {
				newArray[i]=currentB;
				bIndex++;
			}
			MScompareCount++;
		}		
		return newArray;
	}
	
	/**
	 * sorts array in the best way ever!!
	 * takes an array of nums, sums its digits together, and 
	 * counts how many nums have that digit sum. 
	 * for example, nums[12]=5 says that five numbers in the original
	 * array have digits that sum to be 12. 
	 * @param nums the array to "sort"
	 * @return "sorted" array
	 **/
	public static int[] bestSort(int[] nums) {
		//http://en.wikipedia.org/wiki/Creativity
		int current;
		int newCurrent;
		int[] digitSums=new int[nums.length];
		
		for (int i=0;i<nums.length;i++) {
			current=nums[i];
			newCurrent=(current/10)+(current%10);
			digitSums[newCurrent]++;
		}
		
		printArray(digitSums);
		
		return nums;
	}
	
	/**
	 * shuffles an array so that it's full of the numbers
	 * you want, but they're randomly ordered
	 * @param nums the array to shuffle
	 **/
	public static void shuffle(int[] nums) {
		int temp;
		int random1;
		int random2;
		
		for (int i=0;i<1000;i++) {
			random1=(int)(Math.random()*100);
			random2=(int)(Math.random()*100);
			temp=nums[random1];
			nums[random1]=nums[random2];
			nums[random2]=temp;
		}
	}
	
	/**
	 * main function :D holds a menu!
	 **/
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		int size=100;
		int[] nums=new int[size];
		int[] selectionNums=new int[size];
		int[] insertionNums=new int[size];
		int[] quickNums=new int[size];
		int[] esotericNums=new int[size];
		int[] heapNums=new int[size];
		int[] mergeNums=new int[size];
		int[] bestNums=new int[size];
		int choice=0;
		
		//generating random numbers		
		System.out.println("Original numbers:");
		
		for (int i=0;i<size;i++) {
			nums[i]=i+1;
		} 
		
		shuffle(nums);
		printArray(nums);
		System.out.println();
		
		//create menu whoohoo
		System.out.println("1. Selection sort");
		System.out.println("2. Insertion sort");
		System.out.println("3. Quick sort");
		System.out.println("4. Esoteric sort");
		System.out.println("5. Heap sort");
		System.out.println("6. Merge sort");
		System.out.println("7. Best (custom) sort");
		System.out.println("8. Exit");
		
		while (choice!=8) {
			System.out.print("\nChoose a sort (1-8): ");
			choice=scan.nextInt();
			
			while (choice<1 || choice>9) {
				System.out.print("\nInvalid choice. Choose again (1-8): ");
				choice=scan.nextInt();
			}
			
			if (choice==1) {
				System.out.println("Selection Sort:\n");
				System.arraycopy(nums,0,selectionNums,0,size);
				printArray(selectionSort(selectionNums));
			} else if (choice==2) {
				System.out.println("Insertion Sort:\n");
				System.arraycopy(nums,0,insertionNums,0,size);
				printArray(insertionSort(insertionNums));				
			} else if (choice==3) {
				System.out.println("Quick Sort:\n");
				System.arraycopy(nums,0,quickNums,0,size);
				printArray(quickSort(quickNums));				
			} else if (choice==4) {
				System.out.println("Esoteric Sort:\n");
				System.arraycopy(nums,0,esotericNums,0,size);
				printArray(esotericSort(esotericNums));
			} else if (choice==5) {
				System.out.println("Heap Sort:\n");
				System.arraycopy(nums,0,heapNums,0,size);	
				printArray(heapSort(heapNums));
			} else if (choice==6) {
				System.out.println("Merge Sort:\n");
				System.arraycopy(nums,0,mergeNums,0,size);
				printArray(mergeSort(mergeNums));
			} else if (choice==7) {
				System.out.println("Best Sort:\n");
				System.arraycopy(nums,0,bestNums,0,size);	
				bestSort(bestNums);
			} else if (choice==8) {
				System.out.println("You'll be back.\n");
			}
		}	
	}
} 
