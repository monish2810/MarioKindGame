package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyBoardInputs;
import utils.Constants.PlayerConstants;
import utils.Constants.PlayerDirections;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {

    private final int ACTIONS = 7; // number of actions
    private final int NUM_FRAMES = 6; // number of frames per action

    private int xDelta = 100, yDelta = 100;
    private BufferedImage[][] characterStatic;
    private int aniTick, aniIndex, aniSpeed = 12;
    private int playerAction = PlayerConstants.IDLE;
    private boolean isMoving = false;
    private int movingDirection = -1;

    public GamePanel() {
        loadAnimations();
        setPanelSize();
        addKeyListener(new KeyBoardInputs(this));
        setFocusable(true); // Needed for keyboard input
    }

    private void loadAnimations() {
        characterStatic = new BufferedImage[ACTIONS][NUM_FRAMES];
        try {
            for(int action = 0; action < ACTIONS; action++) {
                for(int frame = 0; frame < NUM_FRAMES; frame++) {
                    String[] actionStrs = {"IDLE", "WALK", "RUN", "HURT", "DIE", "JUMP", "ATTACK"};
                    String path = "/2/2_entity_000_" + actionStrs[action] + "_00" + (frame + 1) + ".png";
                    characterStatic[action][frame] = ImageIO.read(getClass().getResourceAsStream(path));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(800, 600);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void updateDirection(int direction) {
        movingDirection = direction;
        isMoving = true;
    }

    public void isPlayerMoving(boolean isMoving) {
        this.isMoving = isMoving;
        movingDirection = -1;
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

    private void setAnimation() {
        if (isMoving) {
            playerAction = PlayerConstants.WALK;
        } else {
            playerAction = PlayerConstants.IDLE;
        }
    }

    private void updateDirection() {
        if (isMoving) {
            switch (movingDirection) {
                case PlayerDirections.LEFT:
                    xDelta -= 2;
                    break;
                case PlayerDirections.UP:
                    yDelta -= 2;
                    break;
                case PlayerDirections.RIGHT:
                    xDelta += 2;
                    break;
                case PlayerDirections.DOWN:
                    yDelta += 2;
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
	        updateAnimationTick();
	        setAnimation();
	        updateDirection();
	        if (characterStatic != null
	                && playerAction >= 0 && playerAction < ACTIONS
	                && aniIndex >= 0 && aniIndex < NUM_FRAMES
	                && characterStatic[playerAction][aniIndex] != null) {
	            g.drawImage(characterStatic[playerAction][aniIndex], xDelta, yDelta, 128, 128, null);
	        }
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }
}
