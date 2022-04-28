package gameFactory;

import java.util.ArrayList;
import java.util.Random;

public class GameData {
	
	// Constructor
	public GameData() {
		
	}
	// Possible words to guess
	public static String[] words = {
			"java", "python", "compiler", "architecture","queue",
			"software", "graph", "array", "stack", "heap", 
			"exception", "javascript", "interface", "abstract", "inheritance"
	};
	
	public static Random RANDOM = new Random();
	public static int maxWrong = 8;
	public static int curWrong = 0;
	public static String targetWord;
	public static char[] curWordProgress;
	public static ArrayList<String> guesses = new ArrayList<String>();
	public static String curGuess = "";
	
	public static void playGame() {
		System.out.println("Welcome to Computer Science Hangman!");
		targetWord = determineWord();
		curWordProgress = new char[targetWord.length()];
		
		for (int i = 0; i < curWordProgress.length; i++) {
			curWordProgress[i] = '_';
		}
	}
	
	private static String determineWord() {
		return words[RANDOM.nextInt(words.length)];
	}
	
	// Sets curGuess to the letter passed in, so it can then be used
	// in the guess function
	public void setGuess(String str) {
		if (str.length() > 1) {
			curGuess = str.substring(0, 1);
		} else
			curGuess = str;
	}
	
	public String guessedContent() {
		StringBuilder builder = new StringBuilder();
	
		for (int i = 0; i < curWordProgress.length; i++) {
			  builder.append(curWordProgress[i]);
		
			  if (i < curWordProgress.length - 1)
				   builder.append(" ");
		  }
	
		return builder.toString();
	}
	
	public static void guess() {		
		if (!guesses.contains(curGuess)) {
			if (targetWord.contains(curGuess)) {
				int index = targetWord.indexOf(curGuess);
	
			    while (index >= 0) {
			    	curWordProgress[index] = curGuess.charAt(0);
			    	index = targetWord.indexOf(curGuess, index + 1);
			    }
			} 
			else {
				curWrong++;
				System.out.println("Incorrect! You have " + (maxWrong - curWrong) + " guesses remaining");
			}
	
			guesses.add(curGuess);
	  }
	}

	public static boolean check() {
		return targetWord.contentEquals(new String(curWordProgress));
	}
}
