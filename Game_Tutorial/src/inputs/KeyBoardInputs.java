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
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            gamePanel.getPlayer().setLeft(true);
        } else if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
        	gamePanel.getPlayer().setUp(true);
        } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
        	gamePanel.getPlayer().setRight(true);
        } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
        	gamePanel.getPlayer().setBottom(true);
        } else if(key == KeyEvent.VK_SPACE) {
        	gamePanel.getPlayer().setIsAttacking(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
        	gamePanel.getPlayer().setLeft(false);
        }
        if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
        	gamePanel.getPlayer().setUp(false);
        }
        if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
        	gamePanel.getPlayer().setRight(false);
        }
        if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
        	gamePanel.getPlayer().setBottom(false);
        }
        if(key == KeyEvent.VK_SPACE) {
        	gamePanel.getPlayer().setIsAttacking(false);
        }
    }
}
