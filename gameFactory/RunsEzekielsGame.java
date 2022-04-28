package gameFactory;

import javafx.stage.Stage;

public class RunsEzekielsGame extends GameSelectorUI {
	public static void StartGame(Stage stage) {
		try {
			Proxy.main(null);
		}catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}