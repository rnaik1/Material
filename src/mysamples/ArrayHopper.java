package mysamples;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ArrayHopper {

	ArrayList<Integer> arr = new ArrayList<Integer>();
	private int[] jump;
	private int[] index;
	boolean errorFlag;

	public ArrayList<Integer> getArr() {
		return arr;
	}
	
	public boolean isErrorFlag() {
		return errorFlag;
	}

	public void readFiletoArray(String path) {
		File file = new File(path);
		BufferedReader freader = null;
		try {
			freader = new BufferedReader(new FileReader(file));
			String textString;
			while ((textString = freader.readLine()) != null) {
				if(Integer.parseInt(textString) < 0)
					throw new NegativeNumberException();
				arr.add(Integer.parseInt(textString));
			}
		} catch (FileNotFoundException e) {
			System.err.println("File Not found. Please check the file path entered");
			errorFlag = true;
		} catch (IOException e) {
		} catch (NegativeNumberException e) {
			System.err.println("Negative Numbers are not allowed in the file");
			arr = new ArrayList<Integer>();
			errorFlag = true;
			return;

		} finally {
			try {
				if (freader != null) {
					freader.close();
				}
			} catch (IOException e) {

			}
		}
	}

	public void hopper() {
		int min;
		int n = arr.size();
		int max = n + 1;
		int ind = 0;

		jump = new int[n];
		index = new int[n];
		jump[n - 1] = 0;
		index[n - 1] = n - 1;

		for (int i = n - 2; i >= 0; i--) {
			// if the ith index element is 0 then we cannot reach n-1 last element
			if (arr.get(i) == 0) {
				jump[i] = max;
				index[i] = max;
			}

			// Suppose we directly reach the end from this index i, then jumps[i] = 1 and index will be the last element n-1
			else if (arr.get(i) >= n - i - 1) {
				jump[i] = 1;
				index[i] = n - 1;
			}

			// Else, to get the min jumps to reach n-1th index, check all the points reachable from this index i
	        // and calc jumps[] value for those points
			else {
				min = n + 1;

				// Iterate all possible next element,
				for (int j = i + 1; j < n && j <= arr.get(i) + i; j++) {
					if (min > jump[j]) {
						min = jump[j];
						ind = j;
					}
				}

				if (min != max) {
					jump[i] = min + 1;
					index[i] = ind;
				} else {
					jump[i] = max;
				}
			}
		}
		if (index[0] > n) {
			System.out.println("failure");
			return;
		}
	}

	// Prints the path
	void printPath(){
		int j = 0;
		do {
			if(index[j] == 0)
				System.out.println("Failure - No Path");
			else{
				System.out.print(j + ", ");
				j = index[j];
			}
		} while (j < index.length - 1 && j != 0);
		if (j != 0)
			System.out.print(index.length - 1 + ", out");
		System.out.println();
	}

	public static void main(String[] args) {
		ArrayHopper arrhopper = new ArrayHopper();
		if(args.length!=0){
			String filePath = args[0];
			arrhopper.readFiletoArray(filePath);
			if(arrhopper.getArr().size() != 0){
				arrhopper.hopper();
				arrhopper.printPath();
			}
			else {
				if(!arrhopper.isErrorFlag())
					System.err.println("File is empty. Array not constructed from the file");
			}
		} else {
			System.err.println("Please enter path via command line argument");
		}
	}
}
