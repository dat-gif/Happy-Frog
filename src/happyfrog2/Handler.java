/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package happyfrog2;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

/**
 *
 * @author Admin
 */
public class Handler {

    LinkedList<GameObject> objects = new LinkedList<GameObject>();

    public void tick() {//cho di qua xu ly tat ca cac object cho list
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);
            tempObject.tick();//voi tick rieng cua tuong loai gameObject
        }
    }

    public void render(Graphics2D g) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject temObject = objects.get(i);
            temObject.render(g);//nhu tich
        }
    }

    public void addObject(GameObject object) {
        this.objects.add(object);
    }

    public void removeObject(GameObject object) {
        this.objects.remove(object);
    }
}
