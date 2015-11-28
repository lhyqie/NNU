package ai.draft;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class AppGUI {
	
	private static final int btnW = 30;
	private static final int btnH = 30;
	
	Game game = null;
	Solver solver = null;
	
	Button[][] btns = null;
	
	Font font = new Font("Arial", Font.BOLD, 15);
	JMultilineLabel startLabel = null; 
	JMultilineLabel endLabel = null; 
	JButton nextMove = new JButton("Next Move");
	JPanel headerPanel = new JPanel();
	JPanel bodyPanel = new JPanel();
	JFrame frame = new JFrame("Game");
	
	private Stack<State> stack = new Stack<State>(); // use stack to reverse the order
	 
	public AppGUI(Game game, Solver solver){
		this.game = game;
		this.solver = solver;
		btns = new Button[game.nRows][game.nCols];
		
		startLabel = new JMultilineLabel("Start \n"+game.start.toString());
		endLabel = new JMultilineLabel("End \n"+game.end.toString());
		
		nextMove.addActionListener(new NextMoveListener());
		headerPanel.add(startLabel);
		headerPanel.add(nextMove);
		headerPanel.add(endLabel);
		
	    bodyPanel.setLayout(new GridLayout(3, 3));
	    
		for(int i = 0; i < game.nRows; i++){
			for(int j = 0; j < game.nCols; j++){
				btns[i][j] = new Button(""+game.start.m[i][j]);
				btns[i][j].setSize(btnW, btnH);
				btns[i][j].setFont(font);;
				bodyPanel.add(btns[i][j]);
			}
		}
		
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLayout(new BorderLayout());
		frame.setVisible(true);
	    frame.add(headerPanel, BorderLayout.NORTH);
	    frame.add(bodyPanel, BorderLayout.CENTER);
	    JFrame.setDefaultLookAndFeelDecorated(true);
	    
	    State lastState = null;  // if a solution is found, lastState will be assigned
    	
    	while(solver.hasNext()){
    		State nextState = solver.nextMove();
    		AppConsole.printStatePair(nextState.prevState, nextState);
    		if(game.end.equals(nextState)){
    			lastState = nextState;
    			break;
    		}
    	}
    	if(lastState != null){
    		System.out.println("Found solution : \n ");
    		JOptionPane.showMessageDialog(null, "Found solution! Click next move to play");
    		AppConsole.printSolution(lastState);
    		// save the path to solution in a stack
    		State p = lastState;
    		while(p != null) {
    			stack.push(p);
    			p = p.prevState;
    		}
    		stack.pop(); // pop the first state
    	}else{
    		System.out.println("No solution found");
    		JOptionPane.showMessageDialog(null, "No solution found");
    	}
	}
	
	class NextMoveListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!stack.isEmpty()) {
				State nextState = stack.pop();
				for(int i = 0; i < game.nRows; i++){
					for(int j = 0; j < game.nCols; j++){
						btns[i][j].setLabel(""+nextState.m[i][j]);
					}
				}
			}
		}
	}
	
	class JMultilineLabel extends JTextArea{
	    private static final long serialVersionUID = 1L;
	    public JMultilineLabel(String text){
	        super(text);
	        setEditable(false);  
	        setCursor(null);  
	        setOpaque(false);  
	        setFocusable(false);  
	        setFont(UIManager.getFont("Label.font"));      
	        setWrapStyleWord(true);  
	        setLineWrap(true);
	        setAlignmentX(CENTER_ALIGNMENT);
	        setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    }
	} 
	
	public static void main(String[] args) {
		
	    int [][] startM = {{1,5,2},
 			   			   {4,0,3},
 			               {6,7,8}};

//		int [][] endM = {{1,5,2},
//						 {0,4,3},
//						 {6,7,8}};

		int [][] endM = {{5,2,0},
				 		 {1,4,3},
				 		 {6,7,8}};

//		int [][] endM = {{5,4,2},
//				 		 {1,0,3},
//				 		 {6,7,8}};
		
		Game game = new Game(new State(startM), new State(endM), 3, 3);
    	Solver solver =  new BFSSolver(game);
		AppGUI gui = new AppGUI(game, solver); 

	}
	
}



