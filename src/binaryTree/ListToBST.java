package binaryTree;

import linkedList.LinkedList;
import linkedList.ListNode;

public class ListToBST {

	public static TreeNode sortedListToBST(ListNode list, int start, int end) {
		if (start > end) return null;
		// same as (start+end)/2, avoids overflow
		int mid = start + (end - start) / 2;
		TreeNode leftChild = sortedListToBST(list, start, mid-1);
		TreeNode parent = new TreeNode((Integer)list.data);
		parent.left = leftChild;
		list = list.next;
		parent.right = sortedListToBST(list, mid+1, end);
		return parent;
	}

	public static TreeNode sortedListToBST(ListNode head, int n) {
		return sortedListToBST(head, 0, n-1);
	}
	
	public static void printTree(TreeNode node)
	{
		if(node == null) return;
		printTree(node.left);
		System.out.print(node.data + " ");
		printTree(node.right);
	}

	public static void main(String[] args) {
		LinkedList l = new LinkedList();
		l.addFirst(37);	l.addFirst(30);	l.addFirst(12);
		l.display();
		System.out.println();
		TreeNode root = sortedListToBST(l.getHead(),3);
		printTree(root);
	}
}
