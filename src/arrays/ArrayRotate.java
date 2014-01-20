package arrays;

public class ArrayRotate {

	/*Function to left rotate arr[] of size n by d*/
	static void leftRotate(int arr[], int d, int n)
	{
		int i;
		for (i = 0; i < d; i++)
			leftRotatebyOne(arr, n);
	}

	static void leftRotatebyOne(int arr[], int n)
	{
		int i, temp;
		temp = arr[0];
		for (i = 0; i < n-1; i++)
			arr[i] = arr[i+1];
		arr[i] = temp;
	}

	/* utility function to print an array */
	static void printArray(int arr[], int size)
	{
		int i;
		for(i = 0; i < size; i++)
			System.out.print(arr[i] + "\t");
	}

	/* Function to left rotate arr[] of size n by d */
	static void leftRotateRev(int arr[], int d, int n)
	{
		rvereseArray(arr, 0, d-1);
		rvereseArray(arr, d, n-1);
		rvereseArray(arr, 0, n-1);
	}

	/*Function to reverse arr[] from index start to end*/
	static void rvereseArray(int arr[], int start, int end)
	{
		int i;
		int temp;
		while(start < end)
		{
			temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			start++;
			end--;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {1, 2, 3, 4, 5, 6, 7};
		leftRotate(arr, 2, 7);
		printArray(arr, 7);

		System.out.println();

		int arr1[] = {1, 2, 3, 4, 5, 6, 7};
		leftRotateRev(arr1, 2, 7);
		printArray(arr1, 7);
	}

}
