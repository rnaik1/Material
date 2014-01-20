package arrays;


public class MatrixRotation {

	public static void rotateMatrix(int[][] a){
		int r = a.length;
		int c = a[0].length;
		for(int i=0;i<r;i++){
			for(int j=0;j<c;j++){
				if(i<j){
					int temp = a[i][j];
					a[i][j] = a[j][i];
					a[j][i] = temp;
				}
			}
		}
		System.out.println("Transpose Matrix: ");
		printMatrix(a);
		// Do a Mirror Image on the Transposed matrix:
		
		for(int i=0;i<r;i++){
			int j = 0; int k = c-1;
			while(j<k){
				int temp = a[i][j];
				a[i][j] = a[i][k];
				a[i][k] = temp;
				j++;
				k--;
			}
		}
		System.out.println("Rotated Matrix is: ");
		printMatrix(a);

	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] < 10 && matrix[i][j] > -10) {
					System.out.print(" ");
				}
				if (matrix[i][j] < 100 && matrix[i][j] > -100) {
					System.out.print(" ");
				}					
				if (matrix[i][j] >= 0) {
					System.out.print(" ");
				}
				System.out.print(" " + matrix[i][j]);				
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
		printMatrix(matrix);
		rotateMatrix(matrix);
		
	}
}
