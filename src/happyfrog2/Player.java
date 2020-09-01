/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package happyfrog2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Admin
 */
public class Player extends GameObject {

    STATE playerState;
    Handler handler;
    Game game;
    BufferedImage img;
    private final double MAX_SPEED = 11;
    private double gravity = 0.012;
    private boolean jumping = false;
    private boolean falling = true;
    private Object ImagesFactory;

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public STATE getPlayerState() {
        return playerState;
    }

    public enum STATE {

        Live,
        Dead
    };

    public Player() {
        super(0, 0, null);
    }

    public Player(double x, double y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        playerState = STATE.Live;

    }

    @Override
    public void tick() {
        //  x += velX;
        y += velY;
        //x = (double) Game.clamp((int) x, 0, Game.WIDTH - 32);//khong cho di qua bien gioi
        y = (double) Game.clamp((int) y, 0, Game.HEIGHT - 32);
        if (jumping || falling) {
            if (velY > MAX_SPEED) {
                velY = MAX_SPEED;
            }
            velY += gravity;
        }

     //   System.out.println(velY);
        collision();

    }

    @Override
    public void render(Graphics2D g) {

        if (id == ID.Player) {
            g.setColor(Color.BLUE);
           // g.drawImage(getPlayerImg(), (int) x, (int) y, null);
        }

        g.fillRect((int) x, (int) y, 20, 0);
    }

    private void collision() {
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject temp = handler.objects.get(i);
            if (temp.getId() == ID.ShortColumn || temp.getId() == ID.Column) {
                if (getBounds().intersects(temp.getBounds())) {
                    System.out.println("loss");
                    playerState = STATE.Dead;
                }
            }

        }

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 35, 35);
    }

    public Image getPlayerImg() {
        ImageIcon ic = new ImageIcon("F:\\KY 4\\prj321\\HappyFrog2\\src\\newFrog (1).jpg");
        return ic.getImage();
    }
}
