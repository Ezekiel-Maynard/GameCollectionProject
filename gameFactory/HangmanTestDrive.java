package gameFactory;

import java.util.Scanner;

public class HangmanTestDrive {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		HangmanPhase game = new HangmanPhase();
		GameData data = new GameData();

		game.takeTurn();
		System.out.println("Enter a lower case letter: ");

		while(GameData.curWrong < GameData.maxWrong) {
			data.setGuess(input.next());
			game.takeTurn();
		    
			// display current state
		    System.out.println("\n" + data.guessedContent());
		}
		
		System.out.println("Out of guesses. Game over!");
		System.out.println("The word was " + GameData.targetWord);
		// game starts, then use setGuess, then pass that in as the letter to guess
	}

}
