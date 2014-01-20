package mysamples;
public class Fibonacci {
	
	// Exponential time - T(n) = T(n-1) + T(n-2)
	public static int fibExponential(int n)
	{
		if ( n <= 1 )
			return n;
		return fibExponential(n-1) + fibExponential(n-2);
	}

	// Dynamic Prog - O(n)
	public static int fibDynamic(int n){
		/* Declare an array to store fibonacci numbers. */
		int []f = new int[n+1];
		f[0] = 0;
		f[1] = 1;

		for (int i = 2; i <= n; i++){
			f[i] = f[i-1] + f[i-2];
		}

		return f[n];
	}

	// O(logn) Time complexity
	public static int fibLogN(int n) {
		int a=1, b=0, p=0, q=1, prev_a, prev_p = 0;
		while(n>0) {
			if (n%2 == 0) {
				prev_p = p;
				p = p*p + q*q;
				q = 2*prev_p*q + q*q;
				n /= 2;
			} else {
				prev_a = a;
				a = b*q + a*q + a*p;
				b = b*p + prev_a*q;
				n -= 1;
			}
		}
		return b;
	}
	
	static /* function that returns nth Fibonacci number */
	int fib(int n)
	{
	  int[][] F = {{1,1},{1,0}};
	  if (n == 0)
	    return 0;
	  power(F, n-1);
	  return F[0][0];
	}
	 
	/* Optimized version of power() in method 4 */
	static void power(int F[][], int n)
	{
	  if( n == 0 || n == 1)
	      return;
	  int M[][] = {{1,1},{1,0}};
	 
	  power(F, n/2);
	  multiply(F, F);
	 
	  if (n%2 != 0)
	     multiply(F, M);
	}
	 
	static void multiply(int F[][], int M[][])
	{
	  int x =  F[0][0]*M[0][0] + F[0][1]*M[1][0];
	  int y =  F[0][0]*M[0][1] + F[0][1]*M[1][1];
	  int z =  F[1][0]*M[0][0] + F[1][1]*M[1][0];
	  int w =  F[1][0]*M[0][1] + F[1][1]*M[1][1];
	 
	  F[0][0] = x;
	  F[0][1] = y;
	  F[1][0] = z;
	  F[1][1] = w;
	}

	public static boolean isFibonacci(int  w)
	{
		double X1 = 5 * Math.pow((double)w, (double)2) + 4;
		double X2 = 5 * Math.pow((double)w, (double)2) - 4;

		long X1_sqrt = (long)Math.sqrt(X1);
		long X2_sqrt = (long)Math.sqrt(X2);   

		return (X1_sqrt*X1_sqrt == X1) || (X2_sqrt*X2_sqrt == X2) ;
	}

	// Dynamic Prog - O(n)
	public static int Tribonnacci(int n){
		/* Declare an array to store Tribonacci numbers. */
		int []f = new int[n+1];
		f[0] = 0;
		f[1] = 1;
		f[2] = 2;

		for (int i = 3; i <= n; i++){
			f[i] = f[i-1] + f[i-2] + f[i-3];
		}

		return f[n];
	}

	static void factorial(int n)
	{
		int digit[] = new int[160];
		for(int i=0;i<digit.length;i++)
			digit[i]=0;
		
		int carry, d, last;
		digit[0]=1; last=0;
		
		for(int m=1;m<=n;m++){
			carry=0;
			
			for(int i=0;i<=last;i++){
				d=digit[i]*m+carry;
				digit[i]=d%10;
				carry=d/10;
			}
			while(carry>0){
				last=last + 1;
				digit[last]=carry%10;
				carry=carry/10;
			}
		}

		for(int i=last;i>=0;i--)
			System.out.print(digit[i]);
		System.out.println();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Exponential Fibonacci: " + fibExponential(6));
		System.out.println("Dynamic Fibonacci: " + fibDynamic(5));
		System.out.println("Log n Fibonacci: " + fibLogN(5));
		System.out.println("Another Log n Fibonacci: " + fib(6));
		System.out.println("Is fibonacci: " + isFibonacci(5));
		System.out.println(Tribonnacci(6));
		factorial(100);
	}

}
