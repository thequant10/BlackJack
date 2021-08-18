package view;

import java.awt.*; 
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.DealPLayerListener;
import controller.ToolBarListener;


import model.interfaces.GameEngine;


public class ToolBar extends JToolBar{
	
	private JComboBox comboBox;
	private JPanel contentPane;
	private JTextField txtBet;
	private ToolBarListener listen;
	private GameEngineCallbackGUI callBack;
	private DealPLayerListener beal;
	private DefaultComboBoxModel defaultComboBoxModel;
	private JButton btnPlaceBet;
	private JButton btnRemoveBet;
	private JButton btnDeal;
	
public ToolBar(GameEngine gameEngine, MainFrame frame, GameEngineCallbackGUI callBack) {
	this.callBack=callBack;
	contentPane= new JPanel();
	listen= new ToolBarListener(gameEngine, frame, this);
	beal= new DealPLayerListener(gameEngine, callBack, this);
		
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	contentPane.setLayout(new BorderLayout(0, 0));

	
	JLabel playerLabel = new JLabel("Player");
	this.add(playerLabel);
	
	comboBox = new JComboBox();
	
	comboBox.setPreferredSize(new Dimension(250, 24));
	defaultComboBoxModel=(DefaultComboBoxModel)comboBox.getModel();
	defaultComboBoxModel.addElement("House");
	
	this.add(comboBox);
	
	
	JLabel betLabel = new JLabel("Bet");
	this.add(betLabel);
	
	txtBet = new JTextField();
	txtBet.setText("");
	txtBet.setPreferredSize(new Dimension(5, 24));
	this.add(txtBet);
	txtBet.setColumns(10);
	
	
	btnPlaceBet = new JButton("PlaceBet");
	btnPlaceBet.setPreferredSize(new Dimension(70,30));
	listen.placeBetListener(btnPlaceBet);
	this.add(btnPlaceBet);
	
	btnRemoveBet = new JButton("RemoveBet");
	btnRemoveBet.setPreferredSize(new Dimension(70,30));
	listen.removeBetListener(btnRemoveBet);
	this.add(btnRemoveBet);
	
	
	listen.comboBoxListener(comboBox);
		

	
	btnDeal = new JButton("Deal");
	btnDeal.setPreferredSize(new Dimension(150,30));
	beal.dealListener(btnDeal);
	this.add(btnDeal);
	check();
	contentPane.add(this);
}
 
//updates the display for combobox
public void displayPlayers(String player, Boolean check) {
	if(check) {
		defaultComboBoxModel.addElement(player);		
	}else {		
		defaultComboBoxModel.removeElement(player);
	}	
}

//inputs player name and returns ID
public String giveID() {
	String playerID = null;
	if (comboBox.getItemCount()>0 && comboBox.getSelectedItem().toString()!=null) {
	String apple= comboBox.getSelectedItem().toString();
	playerID= listen.getID(apple);
	}else {
		JOptionPane.showMessageDialog(null,"There are no players!");
	}
	return playerID;	
}

public int getBet() {
	return Integer.parseInt(txtBet.getText());
}

public JTextField betTF() {
	return txtBet;
}

public JComboBox getcomboBox() {
	return comboBox;
}

public DefaultComboBoxModel combo() {
	return defaultComboBoxModel; 
}


public GameEngineCallbackGUI call () {
	return callBack;
}

//disables toolbar buttons 
public void check() {
		btnPlaceBet.setEnabled(false);
		btnDeal.setEnabled(false);
		btnRemoveBet.setEnabled(false);	
	
}

//enables the toolbar buttons
public void checkNo() {
	btnPlaceBet.setEnabled(true);
	btnDeal.setEnabled(true);
	btnRemoveBet.setEnabled(true);		
}

public DealPLayerListener getDeallisten() {
	return beal;
}





}
