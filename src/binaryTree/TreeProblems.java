package binaryTree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

import linkedList.ListNode;
import stackImplementation.Queue;

public class TreeProblems {

	// ************************************************************************************************

	// Print paths from root to leaves.
	static void printPaths(TreeNode node)
	{
		int path[] = new int[5];
		printPathsRecur(node, path, 0);
	}

	/* Recursive helper function -- given a node, and an array containing
	 the path from the root node up to but not including this node,
	 print out all the root-leaf paths. */
	static void printPathsRecur(TreeNode node, int path[], int pathLen) 
	{
		if (node==null) return;

		/* append this node to the path array */
		path[pathLen] = node.data;
		pathLen++;

		/* it's a leaf, so print the path that led to here */
		if (node.left==null && node.right==null) 
		{
			printArray(path, pathLen);
		}
		else
		{
			/* otherwise try both subtrees */
			printPathsRecur(node.left, path, pathLen);
			printPathsRecur(node.right, path, pathLen);
		}
	}

	/* Utility that prints out an array on a line */
	static void printArray(int ints[], int len)
	{
		for (int i=0; i<len; i++) {
			System.out.print(ints[i] + "\t");
		}
		System.out.println();
	}

	// ************************************************************************************************

	public static void inOrder(TreeNode node) 
	{
		if (node == null) 
			return;

		inOrder(node.left);
		System.out.print(node.data + "\t");
		inOrder(node.right);
	}

	// ************************************************************************************************

	/* Iterative function for inorder tree traversal */
	public static void inOrderStack(TreeNode root)
	{
		/* set current to root of binary tree */
		TreeNode current = root;
		Stack<TreeNode> s = new Stack<TreeNode>();  /* Initialize stack s */
		boolean done = false;

		while (!done)
		{
			/* Reach the left most tNode of the current tNode */
			if(current !=  null)
			{
				/* place pointer to a tree node on the stack before traversing 
	        the node's left subtree */
				s.push(current);
				current = current.left;
			}
			/* backtrack from the empty subtree and visit the tNode 
	       at the top of the stack; however, if the stack is empty,
	      you are done */
			else                                                             
			{
				if (!s.empty())
				{
					current = s.pop();
					System.out.print(current.data + "\t");
					/* we have visited the node and its left subtree. Now, it's right subtree's turn */
					current = current.right;
				}
				else
					done = true; 
			}
		} /* end of while */ 
	}

	// ************************************************************************************************

	/* Function to Indorder traverse binary tree without recursion and 
	   without stack */
	static void MorrisTraversal(TreeNode root)
	{
		TreeNode current,pre;

		if(root == null)
			return;

		current = root;	
		while(current != null)
		{               
			if(current.left == null)
			{
				System.out.print(current.data + "\t");
				current = current.right;      
			}
			else
			{
				/* Find the inorder predecessor of current */
				pre = current.left;
				while(pre.right != null && pre.right != current)
					pre = pre.right;

				/* Make current as right child of its inorder predecessor */
				if(pre.right == null)
				{
					pre.right = current;
					current = current.left;
				}

				/* Revert the changes made in if part to restore the original 
	        tree i.e., fix the right child of predecssor */   
				else 
				{
					pre.right = null;
					System.out.print(current.data + "\t");
					current = current.right;      
				} /* End of if condition pre.right == null */
			} /* End of if condition current.left == null*/
		} /* End of while */
	}


	// ************************************************************************************************

	// An iterative process to print preorder traversal of Binary tree
	static void iterativePreorder(TreeNode root)
	{
		// Base Case
		if (root == null)
			return;

		// Create an empty stack and push root to it
		Stack<TreeNode> nodeStack = new Stack<TreeNode>();
		nodeStack.push(root);

		/* Pop all items one by one. Do following for every popped item
	       a) print it
	       b) push its right child
	       c) push its left child
	    Note that right child is pushed first so that left is processed first */
		while (!nodeStack.empty())
		{
			// Pop the top item from stack and print it
			TreeNode node = nodeStack.pop();
			System.out.print(node.data + "\t");
			//nodeStack.pop();

			// Push right and left children of the popped node to stack
			if (node.right != null)
				nodeStack.push(node.right);
			if (node.left != null)
				nodeStack.push(node.left);
		}
	}

	// ************************************************************************************************

	// Preorder traversal without recursion and without stack
	static void morrisTraversalPreorder(TreeNode root)
	{
		TreeNode current = root;
		while (current != null)
		{
			// If left child is null, print the current node data. Move to
			// right child.
			if (current.left == null)
			{
				System.out.print(current.data + "\t");
				current = current.right;
			}
			else
			{
				// Find inorder predecessor
				TreeNode pre = current.left;
				while (pre.right!=null && pre.right != current)
					pre = pre.right;

				// If the right child of inorder predecessor already points to
				// this node
				if (pre.right == null)
				{
					pre.right = current;
					System.out.print(current.data + "\t");
					current = current.left;
				}

				// If right child doesn't point to this node, then print this
				// node and make right child point to this node
				else
				{
					pre.right = null;
					current = current.right;
				}
			}
		}
	}

	// ************************************************************************************************

	// An iterative function to do post order traversal of a given binary tree
	static void iterativePostOrder(TreeNode root)
	{
		// Create two stacks
		Stack<TreeNode> s1 = new Stack<TreeNode>();
		Stack<TreeNode> s2 = new Stack<TreeNode>();

		// push root to first stack
		s1.push(root);
		TreeNode node;

		// Run while first stack is not empty
		while (!s1.isEmpty())
		{
			// Pop an item from s1 and push it to s2
			node = s1.pop();
			s2.push(node);

			// Push left and right children of removed item to s1
			if (node.left != null)
				s1.push(node.left);
			if (node.right != null)
				s1.push(node.right);
		}

		// Print all elements of second stack
		while (!s2.isEmpty())
		{
			node = s2.pop();
			System.out.print(node.data + "\t");
		}
	}

	// ************************************************************************************************

	// An iterative function to do postorder traversal of a given binary tree
	static void postOrderIterative(TreeNode root)
	{
		// Check for empty tree
		if (root == null)
			return;

		Stack<TreeNode> stack = new Stack<TreeNode>();
		do
		{
			// Move to leftmost node
			while (root != null)
			{
				// Push root's right child and then root to stack.
				if (root.right != null)
					stack.push(root.right);
				stack.push(root);

				// Set root as root's left child  
				root = root.left;
			}

			// Pop an item from stack and set it as root    
			root = stack.pop();

			// If the popped item has a right child and the right child is not
			// processed yet, then make sure right child is processed before root
			if (root.right!=null && (!stack.isEmpty() && stack.peek() == root.right))
			{
				stack.pop();  // remove right child from stack
				stack.push(root);  // push root back to stack
				root = root.right; // change root so that the right 
				// child is processed next
			}
			else  // Else print root's data and set root as null
			{
				System.out.print(root.data + "\t");
				root = null;
			}
		} while (!stack.isEmpty());
	}

	// ************************************************************************************************

	/* Recursive function to construct binary of size len from
	   Inorder traversal in[] and Preorder traversal pre[].  Initial values
	   of inStrt and inEnd should be 0 and len -1.  The function doesn't
	   do any error checking for cases where inorder and preorder
	   do not form a tree */
	static int preIndex = 0;
	static TreeNode buildTree(int in[], int pre[], int inStrt, int inEnd)
	{
		if(inStrt > inEnd)
			return null;

		/* Pick current node from Preorder traversal using preIndex
	    and increment preIndex */
		TreeNode tNode = new TreeNode(pre[preIndex++]);

		/* If this node has no children then return */
		if(inStrt == inEnd)
			return tNode;

		/* Else find the index of this node in Inorder traversal */
		int inIndex = search(in, inStrt, inEnd, tNode.data);

		/* Using index in Inorder traversal, construct left and
	     right subtress */
		tNode.left = buildTree(in, pre, inStrt, inIndex-1);
		tNode.right = buildTree(in, pre, inIndex+1, inEnd);

		return tNode;
	}

	/* UTILITY FUNCTIONS */
	/* Function to find index of value in arr[start...end]
	   The function assumes that value is present in in[] */
	static int search(int arr[], int strt, int end, int value)
	{
		int i;
		for(i = strt; i <= end; i++)
		{
			if(arr[i] == value)
				return i;
		}
		return -1;
	}

	// ************************************************************************************************

	/* Utillity function to check if the given node is leaf or not */
	static boolean isLeaf(TreeNode node)
	{
		if(node == null)
			return false;
		if(node.left == null && node.right == null)
			return true;
		return false;
	}

	/* returns 1 if SumTree property holds for the given
	    tree */
	static boolean isSumTree(TreeNode node)
	{
		int ls; // for sum of nodes in left subtree
		int rs; // for sum of nodes in right subtree

		/* If node is null or it's a leaf node then
	       return true */
		if(node == null || isLeaf(node))
			return true;

		if( isSumTree(node.left) && isSumTree(node.right))
		{
			// Get the sum of nodes in left subtree
			if(node.left == null)
				ls = 0;
			else if(isLeaf(node.left))
				ls = node.left.data;
			else
				ls = 2*(node.left.data);

			// Get the sum of nodes in right subtree
			if(node.right == null)
				rs = 0;
			else if(isLeaf(node.right))
				rs = node.right.data;
			else
				rs = 2*(node.right.data);

			/* If root's data is equal to sum of nodes in left
	           and right subtrees then return 1 else return 0*/
			return(node.data == ls + rs);
		}

		return false;
	}

	// ************************************************************************************************

