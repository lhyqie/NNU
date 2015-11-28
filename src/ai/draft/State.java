package ai.draft;

public class State implements Cloneable{
	
	int[][] m;
	
	char direction = ' ';  	// which direction previous state comes from
	State prevState = null; // pointer to the previous state
	
	int x;  // row index of 0
	int y;  // column index of 0
	
	int depth = 0;  //depth in search path for DFS
	
	public State(int [][] m){
		this.m = m;
		for (int i = 0; i < m.length; i++) {
	        for (int j = 0; j < m[0].length; j++) {
	            if (m[i][j] == 0) {
	                x = i;
	                y = j;
	            }
	        }
		}
		if(x == -1 && y == -1) {
			try {
				throw new Exception("invalid input, no zero in the matrix!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// create a new State based on current state by swapping the element zero with 4 possible directions
	// return null if the move is invalid, otherwise return the new State after move
	public State moveLeft(){
		State newState = null;
		if(y > 0){
			newState = (State)(this.clone());
			newState.swap(x, y-1, x, y);
			newState.y = y-1;
		}
		return newState;
	}
	
	public State moveRight(){
		State newState = null;
		if(y < m[0].length - 1){
			newState = (State)(this.clone());
			newState.swap(x, y+1, x, y);
			newState.y = y+1;
		}
		return newState;
	}
	
	public State moveUp(){
		State newState = null;
		if(x > 0){
			newState = (State)(this.clone());
			newState.swap(x-1, y, x, y);
			newState.x = x-1;
		}
		return newState;
	}
	
	public State moveDown(){
		State newState = null;
		if(x < m.length - 1){
			newState = (State)(this.clone());
			newState.swap(x+1, y, x, y);
			newState.x = x+1;
		}
		return newState;
	}
	
	
	// deep copy of a state
	@Override
	public Object clone(){
		int newM[][] = new int[m.length][m[0].length];
		for(int i = 0; i < m.length; i++){
			for(int j = 0; j < m[0].length; j++){
				newM[i][j] = m[i][j];
			}
		}
		return new State(newM);
	}
	
	@Override
	public boolean equals(Object other){
		State o = (State)(other);
		for(int i = 0; i < m.length; i++){
			for(int j = 0; j < m[0].length; j++){
				if(m[i][j] != o.m[i][j]) return false;
			}
		}
		return true;
	}
	
	@Override
	public int hashCode(){
		int h = 0;
		for(int i = 0; i < m.length; i++){
			for(int j = 0; j < m[0].length; j++){
				h = (h * 33 + m[i][j]);
			}
		}
		return 0;
	}
	
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < m.length; i++){
			for(int j = 0; j < m[0].length; j++){
				sb.append(m[i][j]);
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	private void swap(int x1, int y1, int x2, int y2){
		int t = m[x1][y1];
		m[x1][y1] = m[x2][y2];
		m[x2][y2] = t;
	}
}