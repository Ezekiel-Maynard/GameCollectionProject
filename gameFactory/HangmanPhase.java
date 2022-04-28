package gameFactory;


public class HangmanPhase {
	GameData data = new GameData();
	final static int START = 0;
	final static int CHECK = 1;
	final static int GUESS = 2;
	final static int WIN = 3;
	
	int state = WIN;
	
	
	public HangmanPhase() {
		state = START;
	}
	
	public void takeTurn() {
		if(state == START) {
			GameData.playGame();
			state = GUESS;
		}
		else if(state == GUESS) {
			GameData.guess();
			state = CHECK;
			takeTurn();
		}
		else if(state == CHECK) {
			if(GameData.check()) {
				state = WIN; 
				takeTurn();
			}
			else {
				state = GUESS;
			}
		}
		else if(state == WIN) {
			System.out.println("You guessed the word! You win!");
			System.exit(0);
		}
	}
	
	// Need win condition, and checking for the win condition. Almost there :)
	
}