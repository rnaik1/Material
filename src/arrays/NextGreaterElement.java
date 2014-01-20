package arrays;

import java.util.Stack;

public class NextGreaterElement {

	static void printNGE(int arr[], int n)
	{
		int i = 0;
		Stack<Integer> s = new Stack<Integer>();
		//s.top = -1;
		int element, next;

		/* push the first element to stack */
		s.push(arr[0]);

		// iterate for rest of the elements
		for (i=1; i<n; i++)
		{
			next = arr[i];

			if (!s.empty())
			{
				// if stack is not empty, then pop an element from stack
				element = s.pop();

				/* If the popped element is smaller than next, then
	                a) print the pair
	                b) keep popping while elements are smaller and
	                stack is not empty */
				while (element < next)
				{
					System.out.println(element + "----->" + next);
					if(s.empty())
						break;
					element = s.pop();
				}

				/* If element is greater than next, then push
	               the element back */
				if (element > next)
					s.push(element);
			}

			/* push next to stack so that we can find
	           next greater for it */
			s.push(next);
		}

		/* After iterating over the loop, the remaining
	       elements in stack do not have the next greater
	       element, so print -1 for them */
		while(!s.empty())
		{
			element = s.pop();
			next = -1;
			System.out.println(element + "------>" + next);
		}
	}
	
	public static void nextGreater(int a[]){
		for(int i = 0; i< a.length; i++){
			for (int j = i+1; j < a.length; j++) {
				if(a[i] < a[j]){
					System.out.println(a[i] + "---->" + a[j]);
					break;
				}
				else
					System.out.println(a[i] + "---->-1");
			}
		}
		System.out.println(a[a.length-1] + "---->-1");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[]= {11, 13, 21, 3};
	    int n = arr.length;
	    printNGE(arr, n);
	    System.out.println();
	    nextGreater(arr);
	}

}
