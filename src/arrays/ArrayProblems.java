package arrays;

import java.util.Arrays;
import java.util.Comparator;

import binaryTree.TreeNode;
import binaryTree.TreeProblems;

public class ArrayProblems {

	static // Find the minimum element in a sorted and rotated array
	// The function that handles duplicates.  It can be O(n) in worst case.
	int findMin(int arr[], int low, int high)
	{
		// This condition is needed to handle the case when array is not
		// rotated at all
		if (high < low)  return arr[0];

		// If there is only one element left
		if (high == low) return arr[low];

		// Find mid
		int mid = low + (high - low)/2; /*(low + high)/2;*/

		// Check if element (mid+1) is minimum element. Consider
		// the cases like {1, 1, 0, 1}
		if (mid < high && arr[mid+1] < arr[mid])
			return arr[mid+1];

		// This case causes O(n) time
		if (arr[low] == arr[mid] && arr[high] == arr[mid])
			return Math.min(findMin(arr, low, mid-1), findMin(arr, mid+1, high));

		// Check if mid itself is minimum element
		if (mid > low && arr[mid] < arr[mid - 1])
			return arr[mid];

		// Decide whether we need to go to left half or right half
		if (arr[high] > arr[mid])
			return findMin(arr, low, mid-1);
		return findMin(arr, mid+1, high);
	}

	// ************************************************************************************************

	// A binary search based function that returns index of a peak element
	static int findPeakUtil(int arr[], int low, int high, int n)
	{
		// Fin index of middle element
		int mid = low + (high - low)/2;  /* (low + high)/2 */

		// Compare middle element with its neighbours (if neighbours exist)
		if ((mid == 0 || arr[mid-1] <= arr[mid]) &&
				(mid == n-1 || arr[mid+1] <= arr[mid]))
			return mid;

		// If middle element is not peak and its left neighbor is greater than it
		// then left half must have a peak element
		else if (mid > 0 && arr[mid-1] > arr[mid])
			return findPeakUtil(arr, low, (mid -1), n);

		// If middle element is not peak and its right neighbor is greater than it
		// then right half must have a peak element
		else return findPeakUtil(arr, (mid + 1), high, n);
	}

	static // A wrapper over recursive function findPeakUtil()
	int findPeak(int arr[], int n)
	{
		return findPeakUtil(arr, 0, n-1, n);
	}

	// ************************************************************************************************

	// A structure to store data and its frequency
	public class dataFreq
	{
		int data;
		int freq;

		public dataFreq(){
			data = 0;
			freq = 1;
		}
	}

	public static TreeNode insert(TreeNode node,int data){
		if(node==null){
			node = new TreeNode(data);
		}
		else
		{
			if (data == node.data) // If already present
				node.freq += 1;
			else if(data < node.data)
				node.left = insert(node.left,data);
			else 
				node.right = insert(node.right,data);
		}
		return node;
	}

	// Function to copy elements and their frequencies to count[].
	static int index = 0;
	static void store(TreeNode root, dataFreq count[])
	{
		// Base Case
		if (root == null) return;

		// Recur for left substree
		store(root.left, count);

		// Store item from root and increment index
		count[(index)].freq = root.freq;
		count[(index)].data = root.data;
		(index)++;

		// Recur for right subtree
		store(root.right, count);
	}

