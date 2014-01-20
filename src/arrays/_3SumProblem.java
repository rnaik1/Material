package arrays;
import java.util.Arrays;

/*
Given an array and a value, find if there is a triplet in array whose sum is equal to the given value. 
If there is such a triplet present in array, then print the triplet and return true. Else return false. 
For example, if the given array is {12, 3, 4, 1, 6, 9} and given sum is 24, then there is a triplet (12, 3 and 9) 
present in array whose sum is 24.*/
		
public class _3SumProblem {

	public static void findTriplets(int[] A,int sum){
		Arrays.sort(A);
		for(int i=0;i<=A.length-2;i++){
			int a = A[i];
			int k = i+1;
			int l = A.length-1;
			while (k<l){
				int b = A[k];
				int c = A[l];
				if (b+c == sum-a){
					System.out.println(a + "\t" + b + "\t" + c);
					k++;
					l--;
					
				}
				else if (b+c > sum-a)
					l--;
				else
					k++;  
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {1,2,3,4,5};
		findTriplets(A, 9);
	}

}
