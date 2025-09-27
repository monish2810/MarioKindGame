package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import inputs.KeyBoardInputs;
import entities.Player;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {

    private Player player;

    public GamePanel() {
        player = new Player(100, 100); 
        setPanelSize();
        addKeyListener(new KeyBoardInputs(this));
        setFocusable(true);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(800, 600);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }
    
    public void updateGame() {
        player.updateGame();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g);
    }

    // Forward movement calls to Player
    public void updateDirection(int direction) {
        player.updateDirection(direction);
    }

    public void isPlayerMoving(boolean isMoving) {
        player.isPlayerMoving(isMoving);
    }
}
