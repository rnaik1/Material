package linkedList;

import java.util.NoSuchElementException;

import org.w3c.dom.ls.LSInput;

public class LinkedList {
	ListNode head;
	public ListNode getHead() {
		return head;
	}

	public void setHead(ListNode head) {
		this.head = head;
	}

	int size;
	public LinkedList() {
		head = null;
		size = 0;
	}

	public boolean isEmpty()
	{
		return (head == null);
	}

	public int getSize()
	{
		return size;
	}

	public void addFirst(int item)
	{
		head = new ListNode(item, head);
		++size;
	}

	public void addEnd(int item)
	{
		if (head == null)
			head = new ListNode(item, null);
		else{
			ListNode newNode = new ListNode(item, null);
			ListNode current = head;
			while(current.next!=null)
			{
				current = current.next;
			}
			current.next = newNode;
		}
		++size;
	}

	public void addAtIndex(int item, int index)
	{
		ListNode newNode = new ListNode(item, null);
		ListNode current = head;
		for(int i=1;i<index-1;i++)
		{
			current = current.next;
		}
		newNode.next = current.next;
		current.next = newNode;
		++size;
	}

	static ListNode removeAll(int val, ListNode list) {

		ListNode current = list;
		ListNode temp = list;
		if(list == null)
			return null;
		while(current!=null){
			if(current.data == val){
				current = current.next;
				temp = current;
			}
			else{
				current = current.next;
			}
		}

		return temp;

	}

	public void removeFirst()
	{
		if(head != null)
		{
			head = head.next;
			--size;
		}
		else
			throw new NoSuchElementException();
	}

	public void removeEnd()
	{
		ListNode current = head;
		while(current.next.next!=null)
		{
			current = current.next;
		}
		current.next = null;
	}

	public void removeAtIndex(int index)
	{
		ListNode current = head;
		for(int i=1;i<index-1;i++)
		{
			current = current.next;
		}
		current.next = current.next.next;
	}

	public void reverse()
	{
		ListNode current = head;
		head = null;
		while(current!=null)
		{
			ListNode save = current;
			current = current.next;
			save.next = head;
			head = save;
		}
	}

	ListNode reverseRecur(ListNode start)
	{
		ListNode res;

		if(start==null || start.next == null)
			return start;

		res = reverseRecur(start.next);
		start.next.next = start;
		start.next = null;

		return res;
	}

	public void display()
	{
		ListNode current = head;
		while(current!=null)
		{
			System.out.print(current.data + "\t");
			current = current.next;
		}
		System.out.println();
	}

	// Delete Duplicates from a LinkedList
	public void delDups()
	{
		if(head == null) 
			return;
		ListNode current = head;
		while(current!=null)
		{
			ListNode runner = current;
			while(runner.next!=null)
			{
				if(runner.next.data == current.data)
				{
					runner.next = runner.next.next;
				}
				else
				{
					runner = runner.next;
				}
			}
			current = current.next;
		}
	}

	// Kth to the last element of a linkedList
	public ListNode kthToLast(int k)
	{
		if(k<=0) 
			return null;
		ListNode p1 = head;
		ListNode p2 = head;

		for(int i=0;i<k-1;i++)
		{
			if(p2==null) return null;
			p2 = p2.next;
		}
		if(p2==null) return null;

		while(p2.next!=null)
		{
			p1=p1.next;
			p2=p2.next;
		}

		return p1;
	}

	public ListNode SortedInsert(int x){
		ListNode newNode = new ListNode(x, null);
		if(this.head == null || head.data >= x){
			newNode.next = head;
			this.head = newNode;
		}
		else
		{
			ListNode current = head;
			while(current.next!=null && (Integer)current.next.data<x)
				current=current.next;
			newNode.next = current.next;
			current.next = newNode;
		}
		return head;
	}

	@SuppressWarnings("unused")
	ListNode result = null;
	public void SortedInsertResult(int x){
		ListNode newNode = new ListNode(x, null);

		if(result == null || result.data >= x){
			newNode.next = result;
			result = newNode;
		}
		else
		{
			ListNode current = result;
			while(current.next!=null && (Integer)current.next.data<x)
				current=current.next;
			newNode.next = current.next;
			current.next = newNode;
		}
	}

	public ListNode insertSort(){
		ListNode current = head;
		while(current!=null){
			SortedInsertResult(current.data);
			current = current.next;
		}
		return result;
	}

