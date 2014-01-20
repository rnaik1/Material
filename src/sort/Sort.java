package sort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Sort {

	private static int[] a = {100,90,80,70,60,50,40,30,20,10};
	private static int[] b = new int[a.length];

	public static void selectionSort(int[] arr) {
		int minIndex;
		for (int i = 0; i < arr.length - 1; i++) {
			minIndex = i;
			for (int j = i + 1; j < arr.length; j++)
				if (arr[j] < arr[minIndex])
					minIndex = j;
			if (minIndex != i) {
				int tmp = arr[i];
				arr[i] = arr[minIndex];
				arr[minIndex] = tmp;
			}
		}
	}

	public static void insertionSort(int[] arr) {
		for(int j=1;j<arr.length;j++){
			int x = arr[j];
			int i = j-1;
			while(i>=0 && arr[i]>x){
				arr[i+1] = arr[i];
				i--;
			}
			arr[i+1] = x;
		}
	}

	public static void bubbleSort(int[] arr){
		int n = arr.length;
		for(int i = 0; i< n; i++){
			for(int j=1;j<n-i;j++){
				if(arr[j-1] > arr[j]){
					int t = arr[j-1];
					arr[j-1]=arr[j];
					arr[j]=t;
				}
			}
		}
	}

	public static void MergeSort(int low, int high)
	// a[low : high] is a global array to be sorted.
	// Small(P) is true if there is only one element to
	// sort. In this case the list is already sorted.
	{
		if (low < high) { // If there are more than one element
			// Divide P into subproblems.
			// Find where to split the set.
			int mid = (low + high)/2;
			// Solve the subproblems.
			MergeSort(low, mid);
			MergeSort(mid + 1, high);
			// Combine the solutions.
			Merge(low, mid, high);
		}
	}

	public static void Merge(int low, int mid, int high)
	// a[low:high] is a global array containing two sorted
	// subsets in a[low:mid] and in a[mid+1:high]. The goal
	// is to merge these two sets into a single set residing
	// in a[low:high]. b[] is an auxiliary global array.
	{
		int h = low, i = low, j = mid+1;
		while ((h <= mid) && (j <= high)) {
			if (a[h] <= a[j]) {
				b[i] = a[h]; 
				h++; 
			}
			else {
				b[i] = a[j]; 
				j++; 
			} 
			i++;
		}
		if (h > mid)
			for (int k=j; k<=high; k++) {
				b[i] = a[k]; i++;
			}
		else 
			for (int k=h; k<=mid; k++) {
				b[i] = a[k]; i++;
			}
		for (int k=low; k<=high; k++) a[k] = b[k];
	}

	public static void heapSort(int[] a){
		int count = a.length;

		//first place a in max-heap order
		BuildHeap(a, count);

		int end = count - 1;
		while(end > 0){
			//swap the root(maximum value) of the heap with the
			//last element of the heap
			int tmp = a[end];
			a[end] = a[0];
			a[0] = tmp;
			//put the heap back in max-heap order
			Heapify(a, 0, end - 1);
			//decrement the size of the heap so that the previous
			//max value will stay in its proper place
			end--;
		}
	}

	public static void BuildHeap(int[] a, int count){
		//start is assigned the index in a of the last parent node
		int start = (count) / 2; //binary heap

		while(start >= 0){
			//sift down the node at index start to the proper place
			//such that all nodes below the start index are in heap
			//order
			Heapify(a, start, count - 1);
			start--;
		}
		//after sifting down the root all nodes/elements are in heap order
		
	}

	public static void Heapify(int[] a, int start, int end){
		//end represents the limit of how far down the heap to sift
		int root = start;

		while((root * 2 + 1) <= end){      //While the root has at least one child
			int child = root * 2 + 1;           //root*2+1 points to the left child
			//if the child has a sibling and the child's value is less than its sibling's...
			if(child + 1 <= end && a[child] < a[child + 1])
				child = child + 1;           //... then point to the right child instead
			if(a[root] < a[child]){     //out of max-heap order
				int tmp = a[root];
				a[root] = a[child];
				a[child] = tmp;
				root = child;                //repeat to continue sifting down the child now
			}else
				return;
		}
	}

	public static void quickSort(int arr[], int left, int right) {
		int index = partition(arr, left, right);
		if (left < index - 1)
			quickSort(arr, left, index - 1);
		if (index < right)
			quickSort(arr, index, right);
	}

	public static int partition(int arr[], int p, int r)
	{
		int x = arr[r];
		int i = p-1;
		for(int j=p;j<=r-1;j++){
			if(arr[j]<=x){
				i++;
				int t = arr[i];
				arr[i] = arr[j];
				arr[j] = t;
			}
		}
		int tmp = arr[i+1];
		arr[i+1] = arr[r];
		arr[r] = tmp;

		return i+1;
	}

	/* A[] --> Array to be sorted, l  --> Starting index, h  --> Ending index */
	static void quickSortIterative (int arr[], int l, int h)
	{
		// Create an auxiliary stack
		Stack<Integer> stack = new Stack<Integer>();

		// push initial values of l and h to stack
		stack.push(l);
		stack.push(h);

		// Keep popping from stack while is not empty
		while ( !stack.isEmpty() )
		{
			// Pop h and l
			h = stack.pop();
			l = stack.pop();

			// Set pivot element at its correct position in sorted array
			int p = partition( arr, l, h );

			// If there are elements on left side of pivot, then push left
			// side to stack
			if ( l< p-1 )
			{
				stack.push(l);
				stack.push(p-1);
			}

			// If there are elements on right side of pivot, then push right
			// side to stack
			if ( p+1 < h )
			{
				stack.push(p + 1);
				stack.push(h);
			}
		}
	}

	public static int[] countSort(int arr[],int k){
		int count[] = new int[k+1];
		for(int i=0;i<arr.length;i++){
			count[arr[i]]++;
		}

		for(int i=1;i<count.length;i++){
			count[i] += count[i-1];
		}

		int b[] = new int[arr.length];
		for(int i=arr.length-1;i>=0;i--){
			b[count[arr[i]] - 1] = arr[i];
			count[arr[i]]--;
		}

		for (int i = 0; i < b.length; i++)
			arr[i] = b[i];

		return b;
	}

	public static int[] countSort(int arr[],int k, int exp){
		int count[] = new int[10];
		for(int i=0;i<arr.length;i++){
			count[(arr[i]/exp)%10]++;
		}

		for(int i=1;i<count.length;i++){
			count[i] += count[i-1];
		}

		int b[] = new int[arr.length];
		for(int i=arr.length-1;i>=0;i--){
			b[count[(arr[i]/exp)%10] - 1] = arr[i];
			count[(arr[i]/exp)%10]--;
		}

		for (int i = 0; i < b.length; i++)
			arr[i] = b[i];

		return b;
	}

	// A utility function to get maximum value in arr[]
	static int getMax(int arr[], int n)
	{
		int mx = arr[0];
		for (int i = 1; i < n; i++)
			if (arr[i] > mx)
				mx = arr[i];
		return mx;
	}

	// The main function to that sorts arr[] of size n using Radix Sort
	static void radixsort(int arr[], int n)
	{
		// Find the maximum number to know number of digits
		int m = getMax(arr, n);

		// Do counting sort for every digit. Note that instead of passing digit number,
		// exp is passed. exp is 10^i where i is current digit number
		for (int exp = 1; m/exp > 0; exp *= 10)
			countSort(arr, n, exp);
	}
	
	public static void bucketSort(int[] a, int maxVal){
        int [] bucket=new int[maxVal+1];

        for (int i=0; i<bucket.length; i++){
            bucket[i]=0;
        }

        for (int i=0; i<a.length; i++){
            bucket[a[i]]++;
        }

        for (int i=0; i<bucket.length; i++){
            System.out.print(bucket[i] + "\t");
        }

        int outPos=0;
        for (int i=0; i<bucket.length; i++){
            for (int j=0; j<bucket[i]; j++){
                a[outPos++]=i;
            }
        }
        
        System.out.println();
        
        for (int i=0; i<a.length; i++){
            System.out.print(a[i] + "\t");
        }
    }

	public static int select(int arr[], int p, int r, int i){
		if(i>arr.length) return Integer.MAX_VALUE;
		if(p==r) return arr[p];
		else
		{
			int q = partition(arr, p, r);
			int k = q-p+1;
			if(k==i) return arr[q];
			else if (k>i) return select(arr, p, q-1, i);
			else return select(arr, q+1, r, i-k);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int[] radixSort (int [] data) {
		boolean flag = true;
		int divisor = 2;
		Queue[] buckets = new Queue[10];
		for (int i = 0; i < 10; i++)
			buckets[i] = new LinkedList<Integer>();

		while (flag) {
			flag = false;
			// first copy the values into buckets
			for (int i = 0; i < data.length; i++) {
				int hashIndex = (data[i] / divisor) % 10;
				if (hashIndex > 0) flag = true;
				((LinkedList<Integer>) buckets[hashIndex]).addLast(new Integer(data[i]));
			}
			// then copy the values back into vector
			divisor *= 10;
			int i = 0;
			for (int j = 0; j < 10; j++) {
				while (! buckets[j].isEmpty()) {
					Integer ival = ((LinkedList<Integer>) buckets[j]).getFirst();
					((LinkedList<Integer>) buckets[j]).removeFirst();
					data[i++] = ival.intValue();
				}
			}
		}

		return data;
	}

	static void flip(int arr[], int i)
	{
		int start = 0;
		while (start < i)
		{
			int temp = arr[start];
			arr[start] = arr[i];
			arr[i] = temp;
			start++;
			i--;
		}
	}

	static int findMax(int arr[], int n)
	{
		int mi=0;
		for (int i = 0; i < n; ++i)
			if (arr[i] > arr[mi])
				mi = i;
		return mi;
	}

	// The main function that sorts given array using flip operations
	static int pancakeSort(int[] arr, int n)
	{
		int count = 0;
		// Start from the complete array and one by one reduce current size by one
		for (int curr_size = n; curr_size > 1; --curr_size)
		{
			// Find index of the maximum element in arr[0..curr_size-1]
			int mi = findMax(arr, curr_size);

			// Move the maximum element to end of current array if it's not
			// already at the end
			if (mi != curr_size-1)
			{
				// To move at the end, first move maximum number to beginning 
				flip(arr, mi);

				// Now move the maximum number to end by reversing current array
				flip(arr, curr_size-1);
				
				count++;
			}
		}
		
		return count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//int arr[] = {5,4,1,3,7,9,2};
		int arr[] = {9,8,7,6,5,4,3,2,1};
		//int arr[] = {501,701,201,101,301};
		//radixSort(arr);
		/*pancakeSort(arr, arr.length);
		for (int i=0;i<arr.length;i++)
			System.out.print(arr[i] + "\t");*/
		//int[] sortarr = arr.clone();
		//selectionSort(sortarr);
		//insertionSort(sortarr);
		bubbleSort(arr);
		/*heapSort(sortarr);
		for (int i=0;i<sortarr.length;i++)
			System.out.print(sortarr[i] + "\t");
		System.out.println();*/
		/*MergeSort(0, a.length-1);
		for (int i=0;i<b.length;i++)
			System.out.print(b[i] + "\t");*/
		/*for (int i=0;i<sortarr.length;i++)
			System.out.print(sortarr[i] + "\t");
		quickSort(sortarr, 0, sortarr.length-1);
		System.out.println();
		for (int i=0;i<sortarr.length;i++)
			System.out.print(sortarr[i] + "\t");*/
		//int array[] = {1,3,0,1,3,1,1,0};
		//int result[] = countSort(array, 3);
		//int rarr[] = {212,121,232,231,311,331,302,130,123};
		int rarr[] = {37,12,22,212,44};
		radixsort(rarr, rarr.length);
		for (int i=0;i<rarr.length;i++)
			System.out.print(rarr[i] + "\t");
		//System.out.println(select(arr, 0, arr.length-1, 4));
		
		System.out.println();

		//System.out.println(isMatch("aa","a"));
		//quickSortIterative(arr, 0, arr.length-1);
		//for (int i=0;i<arr.length;i++)
		//System.out.print(arr[i] + "\t");
		
		int maxVal=3;
        int [] data= {1,0,1,2,3,1,0,3,1,0,1,3};
		bucketSort(data,maxVal);
		System.out.println();
		
		int[] cakeArray = {3,4,1,2};
		//int[] cakeArray = {3,1,4,1,5,9,2,6};
		int count = pancakeSort(cakeArray, cakeArray.length);
		System.out.println(count);
		for (int i=0;i<cakeArray.length;i++)
			System.out.print(cakeArray[i] + "\t");
		
		System.out.println("****");
		int select[] = {1,2,3,4,5,6,7,8,9,10};
		System.out.println(select(select, 0, select.length-1, select.length - 4));
		//System.out.println(RandomizedSelect(select, 0, select.length-1, select.length - 4));
		
		int heapArr[] = {4,2,1,3,5};
		BuildHeap(heapArr, heapArr.length);
		for(int i=0;i<heapArr.length;i++)
			System.out.print(heapArr[i] + "\t");
		heapSort(heapArr);
		System.out.println();
		for(int i=0;i<heapArr.length;i++)
			System.out.print(heapArr[i] + "\t");
	}
}
