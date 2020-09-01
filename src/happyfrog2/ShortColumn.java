/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package happyfrog2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Admin
 */
public class ShortColumn extends GameObject {
private int width=40;
private int height=120;

    Handler handler;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ShortColumn(double x, double y, ID id) {
        super(x, y, id);
        velX = -1.0;
        this.handler = handler;
    }

    @Override
    public void tick() {
        x += velX;
//        y += velY;
        x = (double) Game.clamp((int) x, 0, Game.WIDTH);//khong cho di qua bien gioi
//        y = Game.clamp((int) y, 0, Game.HEIGHT - 32-65);

    }

    @Override
    public void render(Graphics2D g) {
        if (id == ID.ShortColumn) {
            g.setColor(Color.red);
        }
        g.fillRect((int) x, (int) y, width,height);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }
}
