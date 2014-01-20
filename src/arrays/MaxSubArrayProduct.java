package arrays;
import java.util.ArrayList;


public class MaxSubArrayProduct {

	public static int getMaxProduct(int[] a) {
		int maxProd = 0;
		int runningProd = 1;
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < a.length; i++) {
			if(runningProd < 0 && a[i] > 0)
				runningProd = 1;
			runningProd *= a[i];
			if (maxProd < runningProd) {
				maxProd = runningProd;
				list.add(i);
			}
			/*else if(runningProd < 0)
				runningProd = 1;*/
		}
		System.out.println(list);
		return maxProd;
	}

	/* Returns the product of max product subarray.  Assumes that the
	   given array always has a subarray with product more than 1 */
	static int maxSubarrayProduct(int arr[], int n)
	{
		// max positive product ending at the current position
		int max_ending_here = 1;

		// min negative product ending at the current position
		int min_ending_here = 1;

		// Initialize overall max product
		int max_so_far = 1;

		/* Traverse through the array. Following values are maintained after the ith iteration:
	       max_ending_here is always 1 or some positive product ending with arr[i]
	       min_ending_here is always 1 or some negative product ending with arr[i] */
		for (int i = 0; i < n; i++)
		{
			/* If this element is positive, update max_ending_here. Update
	           min_ending_here only if min_ending_here is negative */
			if (arr[i] > 0)
			{
				max_ending_here = max_ending_here*arr[i];
				min_ending_here = Math.min(min_ending_here * arr[i], 1);
			}

			/* If this element is 0, then the maximum product cannot
	           end here, make both max_ending_here and min_ending_here 0
	           Assumption: Output is alway greater than or equal to 1. */
			else if (arr[i] == 0)
			{
				max_ending_here = 1;
				min_ending_here = 1;
			}

			/* If element is negative. This is tricky
	           max_ending_here can either be 1 or positive. min_ending_here can either be 1 
	           or negative.
	           next min_ending_here will always be prev. max_ending_here * arr[i]
	           next max_ending_here will be 1 if prev min_ending_here is 1, otherwise 
	           next max_ending_here will be prev min_ending_here * arr[i] */
			else
			{
				int temp = max_ending_here;
				max_ending_here = Math.max(min_ending_here * arr[i], 1);
				min_ending_here = temp * arr[i];
			}

			// update max_so_far, if needed
			if (max_so_far <  max_ending_here)
				max_so_far  =  max_ending_here;
		}

		return max_so_far;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int A[] = {-2, -3, 0, -2, -40};
		//int A[] = {-1, -3, -10, 0, 60};
		int A[] = {6, -3, -10, 0, -2};
		System.out.println(getMaxProduct(A));
		System.out.println(maxSubarrayProduct(A,5));
	}

}
