package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;


import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JOptionPane;


import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;
import view.ToolBar;

public class ToolBarListener {
	private GameEngine gameEngine;
	private MainFrame frame;
	private ToolBar toolBar;

	public ToolBarListener(GameEngine gameEngine, MainFrame frame, ToolBar toolBar) {
		this.frame=frame;
		this.gameEngine=gameEngine;
		this.toolBar=toolBar;
				
	}
	
	public void placeBetListener(JButton placeBet) {
		placeBet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=toolBar.giveID();
				int bet=toolBar.getBet();
				if (bet>getPlayer(id).getPoints()) {
					JOptionPane.showMessageDialog(null, "Bet must be less than "+getPlayer(id).getPoints());
					toolBar.betTF().setText("");
				}else {
					setBet(id, bet);
					frame.revalidate();
					frame.repaint();					
				}
				
			}
		});
	}
	
	public void removeBetListener(JButton removeBet) {
		removeBet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=toolBar.giveID();
				resetBet(id);
				toolBar.betTF().setText("0");
				frame.display(getAllPlayers());
			}
		});
	}
	
	
	public void comboBoxListener(@SuppressWarnings("rawtypes") JComboBox box) {
		box.addItemListener(frame.status());
		box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id=toolBar.giveID();
				frame.status();
				if(getPlayer(id).getResult()>0) {
					frame.CallBack().show();
				}
				if(box.getSelectedItem().toString().equals("House") || getPlayer(id).getResult()>0){
					toolBar.check();				
				}else {
					toolBar.checkNo();					
				}		
				try {
					if (box.getItemCount()>0) {
						Object obj= getBet(toolBar.giveID());
						toolBar.betTF().setText(obj.toString());						
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,e2.getStackTrace());
					
				}				
			}
		});			
	}
	
	
	public void setBet(String id,int bet) {
		gameEngine.getPlayer(id).setBet(bet);
	}
	
	public Collection<Player> getAllPlayers() {
		return gameEngine.getAllPlayers();
	}
	
	public void resetBet(String id) {
		gameEngine.getPlayer(id).resetBet();
	}
	
	public Player getPlayer(String id) {
		return gameEngine.getPlayer(id);
	}
	
	public int getBet(String id) {
		return gameEngine.getPlayer(id).getBet();
	}
	

			
	public String getID(String x) {
		String id= null;
		for (Player playerz: getAllPlayers()) {
			if (playerz.getPlayerName().equals(x)) 				
				id= playerz.getPlayerId();			
		}
		return id;
		
	}
	
	
}