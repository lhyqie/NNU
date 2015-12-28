package algo.graph;

import java.util.ArrayList;
import java.util.Arrays;

public class DirectedAcyclicGraph {
	
	private static final int WHITE = 1;  // unvisited
	private static final int GRAY = 2;   // discovered
	private static final int BLACK = 3;  // explored
	private static int color [] = null;
	
	/**
	 * if a directed graph is acyclic (DAG)
	 * @return
	 * idea: do DFS, if a GRAY node is visited, there is a cycle
	 */
	public static boolean isDAG(MyDGraph dgraph){
		int n = dgraph.n;
		color = new int[n];
		for (int i = 0; i < n; i++) {
			color[i] = WHITE;
		}
		for (int i = 0; i < n; i++) {
			if(!isDAG_at(dgraph, i)) return false;
		}
		return true;
	}
	
	/**
	 * recursively visit node
	 */
	private static boolean isDAG_at(MyDGraph dgraph, int id){
		if(id < 0 || id >= dgraph.n) throw new IllegalArgumentException("id invalid!");
		if(color[id] == BLACK) return true;
		if(color[id] == GRAY) return false;
		color[id] = GRAY;
		for (int v : dgraph.adj[id]) {
			if(!isDAG_at(dgraph, v)) return false;
		}
		color[id] = BLACK;
		return true;
	}
	
	public static void main(String[] args) {
		MyDGraph g1 = new MyDGraph(new ArrayList<String>(Arrays.asList("0->1","0->2","1->2","2->3","3->1")));	
		g1.explain();
		System.out.println(" G is DAG ?  :" + isDAG(g1));
		System.out.println();
		System.out.println();
		
		MyDGraph g2 = new MyDGraph(new ArrayList<String>(Arrays.asList("0->1","0->2","1->2","2->3")));	
		g2.explain();
		System.out.println(" G is DAG ?  :" + isDAG(g2));
	}
}
