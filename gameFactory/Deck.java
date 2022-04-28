package gameFactory;

//Class that deals with the deck(dealing, shuffling, etc)
public class Deck extends Pot{
	private final int LIMIT = 52;
	
	//method that deals the deck
	public void deal(Hand h) {
		int count = 0;
		
		while(count < h.getLimit()) {
			h.add(draw());
			count++;
		}
	}
	
	//method that deals with drawing from the deck
	public Card draw() {
		int rng = (int)(Math.random() * getNumCards());
		Card drawn = getCard(rng);
		
		remove(drawn);
		return drawn;
	}
	
	//method that gets a card from the deck
	private Card getCard(int x) {
		Card[] cards = toArray();
		return cards[x];
	}
	
	//shuffles the deck
	public void shuffle() {
		Card[] cards = toArray();
		int times = 3;
		
		while(times > 0) {
			for(int i = 0; i < cards.length; i++) {
				int rng = (int)(Math.random() * cards.length);
				
				Card temp = cards[i];
				cards[i] = cards[rng];
				cards[rng] = temp;
			}
			times--;
		}
		clear();
		for(int i = 0; i < cards.length; i++) {
			add(cards[i]);
		}
	}
	
	//get method for the variable LIMIT
	public int getLimit() {
		return LIMIT;
	}
}
