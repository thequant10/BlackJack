package model;

import model.interfaces.PlayingCard;

public class PlayingCardImpl implements PlayingCard {
	
	private Suit suit;
	private Value value;
	private int score;
	
	public PlayingCardImpl(Suit suit, Value value) {
		this.suit=suit; 	
		this.value=value;		
		if (value.equals(Value.EIGHT)) {
			 this.score= 8;		
		}else if (value.equals(Value.NINE)) {
			this.score=9;		
		}else if (value.equals(Value.JACK) || value.equals(Value.KING) || value.equals(Value.QUEEN) || value.equals(Value.TEN) ) {
			this.score=10;
		}else if (value.equals(Value.ACE)) {
			this.score=11;
		}
			
	}
	
	@Override
	public Suit getSuit() {
		return suit;
	}

	@Override
	public Value getValue() {
		return value;
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public boolean equals(PlayingCard card) {
		if (this.suit.equals(card.getSuit()) & this.value.equals(card.getValue())) 
		{
			return true;
		}
		return false;
	}
	
	private String modifier(String input) {
		   String word= input.toLowerCase();
		   String s1 = word.substring(0, 1).toUpperCase();
		   String result = s1 + word.substring(1);	   
		   return result;	   
	   }
	
	@Override
	public String toString() {
		return String.format("Suit: %s, Value: %s, Score: %d",
				modifier(getSuit().name()), modifier(getValue().name()), getScore());				
	}
	
	@Override
	public boolean equals(Object card) 
	{
		if (card instanceof PlayingCard) {
		PlayingCardImpl playingcard= (PlayingCardImpl) card;
		if (this.suit.equals(playingcard.getSuit()) & this.value.equals(playingcard.getValue()) ) 
		{
			return true;
		}
		}
		return false;		
	} 
	
	@Override
	public int hashCode() {
		int result= 0;
		result = result + ((suit == null) ? 0 : suit.hashCode());
		result = result + ((value == null) ? 0 : value.hashCode());
		return result;
		
	}
			
}
