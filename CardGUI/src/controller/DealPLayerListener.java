package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameEngineCallbackGUI;
import view.ToolBar;

public class DealPLayerListener 
{

	private GameEngine gameEngine;
	private GameEngineCallbackGUI callback;
	private ToolBar toolBar;
	private int numDealt=0;

	public DealPLayerListener(GameEngine gameEngine, GameEngineCallbackGUI callback, ToolBar toolBar) 
	{
		this.gameEngine = gameEngine;
		this.callback = callback;
		this.toolBar=toolBar;
	}
	
	
	//initializes dealing card for players
	public void dealListener(JButton deal) {
		deal.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				toolBar.check();
				String id=toolBar.giveID();
				Player player=gameEngine.getPlayer(id);
				
				new Thread() 
				{
					@Override
					public void run() 
					{
						numDealt=numDealt+1;						
					    gameEngine.dealPlayer(player, 100);
					}
				}.start();
				
				callback.DealCards();	
			
			}
			
		});
		toolBar.checkNo();

	}
	   
    public int check() {
    	return numDealt;
    }
	
}

		
	



