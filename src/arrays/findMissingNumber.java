package arrays;


public class findMissingNumber {

	static void printTwoElements(int arr[], int size)
	{
		int i;
		System.out.println("The repeating element is");

		for(i = 0; i < size; i++)
		{
			if(arr[Math.abs(arr[i])-1] > 0)
				arr[Math.abs(arr[i])-1] = -arr[Math.abs(arr[i])-1];
			else
				System.out.println(Math.abs(arr[i]));
		}

		System.out.println("\nand the missing element is ");
		for(i=0; i<size; i++)
		{
			if(arr[i]>0)
				System.out.println(i+1);
		}
	}


	public static void main(String[] args) {
		//int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16, 17, 18 ,19, 20};
		int[] arr = {1,2,4,4,3,6,7,8};
		
		printTwoElements(arr, arr.length);
	}
}
