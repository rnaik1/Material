package arrays;

import java.util.Arrays;

public class LargestNumberFormed {
	
	// The main function that prints the arrangement with the largest value.
	// The function accepts a vector of strings
	static void printLargest(Integer[] arr)
	{
		Arrays.sort(arr, new NumComparator());
	 
	    for (int i=0; i < arr.length; i++ )
	        System.out.print(arr[i]);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] a = {new Integer(54),new Integer(546),new Integer(548),new Integer(60)};
		printLargest(a);
	}

}
