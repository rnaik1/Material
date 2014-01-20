package zillow;

import java.util.LinkedList;

public class TrinaryTree {

	/*
	 *  TreeNode - consists of left, middle and right pointers
	 *  Left - consists of data < root's data
	 *  Right - consists of data > root's data
	 *  middle - consists of data = root's data
	 */
	class TreeNode {
		public TreeNode left;
		public TreeNode middle;
		public TreeNode right;
		int data;


		public TreeNode(int nodeData){
			this.data = nodeData;
		}

		public TreeNode() {
		}
	}

	// root of the Trinary Tree
	private TreeNode root;

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public TrinaryTree(){
		root = null;
	}

	// inserts data into a Trinary tree.
	public void insert(int data){
		root = insert(root, data);
	}

	private TreeNode insert(TreeNode node,int data){
		if(node==null){
			node = new TreeNode(data);
		}
		else
		{
			if(data < node.data)
				node.left = insert(node.left,data);
			else if(data == node.data)
				node.middle = insert(node.middle,data);
			else
				node.right = insert(node.right,data);
		}
		return node;
	}

	private TreeNode getMin(TreeNode node) {
		if (node != null) {
			while (node.left != null) {
				node = node.left;
			}
		} 
		return node;
	}

	// deletes data from a Trinary tree and adjusts the pointers of the tree accordingly
	public TreeNode delete(int data) {
		return delete(data, root);
	}

	private TreeNode delete(int data, TreeNode node) {
		if (node == null) {
			throw new RuntimeException();
		} else if (data < node.data) {
			node.left = delete(data, node.left);
		} else if (data > node.data) {
			node.right = delete(data, node.right);
		} else {
			if (node.middle != null) {
				node.middle = delete(data, node.middle);
			} else if (node.right != null) {
				node.data = getMin(node.right).data;
				node.right = delete(getMin(node.right).data, node.right);
			} else {
				node = node.left;
			}
		}
		return node;
	}

	// For printing the tree level order
	static void levelOrder(TreeNode root)
	{
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);

		while (!queue.isEmpty())
		{
			TreeNode temp = queue.remove();
			System.out.print(temp.data + "\t");

			if (temp.left != null)
				queue.add(temp.left);

			if (temp.middle != null)
				queue.add(temp.middle);

			if (temp.right != null)
				queue.add(temp.right);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		
		TrinaryTree t = new TrinaryTree();
		
		t.insert(5);
		t.insert(4);
		t.insert(5);
		t.insert(9);
		t.insert(2);
		t.insert(7);
		t.insert(2);
		
		System.out.println("Tree after creation");
		levelOrder(t.getRoot());

		// I have added the syso statements just for illustration purpose
		System.out.println("\nTree after deleting first 5");
		t.delete(5);
		levelOrder(t.getRoot());
		
		System.out.println("\nTree after deleting second 5 - See how 7 becomes the root of the tree now :)");
		t.delete(5);
		levelOrder(t.getRoot());
	}
}