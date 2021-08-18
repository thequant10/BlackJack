package model;

import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import model.interfaces.PlayingCard.Suit;
import model.interfaces.PlayingCard.Value;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine {
   
	private List<GameEngineCallback>  gameEngineCallbacks = new ArrayList<GameEngineCallback>();
	private Deque<PlayingCard> shuffledCards = null;	
	private List<Player> players; 
	
	public GameEngineImpl() {
		players= new ArrayList<Player>(); 
	}

	@Override
	public void applyWinLoss(Player player, int houseResult) {
		if (player.getResult()>houseResult) {
			int new_points=player.getPoints()+100;
			player.setPoints(new_points);
		}else if (player.getResult()<houseResult) {
			int new_points=player.getPoints()-100;
			player.setPoints(new_points);			
		}else if (player.getResult()==houseResult) {
			player.setPoints(player.getPoints());
			
		}
	}
	
	@Override
	public void addPlayer(Player player) {
		boolean replaced= false;
		for (Player playerz: players) {
			if(playerz.getPlayerId().equals(player.getPlayerId())){
				players.set(players.indexOf(playerz), player);
				replaced=true;
				break;
			}				
			}
		if (replaced==false) {
			players.add(player);
		}			
	}

	
	@Override
	public boolean removePlayer(Player player) {
		boolean movecheck=false;
		for (Player playerz: players) {
			if (playerz.equals(player)) {
				players.remove(player);
				movecheck= true;
				break;
			}else {
				movecheck=false;
			}
		}
		return movecheck;
	}

	@Override
	public boolean placeBet(Player player, int bet) {
		player.setBet(bet);	
		if (bet >0 & player.getPoints()>100) {
			return true;			
		}
		return false;
	}
	
	List <GameEngineCallback> call= new ArrayList<GameEngineCallback>();
	
	
	
	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback)
	{
		this.gameEngineCallbacks.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback)
	{
		// Checking gameEngineCallback in the gameEngine
		if(gameEngineCallbacks.contains(gameEngineCallback))
		{
			this.gameEngineCallbacks.remove(gameEngineCallback);
			return true;
		}
		return false;
	}
	


	@Override
	public void dealPlayer(Player player, int delay) throws IllegalArgumentException
	{
		int totalResult = 0;
		// Throws error message if delay is not properly set
		if (delay < 0 || delay > 1000)
		{
			throw new IllegalArgumentException("Delay cannot be more than 1000 or less than 0");
		}
		while(totalResult < BUST_LEVEL)
		{
			delay(delay);
		    PlayingCard card = getShuffledCard();
		    // Checking latest result with the BUST_LEVEL
			if(totalResult + card.getScore() < BUST_LEVEL) 
			{
				// Increasing total with the card's score
				totalResult = totalResult + card.getScore();
				for(GameEngineCallback gameEngineCallback : gameEngineCallbacks)
				{
					gameEngineCallback.nextCard(player, card, this);
				}	
			}
			else 
			{
				for(GameEngineCallback gameEngineCallback : gameEngineCallbacks)
				{
					gameEngineCallback.bustCard(player, card, this);
				}
				break;
			}
		}
		
		for(GameEngineCallback gameEngineCallback : gameEngineCallbacks)
		{
			gameEngineCallback.result(player, totalResult, this);
		}
		// Adds score of all the cards in the result
		player.setResult(totalResult);
				
	}
	
	@Override
	public void dealHouse(int delay) throws IllegalArgumentException
	{	
		int houseResult = 0;
		// Throws error message if delay is not properly set
		if (delay < 0 || delay > 1000)
		{
			throw new IllegalArgumentException("Delay cannot be more than 1000 or less than 0");
		}
		// Checks result of house with the BUST_LEVEL
		while(houseResult < BUST_LEVEL)
		{
			delay(delay);
			PlayingCard card = getShuffledCard();			
			// Increasing result of house with the card's score
			if(houseResult + card.getScore() < BUST_LEVEL) 
			{
				houseResult = houseResult + card.getScore();
				for(GameEngineCallback gameEngineCallback : gameEngineCallbacks)
				{
					gameEngineCallback.nextHouseCard(card, this);
				}	
			}
			else 
			{
				for(GameEngineCallback gameEngineCallback : gameEngineCallbacks)
				{
					gameEngineCallback.houseBustCard(card, this);
				}
				break;
			}	
		}
		// Applying win/loss for all the players	
		for(Player player : players)
		{
			applyWinLoss(player, houseResult);
		}
		
		for(GameEngineCallback gameEngineCallback : gameEngineCallbacks)
		{
			gameEngineCallback.houseResult(houseResult, this);
		}	
			
		// Reseting the bet before staring a new round 
		players.forEach((player) -> {player.resetBet();});
		
	}


	
	private void delay(int delay) {
		if (delay>1000 || delay<0) {	
			throw new IllegalArgumentException("Delay must be greater than 0 and less than 1000");							   				    
		}else {
			try
			{
			    Thread.sleep(delay);
			}
			catch(InterruptedException ex)
			{
			    Thread.currentThread().interrupt();
			}							
		}
		
	}
	
	
	@Override
	public Player getPlayer(String id) {
		for (Player player: players) {
			if (player.getPlayerId().equals(id)) 				
				return player;			
		}
		
		return null;
	}
	
	
	@Override
	public Collection<Player> getAllPlayers() {
		Collections.sort(players);
		return players; 
	}
	
	LinkedList<PlayingCard> cards= new LinkedList<PlayingCard>();
	
	@Override
	public Deque<PlayingCard> getShuffledHalfDeck() {
		if (cards.isEmpty()) {
			for (Suit suit: Suit.values()) {
				for (Value myVar : Value.values()) {
					PlayingCard card= new PlayingCardImpl(suit, myVar);
					cards.add(card);
					Collections.shuffle(cards);
				}
			}			
		}		
		return(cards);
	}
	
	private PlayingCard getShuffledCard()
	{
		// Picks a card from the top of the shuffled deck
		if(shuffledCards == null || shuffledCards.isEmpty())
		{
			shuffledCards = getShuffledHalfDeck();
		}
		return shuffledCards.pop();
	}

	
}
