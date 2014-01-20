package BitManipulation;

public class BitsProblems {

	static int nextPowerOf2(int n)
	{
		int count = 0;

		/* First n in the below condition is for the case where n is 0*/
		if (n==0 && ((n&(n-1))==0))
			return n;

		while( n != 0)
		{
			n  >>= 1;
			count += 1;
		}

		return 1<<count;
	}

	// *******************************************************************************

	/* Function to check if n is a multiple of 3*/
	static boolean isMultipleOf3(int n)
	{
		int odd_count = 0;
		int even_count = 0;

		/* Make no positive if +n is multiple of 3
	       then is -n. We are doing this to avoid
	       stack overflow in recursion*/
		if(n < 0)   n = -n;
		if(n == 0) return true;
		if(n == 1) return false;

		while(n!=0)
		{
			/* If odd bit is set then
	           increment odd counter */
			if((n & 1) !=0)
				odd_count++;
			n = n>>1;

		/* If even bit is set then
	           increment even counter */
		if((n & 1) != 0)
			even_count++;
		n = n>>1;
		}

		return isMultipleOf3(Math.abs(odd_count - even_count));
	}

	// *******************************************************************************

	/* Function to get parity of number n. It returns 1
	   if n has odd parity, and returns 0 if n has even
	   parity */
	static boolean getParity(int n)
	{
		boolean parity = false;
		while (n!=0)
		{
			parity = !parity;
			// Unset the rightmost set bit
			n = n & (n - 1);
		}        
		return parity;
	}	

	// *******************************************************************************

	static int multiplyBySeven(int n)
	{  
		/* Note the inner bracket here. This is needed 
	       because precedence of '-' operator is higher 
	       than '<<' */
		return ((n<<3) - n);
	}

	// *******************************************************************************

	static int addOvf(int a, int b)
	{
		int result = 0;
		if( a > Integer.MAX_VALUE - b)
			return -1;
		else
		{
			result = a + b;
			return 0;
		}
	}

	// *******************************************************************************

	/*Function to left rotate n by d bits*/
	static int leftRotate(int n, int d)
	{
		/* In n<<d, last d bits are 0. To put first 3 bits of n at 
	     last, do bitwise or of n<<d with n >>(INT_BITS - d) */
		return (n << d)|(n >> (8 - d));
	}

	/*Function to right rotate n by d bits*/
	static int rightRotate(int n, int d)
	{
		/* In n>>d, first d bits are 0. To put last 3 bits of at 
	     first, do bitwise or of n>>d with n <<(INT_BITS - d) */
		return (n >> d)|(n << (8 - d));
	}

	// *******************************************************************************

	static int addOne(int x)
	{
		return (-(~x));
	}

	// *******************************************************************************

	/*Function to find minimum of x and y*/
	static int min(int x, int y)
	{
		return  y + ((x - y) & ((x - y) >> 31));
	}

	// *******************************************************************************

	static void bin(int n)
	{
		/* step 1 */
		if (n > 1)
			bin(n/2);

		/* step 2 */
		System.out.print(n % 2);
	}

	// *******************************************************************************

	// Helper method: given two unequal sized bit strings, converts them to
	// same length by aadding leading 0s in the smaller string. Returns the
	// the new length
	static int makeEqualLength(String str1, String str2)
	{
		int len1 = str1.length();
		int len2 = str2.length();
		if (len1 < len2)
		{
			for (int i = 0 ; i < len2 - len1 ; i++)
				str1 = '0' + str1;
			return len2;
		}
		else if (len1 > len2)
		{
			for (int i = 0 ; i < len1 - len2 ; i++)
				str2 = '0' + str2;
		}
		return len1; // If len1 >= len2
	}

	// The main function that adds two bit sequences and returns the addition
	static String addBitStrings( String first, String second )
	{
		String result="";  // To store the sum bits

		// make the lengths same before adding
		int length = makeEqualLength(first, second);

		int carry = 0;  // Initialize carry

		// Add all bits one by one
		for (int i = length-1 ; i >= 0 ; i--)
		{
			int firstBit = first.charAt(i) - '0';
			int secondBit = second.charAt(i) - '0';

			// boolean expression for sum of 3 bits
			int sum = (firstBit ^ secondBit ^ carry)+'0';

			result = (char)sum + result;

			// boolean expression for 3-bit addition
			carry = (firstBit & secondBit) | (secondBit & carry) | (firstBit & carry);
		}

		// if overflow, then add a leading 1
		if (carry!=0)
			result = '1' + result;

		return result;
	}

	// *******************************************************************************

	// A utility function to check whether n is power of 2 or not. See http://goo.gl/17Arj
	static boolean isPowerOfTwo(int n)
	{  
		return n!=0 && ((n & (n-1)) == 0 );
	}

	// Returns position of the only set bit in 'n'
	static int findPosition(int n)
	{
		if (!isPowerOfTwo(n))
			return -1;

		int i = 1, pos = 1;

		// Iterate through bits of n till we find a set bit
		// i&n will be non-zero only when 'i' and 'n' have a set bit
		// at same position
		while ((i & n)==0)
		{
			// Unset current bit and set the next bit in 'i'
			i = i << 1;

			// increment position
			++pos;
		}

		return pos;
	}

	// *******************************************************************************
	static int swapBits(int x)
	{
		// Get all even bits of x
		int even_bits = x & 0xAAAAAAAA; 

		// Get all odd bits of x
		int odd_bits  = x & 0x55555555; 

		even_bits >>= 1;  // Right shift even bits
		odd_bits <<= 1;   // Left shift odd bits

		return (even_bits | odd_bits); // Combine even and odd bits
	}

