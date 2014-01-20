package arrays;

import java.util.HashMap;

public class OddOccuring {

	static void printTwoOdd(int arr[])
	{
		int xor2 = arr[0]; /* Will hold XOR of two odd occurring elements */
		int set_bit_no;  /* Will have only single set bit of xor2 */
		int x = 0, y = 0;

		/* Get the xor of all elements in arr[]. The xor will basically
	     be xor of two odd occurring elements */
		for(int i = 1; i < arr.length; i++)
			xor2 = xor2 ^ arr[i];

		/* Get one set bit in the xor2. We get rightmost set bit
	     in the following line as it is easy to get */
		set_bit_no = xor2 & ~(xor2-1);

		/* Now divide elements in two sets: 
	    1) The elements having the corresponding bit as 1. 
	    2) The elements having the corresponding bit as 0.  */
		for(int i = 0; i < arr.length; i++)
		{
			/* XOR of first set is finally going to hold one odd 
	       occurring number x */
			if((arr[i] & set_bit_no)!=0)
				x = x ^ arr[i];

			/* XOR of second set is finally going to hold the other 
	       odd occurring number y */
			else
				y = y ^ arr[i]; 
		}

		System.out.println("The two ODD elements are " + x + " & " + y);
	}
	
	static void printTwoOddHash(int a[]){
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for(int i=0;i<a.length;i++){
			if(map.containsKey(a[i])){
				map.put(a[i], map.get(a[i])+1);
			}
			else
				map.put(a[i], 1);
		}
		
		for(int i=0;i<a.length;i++){
			if(map.get(a[i])%2 != 0){
				System.out.println(a[i]);
			}
		}
	}

	public static void main(String[] args) {
		int arr[] = {4, 2, 4, 5, 2, 3, 3, 1};
		printTwoOdd(arr);
		printTwoOddHash(arr);
	}

}
