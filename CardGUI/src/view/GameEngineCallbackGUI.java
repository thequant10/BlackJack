package view;


import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import javax.swing.SwingUtilities;

import controller.DealHouseListener;


import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;


public class GameEngineCallbackGUI implements GameEngineCallback
{
	
	
	private GameEngine gameEngine;
	
	public CardPanel cardPanel;
	private MainFrame frame;
	boolean lean=false;
	public GameEngineCallbackGUI(GameEngine gameEngine, CardPanel cardPanel, MainFrame frame) 
	{
		this.gameEngine = gameEngine;
		this.cardPanel=cardPanel;
		this.frame=frame;
	}
	
	//method for handing cards
	public void DealCards()
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() 
			{
				cardPanel.clearCards();
				cardPanel.repaint();
            
			}
		});
	}
	
	public void show()
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() 
			{
				cardPanel.repaint();
            
			}
		});
	}
        

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) 
	{
            System.out.println("Next Card");
            SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                    	cardPanel.drawNextCard(card);
                    }
		});
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) 
	{
            System.out.println("Bust Card");
            SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                    	cardPanel.drawBustCard(card);
                    }
            });
		
	}

    @Override
    public void result(Player player, int result, GameEngine engine) {           
        smth();
    }
    


    @Override
    public void nextHouseCard(PlayingCard card, GameEngine engine) {
    	System.out.println("Next Card");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	cardPanel.drawNextCard(card);
                
            }
        });

    }

    @Override
    public void houseBustCard(PlayingCard card, GameEngine engine) {
    	System.out.println("Bust Card");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	cardPanel.drawBustCard(card);
            }
        });
    }

    @Override
    public void houseResult(int result, GameEngine engine) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	frame.revalidate();
            	frame.repaint();
            	frame.display(gameEngine.getAllPlayers());
            	JOptionPane.showMessageDialog(null, "Game Finished!");
            }
        });
    }

    
    public void smth() {
    	int dealtP= frame.getBar().getDeallisten().check();
    	int totalP= gameEngine.getAllPlayers().size();
    	if (dealtP+1==totalP) {
    		JOptionPane.showMessageDialog(null, "All players dealt, dealing House");
            frame.getBar().combo().setSelectedItem("House");
            new DealHouseListener(gameEngine, this).actionPerformed(
                new ActionEvent("", 0, "Deal House"));    
    	}else {
    		frame.revalidate();
    		frame.repaint();
    	}
    }
    
    public boolean getLean() {
    	return lean;
    }

	
	

}
