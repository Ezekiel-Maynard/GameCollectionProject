package gameFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//Game Engine and deals with the GUI aspects of the game
public class GameGUI extends Application{
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	//Deals with creating the stage(For the MVC Design pattern this acts as the model function)
	public void start(Stage primaryStage) throws Exception{
		Pane gamePane = new Pane();
		gamePane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
		Scene gameScene = new Scene(gamePane, 600, 600);
		
		//Deals with the card outline boxes
		Rectangle left = new Rectangle(150,100,150,200); 
		Rectangle right = new Rectangle(325,100,150,200);
		left.setStroke(Color.BLACK);
		left.setFill(Color.TRANSPARENT);
		right.setStroke(Color.BLACK);
		right.setFill(Color.TRANSPARENT);
		
		//Reads the information for the cards from a file named 'List of 52 Cards'
		Player one = new Player();
		Player two = new Player();
		Deck deck = new Deck();
		Card[] allCards = new Card[52];
		File listOfCards = new File("List of 52 Cards");
		try {
			Scanner in = new Scanner(listOfCards);
			
			while(in.hasNextLine()) {
				for(int i = 0; i < allCards.length; i++) {
					allCards[i] = new Card(in.next(), in.next());
				}
			}
			
			for(int i = allCards.length - 1; i >= 0; i--) {
				deck.add(allCards[i]);
			}
			
			//shuffles the deck
			deck.shuffle();
			in.close();
		}catch(FileNotFoundException e) {
			System.out.println("File Not Found");
		}
		
		
		//Deals with the player 1 label
		Label leftLabel = new Label("Player 1", left);
		leftLabel.setContentDisplay(ContentDisplay.TOP);
		leftLabel.setLayoutX(150);
		leftLabel.setLayoutY(100);
		
		//Deals with the player 2 label
		Label rightLabel = new Label("Player 2", right);
		rightLabel.setContentDisplay(ContentDisplay.TOP);
		rightLabel.setLayoutX(325);
		rightLabel.setLayoutY(100);
		
		//adds the player 1 and 2 labels
		gamePane.getChildren().addAll(left, leftLabel, right, rightLabel);
		
		//gets the cards that are played and assigns them to card objects
		Card oneDrawn = new Card();
		Card twoDrawn = new Card();
		
		//deals with the labels that indicates which players turn it is
		Text onesTurn = new Text();
		onesTurn.setLayoutX(250);
		onesTurn.setLayoutY(50);
		Text twosTurn = new Text();
		twosTurn.setLayoutX(250);
		twosTurn.setLayoutY(50);
		
		//deals with the winning text
		Text win = new Text();
		win.setLayoutX(250);
		win.setLayoutY(50);
		
		//Deals with the draw and play buttons for each player
		Button p1Draw = new Button("Draw");
		Button p1Shuffle = new Button("Shuffle");
		Button p2Draw = new Button("Draw");
		Button p2Shuffle = new Button("Shuffle");
		
		//deals with the play button
		Button play = new Button("Play");
		
		//Sets up the imageView for each of the players cards played
		ImageView card1 = new ImageView();
		card1.setLayoutX(125);
		card1.setLayoutY(100);
		ImageView card2 = new ImageView();
		card2.setLayoutX(300);
		card2.setLayoutY(100);
		
		
		//deals with the drawing cards for player one
		p1Draw.setLayoutX(150);
		p1Draw.setLayoutY(350);
		p1Draw.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent click) {
				if(one.getHand().isEmpty()) {
					p1Draw.setDisable(true);
				}else {
					if(card1.getImage() != null) {
						gamePane.getChildren().remove(card1);
					}
					oneDrawn.setCard(one.draw());
					card1.setImage(new Image(findImageURL(oneDrawn)));
					gamePane.getChildren().remove(onesTurn);
					card1.setFitWidth(175);
					card1.setFitHeight(200);
					
					if(card2.getImage() != null) {
						gamePane.getChildren().remove(card2);
					}
					
					p2Draw.setDisable(false);
					p2Shuffle.setDisable(false);
					
					p1Draw.setDisable(true);
					p1Shuffle.setDisable(true);
					
					gamePane.getChildren().addAll(card1, twosTurn);
					
					
				}
			}
		});
		
		
		//Shuffles players 1 deck
		p1Shuffle.setLayoutX(235);
		p1Shuffle.setLayoutY(350);
		p1Shuffle.setOnAction(new EventHandler<ActionEvent>() {
			 public void handle(ActionEvent click) {
				if(one.getHand().isEmpty()) {
					p1Shuffle.setDisable(true);
				}else {
					one.getHand().shuffle();
				}
			}
		});
				
		//deals with the drawing cards for player two
		p2Draw.setLayoutX(325);
		p2Draw.setLayoutY(350);
		p2Draw.setOnAction(new EventHandler<ActionEvent>() {
			 public void handle(ActionEvent click) {
				if(two.getHand().isEmpty()) {
					p2Draw.setDisable(true);
				}else {
					twoDrawn.setCard(two.draw());
					card2.setImage(new Image(findImageURL(twoDrawn)));
					gamePane.getChildren().remove(twosTurn);
					
					card2.setFitWidth(175);
					card2.setFitHeight(200);
					
					p1Draw.setDisable(false);
					p1Shuffle.setDisable(false);
					
					p2Draw.setDisable(true);
					p2Shuffle.setDisable(true);
					
					gamePane.getChildren().addAll(card2,onesTurn);
					
					compareCards(oneDrawn, twoDrawn, one, two, 1, leftLabel, rightLabel);
					
					if(one.getHand().isEmpty() || two.getHand().isEmpty()) {
						if(one.getScore()>two.getScore()) {
							win.setText(one.getName()+" is the winner!");
						}else {
							win.setText(two.getName()+" is the winner!");
						}
						
						gamePane.getChildren().removeAll(twosTurn, onesTurn);
						gamePane.getChildren().add(win);
					}
				}
			}
		});
				
		//Shuffles players 2 deck
		p2Shuffle.setLayoutX(410);
		p2Shuffle.setLayoutY(350);
		p2Shuffle.setOnAction(new EventHandler<ActionEvent>() {
			 public void handle(ActionEvent click) {
				if(two.getHand().isEmpty()) {
					p2Shuffle.setDisable(true);
				}else {
					two.getHand().shuffle();
				}	
			}
		});
		
		
		
		
		play.setLayoutX(280);
		play.setLayoutY(400);
		play.setOnAction(new EventHandler<ActionEvent>() {
			 public void handle(ActionEvent click) {
				gamePane.getChildren().remove(play);
				gamePane.getChildren().addAll(p1Draw, p1Shuffle, p2Draw, p2Shuffle, onesTurn);
				
				p2Draw.setDisable(true);
				p2Shuffle.setDisable(true);
				
				deck.deal(one.getHand());
				deck.deal(two.getHand());
						
				one.getHand().shuffle();
				two.getHand().shuffle();
			}
		});
		
		
		//deals with getting the players 2 name and and the text field that gets that name
		TextField name2 = new TextField();
		Label name2Label = new Label("What is player 2's name?", name2);
		name2Label.setContentDisplay(ContentDisplay.BOTTOM);
		name2Label.setLayoutX(220);
		name2Label.setLayoutY(400);
		name2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent click) {
				name2.setText(name2.getText());
				two.setName(name2.getText());
				twosTurn.setText("It's "+two.getName()+"'s turn!");
				rightLabel.setText(name2.getText()+": "+two.getScore());
				gamePane.getChildren().removeAll(name2, name2Label);
				gamePane.getChildren().add(play);
			}
		});
		
		//deals with getting the players 1 name and and the text field that gets that name
		TextField name1 = new TextField();
		Label name1Label = new Label("What is player 1's name?", name1);
		name1Label.setContentDisplay(ContentDisplay.BOTTOM);
		name1Label.setLayoutX(220);
		name1Label.setLayoutY(400);
		name1.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent click) {
				name1.setText(name1.getText());
				one.setName(name1.getText());
				onesTurn.setText("It's "+one.getName()+"'s turn!");
				leftLabel.setText(name1.getText()+": "+one.getScore());
				gamePane.getChildren().removeAll(name1, name1Label);
				gamePane.getChildren().addAll(name2, name2Label);
			}
		});
		
		//adds the player 1 name and its label
		gamePane.getChildren().addAll(name1, name1Label);
		
		//sets up and shows the stage
		primaryStage.setTitle("The Game of War");
		primaryStage.setScene(gameScene);
		primaryStage.show();
	}
	
	//This deals with all the game logic(For MVC Design it represents the controller function)
	public static void compareCards(Card a, Card b, Player p1, Player p2, int war, Label l1, Label l2) {
		//variable for the war event
		int warScore = war;
		
		//if player 1's card is higher
		if(a.valueOrder()>b.valueOrder()) {
			for(int i=0;i<warScore;i++) {
				p1.givePoint();
			}
			updateScore(l1, p1);
		//if players two card is higher
		}else if(b.valueOrder()>a.valueOrder()) {
			for(int i=0;i<warScore;i++) {
				p2.givePoint();
			}
			updateScore(l2, p2);
		//if the two cards are the same and a war event happens(which is dealt with here)
		}else if(a.valueOrder()==b.valueOrder()) {
			int i=0;
			Card c = new Card();
			
			//draws four cards from each player hand and compares the fourth card to decide who wins the war(recursive function)
			while(!p1.getHand().isEmpty() && i<=3) {
				c.setCard(p1.draw());
				i++;
			}
			int j=0;
			Card d = new Card();
			while(!p2.getHand().isEmpty() && j<=3) {
				d.setCard(p2.draw());
				j++;
			}
			warScore++;
			compareCards(c, d, p1, p2, warScore, l1, l2);
		}
	}
	
	//updates the players score and its label
	public static void updateScore(Label l, Player p) {
		l.setText(p.getName() + ": " + p.getScore());
	}
	
	public static ImageView createImage(String url, double x, double y) {
		Image image = new Image(url);
		
		ImageView image2 = new ImageView(image);
		
		image2.setX(x);
		image2.setY(y);
		
		return image2;
	}
	
	//Gets the corresponding cards images using a switch statement. Cards images are stored in the source folder called Images. 
	public static String findImageURL(Card card) {
		switch(card.getValue()) {
		case TWO:
			if(card.getSuit().equals(Suit.CLUBS)) {
				return "2C.png";
			}else if(card.getSuit().equals(Suit.HEARTS)) {
				return "2H.png";
			}else if(card.getSuit().equals(Suit.DIAMONDS)) {
				return "2D.png";
			}else {
				return "2S.png";
			}
		case THREE:
			if(card.getSuit().equals(Suit.CLUBS)) {
				return "3C.png";
			}else if(card.getSuit().equals(Suit.HEARTS)) {
				return "3H.png";
			}else if(card.getSuit().equals(Suit.DIAMONDS)) {
				return "3D.png";
			}else {
				return "3S.png";
			}
		case FOUR:
			if(card.getSuit().equals(Suit.CLUBS)) {
				return "4C.png";
			}else if(card.getSuit().equals(Suit.HEARTS)) {
				return "4H.png";
			}else if(card.getSuit().equals(Suit.DIAMONDS)) {
				return "4D.png";
			}else {
				return "4S.png";
			}
		case FIVE:
			if(card.getSuit().equals(Suit.CLUBS)) {
				return "5C.png";
			}else if(card.getSuit().equals(Suit.HEARTS)) {
				return "5H.png";
			}else if(card.getSuit().equals(Suit.DIAMONDS)) {
				return "5D.png";
			}else {
				return "5S.png";
			}
		case SIX:
			if(card.getSuit().equals(Suit.CLUBS)) {
				return "6C.png";
			}else if(card.getSuit().equals(Suit.HEARTS)) {
				return "6H.png";
			}else if(card.getSuit().equals(Suit.DIAMONDS)) {
				return "6D.png";
			}else {
				return "6S.png";
			}
		case SEVEN:
			if(card.getSuit().equals(Suit.CLUBS)) {
				return "7C.png";
			}else if(card.getSuit().equals(Suit.HEARTS)) {
				return "7H.png";
			}else if(card.getSuit().equals(Suit.DIAMONDS)) {
				return "7D.png";
			}else {
				return "7S.png";
			}
		case EIGHT:
			if(card.getSuit().equals(Suit.CLUBS)) {
				return "8C.png";
			}else if(card.getSuit().equals(Suit.HEARTS)) {
				return "8H.png";
			}else if(card.getSuit().equals(Suit.DIAMONDS)) {
				return "8D.png";
			}else {
				return "8S.png";
			}
		case NINE:
			if(card.getSuit().equals(Suit.CLUBS)) {
				return "9C.png";
			}else if(card.getSuit().equals(Suit.HEARTS)) {
				return "9H.png";
			}else if(card.getSuit().equals(Suit.DIAMONDS)) {
				return "9D.png";
			}else {
				return "9S.png";
			}
		case TEN:
			if(card.getSuit().equals(Suit.CLUBS)) {
				return "10C.png";
			}else if(card.getSuit().equals(Suit.HEARTS)) {
				return "10H.png";
			}else if(card.getSuit().equals(Suit.DIAMONDS)) {
				return "10D.png";
			}else {
				return "10S.png";
			}
		case JACK:
			if(card.getSuit().equals(Suit.CLUBS)) {
				return "JC.png";
			}else if(card.getSuit().equals(Suit.HEARTS)) {
				return "JH.png";
			}else if(card.getSuit().equals(Suit.DIAMONDS)) {
				return "JD.png";
			}else {
				return "JS.png";
			}
		case QUEEN:
			if(card.getSuit().equals(Suit.CLUBS)) {
				return "QC.png";
			}else if(card.getSuit().equals(Suit.HEARTS)) {
				return "QH.png";
			}else if(card.getSuit().equals(Suit.DIAMONDS)) {
				return "QD.png";
			}else {
				return "QS.png";
			}
		case KING:
			if(card.getSuit().equals(Suit.CLUBS)) {
				return "KC.png";
			}else if(card.getSuit().equals(Suit.HEARTS)) {
				return "KH.png";
			}else if(card.getSuit().equals(Suit.DIAMONDS)) {
				return "KD.png";
			}else {
				return "KS.png";
			}
		case ACE:
			if(card.getSuit().equals(Suit.CLUBS)) {
				return "AC.png";
			}else if(card.getSuit().equals(Suit.HEARTS)) {
				return "AH.png";
			}else if(card.getSuit().equals(Suit.DIAMONDS)) {
				return "AD.png";
			}else {
				return "AS.png";
			}
		default:
			return null;
		}
	}
}





























