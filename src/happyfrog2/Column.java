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
public class Column extends GameObject {

    Handler handler;
    private int width = 40;
    private int height;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Column(int height, double x, double y, ID id) {
        super(x, y, id);
        this.height = height;
        velX = -0.002;
        this.handler = handler;
    }

    public Column(double x, double y, ID id) {
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
        if (id == ID.Column) {
            g.setColor(Color.red);
        }
        g.fillRect((int) x, (int) y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }
}
