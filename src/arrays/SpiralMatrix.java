package arrays;

public class SpiralMatrix {

	static void spiralPrint(int a[][])
	{

		int i,  k = 0, l = 0;
		int m = a.length-1, n = a[0].length-1;
		while(k <= m && l <= n){

			for(i = l; i <= n; ++i) {
				System.out.print(a[k][i] + "  ");
			}

			k++;

			for(i = k; i <= m; ++i) {
				System.out.print(a[i][n] + "  ");
			}

			n--;

			if(m >= k) {

				for(i = n; i >= l; --i) {
					System.out.print(a[m][i] + "  ");
				}

				m--;
			}

			for(i = m; i >= k; --i) {
				System.out.print(a[i][l] + "  ");
			}

			l++;
		}


		System.out.println();
	}

	public static void main(String[] args) {
		int a[][] = { {1,  2,  3,  4,  5,  6},
				{7,  8,  9,  10, 11, 12},
				{13, 14, 15, 16, 17, 18}
		};
		spiralPrint(a);
	}
}