	static ListNode ShuffleMergeRecur(ListNode a, ListNode b) {
		ListNode result;
		ListNode recur;
		if (a==null) return(b); // see if either list is empty
		else if (b==null) return(a);
		else {
			// it turns out to be convenient to do the recursive call first --
			// otherwise a.next and b.next need temporary storage.
			recur = ShuffleMergeRecur(a.next, b.next);
			result = a; // one node from a
			a.next = b; // one from b
			b.next = recur; // then the rest
			return(result);
		}
	}

	static ListNode SortedMerge(ListNode a, ListNode b) {
		ListNode sresult = null;
		// Base cases
		if (a==null) return(b);
		else if (b==null) return(a);
		// Pick either a or b, and recur
		if (a.data <= b.data) {
			sresult = a;
			sresult.next = SortedMerge(a.next, b);
		}
		else {
			sresult = b;
			sresult.next = SortedMerge(a, b.next);
		}
		return(sresult);
	}

	@SuppressWarnings("unused")
	static ListNode sortedIntersect(ListNode a, ListNode b)
	{
		ListNode result = null;
		/* base case */
		if(a == null || b == null)
		{
			return null;
		}
		/* If both lists are non-empty */
		/* advance the smaller list and call recursively */
		if(a.data < b.data)
		{
			return sortedIntersect(a.next, b);
		}
		else if(a.data > b.data)
		{
			return sortedIntersect(a, b.next);
		}  
		else if(a.data == b.data)
		{
			/* If same data is found then allocate memory */
			ListNode temp = new ListNode(a.data, null);
			/* If the first node is being added to resultant list */
			if(result == null)
			{
				result = temp;
			}
			/* Else change the next of result and move result to next */
			else
			{     
				result.next = temp;
				result = temp;
			}
			/* advance both lists and call recursively */
			result.next = sortedIntersect(a.next, b.next);
		}
		return result;
	}

	// This function rotates a linked list counter-clockwise and updates the head.
	// The function assumes that k is smaller than size of linked list. It doesn't
	// modify the list if k is greater than or equal to size
	void rotate (int k)
	{
		if (k == 0)
			return;

		// Let us understand the below code for example k = 4 and
		// list = 10.20.30.40.50.60.
		ListNode current = head;

		// current will either point to kth or NULL after this loop.
		//  current will point to node 40 in the above example
		int count = 1;
		while (count < k && current != null)
		{
			current = current.next;
			count++;
		}

		// If current is NULL, k is greater than or equal to count
		// of nodes in linked list. Don't change the list in this case
		if (current == null)
			return;

		// current points to kth node. Store it in a variable.
		// kthNode points to node 40 in the above example
		ListNode kthNode = current;

		// current will point to last node after this loop
		// current will point to node 60 in the above example
		while (current.next != null)
			current = current.next;

		// Change next of last node to previous head
		// Next of 60 is now changed to node 10
		current.next = head;

		// Change head to (k+1)th node
		// head is now changed to node 50
		head = kthNode.next;

		// change next of kth node to NULL
		// next of 40 is now NULL
		kthNode.next = null;
	}

	/* A function to check if there are three elements in a, b and c whose sum 
	   is equal to givenNumber.  The function assumes that the list b is sorted
	   in ascending order and c is sorted in descending order. */
	static boolean isSumSorted(ListNode headA, ListNode headB, ListNode headC, int givenNumber)
	{
		ListNode a = headA;

		// Traverse through all nodes of a
		while (a != null)
		{
			ListNode b = headB;
			ListNode c = headC;

			// For every node of list a, pick two nodes from lists b and c
			while (b != null && c != null)
			{
				// If this a triplet with given sum, print it and return true
				int sum = a.data + b.data + c.data;
				if (sum == givenNumber)
				{
					System.out.println("Triplet Found: " + a.data + "\t" + b.data + "\t" + c.data);
					return true;
				}

				// If sum of this triplet is smaller, look for greater values in b
				else if (sum < givenNumber)
					b = b.next;
				else // If sum is greater, look for smaller values in c
					c = c.next;
			}
			a = a.next;  // Move ahead in list a
		}

		System.out.println("No such triplet");
		return false;
	}

