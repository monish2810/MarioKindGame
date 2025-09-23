package main;

public class GameClass implements Runnable {
    @SuppressWarnings("unused")
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private volatile Thread gameThread; // gameloop
    private final int FPS = 120;
    private final int UPS = 200;
    private volatile boolean running = true;

    public GameClass() {
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    public void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void stopGameLoop() {
        running = false;
    }
    
    public void update() {
		gamePanel.updateGame();
		
	}

    @Override
    public void run() {
        double timePerFrame = 1_000_000_000.0 / FPS;
        double timePerUpdate = 1_000_000_000.0/UPS;
        long lastFrame = System.nanoTime();
        long now;
        
        long previousTime = System.nanoTime();
        
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        
        double deltaU = 0;
        double deltaF = 0;
        
        
        while (running) {
            now = System.nanoTime();
            long currTime = System.nanoTime();
            deltaU += (currTime - previousTime)/timePerUpdate;
            deltaF += (currTime - previousTime)/timePerFrame;
            previousTime = currTime;
            
            
            if(deltaU >= 1) {
            	update();
            	updates++;
            	deltaU--;
            }
            
            if(deltaF >= 1) {
            	gamePanel.repaint();
            	frames++;
            	deltaF--;
            }
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames+" | UPS: "+updates);
                frames = 0;
                updates = 0;
            }
        }
    }
}