	static // The main function that takes an input array as an argument
	// and sorts the array items according to frequency
	int[] sortByFrequency(int arr[], int n)
	{
		// Create an empty BST and insert all array items in BST
		TreeNode root = null;
		for (int i = 0; i < n; ++i)
			root = insert(root, arr[i]);

		TreeProblems.inOrder(root);
		System.out.println();
		// Create an auxiliary array 'count[]' to store data and
		// frequency pairs. The maximum size of this array would
		// be n when all elements are different
		dataFreq count[] = new dataFreq[n];
		for(int i=0;i<count.length;i++){
			count[i] = new ArrayProblems().new dataFreq();
		}

		// int index = 0;
		store(root, count);

		// Sort the count[] array according to frequency (or count)
		Arrays.sort(count,0,index, new Comparator<dataFreq>() {
			public int compare(dataFreq o1, dataFreq o2) {
				if (o1.freq > o2.freq) return 1;
				if (o1.freq < o2.freq) return -1;
				return 0;
			}
		});

		// Finally, traverse the sorted count[] array and copy the
		// i'th item 'freq' times to original array 'arr[]'
		int j = 0;
		for (int i = 0; i < index; i++)
		{
			for (int freq = count[i].freq; freq > 0; freq--)
				arr[j++] = count[i].data;
		}

		return arr;
	}

	// A utility function to print an array of size n
	static void printArray(int arr[], int n)
	{
		for (int i = 0; i < n; i++)
			System.out.print(arr[i] + "\t");
		System.out.println();
	}

	// ************************************************************************************************

	// The main function that prints all combinations of size r
	// in arr[] of size n. This function mainly uses combinationUtil()
	static void printCombination(int arr[], int n, int r)
	{
		// A temporary array to store all combination one by one
		int data[] = new int[r];

		Arrays.sort(arr);

		// Print all combination using temprary array 'data[]'
		combinationUtil(arr, n, r, 0, data, 0);
	}

	/* arr[]  ---> Input Array
	   n      ---> Size of input array
	   r      ---> Size of a combination to be printed
	   index  ---> Current index in data[]
	   data[] ---> Temporary array to store current combination
	   i      ---> index of current element in arr[] */
	static void combinationUtil(int arr[], int n, int r, int index, int data[], int i)
	{
		// Current combination is ready, print it
		if (index == r)
		{
			for (int j=0; j<r; j++)
				System.out.print(data[j] + "\t");
			System.out.println();
			return;
		}

		// When no more elements are there to put in data[]
		if (i >= n)
			return;

		// current is included, put next at next location
		data[index] = arr[i];
		combinationUtil(arr, n, r, index+1, data, i+1);

		// Since the elements are sorted, all occurrences of an element
		// must be together
		if(i+1 != n)
			while (arr[i] == arr[i+1])
				i++;

		// current is excluded, replace it with next (Note that
		// i+1 is passed, but index is not changed)
		combinationUtil(arr, n, r, index, data, i+1);
	}

	// ************************************************************************************************

	private static int magnitudePole(int[] A)
	{
		boolean result = true;
		for(int i = 0; i < A.length; i++)
		{
			for(int j = i - 1; j >= 0; j--)
			{
				if((A[i] - A[j]) < 0 )
				{
					result = false;
					break;
				}
				else
				{
					result = true;
				}
			}

			for(int j = i + 1; j < A.length && result; j++)
			{   
				if((A[j] - A[i]) < 0)
				{                
					result = false;
					break;
				}
			}          
			if(result) return i;              
		}
		return -1;
	}

	// ************************************************************************************************

	/* structure is used to return two values from minMax() */
	class pair 
	{
		int min;
		int max;
	};  

	pair getMinMax(int arr[], int n)
	{
		pair minmax = new pair();
		int i;  

		/* If array has even number of elements then 
	    initialize the first two elements as minimum and 
	    maximum */
		if(n%2 == 0)
		{         
			if(arr[0] > arr[1])     
			{
				minmax.max = arr[0];
				minmax.min = arr[1];
			}  
			else
			{
				minmax.min = arr[0];
				minmax.max = arr[1];
			}
			i = 2;  /* set the starting index for loop */
		}  

		/* If array has odd number of elements then 
	    initialize the first element as minimum and 
	    maximum */
		else
		{
			minmax.min = arr[0];
			minmax.max = arr[0];
			i = 1;  /* set the startung index for loop */
		}

		/* In the while loop, pick elements in pair and 
	     compare the pair with max and min so far */   
		while(i < n-1)  
		{          
			if(arr[i] > arr[i+1])          
			{
				if(arr[i] > minmax.max)        
					minmax.max = arr[i];
				if(arr[i+1] < minmax.min)          
					minmax.min = arr[i+1];        
			} 
			else        
			{
				if(arr[i+1] > minmax.max)        
					minmax.max = arr[i+1];
				if(arr[i] < minmax.min)          
					minmax.min = arr[i];        
			}        
			i += 2; /* Increment the index by 2 as two 
	               elements are processed in loop */
		}

		return minmax;
	} 

