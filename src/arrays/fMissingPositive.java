package arrays;

public class fMissingPositive {
	
	public static int firstMissingPositive(int[] A) {

		if (A.length == 0) return 1;

		int i=0;
		while (i<A.length)
		{
			if (A[i]>=0 && A[i]<A.length) {
				if (A[i]!=i && A[i]!=A[A[i]]) {
					int temp = A[A[i]];
					A[A[i]] = A[i];
					A[i] = temp;
					continue;
				}
			}
			i++;
		}

		for (i=1; i<A.length; i++) {
			if (i!=A[i]) return i;
		}

		if (A[0]==A.length) return A.length+1;

		return A.length;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int A[] = {2, 3, -7, 6, 8, 1, -10, 15};
		int A[] = {1, 2, 4, 6, 3, 7, 8};
		System.out.println(firstMissingPositive(A));
	}

}
