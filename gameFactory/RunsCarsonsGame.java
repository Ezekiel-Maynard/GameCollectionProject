package gameFactory;

import javafx.stage.Stage;

public class RunsCarsonsGame extends GameSelectorUI{
	public static void StartGame(Stage stage) {
		HangmanTestDrive Hangman = new HangmanTestDrive();
		try {
			Hangman.main(null);
			stage.hide();
		}catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*Attempt to get Carson's game to run as a batch s instead of in the console
		 * 
		 * Process proc = Runtime.getRuntime().exec("java -jar Hangman.jar");
		InputStream in = proc.getInputStream();
		InputStream err = proc.getErrorStream();
		*/
	}
}