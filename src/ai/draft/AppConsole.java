package ai.draft;

import java.util.Stack;

public class AppConsole {
		
	public static void main(String[] args) {
				
	    int [][] startM = {{1,5,2},
	        			   {4,0,3},
	        			   {6,7,8}};
        
//	    int [][] endM = {{1,5,2},
//	    				 {0,4,3},
//	    				 {6,7,8}};

//	    int [][] endM = {{5,2,0},
//				 		 {1,4,3},
//				 		 {6,7,8}};

	    int [][] endM = {{5,4,2},
		 		 		 {1,0,3},
		 		 		 {6,7,8}};

        
    	Game game = new Game(new State(startM), new State(endM), 3, 3);
    	
    	Solver solver =  new BFSSolver(game);
		 //new AStarSolver(game);
		 //new DFSSolver(game, 5);
    	
    	
    	System.out.println("Game initializing... \n");
    	System.out.println("Game start state :");
    	System.out.println(game.start);
    	System.out.println("Game target state :");
    	System.out.println(game.end);
    	
    	System.out.println("----------------------------------------------------\nbegin searching.... \n");
    	
    	State lastState = null;  // if a solution is found, lastState will be assigned
    	
    	while(solver.hasNext()){
    		State nextState = solver.nextMove();
    		printStatePair(nextState.prevState, nextState);
    		if(game.end.equals(nextState)){
    			lastState = nextState;
    			break;
    		}
    	}
    	if(lastState != null){
    		System.out.println("=====================================================");
    		System.out.println("Found solution : \n ");
    		printSolution(lastState);
    	}else{
    		System.out.println("No solution found");
    	}

	}
	
	public static void printStatePair(State from, State to){
		if(from == null || to == null) return;
		for(int i = 0; i < from.m.length; i++){
			for(int j = 0; j < from.m[0].length; j++)
				System.out.print(from.m[i][j]);
			if( i == from.m.length / 2) System.out.print("\t"+to.direction+"\t");
			else System.out.print("\t   \t");
			for(int j = 0; j < to.prevState.m[0].length; j++)
				System.out.print(to.m[i][j]);
			System.out.println();
		}
		System.out.println();
	}
	
	public static void printSolution(State lastState){
		Stack<State> stack = new Stack<State>(); // use stack to reverse the order
		State p = lastState;
		while(p != null) {
			stack.push(p);
			p = p.prevState;
		}
		
		System.out.println("move operations");
		while(!stack.isEmpty()) {
			State top = stack.pop();
			printStatePair(top.prevState, top);
		}
	}
	
}