	/* This function returns the leftmost child of nodes at the same level as p.
	   This function is used to getNExt right of p's right child
	   If right child of is null then this can also be sued for the left child */
	static TreeNode getNextRight(TreeNode q)
	{
		TreeNode temp = q.nextRight;

		/* Traverse nodes at p's level and find and return
	       the first node's first child */
		while (temp != null)
		{
			if (temp.left != null)
				return temp.left;
			if (temp.right != null)
				return temp.right;
			temp = temp.nextRight;
		}

		// If all the nodes at q's level are leaf nodes then return null
		return null;
	}

	/* Sets nextRight of all nodes of a tree with root as p */
	static TreeNode connect(TreeNode root)
	{
		TreeNode temp = root;

		if (root==null)
			return null;

		// Set nextRight for root
		root.nextRight = null;

		// set nextRight of all levels one by one
		while (root != null)
		{
			TreeNode q = root;

			/* Connect all childrem nodes of p and children nodes of all other nodes
	          at same level as p */
			while (q != null)
			{
				// Set the nextRight pointer for p's left child
				if (q.left != null)
				{
					// If q has right child, then right child is nextRight of
					// p and we also need to set nextRight of right child
					if (q.right != null)
						q.left.nextRight = q.right;
					else
						q.left.nextRight = getNextRight(q);
				}

				if (q.right != null)
					q.right.nextRight = getNextRight(q);

				// Set nextRight for other nodes in pre order fashion
				q = q.nextRight;
			}

			// start from the first node of next level
			if (root.left != null)
				root = root.left;
			else if (root.right != null)
				root = root.right;
			else
				root = getNextRight(root);
		}
		return temp;
	}

	// ************************************************************************************************

	/* Function to get diameter of a binary tree */
	static int diameter(TreeNode tree)
	{
		/* base case where tree is empty */
		if (tree == null)
			return 0;

		/* get the height of left and right sub-trees */
		int lheight = height(tree.left);
		int rheight = height(tree.right);

		/* get the diameter of left and right sub-trees */
		int ldiameter = diameter(tree.left);
		int rdiameter = diameter(tree.right);

		/* Return max of following three
	   1) Diameter of left subtree
	   2) Diameter of right subtree
	   3) Height of left subtree + height of right subtree + 1 */
		return Math.max(lheight + rheight + 1, Math.max(ldiameter, rdiameter));
	} 

	/* UTILITY FUNCTIONS TO TEST diameter() FUNCTION */

	/*  The function Compute the "height" of a tree. Height is the 
	    number f nodes along the longest path from the root node 
	    down to the farthest leaf node.*/
	static int height(TreeNode node)
	{
		/* base case tree is empty */
		if(node == null)
			return 0;

		/* If tree is not empty then height = 1 + max of left 
	      height and right heights */   
		return 1 + Math.max(height(node.left), height(node.right));
	} 

	// ************************************************************************************************

	/* Iterative insertion
	   Recursion is least preferred unless we gain something
	 */
	static TreeNode insert_node(TreeNode root, TreeNode node)
	{
		/* A crawling pointer */
		TreeNode pTraverse = root;
		TreeNode currentParent = pTraverse;

		// Traverse till appropriate node
		while(pTraverse != null)
		{
			currentParent = pTraverse;

			if( node.data < pTraverse.data )
			{
				/* We are branching to left subtree
	               increment node count */
				pTraverse.lCount++;
				/* left subtree */
				pTraverse = pTraverse.left;
			}
			else
			{
				/* right subtree */
				pTraverse.rCount++;
				pTraverse = pTraverse.right;
			}
		}

		/* If the tree is empty, make it as root node */
		if(root == null)
		{
			root = node;
		}
		else if( node.data < currentParent.data )
		{
			/* Insert on left side */
			currentParent.left = node;
		}
		else
		{
			/* Insert on right side */
			currentParent.right = node;
		}

		return root;
	}

	/* Elements are in an array. The function builds binary tree */
	static TreeNode binary_search_tree(TreeNode root, int keys[], int size)
	{
		TreeNode new_node;

		for(int i = 0; i < size; i++)
		{
			new_node = new TreeNode(keys[i]);

			/* insert into BST */
			root = insert_node(root, new_node);
		}

		return root;
	}

	static int k_smallest_element(TreeNode root, int k)
	{
		int ret = -1;

		if( root != null )
		{
			/* A crawling pointer */
			TreeNode pTraverse = root;

			/* Go to k-th smallest */
			while(pTraverse != null)
			{
				if(pTraverse.lCount + 1 == k)
				{
					ret = pTraverse.data;
					break;
				}
				else if(pTraverse.lCount < k)
				{
					/*  There are less nodes on left subtree
	                    Go to right subtree */
					k = k - (pTraverse.lCount + 1);
					pTraverse = pTraverse.right;
				}
				else
				{
					/* The node is on left subtree */
					pTraverse = pTraverse.left;
				}
			}
		}

		return ret;
	}

	static int k_largest_element(TreeNode root, int k)
	{
		int ret = -1;

		if( root != null )
		{
			/* A crawling pointer */
			TreeNode pTraverse = root;

			/* Go to k-th smallest */
			while(pTraverse != null)
			{
				if(pTraverse.rCount + 1 == k)
				{
					ret = pTraverse.data;
					break;
				}
				else if( pTraverse.rCount < k )
				{
					/*  There are less nodes on left subtree
	                    Go to right subtree */
					k = k - (pTraverse.rCount + 1);
					pTraverse = pTraverse.left;
				}
				else
				{
					/* The node is on left subtree */
					pTraverse = pTraverse.right;
				}
			}
		}

		return ret;
	}

	// ************************************************************************************************

	/*
	   Helper function for getLevel().  It returns level of the data if data is
	   present in tree, otherwise returns 0.
	 */
	static int getLevelUtil(TreeNode node, int data, int level)
	{
		if ( node == null )
			return 0;

		if ( node.data == data )
			return level;

		return getLevelUtil ( node.left, data, level+1) | getLevelUtil ( node.right, data, level+1);
	}

	/* Returns level of given data value */
	static int getLevel(TreeNode node, int data)
	{
		return getLevelUtil(node,data,1);
	}

	// ************************************************************************************************

	static int sumGivenLevel(TreeNode root, int level)
	{
		if(root == null)
			return 0;
		if(level == 1)
			return root.data;
		else if (level > 1)
		{
			return (sumGivenLevel(root.left, level-1) + sumGivenLevel(root.right, level-1));
		}
		return -1;
	}

	// ************************************************************************************************

	/* If target is present in tree, then prints the ancestors
	   and returns true, otherwise returns false. */
	static boolean printAncestors(TreeNode root, int target)
	{
		/* base cases */
		if ( root == null )
			return false;

		if ( root.data == target )
			return true;

		/* If target is present in either left or right subtree of this node,
	     then print this node */
		if ( printAncestors(root.left, target) || printAncestors(root.right, target) )
		{
			System.out.print(root.data + "\t");
			return true;
		}

		/* Else return false */
		return false;
	}

	// ************************************************************************************************

	/* The functions prints all the keys which in the given range [k1..k2].
    The function assumes than k1 < k2 */
	static void Print(TreeNode root, int k1, int k2)
	{
		/* base case */
		if ( null == root )
			return;

		/* Since the desired o/p is sorted, recurse for left subtree first
      If root.data is greater than k1, then only we can get o/p keys
      in left subtree */
		if ( k1 < root.data )
			Print(root.left, k1, k2);

		/* if root's data lies in range, then prints root's data */
		if ( k1 <= root.data && k2 >= root.data )
			System.out.print(root.data + "\t");

		/* If root.data is smaller than k2, then only we can get o/p keys
      in right subtree */
		if ( k2 > root.data )
			Print(root.right, k1, k2);
	}

	// ************************************************************************************************

	/* Given a binary tree, return true if the tree is complete
	   else false */
	static boolean isCompleteBT(TreeNode root)
	{
		// Base Case: An empty tree is complete Binary Tree
		if (root == null)
			return true;

		// Create an empty queue
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();

		// Create a flag variable which will be set true
		// when a non full node is seen
		boolean notFull = false;

		// Do level order traversal using queue.
		queue.add(root);
		while(!queue.isEmpty())
		{
			TreeNode temp_node = queue.remove();

			/* Check if left child is present*/
			if(temp_node.left != null)
			{
				// If we have seen a non full node, and we see a node
				// with non-empty left child, then the given tree is not
				// a complete Binary Tree
				if (notFull)
					return false;

				queue.addLast(temp_node.left);
			}
			else // If this a non-full node, set the flag as true
				notFull = true;

			/* Check if right child is present*/
			if(temp_node.right != null)
			{
				// If we have seen a non full node, and we see a node
				// with non-empty left child, then the given tree is not
				// a complete Binary Tree
				if(notFull)
					return false;

				queue.addLast(temp_node.right);  // Enqueue Right Child
			}
			else // If this a non-full node, set the flag as true
				notFull = true;
		}

		// If we reach here, then the tree is complete Bianry Tree
		return true;
	}

	// ************************************************************************************************

