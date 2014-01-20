package arrays;

/*Majority Element: A majority element in an array A[] of size n is an element that appears more than n/2 times 
 * (and hence there is at most one such element).

Write a function which takes an array and emits the majority element (if it exists), otherwise prints NONE as follows:

       I/P : 3 3 4 2 4 4 2 4 4
       O/P : 4 

       I/P : 3 3 4 2 4 4 2 4
       O/P : NONE*/
       
public class ElementNby2Times {

	public static void printMajority(int a[]){
		int cand = findCandidate(a);
		int count = 0;
		for(int i=0;i<a.length;i++){
			if(a[i] == cand)
				count++;
			if(count>a.length/2){
				System.out.println("Majority Element is: " + cand);
				break;
			}
		}
	}
	
	public static int findCandidate(int a[]){
		int m_index = 0;
		int count = 1;
		for(int i=1;i<a.length;i++)
		{
			if(a[m_index] == a[i])
				count++;
			else
				count--;
			if(count == 0){
				m_index = i;
				count = 1;
			}
		}
		return a[m_index];
	}
	
	public static void main(String[] args) {
		int a[] = {2, 2, 3, 5, 2, 6, 2};
		printMajority(a);
	}

}
