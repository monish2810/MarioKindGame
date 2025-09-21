package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyBoardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private int xDelta = 0, yDelta = 0;

    private BufferedImage img, subImage;

    public GamePanel() {
        mouseInputs = new MouseInputs(this);

        importImg();
        setPanelSize();

        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

        setFocusable(true); // needed for keyboard input
    }

    private void importImg() {
        try (InputStream impImg = getClass().getResourceAsStream("/spriteatlas.png")) {
            if (impImg != null) {
                img = ImageIO.read(impImg);
            } else {
                System.err.println("Image not found: /spriteatlas.png");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void changeXDelta(int value) {
        xDelta += value;
        repaint();
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
        super.paintComponent(g);

        if (img != null) {
        	subImage = img.getSubimage(2*60,1*32,64, 32);
            g.drawImage(subImage, xDelta, yDelta,128,80, null);

            // OR stretch image to fill the panel:
            // g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
        }

        // Draw rectangle on top
        //g.fillRect(100 + xDelta, 100 + yDelta, 200, 50);
    }
}
