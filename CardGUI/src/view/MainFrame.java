package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Collection;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;



import model.GameEngineImpl;
import model.interfaces.GameEngine;
import model.interfaces.Player;



@SuppressWarnings("serial")
public class MainFrame extends JFrame 
{
	private MenuBar menuBar;
	private ToolBar toolBar;
	private SummaryPanel panelSummary;
	private final GameEngine gameEngine;
	private StatusBar statusBar;
	private JSplitPane splitPane;
	private GameEngineCallbackGUI callBack;
	private CardPanel panelCard;
	private JPanel contentPane;

	
	
	/**
	 * Create the frame.
	 */
	public MainFrame() 
	{
		contentPane = new JPanel(new BorderLayout());
        this.setContentPane(contentPane);
	
		panelCard= new CardPanel();
	    gameEngine = new GameEngineImpl();
        gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
        callBack = new GameEngineCallbackGUI(gameEngine, panelCard, this);
        gameEngine.addGameEngineCallback(callBack);
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 732, 432);
		
		statusBar= new StatusBar();
		
		menuBar = new MenuBar(gameEngine, this);
		toolBar= new ToolBar(gameEngine, this, callBack);

		this.setJMenuBar(menuBar);
		
		contentPane.add(toolBar, BorderLayout.NORTH);
		

		panelCard.setPreferredSize(new Dimension(650, 200));
		contentPane.add(panelCard, BorderLayout.CENTER);
		

		
		panelSummary = new SummaryPanel(this);

		panelSummary.setPreferredSize(new Dimension(500, 100));
	
		cool();	
		contentPane.add(splitPane, BorderLayout.SOUTH);
		menuBar.addA().addPlayer("House", 0);
		this.setVisible(true);		
		
	}
	
	public void display(Collection<Player> players) {
		panelSummary.displayPlayers(players);	
	}
	
	public void trueDisplay(String player, Boolean check) {
		toolBar.displayPlayers(player, check);
	}
	
	public ToolBar getBar() {
		return toolBar;
	}
	
	public StatusBar status() {
		return statusBar;
	}
	
	//puts the status bar and the summary panel into a spilt pane
	public void cool() {
		splitPane = new JSplitPane();
	    splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT); 
	    splitPane.setDividerLocation(100);  
	    splitPane.setTopComponent(panelSummary);               
	    splitPane.setBottomComponent(statusBar);
	}
	
	
	public GameEngineCallbackGUI CallBack() {
		return callBack;
	}
		
	
}
