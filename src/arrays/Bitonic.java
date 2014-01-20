package arrays;

/*
Given an array A[0 ... n-1] containing n positive integers, a subarray A[i ... j] is 
bitonic if there is a k with i <= k <= j such that A[i] <= A[i + 1] ... <= A[k] >= A[k + 1] >= .. A[j - 1] > = A[j]. 
Write a function that takes an array as argument and returns the length of the maximum length bitonic subarray.

Expected time complexity of the solution is O(n)

Simple Examples
1) A[] = {12, 4, 78, 90, 45, 23}, the maximum length bitonic subarray is {4, 78, 90, 45, 23} which is of length 5.

2) A[] = {20, 4, 1, 2, 3, 4, 2, 10}, the maximum length bitonic subarray is {1, 2, 3, 4, 2} which is of length 5.

Extreme Examples
1) A[] = {10}, the single element is bitnoic, so output is 1.

2) A[] = {10, 20, 30, 40}, the complete array itself is bitonic, so output is 4.

3) A[] = {40, 30, 20, 10}, the complete array itself is bitonic, so output is 4.*/

public class Bitonic {

	int LengthBitonicArray(int arr[], int n)
	{
	    int i;
	    int []inc = new int[n];
	    int []dec = new int[n];
	    int max;
	    inc[0] = 1; // The length of increasing sequence ending at first index is 1
	    dec[n-1] = 1; // The length of increasing sequence starting at first index is 1
	 
	    // Step 1) Construct increasing sequence array
	    for(i = 1; i < n; i++)
	    {
	        if (arr[i] > arr[i-1])
	            inc[i] = inc[i-1] + 1;
	        else
	            inc[i] = 1;
	    }
	 
	    // Step 2) Construct decreasing sequence array
	    for (i = n-2; i >= 0; i--)
	    {
	        if (arr[i] > arr[i+1])
	            dec[i] = dec[i+1] + 1;
	        else
	            dec[i] = 1;
	    }
	 
	    // Step 3) Find the length of maximum length bitonic sequence
	    max = inc[0] + dec[0] - 1;
	    for (i = 1; i < n; i++)
	    {
	        if (inc[i] + dec[i] - 1 > max)
	        {
	            max = inc[i] + dec[i] - 1;
	        }
	    }
	 
	    return max;
	}
	
	public static void main(String[] args) {
		int arr[] = {12, 4, 78, 90, 45, 23};
	    int n = arr.length;
	    System.out.println("Length of max length Bitnoic Subarray is: " + new Bitonic().LengthBitonicArray(arr, n));

	}

}
