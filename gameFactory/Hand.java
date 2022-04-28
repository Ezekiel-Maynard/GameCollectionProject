package gameFactory;

//Class that deals with the players hands
public class Hand extends Pot{
	private final int LIMIT = 26;
	
	//method that draws a card from the players hand at random
	public Card draw() {
		int rng = (int)(Math.random() * getNumCards());
		Card drawn = getCard(rng);
		
		remove(drawn);
		return drawn;
	}
	
	//gets a card from an array
	private Card getCard(int i) {
		Card[] cards = toArray();
		return cards[i];
	}
	
	//get method for the limit variable
	public int getLimit() {
		return LIMIT;
	}
}
