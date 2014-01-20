package binaryTree;

public class MirrorTree {

	static void mirror(TreeNode node) 
	{
		if (node==null) 
			return;  
		else
		{
			/* do the subtrees */
			mirror(node.left);
			mirror(node.right);

			/* swap the pointers in this node */
			TreeNode temp = node.left;
			node.left  = node.right;
			node.right = temp;
		}
	} 

	static void inOrder(TreeNode node) 
	{
		if (node == null) 
			return;

		inOrder(node.left);
		System.out.println(node.data);

		inOrder(node.right);
	} 

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5); 

		/* Print inorder traversal of the input tree */
		System.out.println("Inorder traversal of the constructed tree is: ");
		inOrder(root);

		/* Convert tree to its mirror */
		mirror(root); 

		/* Print inorder traversal of the mirror tree */
		System.out.println("Inorder traversal of the mirror tree is: ");  
		inOrder(root);

	}

}
