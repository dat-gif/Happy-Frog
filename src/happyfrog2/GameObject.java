/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package happyfrog2;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Admin
 */
public abstract class GameObject {

    protected double x, y;//toa do
    protected ID id;//id cua nguoi choi hay linh
    protected double velX, velY;//toc do cua 2 hunong x,y

    public GameObject(double x, double y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;

    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public abstract void tick();

    public abstract void render(Graphics2D g);
    public abstract Rectangle getBounds();
}
