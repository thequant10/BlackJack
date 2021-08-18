package Client;


import javax.swing.SwingUtilities;

import view.MainFrame;

public class Main 
{
	
	public static void main(String[] arg)
	{		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
				    new MainFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

}
