package main;

import java.awt.Graphics;
import javax.swing.JPanel;
import inputs.KeyBoardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel {
    
    private MouseInputs mouseInputs;
    private int xDelta = 0, yDelta = 0;
    
    public GamePanel() {
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

        setFocusable(true); // needed for keyboard input
    }

    public void changeXDelta(int value) {
        xDelta += value;
        repaint(); // only request redraw when something changes
    }

    public void changeYDelta(int value) {
        yDelta += value;
        repaint();
    }

    public void mouseChangeXYDelta(int x, int y) {
        this.xDelta = x - 100; 
        this.yDelta = y - 100;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // clears the panel before drawing
        g.fillRect(100 + xDelta, 100 + yDelta, 200, 50); // draw rectangle
    }
}
