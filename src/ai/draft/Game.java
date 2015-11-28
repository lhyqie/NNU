package ai.draft;

public class Game{
	
	int nRows;   // size of board
	int nCols;  
	
	State start;  //start state and end state
	State end;
	
	public Game(State start, State end, int nRows, int nCols){
		if(start.m.length != nRows || start.m[0].length != nCols 
				|| end.m.length != nRows || end.m[0].length != nCols){
			try {
				throw new Exception("invalid input, board size disagree!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			this.start = start;
			this.end = end;
			this.nRows = nRows;
			this.nCols = nCols;
		}
	}
}