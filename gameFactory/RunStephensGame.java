package gameFactory;

import javafx.stage.Stage;

public class RunStephensGame extends GameSelectorUI {
	public static void StartGame(Stage stage) {
		GameGUI WarGame = new GameGUI();
		try {
			WarGame.start(stage);
		}catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
