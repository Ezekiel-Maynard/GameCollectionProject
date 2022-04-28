package gameFactory;

public class TicTacToe {
	private boolean pXTurn;
	private int[][] spaces;
	private CommandState commandState;
	//Constructor for model
	public TicTacToe() {
		spaces = new int[3][3];
		pXTurn = true;
		commandState = new CommandState();
	}
	//Helper methods
	public boolean ispXTurn() {
		return pXTurn;
	}
	public boolean ispOTurn() {
		return !pXTurn;
	}
	public boolean isEmpty(int r, int c) {
		return spaces[r][c] == 0;
	}
	public boolean isX(int r, int c) {
		return spaces[r][c] == 1;
	}
	public boolean isO(int r, int c) {
		return spaces[r][c] == 2;
	}
	public boolean isGameOver() {
		if(xWon() || oWon()) return true;
		//Check if all the spaces are filled. If one isn’t the game isn’t over
		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 3; col++) {
				if(spaces[row][col] == 0) return false;
			}
		}
		//Tie game
		return true;
	}
	public boolean xWon() {
		//Check rows
        if(spaces[0][0] == 1 && spaces[0][1] == 1 && spaces[0][2] == 1) return true;
        if(spaces[1][0] == 1 && spaces[1][1] == 1 && spaces[1][2] == 1) return true;
        if(spaces[2][0] == 1 && spaces[2][1] == 1 && spaces[2][2] == 1) return true;
        //Check columns
        if(spaces[0][0] == 1 && spaces[1][0] == 1 && spaces[2][0] == 1) return true;
        if(spaces[0][1] == 1 && spaces[1][1] == 1 && spaces[2][1] == 1) return true;
        if(spaces[0][2] == 1 && spaces[1][2] == 1 && spaces[2][2] == 1) return true;
        //Check diagonals
        if(spaces[0][0] == 1 && spaces[1][1] == 1 && spaces[2][2] == 1) return true;
        if(spaces[0][2] == 1 && spaces[1][1] == 1 && spaces[2][0] == 1) return true;
        
        return false;
	}
	public boolean oWon() {
		//Check rows
        if(spaces[0][0] == 2 && spaces[0][1] == 2 && spaces[0][2] == 2) return true;
        if(spaces[1][0] == 2 && spaces[1][1] == 2 && spaces[1][2] == 2) return true;
        if(spaces[2][0] == 2 && spaces[2][1] == 2 && spaces[2][2] == 2) return true;
        //Check columns
        if(spaces[0][0] == 2 && spaces[1][0] == 2 && spaces[2][0] == 2) return true;
        if(spaces[0][1] == 2 && spaces[1][1] == 2 && spaces[2][1] == 2) return true;
        if(spaces[0][2] == 2 && spaces[1][2] == 2 && spaces[2][2] == 2) return true;
        //Check diagonals
        if(spaces[0][0] == 2 && spaces[1][1] == 2 && spaces[2][2] == 2) return true;
        if(spaces[0][2] == 2 && spaces[1][1] == 2 && spaces[2][0] == 2) return true;
        //Otherwise, there is no line
        return false;
	}
	public void placeX(int r, int c) {
		commandState.executeCommand(new XCommand(this, r, c));
	}
	public void placeO(int r, int c) {
		commandState.executeCommand(new OCommand(this, r, c));
	}
	//XCOMMAND CLASS
	public class XCommand implements Command{
		private TicTacToe model;
		private int prev;
		private boolean prevTurn;
		private int row;
		private int col;
		
		private XCommand(TicTacToe model, int row, int col) {
			this.model = model;
			this.col = col;
			this.row = row;
			//copy previous value
			this.prev = model.spaces[row][col];
			this.prevTurn = model.pXTurn;
		}
		
		@Override
		public void execute() {
			model.spaces[row][col] = 1;
			model.pXTurn = false;
		}

		@Override
		public void undo() {
			model.spaces[row][col] = prev;
			model.pXTurn = prevTurn;
		}
	}
	//OCOMMAND CLASS
	public class OCommand implements Command{
		private TicTacToe model;
		private int prev;
		private boolean prevTurn;
		private int row;
		private int col;
		
		private OCommand(TicTacToe model, int row, int col) {
			this.model = model;
			this.col = col;
			this.row = row;
			//copy previous value
			this.prev = model.spaces[row][col];
			this.prevTurn = model.pXTurn;
		}
		@Override
		public void execute() {
			model.spaces[row][col] = 2;
			model.pXTurn = true;
		}

		@Override
		public void undo() {
			model.spaces[row][col] = prev;
			model.pXTurn = prevTurn;
		}

	}
	//UNDO
	public boolean canUndo() {
		return commandState.isUndoAvailable();
	}
	public void undo() {
		commandState.undo();
	}

}
