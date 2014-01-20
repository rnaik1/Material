package linkedList;

public class DLinkedList {

	node head;
	/* a node of the doubly linked list */
	class node
	{
		int data;
		node next;
		node prev;    
	};

	/* Function to reverse a Doubly Linked List */
	void reverse()
	{
		node temp = null;  
		node current = head;

		/* swap next and prev for all nodes of 
	       doubly linked list */
		while (current !=  null)
		{
			temp = current.prev;
			current.prev = current.next;
			current.next = temp;              
			current = current.prev;
		}      

		/* Before changing head, check for the cases like empty 
	        list and list with only one node */
		if(temp != null )
			head = temp.prev;
	}



	/* UTILITY FUNCTIONS */
	/* Function to insert a node at the beginning of the Doubly Linked List */
	void push(int new_data)
	{
		/* allocate node */
		node new_node = new node();

		/* put in the data  */
		new_node.data  = new_data;

		/* since we are adding at the begining, 
	      prev is always NULL */
		new_node.prev = null;

		/* link the old list off the new node */
		new_node.next = (head);    

		/* change prev of head node to new node */
		if((head) !=  null)
			(head).prev = new_node ;    

		/* move the head to point to the new node */
		(head)    = new_node;
	}

	/* Function to print nodes in a given doubly linked list 
	   This function is same as printList() of singly linked lsit */
	void printList()
	{
		node node = head;
		while(node!=null)
		{
			System.out.print(node.data + "\t");
			node = node.next;
		}
	} 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* Start with the empty list */
		DLinkedList d = new DLinkedList();
		node head = null;

		/* Let us create a sorted linked list to test the functions
		   Created linked list will be 10->8->4->2 */
		d.push(2);
		d.push(4);
		d.push(8);
		d.push(10);

		System.out.println("Original Linked list ");
		d.printList();
		System.out.println();

		/* Reverse doubly linked list */
		d.reverse();

		System.out.println("Reversed Linked list ");
		d.printList();
	}

}
