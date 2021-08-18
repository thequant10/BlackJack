package view;

import java.awt.BorderLayout;

import java.util.Collection;

import javax.swing.DefaultListModel;

import javax.swing.JList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.interfaces.Player;

public class SummaryPanel extends JPanel{
	private JList list;
	private MainFrame frame;
	public SummaryPanel(MainFrame frame) {	
		this.frame=frame;
		list = new JList();
		setLayout(new BorderLayout(1, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
			
		scrollPane.setViewportView(list);	
	}
	
	//updates display for the summary panel
	public void displayPlayers(Collection<Player> players) {
		DefaultListModel defaultListModel=new DefaultListModel<>();
		list.setModel(defaultListModel);
		defaultListModel.clear();
		for (Player player : players) {
			defaultListModel.addElement(player);	
		}
	}		
}
