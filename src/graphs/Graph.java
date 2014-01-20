package graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {

	static int V;    // No. of vertices
	ArrayList<ArrayList<Integer>> adj;

	Graph(int V){
		this.V = V;
		adj = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<V;i++)
			adj.add(new ArrayList<Integer>());
	}

	void addEdge(int v, int w)
	{
		//adj.add(e);
		adj.get(v).add(w); // Add w to v's list.
	}

	// ************************************************************************************************

	void DFSUtil(int v, boolean visited[]){
		// Mark the current node as visited and print it
		visited[v] = true;
		System.out.print(v + "\t");

		// Recur for all the vertices adjacent to this vertex
		for(int i = 0; i < adj.get(v).size(); ++i)
			if(!visited[adj.get(v).get(i)])
				DFSUtil(adj.get(v).get(i), visited);
	}

	void DFS(int v)
	{
		// Mark all the vertices as not visited
		boolean[] visited = new boolean[V];
		for(int i = 0; i < V; i++)
			visited[i] = false;

		// Call the recursive helper function to print DFS traversal
		DFSUtil(v, visited);
	}

	// ************************************************************************************************

	void BFS(int s)
	{
		// Mark all the vertices as not visited
		boolean []visited = new boolean[V];
		for(int i = 0; i < V; i++)
			visited[i] = false;

		// Create a queue for BFS
		Queue<Integer> queue = new ArrayDeque<Integer>();

		// Mark the current node as visited and enqueue it
		visited[s] = true;
		queue.add(s);

		while(!queue.isEmpty())
		{
			// Dequeue a vertex from queue and print it
			s = queue.poll();
			System.out.print(s + "\t");
			//queue.poll();

			// Get all adjacent vertices of the dequeued vertex s
			// If a adjacent has not been visited, then mark it visited
			// and enqueue it
			for(int i = 0; i < adj.get(s).size(); ++i)
			{
				if(!visited[adj.get(s).get(i)])
				{
					visited[adj.get(s).get(i)] = true;
					queue.add(adj.get(s).get(i));
				}
			}
		}
	}

	// ************************************************************************************************

	//static final int ROW = 3;
	//static final int COL = 3;

	// A function to check if a given cell (row, col) can be included in DFS
	static boolean isSafe(int M[][], int row, int col, boolean visited[][])
	{
		return (row >= 0) && (row < M.length) &&     // row number is in range
				(col >= 0) && (col < M[0].length) &&     // column number is in range
				(M[row][col]!=0 && !visited[row][col]); // value is 1 and not yet visited
	}

	// A utility function to do DFS for a 2D boolean matrix. It only considers
	// the 8 neighbors as adjacent vertices
	static void DFS(int M[][], int row, int col, boolean visited[][]){
		// These arrays are used to get row and column numbers of 8 neighbors 
		// of a given cell
		final int rowNbr[] = {-1, -1, -1,  0, 0,  1, 1, 1};
		final int colNbr[] = {-1,  0,  1, -1, 1, -1, 0, 1};

		// Mark this cell as visited
		visited[row][col] = true;

		// Recur for all connected neighbors
		for (int k = 0; k < 8; ++k)
			if (isSafe(M, row + rowNbr[k], col + colNbr[k], visited) )
				DFS(M, row + rowNbr[k], col + colNbr[k], visited);
	}

	// The main function that returns count of islands in a given boolean
	// 2D matrix
	static int countIslands(int M[][])
	{
		// Make a bool array to mark visited cells.
		// Initially all cells are unvisited
		boolean visited[][]  = new boolean[M.length][M[0].length];

		// Initialize count as 0 and traverse through the all cells of
		// given matrix
		int count = 0;
		for (int i = 0; i < M.length; ++i)
			for (int j = 0; j < M[0].length; ++j)
				if (M[i][j]!=0 && !visited[i][j]) // If a cell with value 1 is not
				{                              // visited yet, then new island found
					DFS(M, i, j, visited);     // Visit all cells in this island.
					count++;                   // and increment island count
				}

		return count;
	}

	static int countIslandscactus(int M[][])
	{
		// Make a bool array to mark visited cells.
		// Initially all cells are unvisited
		boolean visited[][]  = new boolean[M.length][M[0].length];

		// Initialize count as 0 and traverse through the all cells of
		// given matrix
		int count = 0;
		for (int i = 0; i < M.length; ++i)
			for (int j = 0; j < M[0].length; ++j)
				if (M[i][j]!=0 && !visited[i][j]) // If a cell with value 1 is not
				{                              // visited yet, then new island found
					DFS(M, i, j, visited);     // Visit all cells in this island.
					count++;                   // and increment island count
				}

		return count;
	}

	// ************************************************************************************************

	boolean isCyclicUtil(int v, boolean visited[], boolean[] recStack)
	{
		if(!visited[v])
		{
			// Mark the current node as visited and part of recursion stack
			visited[v] = true;
			recStack[v] = true;

			// Recur for all the vertices adjacent to this vertex
			for(int i = 0; i < adj.get(v).size(); ++i)
			{
				if ( !visited[adj.get(v).get(i)] && isCyclicUtil(adj.get(v).get(i), visited, recStack) )
					return true;
				else if (recStack[adj.get(v).get(i)])
					return true;
			}

		}
		recStack[v] = false;  // remove the vertex from recursion stack
		return false;
	}

	// Returns true if the graph contains a cycle, else false.
	boolean isCyclic()
	{
		// Mark all the vertices as not visited and not part of recursion
		// stack
		boolean[] visited = new boolean[V];
		boolean[] recStack = new boolean[V];
		for(int i = 0; i < V; i++)
		{
			visited[i] = false;
			recStack[i] = false;
		}

		// Call the recursive helper function to detect cycle in different
		// DFS trees
		for(int i = 0; i < V; i++)
			if (isCyclicUtil(i, visited, recStack))
				return true;

		return false;
	}

	// ************************************************************************************************

	// A BFS based function to check whether d is reachable from s.
	boolean isReachable(int s, int d)
	{
		// Base case
		if (s == d)
			return true;

		// Mark all the vertices as not visited
		boolean []visited = new boolean[V];
		for (int i = 0; i < V; i++)
			visited[i] = false;

		// Create a queue for BFS
		LinkedList<Integer> queue = new LinkedList<Integer>();

		// Mark the current node as visited and enqueue it
		visited[s] = true;
		queue.add(s);

		// it will be used to get all adjacent vertices of a vertex
		//list<int>::iterator i;

		while (!queue.isEmpty())
		{
			// Dequeue a vertex from queue and print it
			s = queue.peek();
			System.out.print(s + "\t");
			queue.pop();

			// Get all adjacent vertices of the dequeued vertex s
			// If a adjacent has not been visited, then mark it visited
			// and enqueue it
			for (int i = 0; i != adj.get(s).size(); ++i)
			{
				// If this adjacent node is the destination node, then return true
				if (adj.get(s).get(i) == d)
					return true;

				// Else, continue to do BFS
				if (!visited[adj.get(s).get(i)])
				{
					visited[adj.get(s).get(i)] = true;
					queue.add(adj.get(s).get(i));
				}
			}
		}

		return false;
	}

	// ************************************************************************************************

	// This function returns true if graph G[V][V] is Bipartite, else false
	static boolean isBipartite(int G[][], int src)
	{
		// Create a color array to store colors assigned to all veritces. Vertex 
		// number is used as index in this array. The value '-1' of  colorArr[i] 
		// is used to indicate that no color is assigned to vertex 'i'.  The value 
		// 1 is used to indicate first color is assigned and value 0 indicates 
		// second color is assigned.
		int colorArr[] = new int[G[0].length];
		for (int i = 0; i < V; ++i)
			colorArr[i] = -1;

		// Assign first color to source
		colorArr[src] = 1;

		// Create a queue (FIFO) of vertex numbers and enqueue source vertex
		// for BFS traversal
		LinkedList <Integer> q = new LinkedList<Integer>();
		q.push(src);

		// Run while there are vertices in queue (Similar to BFS)
		while (!q.isEmpty())
		{
			// Dequeue a vertex from queue ( Refer http://goo.gl/35oz8 )
			int u = q.remove();

			// Find all non-colored adjacent vertices
			for (int v = 0; v < V; ++v)
			{
				// An edge from u to v exists and destination v is not colored
				if (G[u][v]!=0 && colorArr[v] == -1)
				{
					// Assign alternate color to this adjacent v of u
					colorArr[v] = 1 - colorArr[u];
					q.push(v);
				}

				//  An edge from u to v exists and destination v is colored with
				// same color as u
				else if (G[u][v]!=0 && colorArr[v] == colorArr[u])
					return false;
			}
		}

		// If we reach here, then all adjacent vertices can be colored with 
		// alternate color
		return true;
	}

	// ********************************************************************************************

	@SuppressWarnings("unchecked")
	static int dfs(int[][] buf, boolean[] flag, List nodes, int start, int cur){
		if(flag[cur]){
			if (cur == start && nodes.size() != 2 && (Integer)Collections.min(nodes) == start)
				return nodes.size();
			else
				return -1;
		}
		flag[cur] = true;
		nodes.add(cur);
		int ret = 0;
		for (int adj: buf[cur]){
			ret = dfs(buf, flag, nodes, start, adj);
			if (ret > 0)
				return ret;
		}
		nodes.remove(0);
		flag[cur] = false;
		return -1;
	}

	static int get_cactus(int[][] buf){
		int ret; int tmp;
		int n = buf.length;
		ret = 1;
		for (int i=0;i<n;i++){
			boolean flag[] = new boolean[n];
			List nodes = new ArrayList(); 
			tmp = dfs(buf, flag, nodes, i, i);
			if(tmp > 0)
				ret *= tmp;
		}
		return ret;
	}   


	public static void main(String[] args) {
		// Create a graph given in the above diagram
		Graph g = new Graph(4);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 3);

		System.out.println("Following is Depth First Traversal (starting from vertex 2)");
		g.DFS(2);
		System.out.println();

		System.out.println("Following is Breadth First Traversal (starting from vertex 2)");
		g.BFS(2);
		System.out.println();

		/*int M[][]= {  {1, 1, 0, 0, 0},
	            {0, 1, 0, 0, 1},
	            {1, 0, 0, 1, 1},
	            {0, 0, 0, 0, 0},
	            {1, 0, 1, 0, 1}
	        };*/
		/*int M[][]= {
	    		{1, 0, 0},
	            {0, 1, 0},
	            {0, 0, 1}
	        };*/

		int M[][] = {
				{0,1,1,1,0,0,0,0},
				{1,0,0,0,1,0,0,0},
				{1,0,0,0,0,0,0,0},
				{1,0,0,0,1,0,0,0},
				{0,1,0,1,0,0,0,0},
				{0,0,0,0,0,0,1,1},
				{0,0,0,0,0,1,0,1},
				{0,0,0,0,0,1,1,0}
		};
		System.out.println("Number of islands is: " + countIslands(M));
		System.out.println();

		if(g.isCyclic())
			System.out.println("Graph contains cycle");
		else
			System.out.println("Graph doesn't contain cycle");
		System.out.println();

		int u = 1, v = 3;
		if(g.isReachable(u, v))
			System.out.println("There is a path from " + u + " to " + v);
		else
			System.out.println("There is no path from " + u + " to " + v);

		u = 3; v = 1;
		if(g.isReachable(u, v))
			System.out.println("There is a path from " + u + " to " + v);
		else
			System.out.println("There is no path from " + u + " to " + v);

		int G[][] = {{0, 1, 0, 1},
				{1, 0, 1, 0},
				{0, 1, 0, 1},
				{1, 0, 1, 0}
		};

		if(isBipartite(G, 0))
			System.out.println("Yes");
		else
			System.out.println("No");


		Graph cactusG = new Graph(8);
		cactusG.addEdge(0, 1);
		cactusG.addEdge(0, 2);
		cactusG.addEdge(1, 0);
		cactusG.addEdge(1, 3);
		cactusG.addEdge(2, 0);
		cactusG.addEdge(2, 3);
		cactusG.addEdge(3, 1);
		cactusG.addEdge(3, 2);
		cactusG.addEdge(3, 6);
		cactusG.addEdge(3, 7);
		cactusG.addEdge(4, 6);
		cactusG.addEdge(5, 6);
		cactusG.addEdge(6, 3);
		cactusG.addEdge(6, 4);
		cactusG.addEdge(6, 5);
		cactusG.addEdge(6, 7);
		cactusG.addEdge(7, 3);
		cactusG.addEdge(7, 6);

		int cactusGM[][] = {
				{0,1,1,0,0,0,0,0},
				{1,0,0,1,0,0,0,0},
				{1,0,0,1,0,0,0,0},
				{0,1,1,0,0,0,1,1},
				{0,0,0,0,0,0,1,0},
				{0,0,0,0,0,0,1,0},
				{0,0,0,1,1,1,0,1},
				{0,0,0,1,0,0,1,0}
		};

		System.out.println("Number of islands is: " + countIslandscactus(cactusGM));
		System.out.println();
		int[][] edges = {{1,2}, {0,3}, {0,3}, {1,2,6,7}, {6}, {6}, {3,4,5,7}, {3,6}};
		int ret = get_cactus(edges);
		System.out.println(ret);
	}
}
