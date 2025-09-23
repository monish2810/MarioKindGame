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

import utils.Constants.PlayerConstants;
import utils.Constants.PlayerDirections;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	private final int ACTIONS = 7; // number of actions that character can perform
	private final int NUM_FRAMES = 6; // number of pirate frames
	

    //private MouseInputs mouseInputs;
    private int xDelta = 100, yDelta = 100;

    private BufferedImage[][] characterStatic; 

    private int aniTick, aniIndex, aniSpeed = 15; // speed = higher → slower animation
    
    private int playerAction = PlayerConstants.IDLE;
    private boolean isMoving  = false;
    private int movingDirection = -1;
    
    public GamePanel() {
        //mouseInputs = new MouseInputs(this);
        loadAnimations();
        
        setPanelSize();

        addKeyListener(new KeyBoardInputs(this));
        //addMouseListener(mouseInputs);
        //addMouseMotionListener(mouseInputs);

        setFocusable(true); // needed for keyboard input
    }

    private void loadAnimations() {
        characterStatic = new BufferedImage[ACTIONS][NUM_FRAMES];
        try {
        	//IDLE
			characterStatic[0][0] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_IDLE_001.png"));
			characterStatic[0][1] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_IDLE_002.png"));
			characterStatic[0][2] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_IDLE_003.png"));
			characterStatic[0][3] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_IDLE_004.png"));
			characterStatic[0][4] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_IDLE_005.png"));
			characterStatic[0][5] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_IDLE_006.png"));
			//WALK
			characterStatic[1][0] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_WALK_001.png"));
			characterStatic[1][1] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_WALK_002.png"));
			characterStatic[1][2] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_WALK_003.png"));
			characterStatic[1][3] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_WALK_004.png"));
			characterStatic[1][4] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_WALK_005.png"));
			characterStatic[1][5] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_WALK_006.png"));
			//RUN
			characterStatic[2][0] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_RUN_001.png"));
			characterStatic[2][1] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_RUN_002.png"));
			characterStatic[2][2] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_RUN_003.png"));
			characterStatic[2][3] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_RUN_004.png"));
			characterStatic[2][4] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_RUN_005.png"));
			characterStatic[2][5] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_RUN_006.png"));
			//HURT
			characterStatic[3][0] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_HURT_001.png"));
			characterStatic[3][1] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_HURT_002.png"));
			characterStatic[3][2] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_HURT_003.png"));
			characterStatic[3][3] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_HURT_004.png"));
			characterStatic[3][4] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_HURT_005.png"));
			characterStatic[3][5] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_HURT_006.png"));
			//DIE
			characterStatic[4][0] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_DIE_001.png"));
			characterStatic[4][1] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_DIE_002.png"));
			characterStatic[4][2] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_DIE_003.png"));
			characterStatic[4][3] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_DIE_004.png"));
			characterStatic[4][4] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_DIE_005.png"));
			characterStatic[4][5] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_DIE_006.png"));
			//JUMP
			characterStatic[5][0] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_JUMP_001.png"));
			characterStatic[5][1] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_JUMP_002.png"));
			characterStatic[5][2] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_JUMP_003.png"));
			characterStatic[5][3] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_JUMP_004.png"));
			characterStatic[5][4] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_JUMP_005.png"));
			characterStatic[5][5] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_JUMP_006.png"));
			//ATTACK
			characterStatic[6][0] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_ATTACK_001.png"));
			characterStatic[6][1] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_ATTACK_002.png"));
			characterStatic[6][2] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_ATTACK_003.png"));
			characterStatic[6][3] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_ATTACK_004.png"));
			characterStatic[6][4] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_ATTACK_005.png"));
			characterStatic[6][5] = ImageIO.read(getClass().getResourceAsStream("/2/2_entity_000_ATTACK_006.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void updateDirection(int direction) {
    	movingDirection = direction;
    	isMoving = true;
    }
    
    public void isPlayerMoving(boolean isMoving) {
    	this.isMoving = isMoving;
    	if(!isMoving) {
    		movingDirection = -1;
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

//    public void mouseChangeXYDelta(int x, int y) {
//        this.xDelta = x - 100;
//        this.yDelta = y - 100;
//        repaint();
//    }
    
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
    	if(isMoving) {
    		playerAction = PlayerConstants.WALK;
    	}else {
    		playerAction = PlayerConstants.IDLE;
    	}
    }

    private void updateDirection() {
		if(isMoving) {
			switch(movingDirection) {
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
			}
		}
		
	}
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateAnimationTick();
        setAnimation();
        updateDirection();
        if (characterStatic != null) {
            g.drawImage(characterStatic[playerAction][aniIndex], xDelta, yDelta, 128, 128, null);
        }
    }
}
