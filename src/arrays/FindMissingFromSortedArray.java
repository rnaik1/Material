package arrays;

public class FindMissingFromSortedArray {

	public int binary_search(int arr[], int left, int right) 
	{		
		if(right - left <= 0){
			return arr[right] - 1;
		}
		int midIndex = (left + right) / 2;
		//System.out.println(midIndex + " : " + arr[midIndex]);
		if(arr[midIndex] != midIndex){
			//take left
			//System.out.println("LEFT");
			return binary_search(arr, left, midIndex);
		}else{
			//take right
			//System.out.println("RIGHT");
			return binary_search(arr, midIndex + 1, right);
		}
	}

	/* getMissingNo takes array and size of array as arguments*/
	static int getMissingNo(int a[], int n)
	{
		int i;
		int x1 = a[0]; /* For xor of all the elemets in arary */
		int x2 = 1; /* For xor of all the elemets from 1 to n+1 */

		for (i = 1; i< n; i++)
			x1 = x1^a[i];

		for ( i = 2; i <= n+1; i++)
			x2 = x2^i;         

		return (x1^x2);
	}

	static int MissingNumber(int [] a, int n) 
	{
		int low = 0, mid = 0, high = n-1; 
		while (low <= high) 
		{
			mid = (low+high)/2; 
			if( mid == 0 || a[mid] - a[mid - 1] > 1 ) // order of logic must be preserved as shown. 
				return a[mid] - 1; 

			if (a[mid] - mid > 1) 
				high = mid - 1; 
			else //(a[mid] - mid == 1) 
				low = mid + 1; 
		}

		return 0; 
	}

	static int findMissingNumber(int a[]){
		int u = a.length-1;
		int l = 0;
		int ci = (u+l)/2;
		int r;
		while((r=(a[ci]-a[l]))!=(ci-l)){
			if (r == 0)
				u = ci;
			else
				l = ci;
			if (l == u - 1)
				break;
		}
		return (a[u] + a[l])/2;
	}

	public static void main(String[] args) {
		int a[] = {1,2,3,5,6,7,8,9};
		//System.out.println(new FindMissingFromSortedArray().binary_search(a, 0, a.length-1));
		System.out.println(getMissingNo(a, a.length));
		int x = MissingNumber(a, a.length);
		//int x = findMissingNumber(a);
		System.out.println(x);
	}
}