	void segregateEvenOdd()
	{
		ListNode end = head;

		ListNode prev = null;
		ListNode curr = head;

		// Get pointer to the last ListNode /
		while(end.next != null)
			end = end.next; 

		ListNode new_end = end;

		/* Consider all odd nodes before the first even node
	     and move then after end */
		while(curr.data %2 != 0 && curr != end)
		{
			new_end.next = curr;
			curr = curr.next;
			new_end.next.next = null;
			new_end = new_end.next;
		}   

		// 10.8.17.17.15
		/* Do following steps only if there is any even ListNode */
		if (curr.data%2 == 0)
		{
			/* Change the head pointer to point to first even ListNode */
			head = curr;     

			/* now current points to the first even ListNode */
			while(curr != end)
			{
				if ( (curr.data)%2 == 0 )
				{
					prev = curr;
					curr = curr.next;
				}
				else
				{
					/* break the link between prev and current */
					prev.next = curr.next;

					/* Make next of curr as NULL  */
					curr.next = null;

					/* Move curr to end */
					new_end.next = curr;

					/* make curr as new end of list */
					new_end = curr;

					/* Update current pointer to next of the moved ListNode */
					curr = prev.next;
				}
			}
		}

		/* We must have prev set before executing lines following this 
	     statement */
		else  
			prev = curr;

		/* If the end of the original list is odd then move this node to
	    end to maintain same order of odd numbers in modified list */
		if((end.data)%2 != 0)
		{
			prev.next = end.next;
			end.next = null;
			new_end.next = end;
		}
		return;
	}

	/* Reverses alternate k nodes and
	   returns the pointer to the new head ListNode */
	ListNode kAltReverse(int k)
	{
		ListNode current = head;
		ListNode next;
		ListNode prev = null;
		int count = 0;

		/* 1) reverse first k nodes of the linked list */
		while (current != null && count < k)
		{
			next  = current.next;
			current.next = prev;
			prev = current;
			current = next;
			count++;
		}

		/* 2) Now head points to the kth node.  So change next 
	       of head to (k+1)th node*/
		if(head != null)
			head.next = current;   

		/* 3) We do not want to reverse next k nodes. So move the current 
	        pointer to skip next k nodes */
		count = 0;
		while(count < k-1 && current != null )
		{
			current = current.next;
			count++;
		}

		/* 4) Recursively call for the list starting from current.next.
	       And make rest of the list as next of first ListNode */
		if(current !=  null)
			current.next = kAltReverse(k);

		/* 5) prev is new head of the input list */
		return prev;
	}

	LinkedList a; 
	LinkedList b;
	void AlternatingSplit() 
	{
		// split the nodes of source to these 'a' and 'b' lists 
		ListNode current = head;
		a = new LinkedList();
		b = new LinkedList();
		while (current != null)
		{
			a.addEnd(current.data); // Move a node to list 'a' 
			current = current.next;
			if (current != null) 
			{
				b.addEnd(current.data);  // Move a node to list 'b' 
				current = current.next;
			}
		}
		a.display();System.out.println();
		b.display();System.out.println();

	}

	/* function to insert a new_node in a list in sorted way.
	   Note that this function expects a pointer to head node
	   as this can modify the head of the input linked list */
	void sortedInsert(int x)
	{
		ListNode current = head;
		ListNode new_node = new ListNode(x, null);

		// Case 1 of the above algo
		if (current == null)
		{
			new_node.next = new_node;
			head = new_node;
		}

		// Case 2 of the above algo
		else if (current.data >= new_node.data)
		{
			/* If value is smaller than head's value then
	      we need to change next of last ListNode */
			while(current.next != head)
				current = current.next;
			current.next = new_node;
			new_node.next = head;
			head = new_node;
		}

		// Case 3 of the above algo
		else
		{
			/* Locate the node before the point of insertion */
			while (current.next!= head && current.next.data < new_node.data)
				current = current.next;

			new_node.next = current.next;
			current.next = new_node;
		}
	}

	/* Utility function for calculating length of linked list */
	int countNodes(ListNode s)
	{
		int count = 0;
		while (s != null)
		{
			count++;
			s = s.next;
		}
		return count;
	}
	/* Function for swapping kth nodes from both ends of linked list */
	void swapKth(int k)
	{
		// Count nodes in linked list
		int n = countNodes(head);

		// Check if k is valid
		if (n < k)  return;

		// If x (kth node from start) and y(kth node from end) are same
		if (2*k - 1 == n) return;

		// Find the kth node from beginning of linked list. We also find
		// previous of kth node because we need to update next pointer of
		// the previous.
		ListNode x = head;
		ListNode x_prev = null;
		for (int i = 1; i < k; i++)
		{
			x_prev = x;
			x = x.next;
		}

		// Similarly, find the kth node from end and its previous. kth node
		// from end is (n-k+1)th node from beginning
		ListNode y = head;
		ListNode y_prev = null;
		for (int i = 1; i < n-k+1; i++)
		{
			y_prev = y;
			y = y.next;
		}

		// If x_prev exists, then new next of it will be y. Consider the case
		// when y.next is x, in this case, x_prev and y are same. So the statement
		// "x_prev.next = y" creates a self loop. This self loop will be broken
		// when we change y.next.
		if (x_prev != null)
			x_prev.next = y;

		// Same thing applies to y_prev
		if (y_prev != null)
			y_prev.next = x;

		// Swap next pointers of x and y. These statements also break self
		// loop if x.next is y or y.next is x
		ListNode temp = x.next;
		x.next = y.next;
		y.next = temp;

		// Change head pointers when k is 1 or n
		if (k == 1)
			head = y;
		if (k == n)
			head = x;
	}


