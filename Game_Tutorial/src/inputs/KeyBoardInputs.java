package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;

public class KeyBoardInputs implements KeyListener {
	
	private GamePanel gamePanel;
	
	public KeyBoardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key =  e.getKeyCode();
		if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
			gamePanel.changeXDelta(-5);
		}else if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP){
			gamePanel.changeYDelta(-5);
		}else if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
			gamePanel.changeXDelta(5);
		}else if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
			gamePanel.changeYDelta(5);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

}
