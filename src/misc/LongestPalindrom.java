package misc;

public class LongestPalindrom {
	public static void main(String args[]) {
		//String str = "FourscoreandsevenyearsagoourfaathersbroughtforthonthiscontainentanewnationconceivedinzLibertyanddedicatedtothepropositionthatallmenarecreatedequalNowweareengagedinagreahtcivilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth";
		String str = "abcba";

		// Longest Palindrome in O(n^2) - Start
		char[] a = str.toCharArray();
		int low = Integer.MAX_VALUE;
		int upper = Integer.MIN_VALUE;
		for (int i = 0; i < str.length(); i++) {
			int start = i;
			int end = i;
			while (start >= 0 && end < str.length()) {
				if (a[start] == a[end]) {
					if (end - start > upper - low) {
						upper = end;
						low = start;
					}
					end++;
					start--;
				} else {
					break;
				}
			}
		}
		/*for (int i = 0; i < str.length(); i++) {
			int start = i;
			int end = i + 1;
			while (start >= 0 && end < str.length()) {
				if (a[start] == a[end]) {
					if (end - start > upper - low) {
						upper = end;
						low = start;
					}
					end++;
					start--;
				} else {
					break;
				}
			}



		}*/
		for (int i = low; i <= upper; i++) {
			System.out.print(a[i]);
		}
		System.out.println();

		// Longest Palindrome in O(n)
		String result = LongestPalindrom.longestPalindrome(str);
		System.out.println(result);
	}

	// Transform S into T.
	// For example, S = "abba", T = "^#a#b#b#a#$".
	// ^ and $ signs are sentinels appended to each end to avoid bounds checking
	public static String preProcess(String s) {
		int n = s.length();
		if (n == 0) return "^$";
		String ret = "^";
		for (int i = 0; i < n; i++)
			ret += "#" + s.substring(i,i+1);

		ret += "#$";
		return ret;
	}

	public static String longestPalindrome(String s) {
		String T = preProcess(s);
		int n = T.length();
		int[] P = new int[n];
		int C = 0, R = 0;
		for (int i = 1; i < n-1; i++) {
			int i_mirror = 2*C-i; // equals to i' = C - (i-C)

			P[i] = (R > i) ? Math.min(R-i, P[i_mirror]) : 0;

			// Attempt to expand palindrome centered at i
			while (T.charAt(i + 1 + P[i]) == T.charAt(i - 1 - P[i]))
				P[i]++;

			// If palindrome centered at i expand past R,
			// adjust center based on expanded palindrome.
			if (i + P[i] > R) {
				C = i;
				R = i + P[i];
			}
		}

		// Find the maximum element in P.
		int maxLen = 0;
		int centerIndex = 0;
		for (int i = 1; i < n-1; i++) {
			if (P[i] > maxLen) {
				maxLen = P[i];
				centerIndex = i;
			}
		}
		return s.substring((centerIndex - 1 - maxLen)/2, (centerIndex - 1 + maxLen)/2);
	}
}