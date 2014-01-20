package misc;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Misc extends Stack {

	/* return 1 if string contain only digits, else return 0 */
	static boolean valid_digit(char[] ip_str) {
		for (int i = 0; i < ip_str.length; i++) {
			if (!(ip_str[i] >= '0' && ip_str[i] <= '9'))
				return false;
		}
		return true;
	}

	/* return 1 if IP string is valid, else return 0 */
	static boolean is_valid_ip(String ip_str) {
		int num, dots = 0;

		if (ip_str == null)
			return false;

		StringTokenizer st = new StringTokenizer(ip_str, ".");

		while (st.hasMoreElements()) {
			String sTemp = (String) st.nextElement();
			/* after parsing string, it must contain only digits */
			if (!valid_digit(sTemp.toCharArray()))
				return false;

			num = Integer.parseInt(sTemp);

			/* check for valid IP */
			if (num >= 0 && num <= 255) {
				dots++;
			} else
				return false;
		}
		/* valid IP string must contain 3 dots */
		if (dots-1 < 3 || dots-1 > 3)
			return false;
		return true;
	}

	// ********************************************************************************************

	// The main function that receives a set of sets as parameter and
	// returns a set containing intersection of all sets
	static ArrayList<Integer> getIntersection(ArrayList<ArrayList<Integer>> sets) {
		ArrayList<Integer> result = new ArrayList<Integer>(); // To store the
		// resultant set
		int smallSetInd = 0; // Initialize index of smallest set
		int minSize = sets.get(0).size(); // Initialize size of smallest set

		// sort all the sets, and also find the smallest set
		for (int i = 1; i < sets.size(); i++) {
			// sort this set
			// Arrays.sort(sets.get(i).toArray());
			Collections.sort(sets.get(i));

			// update minSize, if needed
			if (minSize > sets.get(i).size()) {
				minSize = sets.get(i).size();
				smallSetInd = i;
			}
		}

		Map<Integer, Integer> elementsMap = new HashMap<Integer, Integer>();

		// Add all the elements of smallest set to a map, if already present,
		// update the frequency
		for (int i = 0; i < sets.get(smallSetInd).size(); i++) {
			if (!elementsMap.containsKey(sets.get(smallSetInd).get(i)))
				elementsMap.put(sets.get(smallSetInd).get(i), 1);
			else
				elementsMap.put(sets.get(smallSetInd).get(i),
						elementsMap.get(sets.get(smallSetInd).get(i)) + 1);
		}

		// iterate through the map elements to see if they are present in
		// remaining sets
		// map<int,int>::iterator it;
		for (Map.Entry<Integer, Integer> entry : elementsMap.entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = "
					+ entry.getValue());
			int elem = entry.getKey();
			int freq = entry.getValue();

			boolean bFound = true;

			// Iterate through all sets
			for (int j = 0; j < sets.size(); j++) {
				// If this set is not the smallest set, then do binary search in
				// it
				if (j != smallSetInd) {
					// If the element is found in this set, then find its
					// frequency
					if (Collections.binarySearch(sets.get(j), elem) >= 0) {
						int lInd = first(sets.get(j).toArray(), 0, sets.get(j)
								.size() - 1, elem);
						int rInd = last(sets.get(j).toArray(), 0, sets.get(j)
								.size() - 1, elem);

						// Update the minimum frequency, if needed
						if (rInd != lInd) {
							if ((rInd - lInd) < freq)
								freq = rInd - lInd;
						}
					}
					// If the element is not present in any set, then no need
					// to proceed for this element.
					else {
						bFound = false;
						break;
					}
				}
			}

			if (bFound) {
				for (int k = 0; k < freq; k++)
					result.add(elem);
			}
		}
		return result;
	}

	/*
	 * if x is present in arr[] then returns the index of FIRST occurrence of x
	 * in arr[0..n-1], otherwise returns -1
	 */
	public static int first(Object arr[], int low, int high, int x) {
		if (high >= low) {
			int mid = (low + high) / 2; /* low + (high - low)/2; */
			if ((mid == 0 || x > (Integer) arr[mid - 1])
					&& (Integer) arr[mid] == x)
				return mid;
			else if (x > (Integer) arr[mid])
				return first(arr, (mid + 1), high, x);
			else
				return first(arr, low, (mid - 1), x);
		}
		return -1;
	}

	/*
	 * if x is present in arr[] then returns the index of LAST occurrence of x
	 * in arr[0..n-1], otherwise returns -1
	 */
	public static int last(Object arr[], int low, int high, int x) {
		if (high >= low) {
			int mid = (low + high) / 2; /* low + (high - low)/2; */
			if ((mid == arr.length - 1 || x < (Integer) arr[mid + 1])
					&& (Integer) arr[mid] == x)
				return mid;
			else if (x < (Integer) arr[mid])
				return last(arr, low, (mid - 1), x);
			else
				return last(arr, (mid + 1), high, x);
		}
		return -1;
	}

	// A utility function to print a set of elements
	static void printset(ArrayList<Integer> set) {
		for (int i = 0; i < set.size(); i++)
			System.out.print(set.get(i) + "\t");
	}

	// ********************************************************************************************

	// The main function that rearranges elements of given array. It puts
	// positive elements at even indexes (0, 2, ..) and negative numbers at
	// odd indexes (1, 3, ..).
	static void rearrange(int arr[], int n) {
		// The following few lines are similar to partition process
		// of QuickSort. The idea is to consider 0 as pivot and
		// divide the array around it.
		int i = -1;
		for (int j = 0; j < n; j++) {
			if (arr[j] < 0) {
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}

		// Now all positive numbers are at end and negative numbers at
		// the beginning of array. Initialize indexes for starting point
		// of positive and negative numbers to be swapped
		int pos = i + 1, neg = 0;

		// Increment the negative index by 2 and positive index by 1, i.e.,
		// swap every alternate negative number with next positive number
		while (pos < n && neg < pos && arr[neg] < 0) {
			int temp = arr[neg];
			arr[neg] = arr[pos];
			arr[pos] = temp;
			pos++;
			neg += 2;
		}
	}

	// A utility function to print an array
	static void printArray(int arr[], int n) {
		for (int i = 0; i < n; i++)
			System.out.print(arr[i] + "\t");
		System.out.println();
	}

	// ********************************************************************************************

	// A utility function to check whether x is numeric
	static boolean isNumericChar(char x) {
		return (x >= '0' && x <= '9') ? true : false;
	}

	// A simple atoi() function. If the given string contains
	// any invalid character, then this function returns 0
	static int myAtoi(String s) {
		if (s == null)
			return 0;

		char[] str = s.toCharArray();

		int res = 0; // Initialize result
		int sign = 1; // Initialize sign as positive
		int i = 0; // Initialize index of first digit

		// If number is negative, then update sign
		if (str[i] == '-') {
			sign = -1;
			i++; // Also update index of first digit
		}

		// Iterate through all digits of input string and update result
		for (; i < str.length; ++i) {
			if (isNumericChar(str[i]) == false)
				return 0; // You may add some lines to write error message
			// to error stream
			res = res * 10 + str[i] - '0';
		}

		// Return result with sign
		return sign * res;
	}

	// Implementation of itoa()
	static String itoa(int num) {
		//int i = 0;
		boolean isNegative = false;
		StringBuilder sb = new StringBuilder();

		/* Handle 0 explicitely, otherwise empty string is printed for 0 */
		if (num == 0) {
			sb.append('0');
		}

		// In standard itoa(), negative numbers are handled only with
		// base 10. Otherwise numbers are considered unsigned.
		if (num < 0) {
			isNegative = true;
			num = -num;
		}

		// Process individual digits
		while (num != 0) {
			int rem = num % 10;
			sb.append(((rem > 9) ? (rem - 10) : rem));
			num = num / 10;
		}

		// If number is negative, append '-'
		if (isNegative)
			sb.append('-');

		// Reverse the string

		return sb.reverse().toString();
	}

	// ********************************************************************************************

	public String InToPost(String infixString) {
		String postfixString = " ";
		Stack<Character> s = new Stack<Character>();

		for (int index = 0; index < infixString.length(); ++index) {
			char chValue = infixString.charAt(index);
			if (chValue == '(') {
				s.push('(');
			} else if (chValue == ')') {
				char oper = s.peek();
				while (oper != '(' && !s.isEmpty()) {
					postfixString += oper + " ";
					s.pop();
					oper = s.peek();
				}
				s.pop();
			} else if (chValue == '+' || chValue == '-') {
				// Stack is empty
				if (s.isEmpty()) {
					s.push(chValue);
					// current Stack is not empty
				} else {
					char oper = s.peek();
					while (!s.isEmpty() && oper != '(' & oper != ')') {
						s.pop();
						postfixString += oper + " ";
					}
					s.push(chValue);
				}
			} else if (chValue == '*' || chValue == '/') {
				if (s.isEmpty()) {
					s.push(chValue);
				} else {
					char oper = s.peek();
					while (!s.isEmpty() && oper != '+' && oper != '-') {
						s.pop();
						postfixString += oper + " ";
						oper = s.peek();
					}
					s.push(chValue);
				}
			} else {
				postfixString += chValue + " ";
			}
		}
		while (!s.isEmpty()) {
			char oper = s.peek();
			if (!(oper == '(')) {
				s.pop();
				postfixString += oper + " ";
			}
		}
		return postfixString;
	}

	// ********************************************************************************************

	static double pow(double x, int n) {
		if (n == 0)
			return 1.0;
		// Compute x^{n/2} and store the result into a temporary
		// variable to avoid unnecessary computing
		double half = pow(x, n / 2);
		if (n % 2 == 0)
			return half * half;
		else if (n > 0)
			return half * half * x;
		else
			return half * half / x;
	}

	// ********************************************************************************************

	// The main function that removes occurences of "b" and "ac" in input string
	static String stringFilter(char[] str) {
		// state is initially ONE (The previous character is not a)
		int state = 1;

		// i and j are index variables, i is used to read next character of
		// input
		// string, j is used for indexes of output string (modified input
		// string)
		int j = 0;

		// Process all characters of input string one by one
		for (int i = 0; i < str.length; i++) {
			/*
			 * If state is ONE, then do NOT copy the current character to output
			 * if one of the following conditions is true ...a) Current
			 * character is 'b' (We need to remove 'b') ...b) Current character
			 * is 'a' (Next character may be 'c')
			 */
			if (state == 1 && str[i] != 'a' && str[i] != 'b') {
				str[j] = str[i];
				j++;
			}

			// If state is TWO and current character is not 'c' (otherwise
			// we ignore both previous and current characters)
			if (state == 2 && str[i] != 'c') {
				// First copy the previous 'a'
				str[j] = 'a';
				j++;

				// Then copy the current character if it is not 'a' and 'b'
				if (str[i] != 'a' && str[i] != 'b') {
					str[j] = str[i];
					j++;
				}
			}

			// Change state according to current character
			state = (str[i] == 'a') ? 2 : 1;
		}

		// If last character was 'a', copy it to output
		if (state == 2) {
			str[j] = 'a';
			j++;
		}

		// Set the string terminator
		// str[j] = '\0';
		return new String(str, 0, j);
	}

	// ********************************************************************************************

	// The method that prints all possible strings of length k. It is
	// mainly a wrapper over recursive function printAllKLengthRec()
	static void printAllKLength(char set[], int k) {
		int n = set.length;
		printAllKLengthRec(set, "", n, k);
	}

	// The main recursive method to print all possible strings of length k
	static void printAllKLengthRec(char set[], String prefix, int n, int k) {

		// Base case: k is 0, print prefix
		if (k == 0) {
			System.out.println(prefix);
			return;
		}

		// One by one add all characters from set and recursively
		// call for k equals to k-1
		for (int i = 0; i < n; ++i) {

			// Next character of input added
			String newPrefix = prefix + set[i];

			// k is decreased, because we have added a new character
			printAllKLengthRec(set, newPrefix, n, k - 1);
		}
	}

	// ********************************************************************************************

	/*
	 * A utility function to check whether a word is present in dictionary or
	 * not. An array of strings is used for dictionary. Using array of strings
	 * for dictionary is definitely not a good idea. We have used for simplicity
	 * of the program
	 */
	static boolean dictionaryContains(String word) {
		String dictionary[] = { "mobile", "samsung", "sam", "sung", "man",
				"mango", "icecream", "and", "go", "i", "like", "ice", "cream" };
		int size = dictionary.length;
		for (int i = 0; i < size; i++)
			if (dictionary[i].equalsIgnoreCase(word))
				return true;
		return false;
	}

	// returns true if string can be segmented into space separated
	// words, otherwise returns false
	static boolean wordBreak(String str) {
		int size = str.length();

		// Base case
		if (size == 0)
			return true;

		// Try all prefixes of lengths from 1 to size
		for (int i = 1; i <= size; i++) {
			// The parameter for dictionaryContains is str.substr(0, i)
			// str.substr(0, i) which is prefix (of input string) of
			// length 'i'. We first check whether current prefix is in
			// dictionary. Then we recursively check for remaining string
			// str.substr(i, size-i) which is suffix of length size-i
			if (dictionaryContains(str.substring(0, i))
					&& wordBreak(str.substring(i, size)))
				return true;
		}

		// If we have tried all prefixes and none of them worked
		return false;
	}

	// ********************************************************************************************

	private static String GetExcelColumnName(int columnNumber) {
		int dividend = columnNumber;
		String columnName = "";
		int modulo;

		while (dividend > 0) {
			modulo = (dividend - 1) % 26;
			columnName = String.valueOf(Character.toChars(65 + modulo))
					+ columnName;
			dividend = (dividend - modulo) / 26;
		}

		return columnName;
	}

	// ********************************************************************************************

	public static void printNos(int n) {
		if (n >= 1) {
			printNos(n - 1);
			System.out.print(n + "\t");
		}

	}

	// ********************************************************************************************

	public static boolean isMatch(String s, String p) {
		if (p.isEmpty()) {
			return s.isEmpty();
		}
		// next char is not '*': must match current character
		if (p.length() == 1 || (p.length() > 1 && p.charAt(1) != '*')) {
			return ((p.charAt(0) == s.charAt(0)) || (p.charAt(0) == '.' && !s
					.isEmpty())) && isMatch(s.substring(1), p.substring(1));
		}
		// next char is '*'
		while ((s.length() > 0 && p.charAt(0) == s.charAt(0))
				|| (p.charAt(0) == '.' && !s.isEmpty())) {
			if (isMatch(s, p.substring(2))) {
				return true;
			}
			s = s.substring(1);
		}
		return isMatch(s, p.substring(2));
	}

	// The main function that checks if two given strings match. The first
	// string may contain wildcard characters
	static boolean match(String first, String second) {
		// If we reach at the end of both strings, we are done
		if (first.length() == 0 && second.length() == 0)
			return true;

		if(first.length() != 0){
			// Make sure that the characters after '*' are present in second string.
			// This function assumes that the first string will not contain two
			// consecutive '*'
			if (first.charAt(0) == '*' && first.substring(1).length() != 0 && second.length() == 0)
				return false;

			// If the first string contains '?', or current characters of both
			// strings match
			if (first.charAt(0) == '?' || (second.length()!=0 && first.charAt(0) == second.charAt(0)))
				return match(first.substring(1), second.substring(1));

			// If there is *, then there are two possibilities
			// a) We consider current character of second string
			// b) We ignore current character of second string.
			if (first.charAt(0) == '*')
				return match(first.substring(1), second) || match(first, second.substring(1));
		}
		return false;
	}

	// ********************************************************************************************
	@SuppressWarnings({ "rawtypes", "unchecked" })
	static public Map<Character, Integer> sortByComparator(
			HashMap<Character, Integer> hM) {

		List list = new LinkedList(hM.entrySet());
		Collections.sort(list, new Comparator() {
			public int compare(Object ele1, Object ele2) {

				if (((Comparable) ((Map.Entry) (ele2)).getValue())
						.compareTo(((Map.Entry) (ele1)).getValue()) > 0) return 1;
				else if (((Comparable) ((Map.Entry) (ele2)).getValue())
						.compareTo(((Map.Entry) (ele1)).getValue()) < 0) return -1;
				else{
					if(((Comparable) ((Map.Entry) (ele2)).getKey()).compareTo(((Map.Entry) (ele1)).getKey()) > 0) return -1;
					if(((Comparable) ((Map.Entry) (ele2)).getKey()).compareTo(((Map.Entry) (ele1)).getKey()) < 0) return 1;
					return 0;
				}
			}
		});
		Map sortedMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	static HashMap<Character, Integer> sortChars(String str){
		HashMap<Character, Integer> hM = new LinkedHashMap<Character, Integer>();
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i)!=' '){
				if (hM.get(str.charAt(i)) != null)
					hM.put(str.charAt(i), hM.get(str.charAt(i)) + 1);
				else
					hM.put(str.charAt(i), 1);
			}
		}
		hM = (HashMap<Character, Integer>) sortByComparator(hM);
		for (Map.Entry<Character, Integer> entry : hM.entrySet()) {
			System.out.println(entry.getKey() + "\t" + entry.getValue());
		}
		return hM;
	}

	/*static void printKFreqWords(String[] arr, int k) {
		HashMap<String, Integer> hM = new HashMap<String, Integer>();
		for (int i = 0; i < arr.length; i++) {
			if (hM.get(arr[i]) != null)
				hM.put(arr[i], hM.get(arr[i]) + 1);
			else
				hM.put(arr[i], 1);
		}

		hM = (HashMap<String, Integer>) sortByComparator(hM);
		int count = 0;
		int prevCount = 0;
		for (String key : hM.keySet()) {
			if (count < k) {
				System.out.println(key);
				if (hM.get(key) != prevCount) {
					count++;
					prevCount = hM.get(key);
				} else {

				}
			}
		}
	}*/

	// ********************************************************************************************

	/* Returns length of longest common substring of X[0..m-1] and Y[0..n-1] */
	static int LCSubStr(char[] X, char[] Y, int m, int n) {
		// Create a table to store lengths of longest common suffixes of
		// substrings. Note that LCSuff[i][j] contains length of longest
		// common suffix of X[0..i-1] and Y[0..j-1]. The first row and
		// first column entries have no logical meaning, they are used only
		// for simplicity of program
		int LCSuff[][] = new int[m + 1][n + 1];
		int result = 0; // To store length of the longest common substring

		/* Following steps build LCSuff[m+1][n+1] in bottom up fashion. */
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0 || j == 0)
					LCSuff[i][j] = 0;

				else if (X[i - 1] == Y[j - 1]) {
					LCSuff[i][j] = LCSuff[i - 1][j - 1] + 1;
					result = Math.max(result, LCSuff[i][j]);
				} else
					LCSuff[i][j] = 0;
			}
		}
		return result;
	}

	public static String longestSubstring(String str1, String str2) {

		StringBuilder sb = new StringBuilder();
		if (str1 == null || str1.isEmpty() || str2 == null || str2.isEmpty())
			return "";

		// ignore case
		str1 = str1.toLowerCase();
		str2 = str2.toLowerCase();

		// java initializes them already with 0
		int[][] num = new int[str1.length()][str2.length()];
		int maxlen = 0;
		int lastSubsBegin = 0;

		for (int i = 0; i < str1.length(); i++) {
			for (int j = 0; j < str2.length(); j++) {
				if (str1.charAt(i) == str2.charAt(j)) {
					if ((i == 0) || (j == 0))
						num[i][j] = 1;
					else
						num[i][j] = 1 + num[i - 1][j - 1];

					if (num[i][j] > maxlen) {
						maxlen = num[i][j];
						// generate substring from str1 => i
						int thisSubsBegin = i - num[i][j] + 1;
						if (lastSubsBegin == thisSubsBegin) {
							// if the current LCS is the same as the last time
							// this block ran
							sb.append(str1.charAt(i));
						} else {
							// this block resets the string builder if a
							// different LCS is found
							lastSubsBegin = thisSubsBegin;
							sb = new StringBuilder();
							sb.append(str1.substring(lastSubsBegin, i + 1));
						}
					}
				}
			}
		}

		return sb.toString();
	}

	// ********************************************************************************************
	// Digit Sum

	static long sumDigitsBelow(long n)
	{
		long result = 0, magnitude = 1, trailing;
		int position = 0;
		long leading;

		/* Find position/magnitude for most-significant digit. */
		while (magnitude < n){ 
			magnitude *= 10;
			++position;
		}

		while (n > 0)
		{
			/* Adjust magnitude/position to next (non-zero) leading digit: */
			while (magnitude > n){
				magnitude /= 10; 
				--position;
			}

			leading = n/magnitude;
			trailing = n%magnitude;
			result += leading*(leading - 1)/2*magnitude;
			result += leading*position*magnitude*9/2;
			result += leading*trailing;

			n = trailing;
		}
		return result;
	}

	// ********************************************************************************************

	// Function to count all possible triangles with arr[] elements
	static int findNumberOfTriangles(int arr[], int n)
	{
		// Sort the array elements in non-decreasing order
		Arrays.sort(arr);

		// Initialize count of triangles
		int count = 0;

		// Fix the first element.  We need to run till n-3 as the other two elements are
		// selected from arr[i+1...n-1]
		for (int i = 0; i < n-2; ++i)
		{
			// Initialize index of the rightmost third element
			int k = i+2;

			// Fix the second element
			for (int j = i+1; j < n; ++j)
			{
				// Find the rightmost element which is smaller than the sum
				// of two fixed elements
				// The important thing to note here is, we use the previous
				// value of k. If value of arr[i] + arr[j-1] was greater than arr[k],
				// then arr[i] + arr[j] must be greater than k, because the
				// array is sorted.
				while (k < n && arr[i] + arr[j] > arr[k])
					++k;

				// Total number of possible triangles that can be formed
				// with the two fixed elements is k - j - 1.  The two fixed
				// elements are arr[i] and arr[j].  All elements between arr[j+1]
				// to arr[k-1] can form a triangle with arr[i] and arr[j].
				// One is subtracted from k because k is incremented one extra
				// in above while loop.
				// k will always be greater than j. If j becomes equal to k, then
				// above loop will increment k, because arr[k] + arr[i] is always
				// greater than arr[k]
				count += k - j - 1;
			}
		}

		return count;
	}

	// ********************************************************************************************

	// The function returns maximum circular contiguous sum in a[]
	static int maxCircularSum (int a[], int n)
	{
		// Case 1: get the maximum sum using standard kadane's algorithm
		int max_kadane = kadane(a, n);

		// Case 2: Now find the maximum sum that includes corner elements.
		int max_wrap  =  0;
		for(int i=0; i<n; i++)
		{
			max_wrap += a[i]; // Calculate array-sum
			a[i] = -a[i];  // invert the array (change sign)
		}

		// max sum with corner elements will be:
		// array-sum - (-max subarray sum of inverted array)
		max_wrap = max_wrap + kadane(a, n);

		// The maximum circular sum will be maximum of two sums
		return (max_wrap > max_kadane)? max_wrap: max_kadane;
	}

	// Standard Kadane's algorithm to find maximum subarray sum
	// See http://www.geeksforgeeks.org/archives/576 for details
	static int kadane (int a[], int n)
	{
		int max_so_far = 0, max_ending_here = 0;
		int i;
		for(i = 0; i < n; i++)
		{
			max_ending_here = max_ending_here + a[i];
			if(max_ending_here < 0)
				max_ending_here = 0;
			if(max_so_far < max_ending_here)
				max_so_far = max_ending_here;
		}
		return max_so_far;
	}

	// ********************************************************************************************

	static int dayofweek(int d, int m, int y)
	{
		int t[] = { 0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4 };
		y -= m < 3?1:0;
		return ( y + y/4 - y/100 + y/400 + t[m-1] + d) % 7;
	}

	// ********************************************************************************************

	static int reversDigits(int num)
	{
		int rev_num = 0;
		while(num > 0)
		{
			rev_num = rev_num*10 + num%10;
			num = num/10;
		}
		return rev_num;
	}

	static // ********************************************************************************************

	// Power set
	void printPowerSet(char []set, int set_size)
	{
		/*set_size of power set of a set with set_size
	      n is (2**n -1)*/
		int pow_set_size = (int) pow(2, set_size);
		int counter, j;

		/*Run from counter 000..0 to 111..1*/
		for(counter = 0; counter < pow_set_size; counter++)
		{
			for(j = 0; j < set_size; j++)
			{
				/* Check if jth bit in the counter is set
	             If set then pront jth element from set */
				if((counter & (1<<j)) != 0)
					System.out.print(set[j]);
			}
			System.out.println();
		}
	}

	// ********************************************************************************************

	/*Returns the square root of n. Note that the function */
	static float squareRoot(float n)
	{
		/*We are using n itself as initial approximation
	   This can definitely be improved */
		float x = n;
		float y = 1;
		double e = 0.000001; /* e decides the accuracy level*/
		while(x - y > e)
		{
			x = (x + y)/2;
			y = n/x;
		}
		return x;
	}

	// ********************************************************************************************

	class TestResult {
		int studentId;
		String testDate;
		int testScore;
	}

	public static Map <Integer, Double> calculateFinalScores (List<TestResult> results) {
		HashMap<Integer, PriorityQueue<Integer>> hM = new HashMap<Integer, PriorityQueue<Integer>>();
		for(int i=0;i<results.size();i++){
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>((int)results.size(),new Comparator<Integer>(){
				public int compare(Integer i1, Integer i2) {
					if (i1 < i2) return 1;
					if (i1 > i2) return -1;
					return 0;
				}
			});
			if (hM.get(results.get(i).studentId) != null){
				pq = hM.get(results.get(i).studentId);
				pq.add(results.get(i).testScore);
				hM.put(results.get(i).studentId, pq);
			}
			else{
				pq.add(results.get(i).testScore);
				hM.put(results.get(i).studentId, pq);
			}
		}

		HashMap<Integer, Double> avgResults = new HashMap<Integer, Double>();

		for (Map.Entry<Integer, PriorityQueue<Integer>> entry : hM.entrySet()) {
			int id = entry.getKey();
			PriorityQueue<Integer> pq1 = entry.getValue();
			int sum = 0;
			for(int i=0;i<5;i++){
				int x = pq1.poll();
				sum+=x;
			}
			avgResults.put(id, (double) (sum/5));
		}

		return avgResults;
	}

	// ********************************************************************************************


	public static void main(String[] args) {
		String ip1 = "128.0.0.1";
		String ip2 = "125.16.100.1";
		String ip3 = "125.512.100.1";
		String ip4 = "125.512.100.abc";
		String ip5 = "125.125.100.1.1.23.4.55";
		if (is_valid_ip(ip1))
			System.out.println(ip1 + "\t" + "Valid");
		else
			System.out.println(ip1 + "\t" + "Not valid");

		if (is_valid_ip(ip2))
			System.out.println(ip2 + "\t" + "Valid");
		else
			System.out.println(ip2 + "\t" + "Not valid");

		if (is_valid_ip(ip3))
			System.out.println(ip3 + "\t" + "Valid");
		else
			System.out.println(ip3 + "\t" + "Not valid");

		if (is_valid_ip(ip4))
			System.out.println(ip4 + "\t" + "Valid");
		else
			System.out.println(ip4 + "\t" + "Not valid");

		if (is_valid_ip(ip5))
			System.out.println(ip5 + "\t" + "Valid");
		else
			System.out.println(ip5 + "\t" + "Not valid");

		// ********************************************************************************************

		ArrayList<ArrayList<Integer>> sets = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> set1 = new ArrayList<Integer>();
		/*
		 * set1.add(1); set1.add(1); set1.add(2); set1.add(2); set1.add(5);
		 */
		set1.add(1);
		set1.add(2);
		set1.add(3);
		set1.add(4);
		set1.add(5);

		sets.add(set1);

		ArrayList<Integer> set2 = new ArrayList<Integer>();
		/*
		 * set2.add(1); set2.add(1); set2.add(4); set2.add(3); set2.add(5);
		 * set2.add(9);
		 */
		set2.add(3);
		set2.add(4);
		set2.add(5);
		set2.add(6);
		set2.add(7);

		sets.add(set2);

		/*
		 * ArrayList<Integer> set3 = new ArrayList<Integer>(); set3.add(1);
		 * set3.add(1); set3.add(2); set3.add(3); set3.add(5); set3.add(6);
		 * 
		 * sets.add(set3);
		 */

		ArrayList<Integer> r = getIntersection(sets);

		printset(r);
		System.out.println();

		// ********************************************************************************************

		int arr[] = { -1, 2, -3, 4, 5, 6, -7, 8, 9 };
		int n = arr.length;
		rearrange(arr, n);
		printArray(arr, n);

		// ********************************************************************************************

		/*String str = "-134";
		int val = myAtoi(str);
		System.out.println(val);*/

		String str1 = "1.25";
		int val1 = myAtoi(str1);
		System.out.println(val1);

		// ********************************************************************************************

		Misc mObj = new Misc();
		System.out.println(mObj.InToPost("(1+2)*3"));
		System.out.println(new PostfixEvaluator().evaluate(mObj
				.InToPost("(1+2)*3")));

		// ********************************************************************************************
		System.out.println(pow(100, 2));

		// ********************************************************************************************
		// char[] strReplace = {'a','b','c','a','c','c','b'};
		char[] strReplace = { 'a', 'b', 'c', 'a', 'b', 'b', 'a', 'c', 'a' };
		System.out.println(stringFilter(strReplace));

		System.out.println("First Test");
		char cset1[] = { 'a', 'b' };
		int k = 3;
		printAllKLength(cset1, k);

		System.out.println("\nSecond Test");
		char cset2[] = { 'a', 'b' };
		k = 1;
		printAllKLength(cset2, 2);

		if (wordBreak("ilikesamsung"))
			System.out.println("Yes");
		else
			System.out.println("No");

		if (wordBreak("iiiiiiii"))
			System.out.println("Yes");
		else
			System.out.println("No");

		if (wordBreak(""))
			System.out.println("Yes");
		else
			System.out.println("No");

		if (wordBreak("ilikelikeimangoiii"))
			System.out.println("Yes");
		else
			System.out.println("No");

		if (wordBreak("samsungandmango"))
			System.out.println("Yes");
		else
			System.out.println("No");

		if (wordBreak("samsungandmangok"))
			System.out.println("Yes");
		else
			System.out.println("No");

		System.out.println("Excel column Name: " + GetExcelColumnName(100));

		printNos(10);
		System.out.println();

		/*System.out.println(isMatch("aa", "aa"));
		System.out.println(isMatch("aaa", "aa"));
		System.out.println(isMatch("aa", "a*"));
		System.out.println(isMatch("aa", ".*"));
		System.out.println(isMatch("ab", ".*"));
		System.out.println(isMatch("aab", "c*a*b"));
		System.out.println(isMatch("geeksforgeeks", "ge.ks*"));*/


		System.out.println("*****************************************************");
		System.out.println("".length());
		System.out.println(match("g*ks", "geeks")); // Yes
		System.out.println(match("ge?ks*", "geeksforgeeks")); // Yes
		System.out.println(match("g*k", "gee")); // No because 'k' is not in
		System.out.println(match("*pqrs", "pqrst")); // No because 't'
		System.out.println(match("abc*bcd", "abcdhghgbcd"));
		System.out.println(match("abc*c?d", "abcd")); // No because
		System.out.println(match("*c*d","abcd")); // Yes 
		System.out.println(match("*?c*d", "abcd")); // Yes
		System.out.println("*****************************************************");


		String countArr[] = { "Fee", "Fi", "Fo", "Fum", "Fee", "Fo", "Fee",
				"Fee", "Fo", "Fi", "Fi", "Fo", "Fum", "Fee" };
		//printKFreqWords(countArr, 3);

		String X = "OldSite:GeeksforGeeks.org";
		String Y = "NewSite:GeeksQuiz.com";

		System.out.println("\nLength of Longest Common Substring is " + LCSubStr(X.toCharArray(), Y.toCharArray(), X.length(), Y.length()));
		System.out.println(longestSubstring(X, Y));
		System.out.println(longestSubstring("abc", "bcd"));

		String s1IMO = "I my bye good";
		String s2IMO = "my bye good boy";

		System.out.println(longestSubstring(s1IMO, s2IMO));

		//StringManipulations sm = new StringManipulations();
		/*System.out.println(StringManipulations.removeChars(s1IMO, longestSubstring(s1IMO, s2IMO)));
		System.out.println(StringManipulations.removeChars(s2IMO, longestSubstring(s1IMO, s2IMO)));*/

		String substring = longestSubstring(s1IMO, s2IMO);
		System.out.println(StringManipulations.removeSubstring(s1IMO, substring));
		System.out.println(StringManipulations.removeSubstring(s2IMO, substring));

		if (ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN)) {
			System.out.println("Big-endian");
		} else {
			System.out.println("Little-endian");
		}

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

		System.out.println("itoa" + itoa(1567));

		int aS = 0; int bS = 10;
		System.out.println(sumDigitsBelow(bS + 1) - sumDigitsBelow(aS));

		System.out.println();
		int triArr[] =   {10, 21, 22, 100, 101, 200, 300};
		System.out.println(findNumberOfTriangles(triArr,triArr.length));

		int circularSubarraySum[] =  {11, 10, -20, 5, -3, -5, 8, -13, 10};
		System.out.println(maxCircularSum(circularSubarraySum, circularSubarraySum.length));
		//System.out.println(kadane(circularSubarraySum, circularSubarraySum.length));

		System.out.println(dayofweek(29, 10, 2013));

		System.out.println(reversDigits(112233));

		char set[] = {'a','b','c'};
		printPowerSet(set, 3);

		int sqn = 100000000;
		System.out.println(squareRoot(sqn));

		String strCount = "the quick brown fox jumps over the lazy dogs";
		sortChars(strCount);
		//printKFreqWords(strCount, 10);

		/*TestResult m = new Misc().new TestResult();
		m.studentId = 1;
		m.testScore = 10;
		TestResult nm = new Misc().new TestResult();
		nm.studentId = 2;
		nm.testScore = 100;
		TestResult m1 = new Misc().new TestResult();
		m1.studentId = 1;
		m1.testScore = 20;
		TestResult nm1 = new Misc().new TestResult();
		nm1.studentId = 2;
		nm1.testScore = 10;
		TestResult m2 = new Misc().new TestResult();
		m2.studentId = 1;
		m2.testScore = 30;
		TestResult nm2 = new Misc().new TestResult();
		nm2.studentId = 2;
		nm2.testScore = 50;
		TestResult m3 = new Misc().new TestResult();
		m3.studentId = 1;
		m3.testScore = 40;
		TestResult nm3 = new Misc().new TestResult();
		nm3.studentId = 2;
		nm3.testScore = 95;
		TestResult m4 = new Misc().new TestResult();
		m4.studentId = 1;
		m4.testScore = 50;
		TestResult nm4 = new Misc().new TestResult();
		nm4.studentId = 2;
		nm4.testScore = 70;
		TestResult m5 = new Misc().new TestResult();
		m5.studentId = 1;
		m5.testScore = 60;
		TestResult nm5 = new Misc().new TestResult();
		nm5.studentId = 2;
		nm5.testScore = 25;
		TestResult m6 = new Misc().new TestResult();
		m6.studentId = 1;
		m6.testScore = 70;
		TestResult nm6 = new Misc().new TestResult();
		nm6.studentId = 2;
		nm6.testScore = 65;
		TestResult m7 = new Misc().new TestResult();
		m7.studentId = 1;
		m7.testScore = 80;
		TestResult nm7 = new Misc().new TestResult();
		nm7.studentId = 2;
		nm7.testScore = 55;
		TestResult m8 = new Misc().new TestResult();
		m8.studentId = 1;
		m8.testScore = 90;
		TestResult nm8 = new Misc().new TestResult();
		nm8.studentId = 2;
		nm8.testScore = 75;*/

		List<TestResult> results = new LinkedList<Misc.TestResult>();
		TestResult tr;
		for(int i=1; i<=3;i++){
			for(int j=1;j<=10;j++){
				tr = new Misc().new TestResult();
				tr.studentId = i;
				tr.testScore = 90+j;
				results.add(tr);
			}
		}
		
		/*results.add(m);results.add(m1);results.add(m2);results.add(m3);results.add(m4);results.add(m5);results.add(m6);results.add(m7);
		results.add(m8);
		results.add(nm);results.add(nm1);results.add(nm2);results.add(nm3);results.add(nm4);results.add(nm5);results.add(nm6);results.add(nm7);
		results.add(nm8);*/
		
		Map<Integer, Double> avgResults = calculateFinalScores(results);
		for (Map.Entry<Integer, Double> entry : avgResults.entrySet()) {
			System.out.println(entry.getKey() + "\t" + entry.getValue());
		}

	}
}
