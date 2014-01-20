package arrays;

/*
Write a function to count number of smaller elements on right of each element in an array.
Given an unsorted array arr[] of distinct integers, construct another array countSmaller[] such that countSmaller[i] contains 
count of smaller elements on right side of each element arr[i] in array.

Examples:

Input:   arr[] =  {12, 1, 2, 3, 0, 11, 1}
Output:  countSmaller[]  =  {6, 1, 2, 2, 0, 1, 0} 

(Corner Cases)
Input:   arr[] =  {5, 4, 3, 2, 1}
Output:  countSmaller[]  =  {4, 3, 2, 1, 0} 

Input:   arr[] =  {1, 2, 3, 4, 5}
Output:  countSmaller[]  =  {0, 0, 0, 0, 0}*/


public class CountSmaller {
	
	private static Node root = null;

	public static void printInorder(Node node) {
		if (null == node) { return ; }
		if (null != node.left) {
			printInorder(node.left);
		}
		System.out.println(node.data + "::" + node.counter);
		if (null != node.right) {
			printInorder(node.right);
		}
	}

	public static Node buildBST(Node node, int [] A) {
		if (null == A) { return null; }
		int len = A.length;

		for (int i=len-1; i>=0; i--) {
			node = insertIntoBST(node, A[i],0);
		}
		return node;
	}

	public static Node insertIntoBST(Node node, int value, int leftSum) {
		if (null == node) { return new Node(value); }
		if (value <= node.data) {
			++node.left_count;
			node.left = insertIntoBST(node.left, value,leftSum);
			if (node.left.left == null && node.left.right == null)
				node.left.counter = leftSum;
		} else {
			node.right = insertIntoBST(node.right, value, node.left_count+leftSum);
			if (node.right.right == null && node.right.left == null)
				node.right.counter = node.left_count+node.counter;
		}
		return node;
	}
	
	public static void main(String [] args) {

		int [] A = {4,12,5,6,1,34,3,2};
		root = buildBST(root, A);
		System.out.println("Printing inorder - data and count of smaller elements to the right: ");
		printInorder(root);

		System.out.println();
		root=null;
		int [] B = {8, 3, 10, 1, 6, 14, 4, 7, 13};
		root = buildBST(root, B);
		System.out.println("Printing inorder - data and count of smaller elements to the right: ");
		printInorder(root);
	}
}

class Node {
	Integer data;
	Integer left_count;
	Integer counter;
	Node left;
	Node right;

	public Node(Integer data) {
		this.data = data;
		this.left_count = 1;
		this.counter = 0;
		this.left = null; this.right = null;
	}
} 