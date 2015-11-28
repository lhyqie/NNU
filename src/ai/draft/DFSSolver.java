package ai.draft;

import java.util.Stack;
import java.util.HashSet;

public class DFSSolver extends Solver {
	Game game = null;
	HashSet<State> set = null;
	Stack<State> stack = null; // double-ended queue
	int maxDepth = -1; //max depth of search
	
	DFSSolver(Game game, int maxDepth){
		this.game = game;
		this.maxDepth = maxDepth;
		
		set = new HashSet<State>();     // hash set to avoid revisited state
		set.add(game.start);
		
		stack = new Stack<State>();      // queue for BFS
		stack.push(game.start);
		
	}
	
	public State nextMove() {
		return null;
	}

	public boolean hasNext() {
		return false;
	}

}
