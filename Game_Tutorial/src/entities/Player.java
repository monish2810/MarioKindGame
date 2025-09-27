package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import utils.Constants.PlayerConstants;
import utils.Constants.PlayerDirections;

public class Player extends Entities {

    private final int ACTIONS = 7;
    private final int NUM_FRAMES = 6;

    private int xDelta = 100, yDelta = 100;
    private BufferedImage[][] characterStatic;
    private int playerAction = PlayerConstants.IDLE;
    private boolean isMoving = false;
    private int aniTick, aniIndex, aniSpeed = 12;
    private int movingDirection = -1;

    public Player(float x, float y) {
        super(x,y);
        loadAnimations();
    }

    private void loadAnimations() {
        characterStatic = new BufferedImage[ACTIONS][NUM_FRAMES];
        try {
            String[] actionStrs = {"IDLE", "WALK", "RUN", "HURT", "DIE", "JUMP", "ATTACK"};
            for (int action = 0; action < ACTIONS; action++) {
                for (int frame = 0; frame < NUM_FRAMES; frame++) {
                    String path = "/2/2_entity_000_" + actionStrs[action] + "_00" + (frame + 1) + ".png";
                    characterStatic[action][frame] = ImageIO.read(getClass().getResourceAsStream(path));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateDirection(int direction) {
        movingDirection = direction;
        isMoving = true;
    }

    public void isPlayerMoving(boolean isMoving) {
        this.isMoving = isMoving;
        if (!isMoving) {
            movingDirection = -1;
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

    private void setAnimation() {
        if (isMoving) {
            playerAction = PlayerConstants.WALK;
        } else {
            playerAction = PlayerConstants.IDLE;
        }
    }

    private void updatePosition() {
        if (isMoving) {
            switch (movingDirection) {
                case PlayerDirections.LEFT: xDelta -= 2; break;
                case PlayerDirections.UP: yDelta -= 2; break;
                case PlayerDirections.RIGHT: xDelta += 2; break;
                case PlayerDirections.DOWN: yDelta += 2; break;
            }
        }
    }

    public void updateGame() {
    	try{
	        updateAnimationTick();
	        setAnimation();
	        updatePosition();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    // Drawing function
    public void draw(Graphics g) {
        if (characterStatic != null &&
            playerAction >= 0 && playerAction < ACTIONS &&
            aniIndex >= 0 && aniIndex < NUM_FRAMES &&
            characterStatic[playerAction][aniIndex] != null) {
            g.drawImage(characterStatic[playerAction][aniIndex], xDelta, yDelta, 128, 128, null);
        }
    }
}
