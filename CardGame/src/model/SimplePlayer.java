package model;
import model.interfaces.Player;

public class SimplePlayer implements Player {
	
	private String id;
	private String PlayerName;
	private int points;
	

	 public SimplePlayer(String id, String PlayerName, int points) {
		 this.PlayerName= PlayerName;
		 this.id=id;
		 this.points=points;
	}

	@Override
	public String getPlayerName() {
		return PlayerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.PlayerName=playerName;
		
	}

	
	@Override
	public int getPoints() {
		 return this.points;
	}

	@Override
	public void setPoints(int points) {	
		this.points= points;		
	}

	@Override
	public String getPlayerId() {
		return id;
	}

	private int bet;
	@Override
	
	public boolean setBet(int bet) {
		this.bet=bet;
		if (bet>0 & getPoints()>100) {
			return true;		
		}else {
			return false;		
		}		
	}

	@Override
	public int getBet() {
		return this.bet;
	}

	@Override
	public void resetBet() {
		this.bet=0;
			
	}
	
	private int result;

	@Override
	public int getResult() {
		return result;
	}

	@Override
	
	public void setResult(int result) {
		this.result=result;
	}
	
	//make sure the equals is correct below
	@Override
	public boolean equals(Player player) 
	{
		if (this.id.equals(player.getPlayerId())) 
		{
			return true;
		}
		return false;
	}
	
	public boolean equals(Object player) 
	{
		if (player instanceof SimplePlayer) {
			SimplePlayer simp= (SimplePlayer) player;
			if (this.id.equals(simp.getPlayerId())) 
			{
				return true;
			}						
		}
		return false;		
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public int compareTo(Player player) {
		
		return this.id.compareTo(player.getPlayerId());
	}
	
	@Override
	public String toString(){
		return String.format("Player: id=%s, name=%s, bet=%d, points=%d, RESULT .. %d",
				getPlayerId(), getPlayerName(), getBet(), getPoints(), getResult() );
		
	}

}
