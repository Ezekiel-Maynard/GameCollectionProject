package gameFactory;

import javafx.stage.Stage;

public class RunsTorinsGame extends GameSelectorUI{
	public static void StartGame(Stage stage) {
		TicTacToeMain TicTacToeGame = new TicTacToeMain();
		try {
			TicTacToeGame.main(null);
			stage.hide();
		}catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/* Attempt to get Torin's game to run as a batch file in PowerShell instead of the console
		 * 
		 * Process proc = Runtime.getRuntime().exec("java -jar TicTacToe.jar");
		InputStream in = proc.getInputStream();
		InputStream err = proc.getErrorStream();
		*/
	}
}

