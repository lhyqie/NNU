package ai.solution;

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
		State top = stack.pop();
		set.add(top);
		
		// keep searching...
		State nextState = null;
		
		
		nextState = top.moveLeft();
		if(nextState != null && !set.contains(nextState) && top.depth < maxDepth) {
			nextState.depth = top.depth + 1;
			nextState.direction = '¡û';
			nextState.prevState = top;
			stack.push(nextState);
		}
		
		nextState = top.moveRight();
		if(nextState != null && !set.contains(nextState) && top.depth < maxDepth) {
			nextState.depth = top.depth + 1;
			nextState.direction = '¡ú';
			nextState.prevState = top;
			stack.push(nextState);
		}
		
		nextState = top.moveUp();
		if(nextState != null && !set.contains(nextState) && top.depth < maxDepth) {
			nextState.depth = top.depth + 1;
			nextState.direction = '¡ü';
			nextState.prevState = top;
			stack.push(nextState);
		}
		
		nextState = top.moveDown();
		if(nextState != null && !set.contains(nextState) && top.depth < maxDepth) {
			nextState.depth = top.depth + 1;
			nextState.direction = '¡ý';
			nextState.prevState = top;
			stack.push(nextState);
		}
		
		
		return top;
	}

	public boolean hasNext() {
		return !stack.isEmpty();
	}

}
