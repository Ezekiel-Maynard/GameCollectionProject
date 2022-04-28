package gameFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TicTacToeMain {
	
	public static void main(String[] args) throws Exception {
		TicTacToeCommandInterpreter interpreter = new TicTacToeCommandInterpreter();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
	    while ((s = in.readLine()) != null) {
	    	interpreter.parseCommand(s);  
	  	}
	}

}
