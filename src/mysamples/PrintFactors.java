package mysamples;

import java.util.*;

class PrintFactors {
	PrintFactors(){}

	public static void printFactors(int num){
		printFactorsList(num,"",num);
	}
	
	public static void printFactorsList(int number, String carry_over_string, int carry_over_num) {
		/*
		  This function contains carry over string as an argument to facilitate the recursive call for subsequent factors 
		  until it reaches prime values.
		  For example, 
		  let's say input number = 32
		  and when i = 8
		  it prints 8*(32/8) ==> 8 * 4
		  but the subsequent reduction of 4 is needed and this is done by recursively passing in 4 as number. 
		  But we also want to maintain the chain "8 * ". So this makes the carry over string as an input argument 
		  for the helper function printFactorsList
		*/
		
		int temp = carry_over_num;
		for (int i = number - 1; i >= 2; i--) {

			if (number % i == 0) { //number divisible by i
				if (temp > i) {
					temp = i;
				}
				
				/* 
				 The check for number/i <= carry_over_num and number/i <=i 
				 are necessary so as not to print 6*16 when printing for input num 96
				 and also to avoid duplicate entries. For example 4*2*2*2 and 2*4*2*2
				*/
				
				if ( (i <= carry_over_num) && (number /i <= carry_over_num) && (number /i <= i)) {
					System.out.println(carry_over_string + i + "*" + (number /i));
					temp = number / i; //temp is adjusted so that it can be passed over the recursive call as carry over num
				}

				if (i <= carry_over_num) {
					// recursive call to make the reduction inside (number/i)
					printFactorsList(number / i, carry_over_string + i + "*", temp);
				}
				
			}// end if
		}// end for
	}// end function

	public static void main(String[] args) {
	   int number = 96;
	   if(number<0){
	     System.out.println("\nERROR: Input number is negative\n");
	    }
	   else if(number==0){
	     System.out.println("\nERROR: Input number is zero\n");   
	    }
	   else{
		System.out.println(number + "*" + 1);
		double start = System.currentTimeMillis();
		printFactors(number);
		double end = System.currentTimeMillis();
		System.out.println("Total Time = " + (end - start) + " ms");
	  }
	}//end main
}// end class