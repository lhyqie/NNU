package ai.draft;

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
		return null;
	}

	public boolean hasNext() {
		return false;
	}
	
}
