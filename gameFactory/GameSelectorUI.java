package gameFactory;
import java.io.IOException;
import java.io.InputStream;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GameSelectorUI extends Application {
   public void start(Stage stage) {
      
      //Button that Starts Stephen's game of War that uses the MVC Design Pattern
      Button War= new Button("Play War");
      War.setTranslateX(265);
      War.setTranslateY(100);
      War.setOnAction(new EventHandler<ActionEvent>(){

		@Override
		public void handle(ActionEvent e) {
			stage.close();				
			RunStephensGame.StartGame(stage);;	
			
		}
    	  
      });
      
      //Button that starts Carson's game of Hangman that uses the state Design Pattern
      //TextArea gametext = new TextArea();
      Button Hangman= new Button("Play Hangman");
      Hangman.setTranslateX(250);
      Hangman.setTranslateY(135);
      Hangman.setOnAction(new EventHandler<ActionEvent>(){
    	  
  		@Override
  		public void handle(ActionEvent e) {

  			try {
				RunsCarsonsGame.StartGame(stage);
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
  		}
      	  
        });
      
      //Button that starts Torin's game of TicTacToe Using the Command Pattern
      Button TicTacToe= new Button("Play Tic-Tac-Toe");
      TicTacToe.setTranslateX(245);
      TicTacToe.setTranslateY(170);
      TicTacToe.setOnAction(new EventHandler<ActionEvent>(){

  		@Override
  		public void handle(ActionEvent e) {

  			TicTacToeMain TicTacToeGame = new TicTacToeMain();
  			try {
				RunsTorinsGame.StartGame(stage);
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
  		}
      	  
        });
      //Button that runs Caleb's Game of using the Iterator Pattern
      Button Connect4= new Button("Play Connect 4");
      Connect4.setTranslateX(248);
      Connect4.setTranslateY(205);
      Connect4.setOnAction(new EventHandler<ActionEvent>(){

		@Override
		public void handle(ActionEvent e) {
			try {
				RunCalebsGame.StartGame(stage);
				stage.close();

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		}
    	  
      });
      //Runs Ezekiel's or my Game of Pong using the Proxy Pattern
      Button Pong= new Button("Play Pong");
      Pong.setTranslateX(262);
      Pong.setTranslateY(240);
      Pong.setOnAction(new EventHandler<ActionEvent>(){

		@Override
		public void handle(ActionEvent e) {
			try {
				RunsEzekielsGame.StartGame(stage);
				stage.close();

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		}
    	  
      });
      
      
      
      //Linear Gradient For Title
      Stop[] stops = new Stop[] {
    		new Stop(0, Color.YELLOW), new Stop(1, Color.RED)
      };
      LinearGradient linearGradient = new LinearGradient(0,0,1,0, true, CycleMethod.NO_CYCLE, stops);
      //Title of Game Selector in Scene
      Text title = new Text();
      title.setText("Do you want to Play a Game?");
      title.setFont(Font.font("Verdana",FontWeight.BOLD, 25));
      title.setFill(linearGradient);
      title.setX(100);
      title.setY(85);
    //Instantiating the group class
      Group root = new Group(War, Hangman, TicTacToe, Connect4, Pong, title);
      //Instantiating the Scene class
      Scene scene = new Scene(root, 600, 300, Color.PURPLE);
      //Setting the scene to the Stage
      stage.setScene(scene);
      //Setting Title to the stage
      stage.setTitle("Game Selection");
    //Displaying the contents of the stage
      stage.show();
   }
   public static void main(String args[]){
      launch(args);
   }
}