package view;


import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


import javax.swing.JLabel;
import javax.swing.JPanel;



@SuppressWarnings("serial")
public class StatusBar extends JPanel implements ItemListener {
	public String state;
	private final JLabel label= new JLabel("status", JLabel.LEFT);
	
	public StatusBar() {
		setLayout(new GridLayout(0,1));
		label.setText("House selected");
		label.setAlignmentY(BOTTOM_ALIGNMENT);
		add(label);		
	}
	
	//changes the status bar depending on the player selected
	@Override
	public void itemStateChanged(ItemEvent e) {
		state= (String) e.getItem();
		label.setText(String.format("%s selected", state));		
	}
	
		


}