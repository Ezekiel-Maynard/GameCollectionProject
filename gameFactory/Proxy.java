package gameFactory;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Proxy extends Application {
	 public void start(Stage stage) {
		 Text title = new Text();
	      title.setText("Click the Buttone to Play Pong");
	      title.setFont(Font.font("Verdana",FontWeight.BOLD, 25));
	      title.setFill(Color.BLACK);
	      title.setX(100);
	      title.setY(85);
	      Button Pong= new Button("Please Click me!");
	      Pong.setTranslateX(250);
	      Pong.setTranslateY(135);
	      Pong.setOnAction(new EventHandler<ActionEvent>(){
	    	  
	  		@Override
	  		public void handle(ActionEvent e) {
	  			Pong pong = new Pong();
	  			try {
					pong.main(null);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	  		}
	      	  
	        });
	      Group root = new Group(title, Pong);
	      Scene scene = new Scene(root, 600, 300, Color.PURPLE);
	      //Setting the scene to the Stage
	      stage.setScene(scene);
	      //Setting Title to the stage
	      stage.setTitle("Game Selection");
	    //Displaying the contents of the stage
	      stage.show();
	 }

public static void main(String [] args) {
	launch(args);
}
}
