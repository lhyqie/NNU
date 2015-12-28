package algo.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class BiconnectedGraph {
	
	/*
	 * How to find if a given graph is Biconnected or not?
		A connected graph is Biconnected if it is connected and doesn¡¯t have any Articulation Point. We mainly need to check two things in a graph.
		1) The graph is connected.
		2) There is not articulation point in graph.
	 */
	public static boolean isBiconnected(MyGraph graph){
		int apIndex = ArticulationPoint.findAPPoint(graph);
		//check whether the given graph is connected or not.
		for (int i = 0; i < graph.n; i++){
			if (ArticulationPoint.visited[i] == false) return false;
		}
		
		if(apIndex != - 1) {
			return false;
		}
		
		return true;
		
	}
	
//	public static void print_two_biconnected_component(MyGraph graph){
//		int apIndex = ArticulationPoint.findAPPoint(graph);
//		// Now check whether the given graph is connected or not.
//		for (int i = 0; i < graph.n; i++) {
//			if (ArticulationPoint.visited[i] == false){
//				System.out.println("graph is not connected");
//				return;
//			}
//		}	
//		
//		if(apIndex != - 1) {
//			
//		}else{
//			System.out.println("graph is biconnected");
//		}
//	}
	
	public static void main(String[] args) {
		
		MyGraph g1 = new MyDGraph(new ArrayList<String>(Arrays.asList("0->1")));
        if (isBiconnected(g1))
            System.out.println("Graph is biconnected");
        else{
            System.out.println("Graph is not biconnected");
        }
        System.out.println();
        System.out.println();
        
        MyGraph g2 = new MyDGraph(new ArrayList<String>(Arrays.asList("1->0", "0->2", "2->1", "0->3", "3->4", "2->4")));
        if (isBiconnected(g2))
        	System.out.println("Graph is biconnected");
        else{
        	System.out.println("Graph is not biconnected");
        }
        System.out.println();
        System.out.println();
        
        MyGraph g3 = new MyDGraph(new ArrayList<String>(Arrays.asList("0->1", "1->2")));
        if (isBiconnected(g3))
        	System.out.println("Graph is biconnected");
        else
        	System.out.println("Graph is not biconnected");
        System.out.println();
        System.out.println();
        
        
        MyGraph g4 = new MyDGraph(new ArrayList<String>(Arrays.asList("1->0", "0->2", "2->1", "0->3", "3->4")));
        if (isBiconnected(g4))
        	System.out.println("Graph is biconnected");
        else
        	System.out.println("Graph is not biconnected");
        System.out.println();
        System.out.println();

        MyGraph g5 = new MyDGraph(new ArrayList<String>(Arrays.asList("0->1", "1->2", "2->0")));
        if (isBiconnected(g5))
        	System.out.println("Graph is biconnected");
        else
        	System.out.println("Graph is not biconnected");
        System.out.println();
        System.out.println();

	}
}
