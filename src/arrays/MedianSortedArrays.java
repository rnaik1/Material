package arrays;

/*Question: There are 2 sorted arrays A and B of size n each. 
 * Write an algorithm to find the median of the array obtained after merging the above 2 arrays
(i.e. array of length 2n). The complexity should be O(log(n))*/

public class MedianSortedArrays {

	public static int getMedian(int a1[], int a2[], int i, int j, int n){
		int m1; /* For median of ar1 */
	    int m2; /* For median of ar2 */
	 
	    /* return -1  for invalid input */
	    if (n <= 0)
	        return -1;
	 
	    if (n == 1)
	        return (a1[i] + a2[j])/2;
	 
	    if (n == 2)
	        return (Math.max(a1[i], a2[j]) + Math.min(a1[i+1], a2[j+1])) / 2;
	 
	    m1 = median(a1, i, n); /* get the median of the first array */
	    m2 = median(a2, j, n); /* get the median of the second array */
	    
	    /* If medians are equal then return either m1 or m2 */
	    if (m1 == m2)
	        return m1;
	 
	     /* if m1 < m2 then median must exist in ar1[m1....] and ar2[....m2] */
	    if (m1 < m2)
	    {
	        if (n % 2 == 0)
	            return getMedian(a1, a2, n/2 - 1, j, n - n/2 +1);
	        else
	            return getMedian(a1, a2, n/2, j, n - n/2);
	    }
	 
	    /* if m1 > m2 then median must exist in ar1[....m1] and ar2[m2...] */
	    else
	    {
	        if (n % 2 == 0)
	            return getMedian(a2, a1, n/2 - 1, i, n - n/2 + 1);
	        else
	            return getMedian(a2, a1, n/2, i, n - n/2);
	    }
	}
	
	public static int median(int a[], int i, int n){
		if (n%2 == 0)
	        return (a[i+n/2] + a[i+n/2-1])/2;
	    else
	        return a[i+n/2];
	}
	public static void main(String[] args) {
		int ar1[] = {1, 12, 15, 26, 38};
	    int ar2[] = {2, 13, 17, 30, 45};
	    int n1 = ar1.length;
	    int n2 = ar2.length;
	    if (n1 == n2)
	      System.out.println(("Median is: " + getMedian(ar1,ar2,0,0,n1)));
	    else
	     System.out.println("Doesn't work for arrays of unequal size");

	}

}
