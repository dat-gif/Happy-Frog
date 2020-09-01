/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package happyfrog2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Admin
 */
public class Screen extends MouseAdapter {

    private Handler handler;
    private Game game;
    private HUD hud;
    private Spawn spawn;
    private GameSave gameSave = new GameSave();
    private boolean isStart = false;

    public int getTestCount() {
        return testCount;
    }

    public void setTestCount(int testCount) {
        this.testCount = testCount;
    }

    public Screen(Game game, Handler handler, HUD hud, Spawn spawn) {
        this.game = game;
        this.handler = handler;
        this.hud = hud;
        this.spawn = spawn;
    }

    public void mouseReleased(MouseEvent e) {
        if (game.getGameState() == game.gameState.READY) {

        }
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (mouseOver(mx, my, 200, 100, 300, 100)) {
            if (game.getGameState()==Game.STATE.End) {
                  hud.setPlayerState(true);
            hud.setScore(0);
            game.stop();
            game = new Game();
            try {
                gameSave.deleteAllFile();
            } catch (IOException ex) {
                Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
           
        } else if (mouseOver(mx, my, 250, 280, 200, 100)) {
            if (!isStart) {

                game.setGameState(Game.STATE.READY);
                tick();
                isStart = true;
            }

        }
    }

    private boolean mouseOver(int mx, int my, int x, int y, int witdh, int height) {
        if (mx > x && mx < x + witdh) {
            if (my > y && my < y + height) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    private int testCount = 4;

    public void tick() {
        if (game.getGameState() == game.gameState.READY) {
            int second;
            second = 3;
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                Scanner keyb = new Scanner(System.in);
                int second = 4;
                int turnOn;

                @Override
                public void run() {
                    setTestCount(second);
                    turnOn = 1;
                    while (turnOn != 0) {
                        second--;
                        System.out.println(second);
                        setTestCount(second);
                        System.out.println(testCount);
                        if (getTestCount() < 0) {
                            turnOn = 0;
                            game.setGameState(Game.STATE.RUN);
                            t.cancel();
                            t.purge();
                            return;
                        }
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, 0, 1000);
        }

    }

    public void render(Graphics2D g) {

        if (game.getGameState() == game.gameState.End) {
            Font font = new Font("arial", 1, 50);
            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawRect(200, 100, 300, 100);
            g.drawString("Try agian ", 250, 150);
            g.drawString("ponit: " + hud.getScore(), 250, 300);
            g.drawString("You got:_", 50, 350);
            if (hud.getScore() < 10) {
                g.fillRect(0, 0, 0, 0);
                g.setColor(new Color(162, 66, 0));
                g.drawString("this happy potato ", 270, 350);
                g.drawImage(getPlayerImg(), 510, 360, null);
            }
            if (hud.getScore() >= 10 && hud.getScore() < 20) {
                g.fillRect(0, 0, 0, 0);
                g.setColor(new Color(162, 66, 0));
                g.drawString("BRONZE MEDAL ", 270, 350);
            } else if (hud.getScore() >= 20 && hud.getScore() < 30) {
                g.fillRect(0, 0, 0, 0);
                g.setColor(Color.white);
                g.drawString("SIlLVER MEDAL ", 270, 350);
            } else if (hud.getScore() >= 30 && hud.getScore() < 40) {
                g.fillRect(0, 0, 0, 0);
                g.setColor(Color.YELLOW);
                g.drawString("GOLD MEDAL ", 270, 350);
            } else if (hud.getScore() >= 40) {
                g.fillRect(0, 0, 0, 0);
                g.setColor(Color.PINK);
                g.drawString("**platinum medal***", 280, 350);
            }
        }

        if (game.getGameState() == game.gameState.START) {
            Font font = new Font("arial", 1, 50);
            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawRect(250, 280, 200, 100);
            g.drawString("START", 270, 350);
            for (int i = 0; i < handler.objects.size(); i++) {
                GameObject gameObject = handler.objects.get(i);
                gameObject.render(g);
            }
        }

        if (game.getGameState() == game.gameState.READY) {
            Font font = new Font("arial", 1, 50);
            g.setFont(font);
            if (getTestCount() == 3) {
                g.setColor(Color.GREEN);
                g.drawRect(0, 0, 0, 0);
            }
            if (getTestCount() == 2) {
                g.setColor(Color.yellow);
                g.drawRect(0, 0, 0, 0);
            }
            if (getTestCount() == 1) {
                g.setColor(Color.orange);
                g.drawRect(0, 0, 0, 0);
            }
            if (getTestCount() == 0) {
                g.setColor(Color.RED);
                g.drawRect(0, 0, 0, 0);
            }

            g.drawString(String.format("%10s%d", "READY IN :", getTestCount()), 270, 350);

            // System.out.println("sss" + testCount);
        }

    }

    public Image getPlayerImg() {
        ImageIcon ic = new ImageIcon("F:\\KY 4\\prj321\\HappyFrog2\\src\\potato.png");
        return ic.getImage();
    }

}
