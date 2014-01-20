package arrays;

public class CielandFloorofArray {

	public static int findCeil(int a[], int x){
		if(x<=a[0])
			return 0;
		for(int i=0;i<a.length;i++){
			if(a[i] == x)
				return i;

			if(a[i] < x && a[i+1] >= x)
				return i+1;
		}
		return -1;
	}

	static int ceilSearch(int arr[], int low, int high, int x)
	{
		int mid;

		/* If x is smaller than or equal to the first element,
	    then return the first element */
		if(x <= arr[low])
			return low; 

		/* If x is greater than the last element, then return -1 */
		if(x > arr[high])
			return -1;  

		/* get the index of middle element of arr[low..high]*/
		mid = (low + high)/2;  /* low + (high - low)/2 */

		/* If x is same as middle element, then return mid */
		if(arr[mid] == x)
			return mid;

		/* If x is greater than arr[mid], then either arr[mid + 1]
	    is ceiling of x or ceiling lies in arr[mid+1...high] */ 
		else if(arr[mid] < x)
		{
			if(mid + 1 <= high && x <= arr[mid+1])
				return mid + 1;
			else
				return ceilSearch(arr, mid+1, high, x);
		}

		/* If x is smaller than arr[mid], then either arr[mid] 
	     is ceiling of x or ceiling lies in arr[mid-1...high] */   
		else
		{
			if(mid - 1 >= low && x > arr[mid-1])
				return mid;
			else    
				return ceilSearch(arr, low, mid - 1, x);
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {1, 2, 8, 10, 10, 12, 19};
		int x = 9;
		int indexLinear = findCeil(arr, x);
		int indexBinarySearch = ceilSearch(arr, 0, arr.length-1, x);
		System.out.println(indexLinear + "\t" + indexBinarySearch);
	}
}