	// This function does inorder traversal to find out the two swapped nodes.
	// It sets three pointers, first, middle and last.  If the swapped nodes are
	// adjacent to each other, then first and middle contain the resultant nodes
	// Else, first and last contain the resultant nodes
	static TreeNode first, middle, last, prev;
	static void correctBSTUtil( TreeNode root)
	{
		if( root != null )
		{
			// Recur for the left subtree
			correctBSTUtil( root.left);

			// If this node is smaller than the previous node, it's violating
			// the BST rule.
			if (prev!=null && root.data < prev.data)
			{
				// If this is first violation, mark these two nodes as
				// 'first' and 'middle'
				if ( first == null )
				{
					first = prev;
					middle = root;
				}

				// If this is second violation, mark this node as last
				else
					last = root;
			}

			// Mark this node as previous
			prev = root;

			// Recur for the right subtree
			correctBSTUtil( root.right);
		}
	}

	// A function to fix a given BST where two nodes are swapped.  This
	// function uses correctBSTUtil() to find out two nodes and swaps the
	// nodes to fix the BST
	static void correctBST(TreeNode root)
	{
		// Set the pointers to find out two nodes
		correctBSTUtil(root);

		// Fix (or correct) the tree
		if(first!=null && last!=null)
		{
			int temp = first.data;
			first.data = last.data;
			last.data = temp;
		}
		else if(first!=null && middle!=null) // Adjacent nodes swapped
		{
			int temp = first.data;
			first.data = middle.data;
			middle.data = temp;
		}

		// else nodes have not been swapped, passed tree is really BST.
	}

	// ************************************************************************************************

	// The function to print data of two BSTs in sorted order
	static void  merge(TreeNode root1, TreeNode root2)
	{
		// If first BST is empty, then output is inorder
		// traversal of second BST
		if (root1 == null)
		{
			inOrder(root2);
			return;
		}
		// If second BST is empty, then output is inorder
		// traversal of first BST
		if (root2 == null)
		{
			inOrder(root1);
			return ;
		}

		// s1 is stack to hold nodes of first BST
		Stack<TreeNode> s1 = new Stack<TreeNode>();

		// Current node of first BST
		TreeNode current1 = root1;

		// s2 is stack to hold nodes of second BST
		Stack<TreeNode> s2 = new Stack<TreeNode>();

		// Current node of second BST
		TreeNode current2 = root2;

		// Run the loop while there are nodes not yet printed.
		// The nodes may be in stack(explored, but not printed)
		// or may be not yet explored
		while (current1 != null || !s1.isEmpty() ||
				current2 != null || !s2.isEmpty())
		{
			// Following steps follow iterative Inorder Traversal
			if (current1 != null || current2 != null)
			{
				// Reach the leftmost node of both BSTs and push ancestors of
				// leftmost nodes to stack s1 and s2 respectively
				if (current1 != null)
				{
					s1.push(current1);
					current1 = current1.left;
				}
				if (current2 != null)
				{
					s2.push(current2);
					current2 = current2.left;
				}

			}
			else
			{
				// If we reach a null node and either of the stacks is empty,
				// then one tree is exhausted, print the other tree
				if (s1.isEmpty())
				{
					while (!s2.isEmpty())
					{
						current2 = s2.pop();
						current2.left = null;
						inOrder(current2);
					}
					return ;
				}
				if (s2.isEmpty())
				{
					while (!s1.isEmpty())
					{
						current1 = s1.pop();
						current1.left = null;
						inOrder(current1);
					}
					return ;
				}

				// Pop an element from both stacks and compare the
				// popped elements
				current1 = s1.pop();
				current2 = s2.pop();

				// If element of first tree is smaller, then print it
				// and push the right subtree. If the element is larger,
				// then we push it back to the corresponding stack.
				if (current1.data < current2.data)
				{
					System.out.print(current1.data + "\t");
					current1 = current1.right;
					s2.push(current2);
					current2 = null;
				}
				else
				{
					System.out.print(current2.data + "\t");
					current2 = current2.right;
					s1.push(current1);
					current1 = null;
				}
			}
		}
	}

	// ************************************************************************************************

	// A utility function that prints all nodes on the path from root to target_leaf
	static boolean printPath (TreeNode root, TreeNode target_leaf)
	{
		// base case
		if (root == null)
			return false;

		// return true if this node is the target_leaf or target leaf is present in
		// one of its descendants
		if (root == target_leaf || printPath(root.left, target_leaf) || printPath(root.right, target_leaf) )
		{
			System.out.print(root.data + "\t");
			return true;
		}

		return false;
	}

	// This function Sets the target_leaf_ref to refer the leaf node of the maximum 
	// path sum.  Also, returns the max_sum using max_sum_ref
	static TreeNode target_leaf_ref = null;
	static int max_sum_ref = Integer.MIN_VALUE;
	static void getTargetLeaf (TreeNode node, int curr_sum)
	{
		if (node == null)
			return;

		// Update current sum to hold sum of nodes on path from root to this node
		curr_sum = curr_sum + node.data;

		// If this is a leaf node and path to this node has maximum sum so far,
		// then make this node target_leaf
		if (node.left == null && node.right == null)
		{
			if (curr_sum > max_sum_ref)
			{
				max_sum_ref = curr_sum;
				target_leaf_ref = node;
			}
		}

		// If this is not a leaf node, then recur down to find the target_leaf
		getTargetLeaf (node.left, curr_sum);
		getTargetLeaf (node.right, curr_sum);
	}

	// Returns the maximum sum and prints the nodes on max sum path
	static int maxSumPath (TreeNode node)
	{
		// base case
		if (node == null)
			return 0;

		// find the target leaf and maximum sum
		getTargetLeaf (node, 0);

		// print the path from root to the target leaf
		printPath (node, target_leaf_ref);

		return max_sum_ref;  // return maximum sum
	}

	// ************************************************************************************************

	/* Recursive function to construct binary of size len from
	   Inorder traversal inorder[]. Initial values of start and end
	   should be 0 and len -1.  
	   Given Inorder Traversal of a Special Binary Tree in which key of every node 
	   is greater than keys in left and right children, construct the Binary Tree and return root.
	 */

	static TreeNode buildTree (int inorder[], int start, int end)
	{
		if (start > end)
			return null;

		/* Find index of the maximum element from Binary Tree */
		int i = max (inorder, start, end);

		/* Pick the maximum value and make it root */
		TreeNode root = new TreeNode(inorder[i]);

		/* If this is the only element in inorder[start..end],
	       then return it */
		if (start == end)
			return root;

		/* Using index in Inorder traversal, construct left and
	       right subtress */
		root.left  = buildTree(inorder, start, i-1);
		root.right = buildTree(inorder, i+1, end);

		return root;
	}

	/* UTILITY FUNCTIONS */
	/* Function to find index of the maximum value in arr[start...end] */
	static int max (int arr[], int strt, int end)
	{
		int max = arr[strt], maxind = strt;
		for(int i = strt+1; i <= end; i++)
		{
			if(arr[i] > max)
			{
				max = arr[i];
				maxind = i;
			}
		}
		return maxind;
	}

	// ************************************************************************************************

	/*
	 * Given an array pre[] that represents Preorder traversal of a special binary tree where every node
	 * has either 0 or 2 children. One more array preLN[] is given which has only two possible values L 
	 * and N. The value L in preLN[] indicates that the corresponding node in Binary Tree is a leaf node
	 * and value N indicates that the corresponding node is non-leaf node. Write a function to construct the 
	 * tree from the given two arrays.
	 * 
	 * Input:  pre[] = {10, 30, 20, 5, 15},  preLN[] = {'N', 'N', 'L', 'L', 'L'}
	   Output: Root of following tree
		          10
		         /  \
		        30   15
		       /  \
		      20   5
	 */

	/* A recursive function to create a Binary Tree from given pre[]
	   preLN[] arrays. The function returns root of tree. index_ptr is used
	   to update index values in recursive calls. index must be initially
	   passed as 0 */
	static int index;
	static TreeNode constructTreeUtil(int pre[], char preLN[], int n)
	{
		int tindex = index; // store the current value of index in pre[]

		// Base Case: All nodes are constructed
		if (index == n)
			return null;

		// Allocate memory for this node and increment index for
		// subsequent recursive calls
		TreeNode temp = new TreeNode(pre[index]);
		(index)++;

		// If this is an internal node, construct left and right subtrees and link the subtrees
		if (preLN[tindex] == 'N')
		{
			temp.left  = constructTreeUtil(pre, preLN, n);
			temp.right = constructTreeUtil(pre, preLN, n);
		}

		return temp;
	}

	static TreeNode constructTree(int pre[], char preLN[], int n)
	{
		// Initialize index as 0. Value of index is used in recursion to maintain
		// the current index in pre[] and preLN[] arrays.
		return constructTreeUtil (pre, preLN, n);
	}

	// ************************************************************************************************

	static boolean hasOnlyOneChild(int pre[], int size)
	{
		// Initialize min and max using last two elements
		int min, max;
		if (pre[size-1] > pre[size-2])
		{
			max = pre[size-1];
			min = pre[size-2];
		}
		else
		{
			max = pre[size-2];
			min = pre[size-1];
		}


		// Every element must be either smaller than min or
		// greater than max
		for(int i=size-3; i>=0; i--)
		{
			if (pre[i] < min)
				min = pre[i];
			else if (pre[i] > max)
				max = pre[i];
			else
				return false;
		}
		return true;
	}

	// ************************************************************************************************

	static int sum;
	public static void sumOfNodes(TreeNode root){
		if(root==null)
			return;
		sumOfNodes(root.left);
		sum+=root.data;
		sumOfNodes(root.right);
	}

	// ************************************************************************************************	

	public static boolean binaryTreeBFS(TreeNode root, int value)
	{
		if (root == null)
			return false;

		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		TreeNode tempNode;
		queue.addLast(root);

		while (!queue.isEmpty())
		{
			tempNode = queue.remove();

			if (tempNode.data == value)
				return true;
			else
			{
				if (tempNode.left != null)
					queue.addLast(tempNode.left);

				if (tempNode.right != null)
					queue.addLast(tempNode.right);
			}
		}
		return false;
	}

