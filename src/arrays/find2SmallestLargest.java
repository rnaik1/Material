package arrays;

public class find2SmallestLargest {

	public static void find2Small(int[] A){
		int n = A.length;
		if(n<2){
			System.out.println("Please provide a larger array");
			return;
		}
		int first = Integer.MAX_VALUE,second = Integer.MAX_VALUE;
		for(int i = 0; i < n ; i++){
			if(A[i] < first)
			{
				second = first;
				first = A[i];
			}
			else if (A[i] < second)
			{
				second = A[i];
			}
		}
		System.out.println("First Small: " + first + "\t" + "Second Small: " + second);

	}

	public static void find2Large(int[] A){
		int n = A.length;
		if(n<2){
			System.out.println("Please provide a larger array");
			return;
		}
		int first = Integer.MIN_VALUE,second = Integer.MIN_VALUE;
		for(int i = 0; i < n ; i++){
			if(A[i] > first)
			{
				second = first;
				first = A[i];
			}
			else if (A[i] > second)
			{
				second = A[i];
			}
		}
		System.out.println("First Large: " + first + "\t" + "Second Large: " + second);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = {16,17,4,3,5,2};
		find2Small(A);
		find2Large(A);
	}
}
