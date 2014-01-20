package arrays;

/*Given an unsorted array of nonnegative integers, find a continous subarray which adds to a given number.

Examples:

Input: arr[] = {1, 4, 20, 3, 10, 5}, sum = 33
Ouptut: Sum found between indexes 2 and 4

Input: arr[] = {1, 4, 0, 0, 3, 10, 5}, sum = 7
Ouptut: Sum found between indexes 1 and 4

Input: arr[] = {1, 4}, sum = 0
Output: No subarray found*/

public class SubarraySum {

	static int subArraySum(int arr[], int n, int sum)
	{
		/* Initialize curr_sum as value of first element
	       and starting point as 0 */
		int curr_sum = arr[0], start = 0;

		/* Add elements one by one to curr_sum and if the curr_sum exceeds the
	       sum, then remove starting element */
		for (int i = 1; i <= n; i++)
		{
			// If curr_sum exceeds the sum, then remove the starting elements
			while (curr_sum > sum && start < i-1)
			{
				curr_sum = curr_sum - arr[start];
				start++;
			}

			// If curr_sum becomes equal to sum, then return true
			if (curr_sum == sum)
			{
				System.out.println("Sum found between indexes " +  start + " and " + (i-1));
				return 1;
			}

			// Add this element to curr_sum
			if (i < n)
				curr_sum = curr_sum + arr[i];
		}

		// If we reach here, then no subarray
		System.out.println("No subarray found");
		return 0;
	}

	public static void main(String[] args) {
		int arr[] = {15, 2, 4, 8, 9, 5, 10, 23};
		//int arr[] = {2,3,-8,-1,2,4,-2,3};
	    int n = arr.length;
	    int sum = 76;
	    subArraySum(arr, n, sum);
	}
}
