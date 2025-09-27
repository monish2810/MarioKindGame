package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import utils.Constants.PlayerConstants;

public class Player extends Entities {

    private final int ACTIONS = 7;
    private final int NUM_FRAMES = 6;
    private float xDelta = 100.0f, yDelta = 100.0f;
    private BufferedImage[][] characterStatic;
    private int playerAction = PlayerConstants.IDLE;
    private boolean isMoving = false;
    private int aniTick, aniIndex, aniSpeed = 12;
    private final float SPEED = 1.5f;
    private boolean left, up, right, bottom;
    private boolean is_attacking = false;
    
    public Player(float x, float y) {
        super(x, y);
        xDelta = (int)x; yDelta = (int)y;
        loadAnimations();
    }

    private void loadAnimations() {
        characterStatic = new BufferedImage[ACTIONS][NUM_FRAMES];
        String[] actionStrs = {"IDLE", "WALK", "RUN", "HURT", "DIE", "JUMP", "ATTACK"};
        try {
            for (int action = 0; action < ACTIONS; action++) {
                for (int frame = 0; frame < NUM_FRAMES; frame++) {
                    String path = "/2/2_entity_000_" + actionStrs[action] + "_00" + (frame + 1) + ".png";
                    BufferedImage img = ImageIO.read(getClass().getResourceAsStream(path));
                    characterStatic[action][frame] = img;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= NUM_FRAMES) {
            	aniIndex = 0;
            	is_attacking = false;
            }
        }
    }
    
    private void setAnimation() {
    	int startAni = playerAction;
        if (isMoving) playerAction = PlayerConstants.WALK;
        else playerAction = PlayerConstants.IDLE;
        
        if(isAttacking()) {
        	playerAction = PlayerConstants.ATTACK;
        }
        
        if(startAni != playerAction) {
        	resetAnimation();
        }
    }

    private void resetAnimation() {
		aniTick =0;
		aniIndex = 0;
		
	}

	private void updatePosition() {
        isMoving = false;
        
        if(isLeft() && !isRight()) {
        	xDelta -= SPEED;
        	isMoving = true;
        }else if(!isLeft() && isRight()) {
        	xDelta += SPEED;
        	isMoving = true;
        }
        
        if(isUp() && !isBottom()) {
        	yDelta -= SPEED;
        	isMoving = true;
        }else if(!isUp() && isBottom()) {
        	yDelta += SPEED;
        	isMoving = true;
        }
    }
    
    public void stopWhenLostWindowFocus() {
    	left = false;
    	up = false;
    	right = false;
    	bottom = false;
    }
    
    public void updateGame() {
        updateAnimationTick();
        setAnimation();
        updatePosition();
    }

    public void draw(Graphics g) {
        if (characterStatic != null &&
            playerAction >= 0 && playerAction < ACTIONS &&
            aniIndex >= 0 && aniIndex < NUM_FRAMES &&
            characterStatic[playerAction][aniIndex] != null) {
            g.drawImage(characterStatic[playerAction][aniIndex],(int)xDelta,(int)yDelta, 128, 128, null);
        }
    }

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isBottom() {
		return bottom;
	}

	public void setBottom(boolean bottom) {
		this.bottom = bottom;
	}

	public boolean isAttacking() {
		return is_attacking;
	}

	public void setIsAttacking(boolean is_attacking) {
		this.is_attacking = is_attacking;
	}

    
    
}
