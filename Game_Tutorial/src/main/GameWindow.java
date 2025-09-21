package main;

import javax.swing.JFrame;

public class GameWindow extends JFrame{
	public GameWindow(GamePanel gamePanel) {
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*After setDefaultCloseOperation we should place 
		 *  setVisible otherwise we get some problems 
		 *  the screen will not render*/
		this.add(gamePanel);
		this.setLocationRelativeTo(null);
		this.setVisible(true); 
		
		
	}
	
}