	// ************************************************************************************************

	static void printUnsorted(int arr[], int n)
	{
		int s = 0, e = n-1, i, max, min;   

		// step 1(a) of above algo
		for (s = 0; s < n-1; s++)
		{
			if (arr[s] > arr[s+1])
				break;
		}
		if (s == n-1)
		{
			System.out.println("The complete array is sorted");
			return;
		}

		// step 1(b) of above algo
		for(e = n - 1; e > 0; e--)
		{
			if(arr[e] < arr[e-1])
				break;
		}

		// step 2(a) of above algo
		max = arr[s]; min = arr[s];
		for(i = s + 1; i <= e; i++)
		{
			if(arr[i] > max)
				max = arr[i];
			if(arr[i] < min)
				min = arr[i];
		}

		// step 2(b) of above algo
		for( i = 0; i < s; i++)
		{
			if(arr[i] > min)
			{  
				s = i;
				break;
			}
		}

		// step 2(c) of above algo
		for( i = n -1; i >= e+1; i--)
		{
			if(arr[i] < max)
			{
				e = i;
				break;
			} 
		}

		// step 3 of above algo
		System.out.println("The unsorted subarray which makes the given array " +
				" sorted lies between the index " + s + "\t" + e);
		return;
	}

	// ************************************************************************************************

	// ************************************************************************************************

	// ************************************************************************************************

	// ************************************************************************************************

	public static void main(String[] args) {

		int arr1[] =  {5, 6, 1, 2, 3, 4};
		int n1 = arr1.length;
		System.out.println("The minimum element is:" + findMin(arr1, 0, n1-1));

		int arr[] = {1, 3, 20, 4, 1, 0};
		int n = arr.length;
		System.out.println("Index of a peak point is: " + findPeak(arr, n));

		int freqArr[] = {2, 3, 2, 4, 5, 12, 2, 3, 3, 3, 12};
	    int freqArrSize = freqArr.length;
	    freqArr = sortByFrequency(freqArr, freqArrSize);
	    printArray(freqArr, freqArrSize);

		int combArray[] = {1, 2, 1, 3, 1};
		int r = 3;
		int combArraySize = combArray.length;
		printCombination(combArray, combArraySize, r);

		int[] A = {};
		System.out.println(magnitudePole(A));
		int[] B = {0};
		System.out.println(magnitudePole(B));      
		int[] C = {1,1,1,1};
		System.out.println(magnitudePole(C));
		int[] D = {4,2,2,3,1,4,7,8,6,9};
		System.out.println(magnitudePole(D));
		int[] E = {2,1,4,2,3,1,3,5,2,7};
		System.out.println(magnitudePole(E));
		int[] F = {2,1,5};
		System.out.println(magnitudePole(F));
		int[] G = {2,3,1,2,1};
		System.out.println(magnitudePole(G));
		int[] H = {2};
		System.out.println(magnitudePole(H));

		int comparr[] = {1000, 11, 445, 1, 330, 3000};
		int arr_size = 6;
		pair minmax = new ArrayProblems().getMinMax(comparr, arr_size);
		System.out.println("Minimum element is: " + minmax.min);
		System.out.println("Maximum element is: " + minmax.max);

		int sortArr[] = {10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60};
		printUnsorted(sortArr, sortArr.length);

	}

}
