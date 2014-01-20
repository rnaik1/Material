package zillow;

public class StringToLong {
	
	static boolean isNumericChar(char x) {
		return (x >= '0' && x <= '9') ? true : false;
	}

	// If the given string contains non-numerical characters then this
	// method returns 0
	static long stringToLong(String s) {
		if (s == null)
			return 0;

		char[] str = s.toCharArray();

		long res = 0;
		int sign = 1;
		int i = 0;

		// If number is negative, then update sign
		if (str[i] == '-') {
			sign = -1;
			i++;
		}

		// Iterate through all digits of input string and update result
		for (; i < str.length; ++i) {
			if (isNumericChar(str[i]) == false)
				return 0;

			res = res * 10 + str[i] - '0';
		}

		// Return result with sign
		return sign * res;
	}

	public static void main(String[] args) {
		long t1 = stringToLong("123"); 
		if (t1 == 123) 
			System.out.println("Success");
		else 
			System.out.println("Failure");
		
		long t1neg = stringToLong("-123"); 
		if (t1neg == -123) 
			System.out.println("Success");
		else 
			System.out.println("Failure");
		
		long t2 = stringToLong("abc"); 
		 if (t2 == 0) 
			 System.out.println("Success");
		 else 
			 System.out.println("Failure");
		 
		 long t3 = stringToLong("-576"); 
		 if (t3 == 576) 
			 System.out.println("Success");
		 else 
			 System.out.println("Failure");

	}

}
