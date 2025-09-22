package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import inputs.KeyBoardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel {
    
    private final int NUM_FRAMES = 6; // number of pirate frames


    private MouseInputs mouseInputs;
    private int xDelta = 100, yDelta = 100;

    private BufferedImage img;
    private BufferedImage[] pirateMovement; 

    private int aniTick, aniIndex, aniSpeed = 15; // speed = higher → slower animation
    
    public GamePanel() {
        mouseInputs = new MouseInputs(this);

        importImg();
        loadAnimations();
        
        setPanelSize();

        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

        setFocusable(true); // needed for keyboard input
    }

    private void loadAnimations() {
        int frameWidth = img.getWidth() / NUM_FRAMES;
        int frameHeight = img.getHeight(); 

        pirateMovement = new BufferedImage[NUM_FRAMES];
        for (int i = 0; i < NUM_FRAMES; i++) {
            pirateMovement[i] = img.getSubimage(i * frameWidth, 0, 32, 32);
        }
    }

    private void importImg() {
        try (InputStream impImg = getClass().getResourceAsStream("/Character_1.png")) {
            if (impImg != null) {
                img = ImageIO.read(impImg);
            } else {
                System.err.println("Image not found: /Character_1.png");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(800, 600);
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
        updateAnimationTick();
        if (pirateMovement != null) {
            g.drawImage(pirateMovement[aniIndex], xDelta, yDelta, null);
        }
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= NUM_FRAMES) {
                aniIndex = 0;
            }
        }
    }
}
