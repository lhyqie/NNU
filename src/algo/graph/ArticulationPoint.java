package algo.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ArticulationPoint {
	
	static int time = 0;
	static final int NIL = -1;
    static int parent[] = null;
	static boolean visited[] = null;
	
	//return index of articulation point
	public static int findAPPoint(MyGraph graph){
		int n = graph.n;
		
		time = 0;
		parent  = new int[n];
		visited = new boolean[n];
        
		int disc[] = new int[n];
        int low[] = new int[n];
        
        boolean ap[] = new boolean[n]; // To store articulation points
        
        // Initialize parent and visited, and ap(articulation point)
        // arrays
        for (int i = 0; i < n; i++)
        {
            parent[i] = NIL;
            visited[i] = false;
            ap[i] = false;
        }
 
        // Call the recursive helper function to find articulation
        // points in DFS tree rooted with vertex 'i'
        for (int i = 0; i < n; i++)
            if (visited[i] == false)
                APUtil(graph, i, visited, disc, low, parent, ap);
 
        // Now ap[] contains articulation points, print them
        for (int i = 0; i < n; i++) {
            if (ap[i] == true){
                return i;
            }
		}
        return -1;
	}
	
	 // A recursive function that find articulation points using DFS
    // u --> The vertex to be visited next
    // visited[] --> keeps tract of visited vertices
    // disc[] --> Stores discovery times of visited vertices
    // parent[] --> Stores parent vertices in DFS tree
    // ap[] --> Store articulation points
    private static void APUtil(MyGraph graph, int u, boolean visited[], int disc[],
                int low[], int parent[], boolean ap[])
    {
 
        // Count of children in DFS Tree
        int children = 0;
 
        // Mark the current node as visited
        visited[u] = true;
 
        // Initialize discovery time and low value
        disc[u] = low[u] = ++time;
 
        // Go through all vertices adjacent to this
        Iterator<Integer> i = graph.adj[u].iterator();
        while (i.hasNext())
        {
            int v = i.next();  // v is current adjacent of u
 
            // If v is not visited yet, then make it a child of u
            // in DFS tree and recur for it
            if (!visited[v])
            {
                children++;
                parent[v] = u;
                APUtil(graph, v, visited, disc, low, parent, ap);
 
                // Check if the subtree rooted with v has a connection to
                // one of the ancestors of u
                low[u]  = Math.min(low[u], low[v]);
 
                // u is an articulation point in following cases
 
                // (1) u is root of DFS tree and has two or more chilren.
                if (parent[u] == NIL && children > 1)
                    ap[u] = true;
 
                // (2) If u is not root and low value of one of its child
                // is more than discovery value of u.
                if (parent[u] != NIL && low[v] >= disc[u])
                    ap[u] = true;
            }
 
            // Update low value of u for parent function calls.
            else if (v != parent[u])
                low[u]  = Math.min(low[u], disc[v]);
        }
    }
    
    public static void main(String[] args) {
    	int apIndex = -1; 
    	
    	MyDGraph g1 = new MyDGraph(new ArrayList<String>(Arrays.asList("1->0","0->2","2->1","0->3","3->4")));
		g1.explain();
		System.out.println();
		apIndex = findAPPoint(g1); 
		if(apIndex == -1){
			System.out.println("no articulation point");
		}else{
			System.out.println("find an articulation point : " + g1.nodeLabels[apIndex]);
		}
        System.out.println();
        System.out.println();
        
        
        MyDGraph g2 = new MyDGraph(new ArrayList<String>(Arrays.asList("0->1","1->2","2->3")));
		g2.explain();
		System.out.println();
		
		apIndex = findAPPoint(g2); 
		if(apIndex == -1){
			System.out.println("no articulation point");
		}else{
			System.out.println("find an articulation point : " + g2.nodeLabels[apIndex]);
		}
        System.out.println();
        System.out.println();
        
        
        MyDGraph g3 = new MyDGraph(new ArrayList<String>(Arrays.asList("0->1","1->2","2->0","1->3","1->4","1->6","3->5","4->5")));
		g3.explain();
		System.out.println();
		
		apIndex = findAPPoint(g3); 
		if(apIndex == -1){
			System.out.println("no articulation point");
		}else{
			System.out.println("find an articulation point : " + g3.nodeLabels[apIndex]);
		}

        
	}
}
