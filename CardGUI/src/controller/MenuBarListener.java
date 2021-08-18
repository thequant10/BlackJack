package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AddPlayerPanel;
import view.MainFrame;

public class MenuBarListener {
	private MainFrame frame;
	private GameEngine gameEngine;
	private AddPlayerPanel addPlayerPanel;
	
	public MenuBarListener(GameEngine gameEngine, MainFrame frame) {
		this.gameEngine=gameEngine;
		this.frame=frame;	
		
	}
	
	//validation check and data input for adding players
	public void addListener(JMenuItem item) {
		item.addActionListener(new ActionListener() {
	
		public void actionPerformed(ActionEvent e) 
		{
			
			addPlayerPanel = new AddPlayerPanel();
			int input = JOptionPane.showConfirmDialog(null, addPlayerPanel, "Add Player", JOptionPane.OK_CANCEL_OPTION);
			if (input== JOptionPane.YES_OPTION && addPlayerPanel.boxcheck()==false)
			{
				String name=addPlayerPanel.getPlayerName();
				int points= addPlayerPanel.getPlayerPoints();
				try {
					addPlayer(name, points);
					JOptionPane.showMessageDialog(null,"Player has been added !");
					//frame
					frame.display(getAllPlayers());						
					frame.trueDisplay(name, true);
				} 
				catch (Exception e2) {
					JOptionPane.showMessageDialog(null,e2.getStackTrace());
				}							
			}else if (input== JOptionPane.YES_OPTION && addPlayerPanel.boxcheck()) {
				JOptionPane.showMessageDialog(null,"Invalid inputs");
								
			}else if (input==JOptionPane.OK_CANCEL_OPTION) {			
			}


}
		});
		
	}
	
	//validation check and data input for removing players
	public void removeListener(JMenuItem item) {
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog(new JFrame(), "Enter Player Name", "Remove Player ",
	                    JOptionPane.PLAIN_MESSAGE);
				String id= getID(input);
				//String id= input;
				if (checkPlayer(id)) {
					removePlayer(id);
					frame.trueDisplay(input, false);
					frame.display(getAllPlayers());
					frame.revalidate();
					frame.repaint();
					if (getAllPlayers().size()<0) {
						frame.getBar().betTF().setText("");					
					}					
					JOptionPane.showMessageDialog(new JFrame(), "Player removed",
	                        "", JOptionPane.PLAIN_MESSAGE);										
				}else if((input != null) && (input.length() > 0)) {
					JOptionPane.showMessageDialog(new JFrame(), "Invalid player name",
	                        "Error invalid ID", JOptionPane.ERROR_MESSAGE);
				}
			
			}
		});
		
	}
	
	
	public void addPlayer(String name,int points) {
		Object hello= gameEngine.getAllPlayers().size();
		String playerId=hello.toString();
		Player player=new SimplePlayer(playerId, name, points);
		gameEngine.addPlayer(player);
	} 
	
	public Player getPlayer(String id) {
		return gameEngine.getPlayer(id);
	}
	
	
	public Collection<Player> getAllPlayers() {
		return gameEngine.getAllPlayers();
	}
	
	public boolean checkPlayer(String id) {
		boolean check= false;
		for (Player playerz: gameEngine.getAllPlayers()) {
			if (playerz.getPlayerId().equals(id)) 				
				check= true;			
		}
		return check;
	}
	
	public void removePlayer(String id) {
		gameEngine.removePlayer(gameEngine.getPlayer(id));
	}
	
	//inputs player name and returns ID
	public String getID(String x) {
		String id= null;
		for (Player playerz: gameEngine.getAllPlayers()) {
			if (playerz.getPlayerName().equals(x)) 				
				id= playerz.getPlayerId();			
		}
		return id;
		
	}
		

}
