package ai.solution;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;


public class BFSSolver extends Solver {
	Game game = null;
	HashSet<State> set = null;
	Queue<State> q = null;
	
	BFSSolver(Game game){
		this.game = game;
		set = new HashSet<State>();     // hash set to avoid revisited state
		set.add(game.start);
		
		q = new LinkedList<State>();      // queue for BFS
		q.add(game.start);
	}
	
	public State nextMove() {
		State top = q.poll();
		set.add(top);
		
		// keep searching...
		State nextState = null;
		
		nextState = top.moveLeft();
		if(nextState != null && !set.contains(nextState)) {
			q.offer(nextState);
			nextState.direction = '¡û';
			nextState.prevState = top;
		}
		
		nextState = top.moveRight();
		if(nextState != null && !set.contains(nextState)) {
			q.offer(nextState);
			nextState.direction = '¡ú';
			nextState.prevState = top;
		}
		
		nextState = top.moveUp();
		if(nextState != null && !set.contains(nextState)) {
			q.offer(nextState);
			nextState.direction = '¡ü';
			nextState.prevState = top;
		}
		
		nextState = top.moveDown();
		if(nextState != null && !set.contains(nextState)) {
			q.offer(nextState);
			nextState.direction = '¡ý';
			nextState.prevState = top;
		}
		
		return top;
	}

	public boolean hasNext() {
		return !q.isEmpty();
	}
	
}
