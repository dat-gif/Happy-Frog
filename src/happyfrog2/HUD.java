/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package happyfrog2;

import java.awt.Graphics;
import javax.swing.JLabel;

/**
 *
 * @author Admin
 */
public class HUD {//Heads Up Display{

    Player player;
    boolean playerState = true;
    Handler handler;
    static int score = 0;
    Window window = new Window();

    public void setPlayerState(boolean playerState) {
        this.playerState = playerState;
    }

    int border;

    public HUD() {
    }

    public HUD(Handler handler) {
        this.handler = handler;

    }

    public int getScore() {

        return score;
    }

    public static void setScore(int score) {
        HUD.score = score;
    }

    public void tick() {
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempo = handler.objects.get(i);
            if (tempo.getId() == ID.Player) {
                border = (int) tempo.getX();
                player = (Player) tempo;
                if (player.getPlayerState() == Player.STATE.Dead) {
                    playerState = false;
                }
            }
        }
   
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempo = handler.objects.get(i);
            if (tempo.getId() == ID.MarkPoint) {
                if (tempo.getX() == border) {
                score = score + 1;                               
                }
            }
        }
    }

    public String sco() {

        String ponit = String.valueOf(score);
//    System.out.println(score);
        return ponit;
    }


}