	// ************************************************************************************************

	public static boolean binaryTreeDFS(TreeNode node, int value ) {

		// Check if it's an empty tree.
		if( node == null ) {
			return false;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>( );
		stack.push(node);

		while(!stack.isEmpty()) {

			TreeNode currentNode = stack.pop();

			if(currentNode.data == value) {
				// Found the value!
				return true;
			}

			if( currentNode.right != null ) {
				stack.push( currentNode.right );
			}

			// As we want to visit left child first, we must push this node last
			if( currentNode.left != null ) {
				stack.push( currentNode.left );
			}
		}
		// Not found.
		return false;
	}

	// ************************************************************************************************

	static TreeNode next = null;
	/* Set next of p and all descendents of p by traversing them in reverse Inorder */
	static void populateNext(TreeNode p)
	{
		// The first visited node will be the rightmost node
		// next of the rightmost node will be null

		if (p != null)
		{
			// First set the next pointer in right subtree
			populateNext(p.right);

			// Set the next as previously visited node in reverse Inorder
			p.next = next;

			// Change the prev for subsequent node
			next = p;

			// Finally, set the next pointer in right subtree
			populateNext(p.left);
		}
	}

	// ************************************************************************************************

	/* In a balanced binary search tree isPairPresent two element which sums to
	   a given value time O(n) space O(logn) */
	// Returns true if a pair with target sum exists in BST, otherwise false
	static boolean isPairPresent(TreeNode root, int target)
	{
		// Create two stacks. s1 is used for normal inorder traversal
		// and s2 is used for reverse inorder traversal
		Stack<TreeNode> s1 = new Stack<TreeNode>();
		Stack<TreeNode> s2 = new Stack<TreeNode>();

		// Note the sizes of stacks is MAX_SIZE, we can find the tree size and
		// fix stack size as O(Logn) for balanced trees like AVL and Red Black 
		// tree. We have used MAX_SIZE to keep the code simple

		// done1, val1 and curr1 are used for normal inorder traversal using s1
		// done2, val2 and curr2 are used for reverse inorder traversal using s2
		boolean done1 = false, done2 = false;
		int val1 = 0, val2 = 0;
		TreeNode curr1 = root, curr2 = root;

		// The loop will break when we either find a pair or one of the two
		// traversals is complete
		while (true)
		{
			// Find next node in normal Inorder traversal.
			while (!done1)
			{
				if (curr1 != null)
				{
					s1.push(curr1);
					curr1 = curr1.left;
				}
				else
				{
					if (s1.isEmpty())
						done1 = true;
					else
					{
						curr1 = s1.pop();
						val1 = curr1.data;
						curr1 = curr1.right;
						done1 = true;
					}
				}
			}

			// Find next node in REVERSE Inorder traversal. The only
			// difference between above and below loop is, in below loop
			// right subtree is traversed before left subtree
			while (!done2)
			{
				if (curr2 != null)
				{
					s2.push(curr2);
					curr2 = curr2.right;
				}
				else
				{
					if (s2.isEmpty())
						done2 = true;
					else
					{
						curr2 = s2.pop();
						val2 = curr2.data;
						curr2 = curr2.left;
						done2 = true;
					}
				}
			}

			// If we find a pair, then print the pair and return
			if ((val1 + val2) == target)
			{
				System.out.println("Pair Found: " + val1 + "+" + val2 + "=" +  target);
				return true;
			}

			// If sum of current values is smaller, then move to next node in
			// normal inorder traversal
			else if ((val1 + val2) < target)
				done1 = false;

			// If sum of current values is greater, then move to next node in
			// reverse inorder traversal
			else if ((val1 + val2) > target)
				done2 = false;

			// If any of the inorder traversals is over, then there is no pair
			// so return false
			if ((curr1==null && s1.isEmpty()) || ((curr2==null) && s2.isEmpty()))
				return false;
		}
	}

	// ************************************************************************************************

	// converts a given linked list representing a complete binary tree into the
	// linked representation of binary tree.
	static TreeNode convertList2Binary(ListNode head, TreeNode root)
	{
		// queue to store the parent nodes
		Queue<TreeNode> q = new Queue<TreeNode>();

		// Base Case
		if (head == null)
		{
			return null;
		}

		// 1.) The first node is always the root node, and add it to the queue
		root = new TreeNode(head.data);
		q.enqueue(root);

		// advance the pointer to the next node
		head = head.next;

		// until the end of linked list is reached, do the following steps
		while (head != null)
		{
			// 2.a) take the parent node from the q and remove it from q
			TreeNode parent = q.dequeue();

			// 2.c) take next two nodes from the linked list. We will add
			// them as children of the current parent node in step 2.b. Push them 
			// into the queue so that they will be parents to the future nodes
			TreeNode leftChild = null, rightChild = null;
			leftChild = new TreeNode(head.data);
			q.enqueue(leftChild);
			head = head.next;
			if (head != null)
			{
				rightChild = new TreeNode(head.data);
				q.enqueue(rightChild);
				head = head.next;
			}

			// 2.b) assign the left and right children of parent
			parent.left = leftChild;
			parent.right = rightChild;
		}
		return root;
	}

	// ************************************************************************************************

	/* Given a binary tree, print its nodes in reverse level order */
	static void reverseLevelOrder(TreeNode root)
	{
		Stack<TreeNode> S = new Stack<TreeNode>();
		LinkedList<TreeNode> Q = new LinkedList<TreeNode>();
		Q.add(root);

		// Do something like normal level order traversal order. Following are the
		// differences with normal level order traversal
		// 1) Instead of printing a node, we push the node to stack
		// 2) Right subtree is visited before left subtree
		while (!Q.isEmpty())
		{
			/* Dequeue node and make it root */
			root = Q.remove();
			S.push(root);

			/* Enqueue right child */
			if (root.right != null)
				Q.add(root.right); // NOTE: RIGHT CHILD IS ENQUEUED BEFORE LEFT

			/* Enqueue left child */
			if (root.left != null)
				Q.add(root.left);
		}

		// Now pop all items from stack one by one and print them
		while (!S.isEmpty())
		{
			root = S.pop();
			System.out.print(root.data + "\t");
		}
	}

	// ************************************************************************************************

	// A recursive function to construct Full from pre[] and post[]. 
	// preIndex is used to keep track of index in pre[].
	// l is low index and h is high index for the current subarray in post[]
	static int preAIndex=0;
	static TreeNode constructTreeUtil (int pre[], int post[], int l, int h)
	{
		// Base case
		if (l > h)
			return null;

		// The first node in preorder traversal is root. So take the node at
		// preIndex from preorder and make it root, and increment preIndex
		TreeNode root = new TreeNode (pre[preAIndex++]);

		// If the current subarray has only one element, no need to recur
		if (l == h)
			return root;

		// Search the next element of pre[] in post[]
		int i;
		for (i = l; i <= h; ++i)
			if (pre[preAIndex] == post[i])
				break;

		// Use the index of element found in postorder to divide postorder array in
		// two parts. Left subtree and right subtree
		if (i <= h)
		{
			root.left = constructTreeUtil (pre, post, l, i);
			root.right = constructTreeUtil (pre, post, i + 1, h-1);
		}

		return root;
	}

	// The main function to construct Full Binary Tree from given preorder and 
	// postorder traversals. This function mainly uses constructTreeUtil()
	static TreeNode constructTree (int pre[], int post[], int size)
	{
		return constructTreeUtil (pre, post, 0, size - 1);
	}

	// ************************************************************************************************

	static void findKthLargestInBST(TreeNode root, int k){
		if(root == null) return;
		int count = 1;
		boolean done = false;
		TreeNode current = root;
		Stack<TreeNode> s = new Stack<TreeNode>();
		while(!done){
			if(current!=null){
				s.push(current);
				current = current.left;
			}
			else
			{
				if(!s.isEmpty()){
					current = s.pop();
					if(count == k){
						System.out.println(current.data);
						break;
					}
					current = current.right;
					count++;
				}
				else
					done = true;
			}
		}
	}

	// ************************************************************************************************

	// A wrapper over VerticalSumUtil()
	private static void VerticalSum(TreeNode root) {

		// base case
		if (root == null) { return; }

		// Creates an empty hashMap hM
		HashMap<Integer, Integer> hM = new HashMap<Integer, Integer>();

		// Calls the VerticalSumUtil() to store the vertical sum values in hM
		VerticalSumUtil(root, 0, hM);

		// Prints the values stored by VerticalSumUtil()
		if (hM != null) {
			System.out.println(hM.entrySet());
		}
	}

	// Traverses the tree in InOrder form and builds a hashMap hM that
	// contains the vertical sum
	private static void VerticalSumUtil(TreeNode root, int hD, HashMap<Integer, Integer> hM) {

		// base case
		if (root == null) {  return; }

		// Store the values in hM for left subtree
		VerticalSumUtil(root.left, hD - 1, hM);

		// Update vertical sum for hD of this node
		int prevSum = (hM.get(hD) == null) ? 0 : hM.get(hD);
		hM.put(hD, prevSum + root.data);

		// Store the values in hM for right subtree
		VerticalSumUtil(root.right, hD + 1, hM);
	}

	// ************************************************************************************************

	static int index_ptr = 0;
	static void storeInorder (TreeNode node, int inorder[])
	{
		// Base Case
		if (node == null)
			return;

		/* first store the left subtree */
		storeInorder(node.left, inorder);

		/* Copy the root's data */
		inorder[index_ptr] = node.data;
		(index_ptr)++;  // increase index for next entry

		/* finally store the right subtree */
		storeInorder (node.right, inorder);
	}

	static /* A helper function to count nodes in a Binary Tree */
	int countNodes (TreeNode root)
	{
		if (root == null)
			return 0;
		return countNodes (root.left) + countNodes (root.right) + 1;
	}

	/* A helper function that copies contents of arr[] to Binary Tree. 
     This functon basically does Inorder traversal of Binary Tree and 
     one by one copy arr[] elements to Binary Tree nodes */
	static int bstIndex_ptr;
	static void arrayToBST (int[] arr, TreeNode root)
	{
		// Base Case
		if (root == null)
			return;

		/* first update the left subtree */
		arrayToBST (arr, root.left);

		/* Now update root's data and increment index */
		root.data = arr[bstIndex_ptr];
		(bstIndex_ptr)++;

		/* finally update the right subtree */
		arrayToBST (arr, root.right);
	}

	// This function converts a given Binary Tree to BST
	static void binaryTreeToBST (TreeNode root)
	{
		// base case: tree is empty
		if(root == null)
			return;

		/* Count the number of nodes in Binary Tree so that
         we know the size of temporary array to be created */
		int n = countNodes(root);

		// Create a temp array arr[] and store inorder traversal of tree in arr[]
		int[] arr = new int[n];
		storeInorder (root, arr);

		// Sort the array using library function for quick sort
		Arrays.sort(arr);

		// Copy array elements back to Binary Tree
		arrayToBST (arr, root);
	}

	// ************************************************************************************************

	//static TreeNode tempNode;
	// A simple function to print leaf nodes of a binary tree
	static void printLeaves(TreeNode  root)
	{
		if (root != null)
		{
			printLeaves(root.left);

			// Print it if it is a leaf node
			if ( root.left==null  &&  root.right==null )
				System.out.print(root.data + "\t");

			printLeaves(root.right);
		}
	}

	// A function to print all left boundry nodes, except a leaf node.
	// Print the nodes in TOP DOWN manner
	static void printBoundaryLeft(TreeNode root)
	{
		if (root != null)
		{
			if (root.left != null)
			{
				// to ensure top down order, print the node
				// before calling itself for left subtree
				System.out.print(root.data + "\t");
				printBoundaryLeft(root.left);
			}
			else if( root.right != null )
			{
				System.out.print(root.data + "\t");
				printBoundaryLeft(root.right);
			}
			// do nothing if it is a leaf node, this way we avoid
			// duplicates in output
		}
	}

	// A function to print all right boundry nodes, except a leaf node
	// Print the nodes in BOTTOM UP manner
	static void printBoundaryRight(TreeNode root)
	{
		if (root != null)
		{
			if ( root.right != null )
			{
				// to ensure bottom up order, first call for right
				//  subtree, then print this node
				printBoundaryRight(root.right);
				System.out.print(root.data + "\t");
			}
			else if ( root.left != null )
			{
				printBoundaryRight(root.left);
				System.out.print(root.data + "\t");
			}
			// do nothing if it is a leaf node, this way we avoid
			// duplicates in output
		}
	}


	// A function to do boundary traversal of a given binary tree
	static void printBoundary (TreeNode root)
	{
		if (root != null)
		{
			System.out.print(root.data + "\t");

			// Print the left boundary in top-down manner.
			printBoundaryLeft(root.left);

			// Print all leaf nodes
			printLeaves(root.left);
			printLeaves(root.right);

			// Print the right boundary in bottom-up manner
			printBoundaryRight(root.right);
		}
	}

	// ************************************************************************************************

	// The main function that constructs BST from pre[]
	static TreeNode constructTree ( int pre[], int size )
	{
		// Create a stack of capacity equal to size
		Stack<TreeNode> stack = new Stack<TreeNode>();

		// The first element of pre[] is always root
		TreeNode root = new TreeNode(pre[0]);

		// Push root
		stack.push(root);

		TreeNode temp;

		// Iterate through rest of the size-1 items of given preorder array
		for (int i = 1; i < size; ++i )
		{
			temp = null;

			/* Keep on popping while the next value is greater than
	           stack's top value. */
			while ( !stack.isEmpty() && pre[i] > stack.peek().data )
				temp = stack.pop();

			// Make this greater value as the right child and push it to the stack
			if ( temp != null)
			{
				temp.right = new TreeNode(pre[i]);
				stack.push(temp.right);
			}

			// If the next value is less than the stack's top value, make this value
			// as the left child of the stack's top node. Push the new node to stack
			else
			{
				stack.peek().left = new TreeNode(pre[i]);
				stack.push(stack.peek().left);
			}
		}
		return root;
	}

	// ************************************************************************************************

	// Function to find ceil of a given input in BST. If input is more
	// than the max key in BST, return -1
	static int Ceil(TreeNode root, int input)
	{
		// Base case
		if( root == null )
			return -1;

		// We found equal key
		if( root.data == input )
			return root.data;

		// If root's key is smaller, ceil must be in right subtree
		if( root.data < input )
			return Ceil(root.right, input);

		// Else, either left subtree or root has the ceil value
		int ceil = Ceil(root.left, input);
		return (ceil >= input) ? ceil : root.data;
	}

	// ************************************************************************************************

	// A utility function to check if a tree node has both left and right children
	static boolean hasBothChild(TreeNode temp)
	{
		return temp!=null && temp.left!=null && temp.right!=null;
	}

	static // Function to insert a new node in complete binary tree
	TreeNode insert(TreeNode root, int data, LinkedList<TreeNode> queue)
	{
		// Create a new node for given data
		TreeNode temp = new TreeNode(data);

		// If the tree is empty, initialize the root with new node.
		if (root == null)
			root = temp;

		else
		{
			// get the front node of the queue.
			TreeNode front = queue.getFirst();

			// If the left child of this front node doesn't exist, set the
			// left child as the new node
			if (front.left == null)
				front.left = temp;

			// If the right child of this front node doesn't exist, set the
			// right child as the new node
			else if (front.right == null)
				front.right = temp;

			// If the front node has both the left child and right child,
			// Dequeue() it.
			if (hasBothChild(front))
				queue.remove();
		}

		// Enqueue() the new node for later insertions
		queue.add(temp);
		return root;
	}

	// Standard level order traversal to test above function
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

			if (temp.right != null)
				queue.add(temp.right);
		}
	}

	// ************************************************************************************************
	/*Tree Isomorphism Problem

	Write a function to detect if two trees are isomorphic. Two trees are called isomorphic if one of them can be 
	obtained from other by a series of flips, i.e. by swapping left and right children of a number of nodes. 
	Any number of nodes at any level can have their children swapped. Two empty trees are isomorphic.*/

	static boolean isIsomorphic(TreeNode n1, TreeNode n2)
	{
		// Both roots are null, trees isomorphic by definition
		if (n1 == null && n2 == null)
			return true;

		// Exactly one of the n1 and n2 is null, trees not isomorphic
		if (n1 == null || n2 == null)
			return false;

		if (n1.data != n2.data)
			return false;

		// There are two possible cases for n1 and n2 to be isomorphic
		// Case 1: The subtrees rooted at these nodes have NOT been "Flipped".
		// Both of these subtrees have to be isomorphic, hence the &&
		// Case 2: The subtrees rooted at these nodes have been "Flipped"
		return
				(isIsomorphic(n1.left,n2.left) && isIsomorphic(n1.right,n2.right))||
				(isIsomorphic(n1.left,n2.right) && isIsomorphic(n1.right,n2.left));
	}

	static // ************************************************************************************************

	// Iterative Function to print all ancestors of a given key
	void printAncestorsIterative(TreeNode root, int key)
	{
		if (root == null) return;

		// Create a stack to hold ancestors
		Stack<TreeNode> stack = new Stack<TreeNode>();

		// Traverse the complete tree in postorder way till we find the key
		while (true)
		{
			// Traverse the left side. While traversing, push the nodes into
			// the stack so that their right subtrees can be traversed later
			while (root != null && root.data != key)
			{
				stack.push(root);  // push current node
				root = root.left;  // move to next node
			}

			// If the node whose ancestors are to be printed is found,
			// then break the while loop.
			if (root != null && root.data == key)
				break;

			// Check if right sub-tree exists for the node at top
			// If not then pop that node because we don't need this
			// node any more.
			if (stack.peek().right == null)
			{
				root = stack.pop();

				// If the popped node is right child of top, then remove the top
				// as well. Left child of the top must have processed before.
				// Consider the following tree for example and key = 3.  If we
				// remove the following loop, the program will go in an
				// infinite loop after reaching 5.
				//          1
				//        /   \
				//       2     3
				//         \
				//           4
				//             \
				//              5
				while (!stack.isEmpty() && stack.peek().right == root)
					root = stack.pop();
			}

			// if stack is not empty then simply set the root as right child
			// of top and start traversing right sub-tree.
			root = stack.isEmpty()? null: stack.peek().right;
		}

		// If stack is not empty, print contents of stack
		// Here assumption is that the key is there in tree
		while (!stack.isEmpty())
			System.out.print(stack.pop().data + "\t");
	}

	// ************************************************************************************************

	// Changes left pointers to work as previous pointers in converted DLL
	// The function simply does inorder traversal of Binary Tree and updates
	// left pointer using previously visited node
	static TreeNode pre = null;
	static void fixPrevPtr(TreeNode root)
	{ 
		if (root != null)
		{
			fixPrevPtr(root.left);
			root.left = pre;
			pre = root;
			fixPrevPtr(root.right);
		}
	}

	// Changes right pointers to work as next pointers in converted DLL
	static TreeNode nextp = null;
	static TreeNode fixNextPtr(TreeNode root)
	{	 
		// Find the right most node in BT or last node in DLL
		while (root!= null && root.right != null)
			root = root.right;

		// Start from the rightmost node, traverse back using left pointers.
		// While traversing, change right pointer of nodes.
		while (root!= null && root.left != null)
		{
			nextp = root;
			root = root.left;
			root.right = nextp;
		}

		// The leftmost node is head of linked list, return it
		return (root);
	}

	// The main function that converts BST to DLL and returns head of DLL
	static TreeNode BTToDLL(TreeNode root)
	{
		// Set the previous pointer
		fixPrevPtr(root);

		// Set the next pointer and return head of DLL
		return fixNextPtr(root);
	}

	// Traverses the DLL from left to right
	static void printList(TreeNode root)
	{
		while (root != null)
		{
			System.out.print(root.data + "\t");
			root = root.right;
		}
	}

	// ************************************************************************************************

	/* The main function that checks if two arrays a[] and b[] of size n construct
	  same BST. The two values 'min' and 'max' decide whether the call is made for
	  left subtree or right subtree of a parent element. The indexes i1 and i2 are
	  the indexes in (a[] and b[]) after which we search the left or right child.
	  Initially, the call is made for INT_MIN and INT_MAX as 'min' and 'max'
	  respectively, because root has no parent.
	  i1 and i2 are just after the indexes of the parent element in a[] and b[]. */
	static boolean isSameBSTUtil(int a[], int b[], int n, int i1, int i2, int min, int max)
	{
		int j, k;

		/* Search for a value satisfying the constraints of min and max in a[] and 
	      b[]. If the parent element is a leaf node then there must be some 
	      elements in a[] and b[] satisfying constraint. */
		for (j=i1; j<n; j++)
			if (a[j]>min && a[j]<max)
				break;
		for (k=i2; k<n; k++)
			if (b[k]>min && b[k]<max)
				break;

		/* If the parent element is leaf in both arrays */
		if (j==n && k==n)
			return true;

		/* Return false if any of the following is true
	      a) If the parent element is leaf in one array, but non-leaf in other.
	      b) The elements satisfying constraints are not same. We either search
	         for left child or right child of the parent element (decinded by min
	         and max values). The child found must be same in both arrays */
		if (((j==n)^(k==n)) || a[j]!=b[k])
			return false;

		/* Make the current child as parent and recursively check for left and right
	      subtrees of it. Note that we can also pass a[k] in place of a[j] as they
	      are both are same */
		return isSameBSTUtil(a, b, n, j+1, k+1, a[j], max) &&  // Right Subtree
				isSameBSTUtil(a, b, n, j+1, k+1, min, a[j]);    // Left Subtree
	}

	// A wrapper over isSameBSTUtil()
	static boolean isSameBST(int a[], int b[], int n)
	{
		return isSameBSTUtil(a, b, n, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	// ************************************************************************************************

	// Iterative method to find height of Bianry Tree
	static int treeHeight(TreeNode root)
	{
		// Base Case
		if (root == null)
			return 0;

		// Create an empty queue for level order tarversal
		LinkedList<TreeNode> q = new LinkedList<TreeNode>();

		// Enqueue Root and initialize height
		q.push(root);
		int height = 0;

		while (true)
		{
			// nodeCount (queue size) indicates number of nodes
			// at current lelvel.
			int nodeCount = q.size();
			if (nodeCount == 0)
				return height;

			height++;

			// Dequeue all nodes of current level and Enqueue all
			// nodes of next level
			while (nodeCount > 0)
			{
				TreeNode node = q.getFirst();
				q.pop();
				if (node.left != null)
					q.push(node.left);
				if (node.right != null)
					q.push(node.right);
				nodeCount--;
			}
		}
	}

	// ************************************************************************************************

	private static final String[] alphabet = {"", "a", "b", "c", "d", "e",
		"f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
		"s", "t", "u", "v", "w", "x", "v", "z"};

	private class Node {

		String dataString;
		Node left;
		Node right;

		Node(String dataString) {
			this.dataString = dataString;
			//Be default left and right child are null. 
		}

		public String getDataString() {
			return dataString;
		}
	}

	public static Node createTree(int data, String pString, int[] arr) {

		// Invalid input as alphabets maps from 1 to 26
		if (data > 26) 
			return null;

		// Parent String + String for this node
		String dataToStr = pString + alphabet[data];

		Node root = new TreeProblems().new Node(dataToStr);

		// if arr.length is 0 means we are done
		if (arr.length != 0) {
			data = arr[0];

			// new array will be from index 1 to end as we are consuming 
			// first index with this node
			int newArr[] = Arrays.copyOfRange(arr, 1, arr.length);

			// left child
			root.left = createTree(data, dataToStr, newArr);

			// right child will be null if size of array is 0 or 1
			if (arr.length > 1) {

				data = arr[0] * 10 + arr[1];

				// new array will be from index 2 to end as we 
				// are consuming first two index with this node
				newArr = Arrays.copyOfRange(arr, 2, arr.length);

				root.right = createTree(data, dataToStr, newArr);
			}
		}
		return root;
	}

	// To print out leaf nodes
	public static void printleaf(Node root) {
		if (root == null) 
			return;

		if (root.left == null && root.right == null) 
			System.out.print(root.getDataString() + "  ");

		printleaf(root.left);
		printleaf(root.right);
	}

	// The main function that prints all interpretations of array
	static void printAllInterpretations(int[] arr) {

		// Step 1: Create Tree
		Node root = createTree(0, "", arr);

		// Step 2: Print Leaf nodes
		printleaf(root);

		System.out.println();  // Print new line
	}

	// ************************************************************************************************

	// A utility function to search x in arr[] of size n
	static int search(int arr[], int x, int n)
	{
		for (int i = 0; i < n; i++)
			if (arr[i] == x)
				return i;
		return -1;
	}

	// Prints postorder traversal from given inorder and preorder traversals
	static void printPostOrder(int in[], int pre[], int n)
	{
		// The first element in pre[] is always root, search it
		// in in[] to find left and right subtrees
		int root = search(in, pre[0], n);

		// If left subtree is not empty, print left subtree
		if (root != 0)
			printPostOrder(in, Arrays.copyOfRange(pre, 1, pre.length), root);

		// If right subtree is not empty, print right subtree
		if (root != n-1)
			printPostOrder(Arrays.copyOfRange(in, root+1, in.length), Arrays.copyOfRange(pre, root+1, pre.length), n-(root+1));

		// Print root
		System.out.print(pre[0] + "\t");
	}

	// ************************************************************************************************

	// A recursive function to find depth of the deepest odd level leaf
	static int depthOfOddLeafUtil(TreeNode root,int level)
	{
		// Base Case
		if (root == null)
			return 0;

		// If this node is a leaf and its level is odd, return its level
		if (root.left==null && root.right==null && (level%2)!=0)
			return level;

		// If not leaf, return the maximum value from left and right subtrees
		return Math.max(depthOfOddLeafUtil(root.left, level+1),
				depthOfOddLeafUtil(root.right, level+1));
	}

	/* Main function which calculates the depth of deepest odd level leaf.
      This function mainly uses depthOfOddLeafUtil() */
	static int depthOfOddLeaf(TreeNode root)
	{
		int level = 1;
		return depthOfOddLeafUtil(root, level);
	}

	// ************************************************************************************************

	/* Recursive function which checks whether all leaves are at same level */
	static int leafLevel;
	static boolean checkUtil(TreeNode root, int level)
	{
		// Base case
		if (root == null)  return true;

		// If a leaf node is encountered
		if (root.left == null && root.right == null)
		{
			// When a leaf node is found first time
			if (leafLevel == 0)
			{
				leafLevel = level; // Set first found leaf's level
				return true;
			}

			// If this is not first leaf node, compare its level with
			// first leaf's level
			return (level == leafLevel);
		}

		// If this node is not leaf, recursively check left and right subtrees
		return checkUtil(root.left, level+1) && checkUtil(root.right, level+1);
	}

	/* The main function to check if all leafs are at same level.
	   It mainly uses checkUtil() */
	static boolean check(TreeNode root)
	{
		int level = 0;
		return checkUtil(root, level);
	}

	// ************************************************************************************************

	// Recursive function to print left view of a binary tree.
	static int max_level;
	static void leftViewUtil(TreeNode root, int level)
	{
		// Base Case
		if (root==null)  return;

		// If this is the first node of its level
		if (max_level < level)
		{
			System.out.print(root.data + "\t");
			max_level = level;
		}

		// Recur for left and right subtrees
		leftViewUtil(root.left, level+1);
		leftViewUtil(root.right, level+1);
	}

	// A wrapper over leftViewUtil()
	static void leftView(TreeNode root)
	{
		leftViewUtil(root, 1);
	}

	static int max_level_1;
	static void rightViewUtil(TreeNode root, int level)
	{
		// Base Case
		if (root==null)  return;

		// If this is the first node of its level
		if (max_level_1 < level)
		{
			System.out.print(root.data + "\t");
			max_level_1 = level;
		}

		// Recur for left and right subtrees
		rightViewUtil(root.right, level+1);
		rightViewUtil(root.left, level+1);

	}

	// A wrapper over leftViewUtil()
	static void rightView(TreeNode root)
	{
		rightViewUtil(root, 1);
	}

	// ************************************************************************************************

	// Recursive function to add all greater values in every node
	static int sumG;
	static void modifyBSTUtil(TreeNode root)
	{
		// Base Case
		if (root == null)  return;

		// Recur for right subtree
		modifyBSTUtil(root.right);

		// Now *sum has sum of nodes in right subtree, add
		// root.data to sum and update root.data
		sumG = sumG + root.data;
		root.data = sumG;

		// Recur for left subtree
		modifyBSTUtil(root.left);
	}

	// A wrapper over modifyBSTUtil()
	static void modifyBST(TreeNode root)
	{
		modifyBSTUtil(root);
	}

	// ************************************************************************************************

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {

		TreeNode nextPopulateRoot = new TreeNode(10);
		nextPopulateRoot.left = new TreeNode(8);
		nextPopulateRoot.right = new TreeNode(12);
		nextPopulateRoot.left.left  = new TreeNode(3);
		
		BTreePrinter.printNode(nextPopulateRoot);
		
		System.out.println();

		// Populates nextRight pointer in all nodes
		populateNext(nextPopulateRoot);

		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5); 

		sumOfNodes(root);
		System.out.println("Yo Darling the Sum is: " + sumGivenLevel(root,1));
		System.out.println("Sum is: " + sum);
		System.out.println(binaryTreeBFS(root, 3));
		System.out.println(binaryTreeDFS(root, 3));
		System.out.println();

		/* Print inorder traversal of the input tree */
		System.out.println("Inorder traversal of the constructed tree is: ");
		inOrder(root);
		System.out.println();

		System.out.println("Paths are: ");
		printPaths(root);
		System.out.println();

		System.out.println("Without Stack");
		MorrisTraversal(root);
		System.out.println();
		morrisTraversalPreorder(root);
		System.out.println();
		iterativePreorder(root);
		System.out.println();
		iterativePostOrder(root);
		System.out.println();
		postOrderIterative(root);
		System.out.println();


		System.out.println("Using Stack");
		inOrderStack(root);

		System.out.println("\n");
		// SumTree Construction
		TreeNode sumRoot = new TreeNode(26);
		sumRoot.left = new TreeNode(10);
		sumRoot.right = new TreeNode(3);
		sumRoot.left.left = new TreeNode(4);
		sumRoot.left.right = new TreeNode(6);
		sumRoot.right.right = new TreeNode(3);

		System.out.println("Is the given tree a SumTree??\n" + isSumTree(sumRoot));
		System.out.println();

		TreeNode connectRoot = new TreeNode(10);
		connectRoot.left = new TreeNode(8);
		connectRoot.right = new TreeNode(2);
		connectRoot.left.left = new TreeNode(3);
		connectRoot.right.right = new TreeNode(90);

		// Populates nextRight pointer in all nodes
		connectRoot = connect(null);

		// Let us check the values of nextRight pointers
		/*System.out.println("Following are populated nextRight pointers in the tree (-1 is printed if there is no nextRight) \n");
	    System.out.println("nextRight of " + connectRoot.data + " is " +  connectRoot.nextRight != null? connectRoot.nextRight.data: -1);
	    System.out.println("nextRight of %d is %d \n" + "\t" +  connectRoot.left.data + "\t" + connectRoot.left.nextRight != null? connectRoot.left.nextRight.data: -1);
	    System.out.println("nextRight of %d is %d \n" + "\t" +  connectRoot.right.data + "\t" +  connectRoot.right.nextRight != null? connectRoot.right.nextRight.data: -1);
	    System.out.println("nextRight of %d is %d \n" + "\t" +  connectRoot.left.left.data + "\t" +  connectRoot.left.left.nextRight != null? connectRoot.left.left.nextRight.data: -1);
	    System.out.println("nextRight of %d is %d \n" + "\t" +  connectRoot.right.right.data + "\t" +  connectRoot.right.right.nextRight != null? connectRoot.right.right.nextRight.data: -1);*/

		System.out.println();

		TreeNode diaRoot = new TreeNode(1);
		diaRoot.left = new TreeNode(2);
		diaRoot.right = new TreeNode(3);
		diaRoot.left.left  = new TreeNode(4);
		diaRoot.left.right = new TreeNode(5);

		System.out.println("Diameter of the given binary tree is: " + diameter(diaRoot));
		System.out.println();

		int ele[] = { 20, 8, 22, 4, 12, 10, 14 };
		int i;
		TreeNode kSmallestProbroot = null;

		/* Creating the tree given in the above diagram */
		kSmallestProbroot = binary_search_tree(kSmallestProbroot, ele,ele.length);

		/*  It should print the sorted array */
		for(i = 1; i <= ele.length; i++)
		{
			System.out.println("kth smallest elment for k = " + i + " is " + k_smallest_element(kSmallestProbroot, i));
			System.out.println("kth largest elment for k = " + i + " is " + k_largest_element(kSmallestProbroot, i));
		}
		System.out.println();

		System.out.println(getLevel(root, 5));
		System.out.println();

		System.out.println(printAncestors(root, 5));
		System.out.println();

		TreeNode printK1K2 = kSmallestProbroot;
		Print(printK1K2, 10, 22);
		System.out.println();

		TreeNode isCompleteRoot  = new TreeNode(1);
		isCompleteRoot.left = new TreeNode(2);
		isCompleteRoot.right = new TreeNode(3);
		isCompleteRoot.left.left = new TreeNode(4);
		isCompleteRoot.left.right = new TreeNode(5);
		isCompleteRoot.right.right = new TreeNode(6);

		System.out.println("Is it complete???\t" + isCompleteBT(root));
		System.out.println();

		TreeNode correctTreeRoot = new TreeNode(6);
		correctTreeRoot.left        = new TreeNode(10);
		correctTreeRoot.right       = new TreeNode(2);
		correctTreeRoot.left.left  = new TreeNode(1);
		correctTreeRoot.left.right = new TreeNode(3);
		correctTreeRoot.right.right = new TreeNode(12);
		correctTreeRoot.right.left = new TreeNode(7);

		correctBST(correctTreeRoot);
		MorrisTraversal(correctTreeRoot);

		int in[] = {2,3,4,5,6};
		int pre[] = {5,3,2,4,6};
		int len = in.length;
		TreeNode buildTreeRoot = buildTree(in, pre, 0, len - 1);
		System.out.println();

		System.out.println("Inorder traversal of the Built tree is: ");
		inOrder(buildTreeRoot);
		System.out.println();

		System.out.println("Merging of BST");
		TreeNode root1 = null, root2 = null;

		/* Let us create the following tree as first tree
	            3
	          /  \
	         1    5
		 */
		root1 = new TreeNode(3);
		root1.left = new TreeNode(1);
		root1.right = new TreeNode(5);

		/* Let us create the following tree as second tree
	            4
	          /  \
	         2    6
		 */
		root2 = new TreeNode(4);
		root2.left = new TreeNode(2);
		root2.right = new TreeNode(6);
		root2.right.left = new TreeNode(5);

		// Print sorted nodes of both trees
		merge(root1, root2);
		System.out.println();

		TreeNode MaxSumPathRoot = null;

		/* Constructing tree given in the above figure */
		MaxSumPathRoot = new TreeNode(10);
		MaxSumPathRoot.left = new TreeNode(-2);
		MaxSumPathRoot.right = new TreeNode(7);
		MaxSumPathRoot.left.left = new TreeNode(8);
		MaxSumPathRoot.left.right = new TreeNode(-4);

		System.out.println("Following are the nodes on the maximum sum path: ");
		int sum = maxSumPath(MaxSumPathRoot);
		System.out.println();
		System.out.println("Sum of the nodes is: " + sum);
		System.out.println();

		int inorder[] = {5, 10, 40, 30, 28};
		int inlen = inorder.length;
		TreeNode specialInorderBuildTreeRoot = buildTree(inorder, 0, inlen - 1);
		inOrder(specialInorderBuildTreeRoot);
		System.out.println();

		TreeNode specialPreOrderConstructTreeRoot = null;

		/* Constructing tree given in the above figure
	          10
	         /  \
	        30   15
	       /  \
	      20   5 */
		int preA[] = {10, 30, 20, 5, 15};
		char preLN[] = {'N', 'N', 'L', 'L', 'L'};
		int n = preA.length;

		// construct the above tree
		specialPreOrderConstructTreeRoot = constructTree (preA, preLN, n);

		// Test the constructed tree
		System.out.println("Following is Inorder Traversal of the Constructed Binary Tree: ");
		inOrder (specialPreOrderConstructTreeRoot);
		System.out.println();

		int preOnlyOneChild[] = {8, 3, 5, 7, 6};
		int size = preOnlyOneChild.length;
		System.out.println(hasOnlyOneChild(preOnlyOneChild,size));

		TreeNode sumEqualRoot =  new TreeNode(15);
		sumEqualRoot.left = new TreeNode(10);
		sumEqualRoot.right = new TreeNode(20);
		sumEqualRoot.left.left = new TreeNode(8);
		sumEqualRoot.left.right = new TreeNode(12);
		sumEqualRoot.right.left = new TreeNode(16);
		sumEqualRoot.right.right = new TreeNode(25);

		int target = 20;
		if (isPairPresent(sumEqualRoot, target) == false)
			System.out.println("No such values are found\n");

		System.out.println();
		System.out.println("Linked List to a Complete Binary Tree");

		linkedList.LinkedList a = new linkedList.LinkedList();
		a.addFirst(36);
		a.addFirst(30);
		a.addFirst(25);
		a.addFirst(15);
		a.addFirst(12);
		a.addFirst(10);
		ListNode head = (ListNode) a.getHead();
		TreeNode treeToListRoot = null;
		treeToListRoot = convertList2Binary(head,treeToListRoot);
		inOrder(treeToListRoot);
		System.out.println();

		System.out.println("Reverse");
		reverseLevelOrder(treeToListRoot);
		System.out.println();

		int preFull[] = {1, 2, 4, 8, 9, 5, 3, 6, 7};
		int postFull[] = {8, 9, 4, 5, 2, 6, 7, 3, 1};
		int preSize = preFull.length;

		TreeNode fullConstructionRoot = constructTree(preFull, postFull, preSize);

		System.out.println("Inorder traversal of the constructed tree: ");
		inOrder(fullConstructionRoot);
		System.out.println();

		System.out.println("Kth largest");
		findKthLargestInBST(buildTreeRoot, 2);
		System.out.println();

		TreeNode verticalRoot = new TreeNode(1);
		verticalRoot.left = new TreeNode(2);
		verticalRoot.right = new TreeNode(3);
		verticalRoot.left.left = new TreeNode(4);
		verticalRoot.left.right = new TreeNode(5);
		verticalRoot.right.left = new TreeNode(6);
		verticalRoot.right.right = new TreeNode(7);

		System.out.println("Following are the values of vertical sums with "
				+ "the positions of the columns with respect to root ");
		VerticalSum(verticalRoot);
		System.out.println();

		TreeNode binaryToBSTRoot = new TreeNode(10);
		binaryToBSTRoot.left = new TreeNode(30);
		binaryToBSTRoot.right = new TreeNode(15);
		binaryToBSTRoot.left.left = new TreeNode(20);
		binaryToBSTRoot.right.right = new TreeNode(5);

		// convert Binary Tree to BST
		binaryTreeToBST (binaryToBSTRoot);

		System.out.println("Following is Inorder Traversal of the converted BST: ");
		inOrder(binaryToBSTRoot);
		System.out.println();

		TreeNode printBoundaryRoot = new TreeNode(20);
		printBoundaryRoot.left = new TreeNode(8);
		printBoundaryRoot.left.left = new TreeNode(4);
		printBoundaryRoot.left.right = new TreeNode(12);
		printBoundaryRoot.left.right.left = new TreeNode(10);
		printBoundaryRoot.left.right.right = new TreeNode(14);
		printBoundaryRoot.right = new TreeNode(22);
		printBoundaryRoot.right.right = new TreeNode(25);

		TreeProblems t = new TreeProblems();
		//t.tempNode = printBoundaryRoot;
		printBoundary(printBoundaryRoot);
		System.out.println();

		int constructTreePre[] = {10, 5, 1, 7, 40, 50};
		int constructTreeSize = constructTreePre.length;

		TreeNode constructTreeRoot = constructTree(constructTreePre, constructTreeSize);

		System.out.println("Inorder traversal of the constructed tree: ");
		inOrder(constructTreeRoot);
		System.out.println();

		TreeNode ceilRoot = new TreeNode(8);
		ceilRoot.left = new TreeNode(4);
		ceilRoot.right = new TreeNode(12);
		ceilRoot.left.left = new TreeNode(2);
		ceilRoot.left.right = new TreeNode(6);
		ceilRoot.right.left = new TreeNode(10);
		ceilRoot.right.right = new TreeNode(14);

		for(int itr = 0; itr < 16; itr++)
			System.out.println(itr + "\t" + Ceil(ceilRoot, itr));

		System.out.println();

		TreeNode compTreeRoot = null;
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();

		for(int itr = 1; itr <= 12; ++itr)
			compTreeRoot = insert(compTreeRoot, itr, queue);

		levelOrder(compTreeRoot);
		System.out.println();
		inOrder(compTreeRoot);
		System.out.println();

		TreeNode n1 = new TreeNode(1);
		n1.left        = new TreeNode(2);
		n1.right       = new TreeNode(3);
		n1.left.left  = new TreeNode(4);
		n1.left.right = new TreeNode(5);
		n1.right.left  = new TreeNode(6);
		n1.left.right.left = new TreeNode(7);
		n1.left.right.right = new TreeNode(8);

		TreeNode n2 = new TreeNode(1);
		n2.left         = new TreeNode(3);
		n2.right        = new TreeNode(2);
		n2.right.left   = new TreeNode(4);
		n2.right.right   = new TreeNode(5);
		n2.left.right   = new TreeNode(6);
		n2.right.right.left = new TreeNode(8);
		n2.right.right.right = new TreeNode(7);

		if (isIsomorphic(n1, n2) == true)
			System.out.println("Yes");
		else
			System.out.println("No");

		TreeNode iterativePrintAncestors = new TreeNode(1);
		iterativePrintAncestors.left = new TreeNode(2);
		iterativePrintAncestors.right = new TreeNode(3);
		iterativePrintAncestors.left.left = new TreeNode(4);
		iterativePrintAncestors.left.right = new TreeNode(5);
		iterativePrintAncestors.right.left = new TreeNode(6);
		iterativePrintAncestors.right.right = new TreeNode(7);
		iterativePrintAncestors.left.left.left = new TreeNode(8);
		iterativePrintAncestors.left.right.right = new TreeNode(9);
		iterativePrintAncestors.right.right.left = new TreeNode(10);

		System.out.println("Following are all keys and their ancestors\n");
		for (int key = 1; key <= 10; key++)
		{
			System.out.print(key + ":");
			printAncestorsIterative(iterativePrintAncestors, key);
			System.out.println();
		}

		TreeNode tree2List = new TreeNode(10);
		tree2List.left        = new TreeNode(12);
		tree2List.right       = new TreeNode(15);
		tree2List.left.left  = new TreeNode(25);
		tree2List.left.right = new TreeNode(30);
		tree2List.right.left = new TreeNode(36);

		System.out.println("\n\t\tInorder Tree Traversal\n\n");
		inOrder(tree2List);

		TreeNode dllHead = BTToDLL(tree2List);

		System.out.println("\n\n\t\tDLL Traversal\n\n");
		printList(dllHead);

		System.out.println();

		/*int aArray[] = {8, 3, 6, 1, 4, 7, 10, 14, 13};
		int bArray[] = {8, 10, 14, 3, 6, 4, 1, 7, 13};*/
		int aArray[] = {1,2,3};
		int bArray[] = {2,1,3};
		int nSize = aArray.length;
		if(isSameBST(aArray, bArray, nSize))
			System.out.println("BSTs are same");
		else
			System.out.println("BSTs not same");


		TreeNode heightIterativeroot = new TreeNode(1);
		heightIterativeroot.left = new TreeNode(2);
		heightIterativeroot.right = new TreeNode(3);
		heightIterativeroot.left.left = new TreeNode(4);
		heightIterativeroot.left.right = new TreeNode(5);

		System.out.println("Height of tree is " + treeHeight(heightIterativeroot));

		// aacd(1,1,3,4) amd(1,13,4) kcd(11,3,4)
		// Note : 1,1,34 is not valid as we don't have values corresponding
		// to 34 in alphabet
		int[] arr = {1, 1, 3, 4};
		printAllInterpretations(arr);

		// aaa(1,1,1) ak(1,11) ka(11,1)
		int[] arr2 = {1, 1, 1};
		printAllInterpretations(arr2);

		// bf(2,6) z(26)
		int[] arr3 = {2, 6};
		printAllInterpretations(arr3);

		// ab(1,2), l(12)  
		int[] arr4 = {1, 2};
		printAllInterpretations(arr4);

		// a(1,0} j(10)  
		int[] arr5 = {1, 0};
		printAllInterpretations(arr5);

		// "" empty string output as array is empty
		int[] arr6 = {};
		printAllInterpretations(arr6);

		// abba abu ava lba lu
		int[] arr7 = {1, 2, 2, 1};
		printAllInterpretations(arr7);

		// Let us create tree shown in thirdt example
		TreeNode checkRoot = new TreeNode(12);
		checkRoot.left = new TreeNode(5);
		checkRoot.left.left = new TreeNode(3);
		checkRoot.left.right = new TreeNode(9);
		checkRoot.left.left.left = new TreeNode(1);
		checkRoot.left.right.left = new TreeNode(1);
		if (check(checkRoot))
			System.out.println("Leaves are at same level\n");
		else
			System.out.println("Leaves are not at same level\n");

		TreeNode leftViewRoot = new TreeNode(12);
		leftViewRoot.left = new TreeNode(10);
		leftViewRoot.right = new TreeNode(30);
		leftViewRoot.right.left = new TreeNode(25);
		leftViewRoot.right.right = new TreeNode(40);

		leftView(leftViewRoot);
		rightView(leftViewRoot);
		System.out.println();

		//TreeNode root = null;
		BinaryTree t1 = new BinaryTree();	    
		t1.insert(50);
		t1.insert(30);
		t1.insert(20);
		t1.insert(40);
		t1.insert(70);
		t1.insert(60);
		t1.insert(80);

		modifyBST(t1.getRoot());

		// print inoder tarversal of the modified BST
		inOrder(t1.getRoot());

		int inPost[] = {4, 2, 5, 1, 3, 6};
		int prePost[] =  {1, 2, 4, 5, 3, 6};
		System.out.println("Postorder traversal ");
		printPostOrder(inPost, prePost, prePost.length);
		System.out.println();
		
		System.out.println(depthOfOddLeaf(t1.getRoot()));
	}
}