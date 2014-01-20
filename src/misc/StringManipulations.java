package misc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class StringManipulations {

	// Returns false if no valid window is found. Else returns 
	// true and updates minWindowBegin and minWindowEnd with the 
	// starting and ending position of the minimum window.
	static int minWindowBegin; static int minWindowEnd;
	static boolean minWindow(char[] S, char[] T) {
		int sLen = S.length;
		int tLen = T.length;
		int needToFind[] = new int[256];

		for (int i = 0; i < tLen; i++)
			needToFind[T[i]]++;

		int hasFound[] = new int[256];
		int minWindowLen = Integer.MAX_VALUE;
		int count = 0;
		for (int begin = 0, end = 0; end < sLen; end++) {
			// skip characters not in T
			if (needToFind[S[end]] == 0) continue;
			hasFound[S[end]]++;
			if (hasFound[S[end]] <= needToFind[S[end]])
				count++;

			// if window constraint is satisfied
			if (count == tLen) {
				// advance begin index as far right as possible,
				// stop when advancing breaks window constraint.
				while (needToFind[S[begin]] == 0 || hasFound[S[begin]] > needToFind[S[begin]]) {
					if (hasFound[S[begin]] > needToFind[S[begin]])
						hasFound[S[begin]]--;
					begin++;
				}

				// update minWindow if a minimum length is met
				int windowLen = end - begin + 1;
				if (windowLen < minWindowLen) {
					minWindowBegin = begin;
					minWindowEnd = end;
					minWindowLen = windowLen;
				} // end if
			} // end if
		} // end for

		return (count == tLen) ? true : false;
	}

	public static boolean isIsomorphic(String s1, String s2){

		if(s1 == null || s2 == null)
			return false;
		if(s1.length()!=s2.length())
			return false;

		HashMap<Character, Integer> hM = new LinkedHashMap<Character, Integer>();
		for(int i=0; i<s1.length();i++){
			if(hM.get(s1.charAt(i)) == null){
				hM.put(s1.charAt(i), i);
			}
		}

		StringBuilder sb1 = new StringBuilder();
		for(Map.Entry<Character, Integer> entry : hM.entrySet()){
			sb1.append(entry.getValue());
		}

		hM = new LinkedHashMap<Character, Integer>();
		for(int i=0; i<s2.length();i++){
			if(hM.get(s2.charAt(i)) == null){
				hM.put(s2.charAt(i), i);
			}
		}
		StringBuilder sb2 = new StringBuilder();
		for(Map.Entry<Character, Integer> entry : hM.entrySet()){
			sb2.append(entry.getValue());
		}

		if(sb1.toString().equals(sb2.toString())){
			System.out.print("Strings are equal: isoMorphic");
			return true;
		}
		return false;
	}

	// To find the first non repeated character in the given string s.
	public static Character firstNonRepeatedCharacter(String s){
		Hashtable<Character, Integer> table = new Hashtable<Character, Integer>();
		//HashMap<Character, Integer> table = new LinkedHashMap<Character, Integer>();
		for(int i=0;i<s.length();i++){
			if(!table.containsKey(s.charAt(i))){
				table.put(s.charAt(i), 1);
			}
			else{
				table.put(s.charAt(i), table.get(s.charAt(i)) + 1);
			}
		}
		for(int i=0;i<s.length();i++){
			if(table.get(s.charAt(i))==1){
				return s.charAt(i);
			}
		}
		return null;
	}

	// To remove the given character from the String
	public static String removeChars(String str, String remove){

		char[] s = str.toCharArray();
		char[] r = remove.toCharArray();

		int[] flag = new int[256];

		for(int i=0;i<r.length;i++){
			flag[r[i]]++;
		}
		int j=0,k=0;
		while(j<s.length){
			if(flag[s[j]] == 0){
				s[k] = s[j];
				k++;
			}
			j++;
		}
		return new String(s,0,k);
	}

	public static String removeSubstring(String str, String remove){
		int j = 0; int k = 0; int prevIndex = 0;
		while(j<=str.length()-1 && k<remove.length()){
			if(str.charAt(j) == remove.charAt(k)){
				if(k == 0) prevIndex = (j==0)?0:j;
				j++;
				k++;
				if(k == remove.length()){
					str = str.substring(0,prevIndex) + str.substring(j);
					k = 0;
					j = 0;
				}
			}
			else {
				j++;
				k = 0;
			}
		}
		return str;
	}


	public static String remDups(String s){
		int chars[] = new int[256];
		char[] str = s.toCharArray();
		for(int i=0;i<str.length;i++){
			chars[str[i]]++;
		}
		int j=0,k=0;
		while(j<str.length){
			if(chars[str[j]]==1){
				str[k] = str[j];
				k++;
			}
			j++;
		}
		return new String(str,0,k);
	}

	// Reversing the words in the sentence i.e. I am Revanth -> Revanth am I
	public static void reverseSentence(String sentence){
		//String[] words = sentence.split("");
		String s = reverseString(sentence);
		char[] revSentence = s.toCharArray();
		int start=0;int end=0;
		while(end<revSentence.length){
			if(revSentence[end]!=' '){
				start = end;
				while(revSentence[end]!=' ')
					end++;
				end--;
				reverseString(revSentence,start,end);
			}
			end++;
		}
		//System.out.println(new String(revSentence,0,revSentence.length));
		System.out.println(String.copyValueOf(revSentence));
	}

	// Reverse the characters in a string
	public static void reverseString(char[] chars, int start, int end){
		while(end>start){
			char temp = chars[start];
			chars[start] = chars[end];
			chars[end] = temp;
			start++;end--;
		}
	}

	// Reverse the string using recursion
	public static void reverseRecur(char[] str, int index){
		if(index<str.length){
			reverseRecur(str,index+1);
			System.out.print(str[index] + "\t");
		}
	}

	// Reverse the characters in a string
	public static String reverseString(String str){
		char[] chars = str.toCharArray();
		int i,j;
		for(i=0,j=chars.length-1;i<j;i++,j--){
			char temp = chars[i];
			chars[i] = chars[j];
			chars[j] = temp;
		}

		return new String(chars,0,chars.length);
	}

	public static ArrayList<String> letterCombinations(String digits) {
		// Telephone words
		@SuppressWarnings("serial")
		final HashMap<Character,String> map = new HashMap<Character,String>(){{
			put('0',"0");
			put('1',"1");
			put('2',"abc");
			put('3',"def");
			put('4',"ghi");
			put('5',"jkl");
			put('6',"mno");
			put('7',"pqrs");
			put('8',"tuv");
			put('9',"wxyz");
		}} ;
		ArrayList<String> res = new ArrayList<String>();
		ArrayList<String> preres = new ArrayList<String>();
		res.add("");

		String letters="";
		for(int i=0;i<digits.length();i++){
			letters = map.get(digits.charAt(i));
			for(String str: res){
				for(int j=0;j<letters.length();j++)
					preres.add(str+letters.charAt(j));
			}
			res = preres;
			preres = new ArrayList<String>();
		}
		return res;
	}

	// Find the occurrence of a word in the another word and return its starting index
	//char[] a1 = {'w','o','r','l','d'};	char[] a2 = {'h','e','l','l','o', ' ','w','o','r','l','d'};
	public static int find(char[]a1, char[]a2,int start){
		int index = 0;
		for(int i=start;i<start+a1.length;i++){
			if(a2[i] == a1[i-start]){
				index = i;
			}
			else
				return -1;
		}
		return index-a1.length+1;
	}
	public static int getIndex(char[]a1, char[]a2){
		//int start = 0;
		int index = 0;
		for(int i=0;i<a2.length;i++)
		{
			if(a2[i] == a1[0]){
				//start = i;
				index = find(a1,a2,i);
				if(index!=-1) break;
			}
		}
		return index;
	}

	// Length of the longest substring without repeated characters
	public static int lengthOfLongestSubstring(char[] s) {
		int n = s.length;
		int i = 0, j = 0;
		int maxLen = 0;
		boolean exist[] = new boolean[256];
		while (j < n) {
			if (exist[s[j]]) {
				maxLen = Math.max(maxLen, j-i);
				while (s[i] != s[j]) {
					exist[s[i]] = false;
					i++;
				}
				i++;
				j++;
			} else {
				exist[s[j]] = true;
				j++;
			}
		}
		maxLen = Math.max(maxLen, n-i);
		return maxLen;
	}

	static int longestUniqueSubsttr(char[] str)
	{
		int n = str.length;
		int cur_len = 1;  // To store the lenght of current substring
		int max_len = 1;  // To store the result
		int prev_index;  // To store the previous index
		int i;
		//int *visited = (int *)malloc(sizeof(int)*NO_OF_CHARS);
		int[] visited = new int[256];

		/* Initialize the visited array as -1, -1 is used to indicate that
	       character has not been visited yet. */
		for (i = 0; i < visited.length;  i++)
			visited[i] = -1;

		/* Mark first character as visited by storing the index of first 
	       character in visited array. */
		visited[str[0]] = 0;

		/* Start from the second character. First character is already processed
	       (cur_len and max_len are initialized as 1, and visited[str[0]] is set */
		for (i = 1; i < n; i++)
		{
			prev_index =  visited[str[i]];

			/* If the currentt character is not present in the already processed
	           substring or it is not part of the current NRCS, then do cur_len++ */
			if (prev_index == -1 || i - cur_len > prev_index)
				cur_len++;

			/* If the current character is present in currently considered NRCS,
	           then update NRCS to start from the next character of previous instance. */
			else
			{
				/* Also, when we are changing the NRCS, we should also check whether 
	              length of the previous NRCS was greater than max_len or not.*/
				if (cur_len > max_len)
					max_len = cur_len;

				cur_len = i - prev_index;
			}

			visited[str[i]] = i; // update the index of current character
		}

		// Compare the length of last NRCS with max_len and update max_len if needed
		if (cur_len > max_len)
			max_len = cur_len;

		return max_len;
	}

	// Array Multiplication - product of all the other array values expect that at the index
	public static int[] array_multiplication(int arrMul[], int n) {
		int left = 1;
		int right = 1;
		int[] OUTPUT = new int[n];
		for (int i = 0; i < n; i++)
			OUTPUT[i] = 1;
		for (int i = 0; i < n; i++) {
			OUTPUT[i] *= left;
			OUTPUT[n - 1 - i] *= right;
			left *= arrMul[i];
			right *= arrMul[n - 1 - i];
		}
		return OUTPUT;
	}

	// Count of words in a String - also handles the case when there are additional spaces in the middle of the words.
	public static int countOfWords(String s){
		char[] str = s.toCharArray();
		int count = 0;
		boolean inWord = false;
		for(int i=0;i<str.length;i++){
			if(!inWord && Character.isLetter(str[i])){
				inWord = true;
				count++;
			}
			else if (inWord && str[i]==' '){
				inWord = false;
			}
		}
		return count;
	}

	// Prime numbers generation which are <=n
	public static void prime(int n){
		for(int num = 1;num<=n;num++){
			int count = 0;
			for(int i=2;i<=num/2;i++){
				if(num%i==0){
					count++;
					break;
				}
			}
			if(count==0 && num!= 1)
				System.out.print(num + "\t");
		}
	}

	// Prime numbers generation which are <=n
	public static void prime_sieve(int n) {
		boolean prime[] = new boolean[n+1];
		prime[0] = false;
		prime[1] = false;
		int i;
		for (i = 2; i <= n; i++)
			prime[i] = true;

		int limit = (int)Math.sqrt((double)n);
		for (i = 2; i <= limit; i++) {
			if (prime[i]) {
				for (int j = i * i; j <= n; j += i)
					prime[j] = false;
			}
		}

		for(int j=0;j<prime.length;j++){
			if(prime[j])
				System.out.print(j + "\t");
		}
		System.out.println();
	}

	// Prime numbers generation which are <=n
	public static void prime_sieve(int a, int b) {
		boolean prime[] = new boolean[b-a+2];
		//prime[0] = false;
		//prime[1] = false;
		int i;
		for (i = 0; i <= b-a+1; i++)
			prime[i] = true;

		int limit = (int)Math.sqrt((double)b);
		for (i = a; i <= a+limit; i++) {
			if (prime[a-i]) {
				for (int j = i * i; j <= b; j += i)
					prime[j] = false;
			}
		}

		for(int j=a;j<prime.length;j++){
			if(prime[a-j] == true)
				System.out.print(a-j + "\t");
		}
		System.out.println();
	}

	// Array rotation by m values
	public static void rotate(char[] a, int m)
	{
		int length = a.length;
		m = m % length;

		for (int i = 0; i < m; i++)
		{
			int j = (i + m) % length;
			char temp = a[i];
			a[i] = a[j];
			a[j] = temp;
			j = (i - 1 + length) % length;
			temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}

		for(int i=0;i<a.length;i++){
			System.out.print(a[i] + "\t");
		}
	}

	// No. of ways in which the number m can be calculated as a double square - 100 = 10^2 + 0^2 || 8^2 + 6^2
	public static int doubleSquare(int m) {
		int total = 0;
		int limit = (int)Math.sqrt((double)m / 2);
		for (int i = 0; i <= limit; i++) {
			int ii = i*i;
			for (int j = i; ; j++) {
				int sum = ii + j*j;
				if (sum == m)
					total++;
				else if (sum > m)
					break;
			}
		}
		return total;
	}

	// To check if a given number x is a palindrome or not. Also handles the case of overflow when reversing the digits
	public static boolean isPalindrome(int x) {
		if (x < 0) return false;
		int div = 1;
		while (x / div >= 10) {
			div *= 10;
		}
		while (x != 0) {
			int l = x / div;
			int r = x % 10;
			if (l != r) return false;
			x = (x % div) / 10;
			div /= 100;
		}
		return true;
	}

	// To find the intersection of 2 sorted arrays.
	public static ArrayList<Integer> findIntersection(int[]A, int[] B) {
		ArrayList<Integer> intersection = new ArrayList<Integer>();
		//intersection.ge
		//intersection.size()
		int n1 = A.length;
		int n2 = B.length;
		int i = 0, j = 0;
		while (i < n1 && j < n2) {
			if (A[i] > B[j]) {
				j++;
			} else if (B[j] > A[i]) {
				i++;
			} else {
				intersection.add(A[i]);
				i++;
				j++;
			}
		}
		return intersection;
	}

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

	// To count the no.of ones in a bit value of an integer
	public static int number_of_ones(int x) {
		int total_ones = 0;
		while (x != 0) {
			x = x & (x-1);
			total_ones++;
		}
		return total_ones;
	}

	static int getSum(int n){
		int sum = digitSum(n);
		while(sum>9){
			sum = digitSum(sum);
		}
		return sum;
	}
	static int digitSum(int n){
		int sum = 0;
		while(n!=0){
			int x = n%10;
			sum+=x;
			n/=10;
		}
		return sum;
	}

	public static String removeDups(String str){
		char[] charArray = str.toCharArray();
		int[] charCountArray = new int[256];
		int start=0;
		for(int i=0;i<=charArray.length-1;i++){
			if(charCountArray[charArray[i]] == 0)
			{
				charCountArray[charArray[i]]++;
				charArray[start] = charArray[i];
				start++;
			}
		}
		return new String(charArray,0,start);
	}

	/* Function to print n equal parts of str*/
	static void divideString(char[] str, int n)
	{
		int str_size = str.length;

		/*Check if string can be divided in n equal parts */
		if(str_size % n != 0)
		{
			System.out.println("Invalid Input: String size is not divisible by n");
			return;
		}

		/* Calculate the size of parts to find the division points*/
		int part_size = str_size/n;
		for(int i = 0; i< str_size; i++)
		{
			if(i%part_size == 0)
				System.out.println(); /* newline separator for different parts */
			System.out.println(str[i]);
		}
	}

	// Naive Pattern Search.
	static void search(char[] pat, char[] txt)
	{
		int M = pat.length;
		int N = txt.length;

		/* A loop to slide pat[] one by one */
		for (int i = 0; i <= N - M; i++)
		{
			int j;

			/* For current index i, check for pattern match */
			for (j = 0; j < M; j++)
			{
				if (txt[i+j] != pat[j])
					break;
			}
			if (j == M)  // if pat[0...M-1] = txt[i, i+1, ...i+M-1]
			{
				System.out.println("Pattern found at index: " + i);
			}
		}	
	}

	// Returns true if C is an interleaving of A and B, otherwise
	// returns false
	static boolean isInterleaved (String A, String B, String C)
	{
		int i=0;
		int a=0; int b=0;
		// Iterate through all characters of C.
		while (i<=C.length()-1)
		{
			// Match first character of C with first character of A,
			// If matches then move A to next 
			if (a<A.length() && (A.charAt(a) == C.charAt(i)))
				a++;

			// Else Match first character of C with first character of B,
			// If matches them move B to next 
			else if (b<A.length() && (B.charAt(b) == C.charAt(i)))
				b++;

			// If doesn't match with either A or B, then return false
			else
				return false;

			// Move C to next for next iteration
			i++;
		}

		// If A or B still have some characters, then length of C is smaller 
		// than sum of lengths of A and B, so return false
		if (a!=A.length() || b!=B.length())
			return false;

		return true;
	}

	// A utility function to find factorial of n
	static int fact(int n)
	{
		return (n <= 1)? 1 :n * fact(n-1);
	}

	// A utility function to count smaller characters on right
	// of arr[low]
	static int findSmallerInRight(char[] str, int low, int high)
	{
		int countRight = 0, i;

		for (i = low+1; i <= high; ++i)
			if (str[i] < str[low])
				++countRight;

		return countRight;
	}

	// A function to find rank of a string in all permutations
	// of characters
	static int findRank (char[] str)
	{
		int len = str.length;
		int mul = fact(len);
		int rank = 1;

		for (int i = 0; i < len; ++i)
		{
			mul /= len - i;

			// count number of chars smaller than str[i]
			// fron str[i+1] to str[len-1]
			int countRight = findSmallerInRight(str, i, len-1);

			rank += countRight * mul ;
		}
		return rank;
	}

	//Given a string of length n, print all permutation of the given string. 
	//Repetition of characters is allowed. Print these permutations in lexicographically sorted order
	/* The main function that recursively prints all repeated permutations of
	  the given string. It uses data[] to store all permutations one by one */
	static void allLexicographicRecur (char[] str, char[] data, int last, int index)
	{
		int len = str.length;

		// One by one fix all characters at the given index and recur for the
		// subsequent indexes
		for (int i=0; i<len; i++ )
		{
			// Fix the ith character at index and if this is not the last index
			// then recursively call for higher indexes
			data[index] = str[i] ;

			// If this is the last index then print the string stored in data[]
			if (index == last)
				System.out.println(data);
			else // Recur for higher indexes
				allLexicographicRecur (str, data, last, index+1);
		}
	}

	static /* This function sorts input string, allocate memory for data (needed for
	  allLexicographicRecur()) and calls allLexicographicRecur() for printing all
	  permutations */
	void allLexicographic(char []str)
	{
		int len = str.length;

		// Create a temp array that will be used by allLexicographicRecur()
		char []data = new char[len];

		// Sort the input string so that we get all output strings in
		// lexicographically sorted order
		Arrays.sort(str);
		//qsort(str, len, sizeof(char), compare);

		// Now print all permutaions
		allLexicographicRecur (str, data, len-1, 0);
	}


	// ********************************************************************************************

	// This function finds the index of the smallest character
	// which is greater than 'first' and is present in str[l..h]
	static int findCeil (char str[], char first, int l, int h)
	{
		// initialize index of ceiling element
		int ceilIndex = l;

		// Now iterate through rest of the elements and find
		// the smallest character greater than 'first'
		for (int i = l+1; i <= h; i++)
			if (str[i] > first && str[i] < str[ceilIndex])
				ceilIndex = i;

		return ceilIndex;
	}

	// Print all permutations of str in sorted order
	static void sortedPermutations ( char str[] )
	{
		// Get size of string
		int size = str.length;

		// Sort the string in increasing order
		//qsort( str, size, sizeof( str[0] ), compare );
		Arrays.sort(str);

		// Print permutations one by one
		boolean isFinished = false;
		while ( ! isFinished )
		{
			// print this permutation
			System.out.println(str);

			// Find the rightmost character which is smaller than its next
			// character. Let us call it 'first char'
			int i;
			for ( i = size - 2; i >= 0; --i )
				if (str[i] < str[i+1])
					break;

			// If there is no such chracter, all are sorted in decreasing order,
			// means we just printed the last permutation and we are done.
			if ( i == -1 )
				isFinished = true;
			else
			{
				// Find the ceil of 'first char' in right of first character.
				// Ceil of a character is the smallest character greater than it
				int ceilIndex = findCeil( str, str[i], i + 1, size - 1 );

				// Swap first and second characters
				//swap( &str[i], &str[ceilIndex] );
				char temp = str[i];
				str[i] = str[ceilIndex];
				str[ceilIndex] = temp;

				// Sort the string on right of 'first char'
				//qsort( str + i + 1, size - i - 1, sizeof(str[0]), compare );
				Arrays.sort(str, i+1, size);
			}
		}
	}

	/* prints list items having all characters of word */
	static void printWords(String[] list, char[] word)
	{
		/*Since calloc is used, map[] is initialized as 0 */
		int[] map = new int[256];
		int count = 0;  

		/*Set the values in map */
		for (int i = 0; i<word.length; i++)
			map[word[i]]++;

		/* Check each item of list if has all characters
         of word*/
		for (int i = 0; i < list.length; i++)
		{
			for(int j = 0; j<list[i].length(); j++)
			{
				if(map[list[i].charAt(j)] != 0)
				{
					count++;

					/* unset the bit so that strings like sss not printed*/        
					map[list[i].charAt(j)] = 0;
				}     
			}
			if(count == word.length)
				System.out.println(list[i]);
			count = 0;
			/*Set the values in map for next item*/
			for (int k = 0; k<word.length; k++)
				map[word[k]]++;         
		}
	}

	public static void main(String[] args) {
		String s = "abaccdeddddeeefffghhiiikkkzmnop";
		char c = firstNonRepeatedCharacter(s);
		System.out.println("The First non repeated character is: " + c);
		System.out.println("Yo Yo: " + removeChars("google", "g"));
		System.out.println("Yo Yo: " + removeChars("ba    b     ba    a    ba        ba", "ba"));
		System.out.println("Reverse of Revanth is: " + reverseString("Revanth is a good boy"));
		reverseSentence("         I am        Revanth");
		System.out.println(letterCombinations("123"));

		char[] a1 = {'w','o','r','l','d'};
		//char[] a1 = {'z'};
		char[] a2 = {'h','e','l','l','o', ' ','w','o','r','l','d'};
		System.out.println("Your Index is: " + getIndex(a1, a2));

		System.out.println("Length of Longest substring without repeated characters is: " + lengthOfLongestSubstring("abaccd".toCharArray()));
		System.out.println("Length of Longest substring without repeated characters is: " + longestUniqueSubsttr("abaccd".toCharArray()));

		int arr[] = {2,1,2,3};
		int[] OUTPUT = new int[4];
		OUTPUT = array_multiplication(arr, 4);
		System.out.println(OUTPUT);
		for (int i = 0; i < 4; i++) {
			System.out.print(OUTPUT[i] + "\t");
		}
		System.out.println();

		String countWord = "Revanth     \t \nRocks  ,   The     6767 7878 World";
		System.out.println("String is: " + countWord);
		System.out.println("No. of Words in countWord String are: " + countOfWords(countWord));
		prime(30);
		System.out.println();
		prime_sieve(30);
		//prime_sieve(10,15);
		//SieveOfEratosthenes(30);
		//int[] rotateArray = {1,2,3,4,5,6,7,8,9,10};
		char[] rotateArray = {'a', 'b', 'c', 'd', 'e'};
		rotate(rotateArray,3);
		System.out.println();
		System.out.println(doubleSquare(101));
		//2147483647
		System.out.println(isPalindrome(89098));
		//System.out.println(reverse(1234567899));
		int a3[] = {-5,2,4,9, 11,73};
		int a4[] = {-5,-1,4,7,9,9, 73};
		System.out.println("*******Intersection*****");
		System.out.println(findIntersection(a3, a4));
		System.out.println(reverseXor(1));
		System.out.println(number_of_ones(5));
		/*freq("XZXGFHRHZXLIVGDLWFLWVHPGLKHRGGRMTFMWVINBWVHPIFMMRMTERHGZZMWZKZXSVZKZXSVILXPHZMWSVMXVRXZMNZRMGZRMNBLDMDVYKZTVHZMWMLGWVKVMWLMBLFPMLDDSLGLHVIEVFKIVJFVHGHGSVXOZHHHBMLKHRHKZTVRHZMRNKLIGZMGIVHLFIXVZMWRGXLMGZRMHWVGZROHLUVZXSXOZHHZMWXOZHHMLGVHDRGSZFWRLZOGSLFTSWRHXFHHRMTSLNVDLIPKILQVXGKILYOVNHDRGSKVVIHRHZXXVKGZYOVYVXZIVUFOGSZGBLFDLIPLFGBLFIHLOFGRLMHZMBRNKILKIRVGBRMSLNVDLIPHZMWVCZNHDROOYVGIVZGVWEVIBHVIRLFHOB");
		System.out.println(replaceCipher("XZXGFHRHZXLIVGDLWFLWVHPGLKHRGGRMTFMWVINBWVHPIFMMRMTERHGZZMWZKZXSVZKZXSVILXPHZMWSVMXVRXZMNZRMGZRMNBLDMDVYKZTVHZMWMLGWVKVMWLMBLFPMLDDSLGLHVIEVFKIVJFVHGHGSVXOZHHHBMLKHRHKZTVRHZMRNKLIGZMGIVHLFIXVZMWRGXLMGZRMHWVGZROHLUVZXSXOZHHZMWXOZHHMLGVHDRGSZFWRLZOGSLFTSWRHXFHHRMTSLNVDLIPKILQVXGKILYOVNHDRGSKVVIHRHZXXVKGZYOVYVXZIVUFOGSZGBLFDLIPLFGBLFIHLOFGRLMHZMBRNKILKIRVGBRMSLNVDLIPHZMWVCZNHDROOYVGIVZGVWEVIBHVIRLFHOB"));*/

		System.out.println(getSum(16578));
		System.out.println();

		System.out.println("Removing Duplicates from geeks: " + removeDups("google"));
		System.out.println();

		reverseRecur("geeksforgeeks".toCharArray(),0);
		System.out.println();

		divideString("geeksfoourgeeks".toCharArray(), 3);
		System.out.println();

		search("GEEK".toCharArray(), "GEEKSFORGEEKS".toCharArray());
		System.out.println();

		/*char[] txt = "ABABDABACDABABCABAB".toCharArray();
		char[] pat = "ABABCABAB".toCharArray();
		KMPSearch(pat, txt);
		System.out.println();*/

		/*String str1 = "AB";
		String str2 = "CD";
		printIls(str1, str2, str1.length(), str2.length());
		System.out.println();*/

		String A = "AB";
		String B = "CD";
		String C = "ACBG";
		System.out.println(isInterleaved(A, B, C));

		/*char str[] = "a1b2c3d4e5f6g7".toCharArray();
		System.out.println(str);
		moveNumberToSecondHalf(str);
		System.out.println(str);
		System.out.println();*/

		//char string[] = "string".toCharArray();
		char string[] = "cab".toCharArray();
		System.out.println(findRank(string));

		char str11[] = {'A','B','C'};
		System.out.println("All permutations with repetition are: ");
		allLexicographic(str11);
		System.out.println();

		char str12[] = {'C','B','A'};
		sortedPermutations( str12 );

		System.out.println(remDups("AbcdAbcddef"));

		char str[] = "sun".toCharArray();
		String list[] = {"geeksforgeeks", "unsorted", "sunday", "nujust", "sss" };
		printWords(list, str);

		minWindow("acba".toCharArray(), "ab".toCharArray());
		System.out.println(minWindowBegin);
		System.out.println(minWindowEnd);

		System.out.println(isIsomorphic("turtle", "tletur"));
		System.out.println(isIsomorphic("bar", "foo"));
		System.out.println(isIsomorphic("foo", "app"));
		System.out.println(isIsomorphic("ab", "ca"));
		System.out.println(isIsomorphic("app1111p", "21q3333q"));

		System.out.println();

		System.out.println(removeSubstring("abcdefabc", "abc"));
	}
}