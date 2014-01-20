package binaryTree;

import java.util.HashSet;
import java.util.Set;

public class BinaryTree {

	/**
	 * @param args
	 */
	private TreeNode root;
	
	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	private static class Node{
		public Node left;
		public Node right;
		public int data;
		public int freq;
		
		Node(int nodeData){
			this.data = nodeData;
		}
	}
	
	public BinaryTree(){
		root = null;
	}
	
	// Lookup a value 
	public boolean lookup(int data){
		return(lookup(root,data));
	}
	private boolean lookup(TreeNode node,int data){
		if(node == null) 
			return false;
		if(node.data == data) 
			return true;
		else if (data < node.data) 
			return (lookup(node.left, data));
		else 
			return (lookup(node.right, data));
	}
	
	// inserts data into a binary search tree.
	public void insert(int data){
		root = insert(root, data);
	}
	private TreeNode insert(TreeNode node,int data){
		if(node==null){
			node = new TreeNode(data);
		}
		else
		{
			if(data <=node.data)
				node.left = insert(node.left,data);
			else
				node.right = insert(node.right,data);
		}
		return node;
	}
	public void printTree(){
		printTree(root);
		System.out.println();
	}
	
	int temp;
	Set<Integer> set = new HashSet<Integer>();
	private void printTree(TreeNode node)
	{
		if(node == null) return;
		printTree(node.left);
		if(temp == node.data){
			set.add(temp);
		}
		temp = node.data;
		System.out.print(node.data + " ");
		printTree(node.right);
	}
	
	public int maxDepth(){
		return(maxDepth(root));
	}
	private int maxDepth(TreeNode node){
		if(node==null) return 0;
		else
		{
			int lDepth = maxDepth(node.left);
			int rDepth = maxDepth(node.right);
			return Math.max(lDepth,rDepth) + 1;
		}
	}
	
	public int minValue(){
		return minValue(root);
	}
	private int minValue(TreeNode node){
		TreeNode current = node;
		while(current.left!=null)
		{
			current=current.left;
		}
		return current.data;
	}
	
	public int maxValue(){
		return maxValue(root);
	}
	private int maxValue(TreeNode node){
		TreeNode current = node;
		while(current.right!=null)
		{
			current=current.right;
		}
		return current.data;
	}
	
	
	// Has pathSum = given sum
	public boolean pathSum(int sum){
		return pathSum(root,sum);
	}
	private boolean pathSum(TreeNode node,int sum){
		if(node==null) return (sum==0);
		else
		{
			int subSum = sum - node.data;
			return(pathSum(node.left,subSum) || pathSum(node.right,subSum));
		}
	}
	
	// print paths from root to leaves
	public void printPaths() {
		int path[] = new int[1000];
		printPaths(root, path, 0);
	}
	private void printPaths(TreeNode node, int[] path, int index){
		if(node==null) return;
		path[index++] = node.data;
		
		if(node.left==null && node.right==null){
			for(int i=0;i<index;i++)
				System.out.println(path[i] + " ");
			System.out.println();
		}
		else{
			printPaths(node.left,path,index);
			printPaths(node.right,path,index);
		}
	}
	
	// Compare 2 binary trees
	public boolean sameTree(BinaryTree other){
		return sameTree(root,other.root);
	}
	private boolean sameTree(TreeNode a, TreeNode b){
		if(a==null && b==null) return true;
		else if(a!=null && b!=null){
			return(a.data == b.data && (sameTree(a.left, b.left)) && (sameTree(a.right, b.right)));
		}
		else
			return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTree t = new BinaryTree();
		t.insert(10); t.insert(10); t.insert(10); t.insert(5); t.insert(15); t.insert(15); t.insert(-10); t.insert(100);	
		t.insert(-10); t.insert(150); t.insert(3); t.insert(2);
		t.printTree();
		System.out.println(t.set);
		/*System.out.println(t.maxDepth());
		System.out.println("Minimum Value: " + t.minValue());
		System.out.println("Maximum Value: " + t.maxValue());
		System.out.println("Path with sum as 10 exists: " + t.pathSum(10));
		System.out.println("Path with sum as 100 exists: " + t.pathSum(100));
		System.out.println("Path with sum as 275 exists: " + t.pathSum(275));
		t.printPaths();*/
		
		BinaryTree t1 = new BinaryTree();
		t1.insert(10); t1.insert(5); t1.insert(15); t1.insert(100);	t1.insert(-10); t1.insert(150); t1.insert(3); t1.insert(2);	
		t1.printTree();
		System.out.println(t1.set);
		
		System.out.println(t.sameTree(t1));
		
		//System.out.println(t.lookup(25));
		//System.out.println(t.lookup(15));
	}
}