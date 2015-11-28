package ai.draft;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStarSolver extends Solver {
	
	Game game = null;
	HashSet<State> set = null;
	PriorityQueue<State> pq = null;
	
	// compare two states by their hamming distance to game.end
	Comparator<State> stateComparator = new Comparator<State>(){
		@Override
		public int compare(State s1, State s2) {
			return hammingDistance(s1, game.end) - hammingDistance(s2, game.end);
		}
	};
	
	AStarSolver(Game game){
		this.game = game;
		set = new HashSet<State>();     // hash set to avoid revisited state
		set.add(game.start);
		
		pq = new PriorityQueue<State>(stateComparator);      // priority queue for heuristic search
		pq.add(game.start);
	}
	
	
	// hamming distance https://en.wikipedia.org/wiki/Hamming_distance
	private int hammingDistance(State s1, State s2){ 
		int dist = 0;
		for(int i = 0; i < s1.m.length; i++){
			for(int j = 0; j < s1.m[0].length; j++){
				if(s1.m[i][j] != s2.m[i][j]) dist += 1;
			}
		}
		return dist;
	}

	
	@Override
	public State nextMove() {
		return null;
	}

	@Override
	public boolean hasNext() {
		return false;
	}

}
