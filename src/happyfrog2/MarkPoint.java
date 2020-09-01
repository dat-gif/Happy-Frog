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
public class MarkPoint extends GameObject {

    Handler handler;

    private int width = 10;
    private int height = Game.HEIGHT;

    public MarkPoint(double x, double y, ID id) {
        super(x, y, id);
        velX = -1.0;
        this.handler = handler;
    }

    @Override
    public void tick() {
        x += velX;
        x = (double) Game.clamp((int) x, 0, Game.WIDTH);
    }

    @Override
    public void render(Graphics2D g) {
        
        if (id == ID.MarkPoint) {
            g.setColor(new Color(1f,.5f,1f,.5f));
            g.drawRect((int) x, (int) y, width, height);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }

}
