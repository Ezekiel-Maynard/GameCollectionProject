package gameFactory;

//Class that deals with the pot(discard pile)
public class Pot {
	//Global Variables
	private Card topCard;
	private Card bottomCard;
	
	private Node first;
	private Node last;
	
	private int numOfCards;
	
	//Constructor with no arguments that sets the global variable to null
	public Pot() {
		topCard = null;
		bottomCard = null;
		
		first = null;
		last = null;
		
		numOfCards = 0;
	}
	
	//methods that adds a card to the pot
	public void add(Card c) {
		Node newNode = new Node(c);
		
		//If the card is the first card in the pot
		if(first == null) {
			first = newNode;
			last = first;
			topCard = newNode.getCard();
			bottomCard = newNode.getCard();
		}else {
			first.setPrevious(newNode);
			newNode.setNext(first);
			first = newNode;
			topCard = newNode.getCard();
		}
		numOfCards++;
	}
	
	//method that adds a card to the bottom of the pot
	public void addToBottom(Card c) {
		Node newNode = new Node(c);
		
		Node temp = last;
		last.setNext(newNode);
		last = newNode;
		last.setPrevious(temp);
		bottomCard = c;
		
		numOfCards++;
	}
	
	//removes a card form the pot
	public void remove(Card c) {
		//Exception Dealing
		if(isEmpty()) {
			throw new NullPointerException();
		}else if(searching(c) == -1) {
			throw new IndexOutOfBoundsException();
		}
		
		int place = searching(c);
		
		if(place == 0) {
			if(numOfCards == 1) {
				first = null;
				last = null;
				topCard = null;
				bottomCard = null;
				numOfCards = 0;
			}else {
				first = first.getNext();
				first.setPrevious(null);
				topCard = first.getCard();
				numOfCards--;
			}
		}else if(place == (numOfCards - 1)) {
			last = last.getPrevious();
			last.setNext(null);
			bottomCard = last.getCard();
			numOfCards--;
		}else {
			int i = 0;
			Node nextNode = first;
			
			for(Node currentNode = first; nextNode != null; currentNode = nextNode) {
				nextNode = currentNode.getNext();
				
				if(i == place) {
					currentNode.getPrevious().setNext(nextNode);
					currentNode.getNext().setPrevious(currentNode.getPrevious());
					numOfCards--;
					break;
				}
				i++;
			}
		}
	}
	
	//method that searches for a card in the pot
	public int searching(Card c) {
		Card[] cards = toArray();
		int index = -1;
		
		for(int i = 0; i < cards.length; i++) {
			if(cards[i].equals(c)) {
				index = i;
			}
		}
		
		return index;
	}
	
	//shuffles the pot
	public void shuffle() {
		Card[] cards = toArray();
		
		for(int i = 0; i < cards.length; i++) {
			int rng = (int)(Math.random() * cards.length);
			
			Card temp = cards[i];
			cards[i] = cards[rng];
			cards[rng] = temp;
		}
		
		clear();
		for(int i = 0; i < cards.length; i++) {
			add(cards[i]);
		}
	}
	
	//sorts the cards by value and suit
	public void sort() {
		int lowest = 1;
		int count = 0;
		
		Suit[] suits = {Suit.CLUBS, Suit.HEARTS, Suit.DIAMONDS, Suit.SPADES};
		Card[] cards = toArray(), array = new Card[numOfCards];
		
		for(int i = 0; i < suits.length; i++) {
			for(int j = lowest; j <= 14; j++) {
				for(int k = 0; k < cards.length; k++) {
					if(cards[k].getSuit().equals(suits[i]) && cards[k].valueOrder() == j) {
						array[count] = cards[k];
						count++;
					}
				}
			}
		}
		
		clear();
		for(int i = array.length - 1; i >= 0; i--) {
			add(array[i]);
		}
	}
	
	//get methods
	public Card getTop() {
		return topCard;
	}
	public Card getBottom() {
		return bottomCard;
	}
	public int getNumCards() {
		return numOfCards;
	}
	
	//Checks if the pot is empty
	public boolean isEmpty() {
		return numOfCards == 0;
	}
	
	//Clears the pot
	public void clear() {
		if(isEmpty()) {
			throw new NullPointerException();
		}
		
		while(!isEmpty()) {
			remove(topCard);
		}
	}
	
	//method that returns and array of cards
	public Card[] toArray() {
		Card[] cards = new Card[getNumCards()];
		
		int i = 0;
		Node n = first;
		
		while(n != null && i < getNumCards()) {
			Card c = n.getCard();
			n = n.getNext();
			cards[i] = c;
			i++;
		}
		
		return cards;
	}
	
	//Class that deals with the nodes in the pot(player 1 and player 2 pots)
	private class Node{
		//global variables
		private Card c;
		private Node next;
		private Node previous;
		
		//Constructor with no arguments that sets the global variable to null
		public Node() {
			c = null;
			next = null;
			previous = null;
		}
		
		//Constructor with a card argument that sets the card global variable
		public Node(Card c) {
			this.c = c;
		}
		
		//set methods
		public void setCard(Card c) {
			this.c = c;
		}
		public void setNext(Node next) {
			this.next = next;
		}
		public void setPrevious(Node previous) {
			this.previous = previous;
		}
		
		//Get methods
		public Card getCard() {
			return c;
		}
		public Node getNext() {
			return next;
		}
		public Node getPrevious() {
			return previous;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

