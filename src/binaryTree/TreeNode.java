package binaryTree;


public class TreeNode {
		public TreeNode left;
		public TreeNode right;
		public TreeNode nextRight;
		public TreeNode next;
		public int data;
		public int lCount;
		public int rCount;
		public int freq;
		
		public TreeNode(int nodeData){
			this.data = nodeData;
			freq = 1;
		}
		
		public TreeNode() {
			// TODO Auto-generated constructor stub
		}

		public TreeNode insert(TreeNode node,int data){
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
}