/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package happyfrog2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.image.BufferStrategy;
import java.nio.Buffer;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Admin
 */
public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1550691097823471818L;
    public static final int WIDTH = 700, HEIGHT = 500;
    private Thread thread;
    private boolean running = false, hadRunning = false;
    private Spawn spawner;
    public STATE gameState = STATE.START;
    public GameSave gameSave = new GameSave();

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
    private Handler handler;
    private HUD hud;
    private Screen screen;
    private Game game;

    public enum STATE {

        RUN,
        START,
        READY,
        End
    };

    public Game() {
        game = this;
        handler = new Handler();//nen de tao hander len truoc boi tao window truoc hander co the gay loi
        hud = new HUD(handler);

        spawner = new Spawn(handler, hud);
        screen = new Screen(this, handler, hud, spawner);

        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(screen);
        Window window = new Window(WIDTH, HEIGHT, "HappyFrog", this, hud);

    }

    public STATE getGameState() {
        return gameState;
    }

    public void setGameState(STATE gameState) {
        this.gameState = gameState;
    }

    public void run() {
        //game loop// tìm hiểu thêm về game loop
        this.requestFocus();///
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;// the amount of time (in nanoseconds) that one frame should have in a 60FPS
        double ns = 100000000 / amountOfTicks;
        double delta = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
            }
//            System.out.println("run");
        }
        stop();
    }

    private void tick() {//update

        if (gameState == STATE.RUN) {

            handler.tick();
            hud.tick();
            spawner.tick();
            if (!hud.playerState) {
                gameState = STATE.End;
            }

        } else if (gameState == STATE.End) {
          
            for (int i = 0; i < handler.objects.size(); i++) {
                GameObject gameObject = handler.objects.get(i);
                handler.removeObject(gameObject);
            }
        } else if (gameState == STATE.START) {
            spawner.tick();

        } else if (gameState == STATE.READY) {
      
        }

    }

//        
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();//lớp lấy hình để làm video
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render((Graphics2D) g);
        //  hud.render(g);//test thoi
        if (gameState == STATE.End) {
            screen.render((Graphics2D) g);
        } else if (gameState == STATE.START) {
            screen.render((Graphics2D) g);
            handler.render((Graphics2D) g);
        } else if (gameState == STATE.READY) {
            screen.render((Graphics2D) g);
        
        }

        g.dispose();
        bs.show();

    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
        hadRunning = true;

    }

    public synchronized void stop() {
        try {
            running = false;
            hadRunning = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int clamp(int var, int min, int max) {
        if (var >= max) {
            return var = max;
        } else if (var <= min) {
            return var = min;
        } else {
            return var;
        }

    }

    public static void main(String[] args) {
        new Game();
    }

}
