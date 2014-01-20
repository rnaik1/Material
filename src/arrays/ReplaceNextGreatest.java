package arrays;

public class ReplaceNextGreatest {

	public static void findAndReplaceNextMax(int[] A){
		int n = A.length;
		int max = A[n-1];
		A[n-1] = -1;
		for(int i=n-2;i>=0;i--){
			int temp = A[i];
			A[i] = max;
			if(max<temp) max=temp;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = {16,17,18,3,5,2};
		findAndReplaceNextMax(A);
		for(int i=0;i<A.length;i++)
			System.out.print(A[i] + "\t");
		System.out.println();
	}

}
