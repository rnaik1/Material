package Spotify;

import java.util.Scanner;

public class ReverseBinary {
	
	static int log2(int value) {
	    return Integer.SIZE-Integer.numberOfLeadingZeros(value);
	}

	static int reverseBits(int num)
	{
		int  NO_OF_BITS = log2(num);
		int reverse_num = 0;
		int i;
		for (i = 0; i < NO_OF_BITS; i++)
		{
			if((num & (1 << i))!=0)
				reverse_num |= 1 << (NO_OF_BITS - 1 - i);
		}
		return reverse_num;
	}

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		int num = s.nextInt();
		System.out.println(reverseBits(num));

	}

}
