package main;

public class GameClass {
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	public GameClass(){
		gamePanel = new GamePanel();
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();//for inputs to work
		
	}
}
