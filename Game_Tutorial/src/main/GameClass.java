package main;

public class GameClass implements Runnable{
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread; // gameloop
	private final int FPS = 120; //setting required fps
	
	
	public GameClass(){
		gamePanel = new GamePanel();
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus(); // makes sure keyboard input works
		startGameLoop();
	}
	
	public void startGameLoop() {
		gameThread = new Thread(this); // pass this class (Runnable) to Thread
		gameThread.start();
	}
	
	int frames = 0;
	private long lastCheck = 0;
	
	@Override
	public void run() {
		double timePerFrame = 1_000_000_000.0 / FPS; // how many nanoseconds per frame
	    long lastFrame = System.nanoTime();
	    long now;

	    while(true) { // infinite game loop
	        now = System.nanoTime();

	        if(now - lastFrame >= timePerFrame) {
	            gamePanel.repaint(); // redraws screen
	            lastFrame = now;    // update last frame time
	            frames++;
	        }

	        // FPS counter
	        if(System.currentTimeMillis() - lastCheck >= 1000) {
	            lastCheck = System.currentTimeMillis();
	            System.out.println("FPS: " + frames);
	            frames = 0;
	        }
	    }
		
		
	}
}
