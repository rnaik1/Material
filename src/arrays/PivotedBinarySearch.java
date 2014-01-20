package arrays;

public class PivotedBinarySearch {

	static /* Searches an element no in a pivoted sorted array arrp[]
	   of size arr_size */
	int pivotedBinarySearch(int arr[], int arr_size, int no)
	{
		int pivot = findPivot(arr, 0, arr_size-1);

		// If we didn't find a pivot, then array is not rotated at all
		if (pivot == -1)
			return binarySearch(arr, 0, arr_size-1, no);

		// If we found a pivot, then first compare with pivot and then
		// search in two subarrays around pivot
		if (arr[pivot] == no)
			return pivot;
		if (arr[0] <= no)
			return binarySearch(arr, 0, pivot-1, no);
		else
			return binarySearch(arr, pivot+1, arr_size-1, no);
	}

	/* Function to get pivot. For array 3, 4, 5, 6, 1, 2
	   it will return 3 */
	static int findPivot(int arr[], int low, int high)
	{
		if (high < low)
			return -1;
		
		if (high == low) return low;

		int mid = (low + high)/2;   /*low + (high - low)/2;*/
		if (mid < high && arr[mid] > arr[mid + 1])
			return mid;
		if (arr[low] >= arr[mid])
			return findPivot(arr, low, mid-1);
		else
			return findPivot(arr, mid + 1, high);
	}

	/* Standard Binary Search function*/
	static int binarySearch(int arr[], int low, int high, int no)
	{
		if (high < low)
			return -1;

		int mid = (low + high)/2;  /*low + (high - low)/2;*/

		if (no == arr[mid])
			return mid;
		if (no > arr[mid])
			return binarySearch(arr, (mid + 1), high, no);
		else
			return binarySearch(arr, low, (mid -1), no);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int arr[] = {3, 4, 5, 6, 1, 2};
		int arr[] = {30, 40, 50, 60, 10, 20};
		int no = 30;
		System.out.println("Index of the element is: " + pivotedBinarySearch(arr, arr.length, no));

	}

}
