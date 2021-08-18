package view;


import java.awt.Label;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class AddPlayerPanel extends JPanel
{


	private JTextField PlayerName;
	private JTextField PlayerPoints;
	public AddPlayerPanel()
	{
		
				
		setLayout(new GridLayout(5,5,5,5));
		PlayerName = new JTextField();
		PlayerPoints = new JTextField();
		add(new Label("Name"));
		add(PlayerName);
		add(new Label("Points"));
		add(PlayerPoints);			
		
	}
	
	public String getPlayerName() 
	{
		return this.PlayerName.getText();
	}
	
	public int getPlayerPoints()
	{
		return Integer.valueOf(this.PlayerPoints.getText());
	}
	
	//check if correct values have been entered
	public boolean validCheck() {
		boolean x;
		if (PlayerName.getText() instanceof String ) {
			x=true;		
		}else {
			x=false;
		}
		return x;
	}
	
	//check if values are entered
	public Boolean boxcheck() {
		boolean x=false;
		if(PlayerPoints.getText().isEmpty() || PlayerName.getText().isEmpty()) {
			x=true;					
		}
		return x;
	}

}
