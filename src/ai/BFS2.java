package ai;

public class BFS2 {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        
        int [][] start = {{1,5,2},
	        			  {4,0,3},
	        			  {6,7,8}};
        
        int [][] end =	 {{1,5,2},
			  			  {4,3,8},
			  			  {6,7,0}};
        
        Game game = new Game(new State(start), new State(end));
        game.BFS_solve();
        
	}

	
}

class Game{
	public Game(State start, State end){
		this.start = start;
		this.end = end;
		this.x = -1;
		this.y = -1;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++) {
                if (start.m[i][j] == 0) {
                    x = i;
                    y = j;
                }
            }
        }
        if(x == -1 && y == -1){
        	try {
				throw new Exception("invalid input, no zero in the matrix!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }	    
	}
	
	int x;  // row index of 0
	int y;  // column index of 0
	
	State start;
	State end;
	
	
	public void BFS_solve(){
		
	}
}

class State{
	
	int[][] m;
	
	public State(int [][] m){
		this.m = m;
	}
}
