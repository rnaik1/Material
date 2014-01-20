package arrays;

public class IsArrayContinuos {

	static boolean areConsecutive(int arr[], int n)
	{
		if ( n <  1 )
			return false;

		/* 1) Get the minimum element in array */
		int min = getMin(arr, n);

		/* 2) Get the maximum element in array */
		int max = getMax(arr, n);

		/* 3) max - min + 1 is equal to n,  then only check all elements */
		if (max - min  + 1 == n)
		{
			boolean[] visited = new boolean[n];
			for (int i = 0; i < n; i++)
			{
				/* If we see an element again, then return false */
				if ( visited[arr[i] - min])
					return false;

				/* If visited first time, then mark the element as visited */
				visited[arr[i] - min] = true;
			}

			/* If all elements occur once, then return true */
			return true;
		}

		return false; // if (max - min  + 1 != n)
	}

	/* UTILITY FUNCTIONS */
	static int getMin(int arr[], int n)
	{
		int min = arr[0];
		for (int i = 1; i < n; i++)
			if (arr[i] < min)
				min = arr[i];
		return min;
	}

	static int getMax(int arr[], int n)
	{
		int max = arr[0];
		for (int i = 1; i < n; i++)
			if (arr[i] > max)
				max = arr[i];
		return max;
	}
	public static void main(String[] args) {
		int arr[]= {5, 4, 2, 3, 1, 6};
//		int arr[]= {5, 4, 4, 2, 3, 1, 6};
		// int arr[]= {5,6,7,8};
	    int n = arr.length;
	    System.out.println(areConsecutive(arr, n));
	}

}
