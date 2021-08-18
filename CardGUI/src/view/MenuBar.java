package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;



import controller.MenuBarListener;
import model.interfaces.GameEngine;


@SuppressWarnings("serial")
public class MenuBar extends JMenuBar
{

	private MenuBarListener listen;
	public MenuBar(GameEngine gameEngine, MainFrame frame) 
	{
		JMenu menu = new JMenu("Menu");
		listen= new MenuBarListener(gameEngine, frame);
		
		JMenuItem addPlayer = new JMenuItem("Add Player");
		listen.addListener(addPlayer);
		

		
		JMenuItem removePlayer = new JMenuItem("RemovePlayer");
		listen.removeListener(removePlayer);

			
		JMenuItem Exit = new JMenuItem("Exit");
		Exit.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0);
			}
		});
		menu.add(addPlayer);
		menu.add(removePlayer);
		menu.add(Exit);
		add(menu);
		

	}
	
	public MenuBarListener addA() {
		return listen;
	}
	
	


}
