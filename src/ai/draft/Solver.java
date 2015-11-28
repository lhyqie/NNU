package ai.draft;

public abstract class Solver {
	
	public abstract State nextMove();
	
	public abstract boolean hasNext();
}
