package gameFactory;

import java.util.Arrays;
import java.util.List;

//Class that deals with the card objects
public class Card {
	
	//Global Variables that store the value and suit of the card
	private Suit suit;
	private Values value;
	
	//Constructor with no arguments that sets the value and suit of a card object to null
	public Card() {
		suit = null;
		value = null;
	}
	
	//Constructor with two arguments
	public Card(String s, String v) {
		this.suit = convertSuit(s);
		this.value = convertValue(v);
	}
	
	//Gets the suit of the card object
	public Suit getSuit() {
		return suit;
	}
	
	//Gets the value of the card object
	public Values getValue() {
		return value;
	}
	
	//Sets both the value and suit of the card object
	public void setCard(Card c) {
		suit = c.getSuit();
		value = c.getValue();
	}
	
	//Converts a string argument into a value for a card object
	private Values convertValue(String value) {
		List<Values> a = Arrays.asList(Values.values());
		for(Values v : a) {
			if(v.toString().equals(value)) {
				return v;
			}
		}
		
		return null;
	}
	
	//Converts a string argument into a suit for a card object
	private Suit convertSuit(String suit) {
		List<Suit> a = Arrays.asList(Suit.values());
		for(Suit s : a) {
			if(s.toString().equals(suit)) {
				return s;
			}
		}
		
		return null;
	}
	
	//returns a string that decribes the card object
	public String toString() {
		return getValue() + " OF " + getSuit();
	}
	
	//returns an integer that describes the order of the selected suit
	public int suitOrder() {
		switch(suit) {
		case CLUBS:
			return 4;
		case DIAMONDS:
			return 3;
		case HEARTS:
			return 2;
		case SPADES:
			return 1;
		default:
			return 0;
		}
	}
	
	//returns an integer that describes the order of the selected value
	public int valueOrder() {
		switch (value) {
		case TWO:
			return 2;
		case THREE:
			return 3;
		case FOUR:
			return 4;
		case FIVE:
			return 5;
		case SIX:
			return 6;
		case SEVEN:
			return 7;
		case EIGHT:
			return 8;
		case NINE:
			return 9;
		case TEN:
			return 10;
		case JACK:
			return 11;
		case QUEEN:
			return 12;
		case KING:
			return 13;
		case ACE:
			return 14;		
		default:
			return 0;
		}
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

