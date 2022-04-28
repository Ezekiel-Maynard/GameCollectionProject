package gameFactory;

import javafx.stage.Stage;

public class RunCalebsGame extends GameSelectorUI {
	public static void StartGame(Stage stage) {
		C4 Connect = new C4();
		try {
			Connect.setVisible(true);
		}catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

