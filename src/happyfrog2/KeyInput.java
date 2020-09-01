/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package happyfrog2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Admin
 */
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Admin
 */
public class KeyInput extends KeyAdapter {

    private Handler handler;
    private boolean uP = false;
    private boolean dP = false;
    private boolean lP = false;
    private boolean rP = false;
    private boolean[] keyDown = new boolean[4];

    public KeyInput(Handler handler) {
        this.handler = handler;
        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempGameObject = handler.objects.get(i);
            if (tempGameObject.getId() == ID.Player) {
                
                if (key == KeyEvent.VK_W) {
                    if (tempGameObject.getY() == 0) {
                        tempGameObject.setVelY(0.5);
                      
                    } else {
                        tempGameObject.setVelY(-1.0);
                    }
                    break;
                }
            }
        }

    }

//    public void keyReleased(KeyEvent e) {//neu khong set released object se troi khong dung lai
//        int key = e.getKeyCode();
//        for (int i = 0; i < handler.objects.size(); i++) {
//            GameObject tempGameObject = handler.objects.get(i);
//            if (tempGameObject.getId() == ID.Player) {
//                if (key == KeyEvent.VK_W) {                           
//                    if (tempGameObject.getY()==0) {
//                        tempGameObject.setVelY(tempGameObject.getVelY()+1);
//                    break;
//                    }
//                }
//                
//           }
//
//        }
//    }
}
