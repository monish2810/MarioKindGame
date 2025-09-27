package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import inputs.KeyBoardInputs;
import entities.Player;

public class GamePanel extends JPanel {

    private Player player;

    public GamePanel() {
        player = new Player(100, 100);
        addKeyListener(new KeyBoardInputs(this));
        setFocusable(true);
    }

    // Use this for layout managers
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    public void updateGame() {
        player.updateGame();
    }

    public Player getPlayer() {
    	return player;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g);
    }
}