	// Function to sort a linked list of 0s, 1s and 2s
	void sortList()
	{
		int count[] = {0, 0, 0};  // Initialize count of '0', '1' and '2' as 0
		ListNode ptr = head;

		/* count total number of '0', '1' and '2'
		 * count[0] will store total number of '0's
		 * count[1] will store total number of '1's
		 * count[2] will store total number of '2's  */
		while (ptr != null)
		{
			count[ptr.data] += 1;
			ptr = ptr.next;
		}

		int i = 0;
		ptr = head;

		/* Let say count[0] = n1, count[1] = n2 and count[2] = n3
		 * now start traversing list from head node,
		 * 1) fill the list with 0, till n1 > 0
		 * 2) fill the list with 1, till n2 > 0
		 * 3) fill the list with 2, till n3 > 0  */
		while (ptr != null)
		{
			if (count[i] == 0)
				++i;
			else
			{
				ptr.data = i;
				--count[i];
				ptr = ptr.next;
			}
		}
	}

	void splitList(ListNode a){

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList l = new LinkedList();
		l.addFirst(1);	l.addFirst(2);	l.addFirst(3); l.addFirst(11);	
		l.addFirst(12); l.addFirst(10);	l.addFirst(12);	l.addFirst(12);	
		l.addFirst(10);	l.addFirst(12);
		l.display(); 
		System.out.println();
		//l.reverse();
		//l.RecursiveReverse(l.getHead());
		/*ListNode xx = l.reverseRecur(l.getHead());
		l.head = xx;
		System.out.println("Reversed List: ");
		l.display();
		System.out.println();*/

		LinkedList lRev = new LinkedList();
		lRev.addFirst(4); lRev.addFirst(3);	lRev.addFirst(2);	lRev.addFirst(1);
		ListNode xx1 = lRev.reverseRecur(lRev.getHead());
		lRev.head = xx1;
		System.out.println("Reversed Rev List: ");
		lRev.display();
		System.out.println();


		l.delDups();
		System.out.println("After Duplicates Removed:");
		l.display();
		System.out.println();

		/*
		l.addFirst(14);	l.addFirst(15);	l.addFirst(16);	l.addFirst(17);
		l.display();System.out.println();
		ListNode temp = l.kthToLast(4);
		System.out.println("Kth Element: " + temp.data);
		l.addEnd(18);
		l.addEnd(19);
		l.addEnd(20);
		l.display();System.out.println();
		l.addAtIndex(21, 4);
		l.display(); System.out.println();
		l.removeEnd(); l.removeEnd();
		l.display();System.out.println();
		l.removeAtIndex(4);
		l.display();System.out.println();*/

		LinkedList lsortInsert = new LinkedList();
		/*lsortInsert.SortedInsert(10);
		lsortInsert.SortedInsert(9);
		lsortInsert.SortedInsert(15);
		lsortInsert.SortedInsert(5);*/

		lsortInsert.SortedInsert(8);
		lsortInsert.SortedInsert(9);
		lsortInsert.SortedInsert(10);
		lsortInsert.SortedInsert(5);
		lsortInsert.SortedInsert(6);
		lsortInsert.SortedInsert(7);

		System.out.println("*****Sorted Insert Display*****");
		lsortInsert.display();System.out.println();

		LinkedList lInsertSort = new LinkedList();
		lInsertSort.addFirst(7);lInsertSort.addFirst(6);lInsertSort.addFirst(5);
		lInsertSort.addFirst(10);lInsertSort.addFirst(9);lInsertSort.addFirst(8); 
		ListNode abc = lInsertSort.insertSort();
		lInsertSort.head = abc;
		System.out.println("Insert Sort of the list: ");
		lInsertSort.display();System.out.println();

		ListNode x = lInsertSort.reverseRecur(lInsertSort.getHead());
		System.out.println("ReverRecur List");
		lInsertSort.head = x;
		lInsertSort.display();System.out.println();

		LinkedList a = new LinkedList();
		a.addFirst(1);
		a.addFirst(2);
		a.addFirst(3);

		LinkedList b = new LinkedList();
		b.addFirst(4);
		b.addFirst(5);
		b.addFirst(6);

		ListNode c = ShuffleMergeRecur(a.getHead(),b.getHead());
		LinkedList lShuffleList = new LinkedList();
		lShuffleList.head = c;
		System.out.println("Shuffled merged List:");
		lShuffleList.display();
		System.out.println();

		LinkedList aSortMerge = new LinkedList();
		aSortMerge.addFirst(6);
		aSortMerge.addFirst(3);
		aSortMerge.addFirst(2);

		LinkedList bSortMerge = new LinkedList();
		bSortMerge.addFirst(3);
		bSortMerge.addFirst(2);
		bSortMerge.addFirst(1);

		ListNode sortMergeNode = SortedMerge(aSortMerge.getHead(),bSortMerge.getHead());
		LinkedList sortList = new LinkedList();
		sortList.head = sortMergeNode;
		System.out.println("Sorted List of the two lists: 2.3.6 and 1.2.3");
		sortList.display();
		System.out.println();

		/*ListNode sortMergeNode = SortedMerge(aSortMerge.getHead(),bSortMerge.getHead());
		System.out.println(sortMergeNode);*/

		ListNode sortedIntersectResult = null;
		sortedIntersectResult = sortedIntersect(aSortMerge.getHead(),bSortMerge.getHead());
		LinkedList sortedIntersectList = new LinkedList();
		sortedIntersectList.head = sortedIntersectResult;
		aSortMerge.display();
		bSortMerge.display();
		System.out.println("Sorted Intesect List of the two lists: ");
		sortedIntersectList.display();
		System.out.println();

		LinkedList rotateList = new LinkedList();
		rotateList.addFirst(60);	rotateList.addFirst(50);	rotateList.addFirst(40);
		rotateList.addFirst(30);	rotateList.addFirst(20);	rotateList.addFirst(10);

		System.out.println("Given linked list: ");
		rotateList.display();
		rotateList.rotate(4);
		System.out.println();

		System.out.println("Rotated Linked list: ");
		rotateList.display();
		System.out.println();

		/* Start with the empty list */
		LinkedList A = new LinkedList();
		LinkedList B = new LinkedList();
		LinkedList C = new LinkedList();

		/*create a linked list 'a' 10.15.5.20 */	    
		A.addFirst(20);	A.addFirst(4);	A.addFirst(15); A.addFirst(10);
		/*create a sorted linked list 'b' 2.4.9.10 */
		B.addFirst(10);	B.addFirst(9);	B.addFirst(4); B.addFirst(2);
		/*create another sorted linked list 'c' 8.4.2.1 */
		C.addFirst(1);	C.addFirst(2);	C.addFirst(4); C.addFirst(8);
		int givenNumber = 25;
		isSumSorted(A.getHead(), B.getHead(), C.getHead(), givenNumber);
		System.out.println();

		LinkedList lSegOddEven = new LinkedList();
		lSegOddEven.addFirst(6);
		lSegOddEven.addFirst(7);
		lSegOddEven.addFirst(1);
		lSegOddEven.addFirst(4);
		lSegOddEven.addFirst(5);
		lSegOddEven.addFirst(10);
		lSegOddEven.addFirst(12);
		lSegOddEven.addFirst(8);
		lSegOddEven.addFirst(15);
		lSegOddEven.addFirst(17);
		lSegOddEven.display();
		System.out.println();
		lSegOddEven.segregateEvenOdd();
		lSegOddEven.display();
		System.out.println();

		LinkedList lKReverse = new LinkedList();
		for(int i = 20; i > 0; i--)
			lKReverse.addFirst(i);
		//lKReverse.display();
		//lKReverse.kAltReverse(3);
		//lKReverse.display();
		System.out.println();

		LinkedList lSplit = new LinkedList();
		lSplit.addFirst(5);lSplit.addFirst(4);lSplit.addFirst(3);lSplit.addFirst(2);lSplit.addFirst(1);
		lSplit.AlternatingSplit();

		/*LinkedList insertList = new LinkedList();
		insertList.sortedInsert(10);
		insertList.sortedInsert(9);
		insertList.sortedInsert(8);
		insertList.sortedInsert(7);
		insertList.sortedInsert(6);*/

		// insertList.display();

		LinkedList lI = new LinkedList();
		lI.addEnd(2);
		lI.addEnd(3);
		lI.addEnd(1);
		lI.addEnd(2);
		lI.addEnd(3);

		ListNode result = removeAll(2,lI.head);
		System.out.println();


	}
}