	// *******************************************************************************

	static int getFirstSetBitPos(int n)
	{
		return log(n&-n)+1;
	}

	static int log(int v){
		int r = 0;
		while ((v >>= 1)!=0) // unroll for more speed...
		{
			r++;
		}
		return r;
	}

	// *******************************************************************************

	// Reversing the bits of an integer using XOR strategy
	public static int reverseXor(int x) {
		int n = 8;
		for (int i = 0; i < n/2; i++) {
			x = swapBits(x, i, n-i-1);
		}
		return x;
	}
	public static int swapBits(int x, int i, int j) {
		int lo = ((x >> i) & 1);
		int hi = ((x >> j) & 1);
		if ((lo^hi) != 0) {
			x ^= ((1 << i) | (1 << j));
		}
		return x;
	}

	static int reverseBits(int num)
	{
		int  NO_OF_BITS = 8;
		int reverse_num = 0;
		int i;
		for (i = 0; i < NO_OF_BITS; i++)
		{
			if((num & (1 << i))!=0)
				reverse_num |= 1 << (NO_OF_BITS - 1 - i);
		}
		return reverse_num;
	}

	// *******************************************************************************

	/* This function will return absoulte value of n*/
	static int getAbs(int n)
	{
		int mask = n >> 31;
			return ((n + mask) ^ mask);
	}

	// *******************************************************************************

	static int snoob(int x){
		int smallest = x&-x;
		int ripple = x + smallest;
		int ones = x ^ ripple;
		ones = (ones>>2)/smallest;
		return ripple|ones;
	}

	// *******************************************************************************

	static int swapBits(int x, int p1, int p2, int n)
	{
		/* Move all bits of first set to rightmost side */
		int set1 =  (x >> p1) & ((1 << n) - 1);

		/* Moce all bits of second set to rightmost side */
		int set2 =  (x >> p2) & ((1 << n) - 1);

		/* XOR the two sets */
		int xor = (set1 ^ set2);

		/* Put the xor bits back to their original positions */
		xor = (xor << p1) | (xor << p2);

		/* XOR the 'xor' with the original number so that the 
	       two sets are swapped */
		int result = x ^ xor;

		return result;
	}

	// *******************************************************************************

	static int getSingle(int arr[], int n)
	{
		// Initialize result
		int result = 0;
		int x, sum;

		// Iterate through every bit
		for (int i = 0; i < 32; i++)
		{
			// Find sum of set bits at ith position in all
			// array elements
			sum = 0;
			x = (1 << i);
			for (int j=0; j< n; j++ )
			{
				if ((arr[j] & x) != 0)
					sum++;
			}

			// The bits with sum not multiple of 3, are the
			// bits of element with single occurrence.
			if (sum % 3 != 0)
				result |= x;
		}

		return result;
	}

	// *******************************************************************************

	/* implementation of strcmp that ingnores cases */
	static int ic_strcmp(String s1, String s2)
	{
		int i;
		for (i = 0; i<s1.length() && i<s2.length(); ++i)
		{
			/* If characters are same or inverting the 6th bit makes them same */
			if (s1.charAt(i) == s2.charAt(i) || (s1.charAt(i)^ 32) == s2.charAt(i))
				continue;
			else
				break;
		}

		/* Compare the last (or first mismatching in case of not same) characters */
		if (s1.charAt(i) == s2.charAt(i))
			return 0;
		if ((s1.charAt(i)|32) < (s2.charAt(i)|32)) //Set the 6th bit in both, then compare
			return -1;
		return 1;
	}

	// *******************************************************************************


	public static void main(String[] args) {
		int n = 17;
		System.out.println(nextPowerOf2(n));
		System.out.println(isMultipleOf3(30) + "\t" + isMultipleOf3(0) + "\t" + isMultipleOf3(10) + "\t" + isMultipleOf3(-90));
		System.out.println(getParity(n)? "odd": "even");
		System.out.println("Multiply by 7" + multiplyBySeven(10));
		System.out.println(addOvf(10, Integer.MAX_VALUE));
		System.out.println(leftRotate(2,2));
		System.out.println(rightRotate(2, 2));
		System.out.println(addOne(10));
		System.out.println(min(10, min(15, 25)));
		bin(5000);System.out.println();
		System.out.println(addBitStrings("10", "10"));
		int nPosEle = 16;
		System.out.println(findPosition(nPosEle));
		int x = 23;
		System.out.println(swapBits(x));

		System.out.println(getFirstSetBitPos(12));

		int a = 0;
		// 0x0....01
		int b = 1;
		// 0x0..0,0.01,0..0,0..0
		int combine = (b << 16) | a;
		if (combine == 65536) {
			System.out.println("LittleEndian");
		} else {
			System.out.println("BigEndian");
		}

		System.out.println(reverseBits(1));

		System.out.println(getAbs(1100));

		System.out.println(snoob(156));

		System.out.println(swapBits(28, 0, 3, 2));

		int arr[] = {12, 1, 12, 3, 12, 1, 1, 2, 3, 2, 2, 3, 7};
		System.out.println(getSingle(arr, arr.length));

		/*System.out.println(ic_strcmp("Geeks", "apple"));
		//System.out.println(ic_strcmp("", "ABCD"));
		//System.out.println(ic_strcmp("ABCD".toCharArray(), "z".toCharArray()));
		//System.out.println(ic_strcmp("ABCD".toCharArray(), "abcdEghe".toCharArray()));
		System.out.println(ic_strcmp("GeeksForGeeks", "gEEksFORGeEKs"));
		System.out.println(ic_strcmp("GeeksForGeeks", "geeksForGeeks"));*/


	}

}
