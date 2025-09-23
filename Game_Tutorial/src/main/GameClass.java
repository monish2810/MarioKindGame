package main;

public class GameClass implements Runnable {
    @SuppressWarnings("unused")
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private volatile Thread gameThread; // gameloop
    private final int FPS = 120;
    private volatile boolean running = true;
    int frames = 0;
    private long lastCheck = 0;

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

    @Override
    public void run() {
        double timePerFrame = 1_000_000_000.0 / FPS;
        long lastFrame = System.nanoTime();
        long now;

        while (running) {
            now = System.nanoTime();
            if (now - lastFrame >= timePerFrame) {
                gamePanel.repaint();
                lastFrame = now;
                frames++;
            }
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
    }
}
