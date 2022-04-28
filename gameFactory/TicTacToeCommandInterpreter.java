package gameFactory;

public class TicTacToeCommandInterpreter {
	
	private enum ParseState {START, PLAYING, DONE}
	
	private TicTacToe model;
	private ParseState state;
	
	public TicTacToeCommandInterpreter() {
		model = new TicTacToe();
		state = ParseState.START;
		startGame();
	}
	
	private void startGame() {
		System.out.println("Tic-Tac-Toe Game");
		System.out.println("----------------");
		System.out.println();
		listCommands();
		System.out.println();
		showGrid();
		state = ParseState.PLAYING;
	}
	
	private void listCommands() {
		System.out.println("x row col - Put an X on the grid at the specified row and column");
		System.out.println("o row col - Put an O on the grid at the specified row and column");
		System.out.println("u - Undo the last move");
		System.out.println();
	}
	
	public void parseCommand(String command) throws Exception {
		switch(state) {
		case START:
			break;
		case PLAYING:
			parseGameplayCommand(command);
			break;
		case DONE:
			break;
		default:
			throw new Exception("Parser Exception");
		}
	}
	
	private void parseGameplayCommand(String command) throws Exception {
		char commandType = command.charAt(0);
		String[] tokens = command.split(" ");
		
		switch(commandType) {
		case 'x':
			if(!model.ispXTurn()) {
				System.out.println("It isn't the X player's turn.");
				System.out.println();
				return;
			}			
			try {
				int row = Integer.parseInt(tokens[1]);
				int col = Integer.parseInt(tokens[2]);
				if(!model.isEmpty(row, col)) {
					System.out.println("That space is already filled.");
					System.out.println();
					return;	
				}
				model.placeX(row, col);
				if(model.isGameOver()) {
					endGame();
				} else {
					showGrid();
				}
			} catch (Exception e) {
				System.out.println("Invalid command input.");
				System.out.println();
			}
			break;
		case 'o':
			if(!model.ispOTurn()) {
				System.out.println("It isn't the O player's turn.");
				System.out.println();
				return;
			}
			try {
				int row = Integer.parseInt(tokens[1]);
				int col = Integer.parseInt(tokens[2]);
				if(!model.isEmpty(row, col)) {
					System.out.println("That space is already filled.");
					System.out.println();
					return;	
				}
				model.placeO(row, col);
				showGrid();
			} catch (Exception e) {
				//Ignore exceptions basically
				System.out.println("Invalid command input.");
				System.out.println();
			}	
			break;
		case 'u':
			if(model.canUndo()) {
				model.undo();
				System.out.println("The most recent move has been undone.");
				System.out.println();
				showGrid();
			} else {
				System.out.println("Can't undo any more moves.");
				System.out.println();	
			}
			break;
		default:
			System.out.println("Invalid command.");
			listCommands();
			System.out.println();
		}
	}
	
	
	private void showGrid() {		
		System.out.format( " %s | %s | %s%n", getGridCharacter(0, 0), getGridCharacter(0, 1), getGridCharacter(0, 2));
		System.out.println("-----------");
		System.out.format( " %s | %s | %s%n", getGridCharacter(1, 0), getGridCharacter(1, 1), getGridCharacter(1, 2));
		System.out.println("-----------");
		System.out.format( " %s | %s | %s%n", getGridCharacter(2, 0), getGridCharacter(2, 1), getGridCharacter(2, 2));
		System.out.println();
		
		
		if(model.ispXTurn()) {
			System.out.println("It is currently the X player's turn. Enter a command:");
			System.out.println();
		} else {
			System.out.println("It is currently the O player's turn. Enter a command:");
			System.out.println();
		}
	}
	
	private String getGridCharacter(int row, int col) {
		if(model.isEmpty(row, col)) {
			return " ";
		} else if(model.isX(row, col)) {
			return "X";
		}
		return "O";
	}
	
	
	private void endGame() {
		if(model.xWon()) {
			System.out.println("The X player won. Restart the program to play again.");
			System.out.println();			
		} else if(model.oWon()) {
			System.out.println("The O player won. Restart the program to play again.");
			System.out.println();		
		} else {
			System.out.println("Tie Game. Restart the program to play again.");
			System.out.println();
		}
		
		state = ParseState.DONE;
		System.exit(0);
	}
	
	

}