package gameFactory;


public class Player {
	//global variables
	private String name;
	private int score;
	public Hand hand;
	
	//Constructor that has no arguments
	public Player() {
		name = null;
		hand = new Hand();
		score = 0;
	}
	
	//set methods
	public void setName(String name) {
		this.name = name;
	}
	
	//Get methods
	public String getName() {
		return name;
	}
	public Hand getHand() {
		return hand;
	}
	public int getNumOfCards() {
		return hand.getNumCards();
	}
	public int getScore() {
		return score;
	}
	
	//draws a card from players hand
	public Card draw() {
		return hand.draw();
	}
	
	//adds a card to the players hand
	public void addToHand(Card card) {
		hand.add(card);
	}
	
	//gives player a point
	public void givePoint() {
		score++;
	}
	
	//reduces a player points
	public void reducePoint() {
		score--;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
