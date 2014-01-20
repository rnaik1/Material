package mysamples;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Solution2 {

	// Array Multiplication - product of all the other array values expect that at the index
	public static long[] array_multiplication(long arrMul[], int n) {
		int left = 1;
		int right = 1;
		long[] OUTPUT = new long[n];
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

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if(N>2){
			long a[] = new long[N];
			for(int i=0;i<N;i++)
				a[i] = Long.parseLong(br.readLine());
			long result[] = array_multiplication(a, N);
			for(int i=0;i<N;i++)
				System.out.println(result[i]);
		}
	}
}
