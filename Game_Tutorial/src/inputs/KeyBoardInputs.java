package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import utils.Constants.PlayerDirections;
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
            gamePanel.getPlayer().updateDirection(PlayerDirections.LEFT);
        } else if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
        	gamePanel.getPlayer().updateDirection(PlayerDirections.UP);
        } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
        	gamePanel.getPlayer().updateDirection(PlayerDirections.RIGHT);
        } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
        	gamePanel.getPlayer().updateDirection(PlayerDirections.DOWN);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT
            || key == KeyEvent.VK_W || key == KeyEvent.VK_UP
            || key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT
            || key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
        	gamePanel.getPlayer().isPlayerMoving(false);
        }
    }
}
