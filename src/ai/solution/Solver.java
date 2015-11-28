package ai.solution;

public abstract class Solver {
	
	public abstract State nextMove();
	
	public abstract boolean hasNext();
}
