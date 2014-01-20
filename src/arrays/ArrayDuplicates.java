package arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ArrayDuplicates {
	
	public static void printDuplicates(int A[]){
		for(int i = 0; i < A.length; i++)
		  {
		    if(A[Math.abs(A[i])] > 0)
		      A[Math.abs(A[i])] = -A[Math.abs(A[i])];
		    else
		      System.out.println(Math.abs(A[i]));
		  }
	}
	
	public static Set<Integer> printDuplicatesUsingHash(int A[]){
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		Set<Integer> set = new HashSet<Integer>();
		for(int i=0;i<A.length;i++){
			if(map.containsKey(A[i]) && map.get(A[i])>0)
				//System.out.println(A[i]);
				set.add(A[i]);
			else
				map.put(A[i], 1);
		}
		return set;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Numbers are given from 1 to n
		//int A[] = {1,1,1,1,2,3,3,3,3,4,4,5,7};
		int A[] = {1,1,2,2,3,3};
		//printDuplicates(A);
		Set<Integer> set = printDuplicatesUsingHash(A);
		System.out.println(set);
	}
}
