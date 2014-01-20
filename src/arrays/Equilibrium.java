package arrays;

public class Equilibrium {

	static public int equilibrium(int arr[], int n)
	{
		int sum = 0;      // initialize sum of whole array
		int leftsum = 0; // initialize leftsum
		int i;

		/* Find sum of the whole array */
		for (i = 0; i < n; ++i)
			sum += arr[i];

		for( i = 0; i < n; ++i)
		{
			sum -= arr[i]; // sum is now right sum for index i

			if(leftsum == sum)
				return i;

			leftsum += arr[i];
		}

		/* If no equilibrium index found, then return 0 */
		return -1;
	}
	public static void main(String[] args) {
		int arr[] = {-7, 1, 5, 2, -4, 3, 0};
		//int arr[] = {7,2,3,2,4,3};
		int n = arr.length;
		System.out.println("First equilibrium index is: " + equilibrium(arr, n));
	}
}
