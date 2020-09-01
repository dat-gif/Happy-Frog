/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package happyfrog2;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Spawn {

    LinkedList<GameObject> objects = new LinkedList<>();
    private boolean fistBorn = true, hadLoad = false, exsitPlayer = true;
    private final Handler handler;
    private Handler handlerLoad;
    private final HUD hud;
    GameSave gameSave = new GameSave();
    private final int checkFile = gameSave.checkFile();

    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    int i = 0;

    public void tick() {
        //load data from file 
        if (!hadLoad) {
            if (gameSave.checkFile() == 1) {
                try {
                    gameSave.loadScore(hud);
                    objects = gameSave.loadGameObjectsd();
                    for (GameObject get : objects) {
                        handler.addObject(get);
                    }
                    fistBorn = false;
                } catch (IOException ex) {
                    Logger.getLogger(Spawn.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            exsitPlayer = false;
            hadLoad = true;
            System.out.println("------------------load");

            for (int j = 0; j < handler.objects.size(); j++) {
                GameObject temp = handler.objects.get(i);
                if (temp.getId() == ID.Player) {
                    Player tempPlayer = new Player(temp.getX(), temp.getY(), ID.Player, handler);
                    handler.removeObject(temp);
                    handler.addObject(tempPlayer);
                }
            }
        }

        //create data if file not exit
        if (fistBorn) {
            if (!exsitPlayer) {
                handler.addObject(new Player(200.0, 200.0, ID.Player, handler));
            }

            bornRandom();
            fistBorn = false;
            System.out.println("--------bore");
        } else {
            for (int i = 0;
                    i < handler.objects.size();
                    i++) {
                GameObject temp = handler.objects.get(i);
                if (temp.getId() == ID.MarkPoint && temp.getX() == Game.WIDTH - 300) {
                    bornRandom();
                }
            }
        }
//delete phần thừa
        for (int i = 0;
                i < handler.objects.size();
                i++) {
            GameObject temp = handler.objects.get(i);
            if (temp.getId() == ID.Column) {
                if (temp.getX() == 0) {
                    handler.removeObject(temp);
                }
            }
        }
        for (int i = 0;
                i < handler.objects.size();
                i++) {
            GameObject temp = handler.objects.get(i);
            if (temp.getId() == ID.MarkPoint) {
                if (temp.getX() == 0) {
                    handler.removeObject(temp);
                }
            }
        }

    }

    public void bornRandom() {
        Random random = new Random();
        int maxMarkPoint = 250;
        int minMarkPoint = 100;
        int maxHeightColumn = 200, minHeightColunm = 150;
        int heightTop = random.nextInt((maxHeightColumn - minHeightColunm) + 1) + minHeightColunm;
        handler.addObject(new Column(heightTop, Game.WIDTH, 0.0, ID.Column));
        int heightMarkPoint = random.nextInt((maxMarkPoint - minMarkPoint) + 1) + minMarkPoint;
        int heightBottom = Game.HEIGHT - heightTop - heightMarkPoint;
        handler.addObject(new Column(heightBottom, Game.WIDTH, Game.HEIGHT - heightBottom, ID.Column));
        handler.addObject(new MarkPoint((double) Game.WIDTH, 0.0, ID.MarkPoint));

    }

}
