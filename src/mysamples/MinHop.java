package mysamples;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * @author Yao Huang
 * This problem is to find the minimum hops from the first element to the last in an array.
 */
public class MinHop {
	
	// Store the minimum jumping number for current index to the end.
	// minLength[0] will be the result.
	private int[] minLength;
	
	// Store each index we choose to jump
	private int[] index;
	
	// The length of array
	private int n;
	
	// The input array
	private ArrayList<Integer> inputArray;
	
	public MinHop() {
		inputArray = new ArrayList<Integer>();
	}

	/**
	 * Set an array contains all the inputs from external file.
	 */
	public void initializeArray() {
		
		// file location, which may be modified later based on where the fill puts.
		File file = new File("src/test.txt");
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			String textString = null;
			
			while((textString = reader.readLine()) != null) {
				inputArray.add(Integer.parseInt(textString));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
		
			}
		}
		
		// Set length of minlenth array, index array, and inputArray.
		minLength = new int[inputArray.size()];
		index = new int[inputArray.size()];
		n = inputArray.size();
	}

	/**
	 * @param array The input
	 * @param numberOfNodes The length of array
	 * @return The shortest number to jump from the beginning to the end.
	 */
	public void shortest() {
		
		int min;
		
		// The number for initialize.
		int max = n + 1;
		
		// Store the index of array which will be the next hop
		int ind = 0;
		
		// The index of n-1 is the last element of array, which is the end.
		// We do not need to jump anymore.
		minLength[n-1] = 0;
		
		// Set the last element be the last index of array, which is n-1
		index[n-1] = n-1;
		
		for (int i = n-2; i >= 0; i--) {
			// If the current value is 0, we will never reach the end
			// since the number of next jump is 0
			if (inputArray.get(i) == 0) {
				minLength[i] = max;
				index[i] = max;
			}
			
			// If current value of the element is greater than the sum from 
			// the current index to the end, we only need one hop that can 
			// reach the end. Thus we set minLength of the index equal to 1.
			// The current value in the index[i] will be the next hop, 
			// which is the end of array.
			else if (inputArray.get(i) >= n - i - 1) {
				minLength[i] = 1;
				index[i] = n-1;
			}
			
			else {
				// Initialize min value be just larger the length of array.
				min = n+1; 
				
				// Iterate all possible next element, 
				for (int j = i+1; j < n && j <= inputArray.get(i) + i; j++) {
					if (min > minLength[j]) {
						min = minLength[j];
						ind = j;
					}
				}
				
				// handle overflow
				if (min != max) {
					minLength[i] = min + 1;
					index[i] = ind;
				}
				else {
					minLength[i] = max;
				}
			}
		}
		
		// If the value of first index is larger than the length of array,
		// There is no solution, print out failure.
		if (index[0] > n) {
			System.out.println("failure");
		}	
	}
	
	/**
	 * Print the hop index during jumping. 
	 */
	public void printIndexArray() {
		int j = 0;
		do {
			System.out.print(j + ", ");
			j = index[j];
		}while(j < index.length - 1 && j!=0);
		if(j!=0)
			System.out.print(index.length-1);
		System.out.println();
	}
	
	/**
	 * Print the minimum length to jump from the first index to the end.
	 */
	public void printLengthArray() {
		
		// Include the first index
		System.out.println(minLength[0] + 1);
	}
	
	// You can either use following main function to run my code 
	// or using the Junit test file to run.
	// Either way works for my program.
	
	public static void main(String[] args) {
		MinHop testHop = new MinHop();
		testHop.initializeArray();
		testHop.shortest();
		testHop.printIndexArray();	
		//testHop.printLengthArray();
	}
	
}
