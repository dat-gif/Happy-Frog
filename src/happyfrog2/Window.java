/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package happyfrog2;

import java.awt.Canvas;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class Window extends JFrame implements ActionListener {

    public Window() {
    }
    Time t;
    HUD hud;
    JButton exitB, stopButton, saveButton;
    JLabel labelScore;
    Canvas canvas;
    JFrame frame;
    int score = 0;

    Game game;
    GameSave save = new GameSave();

    public void setLabelScore(JLabel labelScore) {
        this.labelScore = labelScore;
    }

    public JLabel getLabelScore() {
        return labelScore;
    }

    Timer timer = new Timer();

    int secondsToWait = 0;

//    lblTimer.setText(secondsToWait + "");
//    timer.scheduleAtFixedRate(task, 1000, 1000);
    public Window(int width, int height, String title, Game gameIn, HUD hud) {

        frame = new JFrame(title);
        game = gameIn;
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(width + 100, height + 100));
        panel.setBackground(Color.WHITE);

        panel.setLayout(null);
        game.setBounds(50, 40, width, height);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.add(game);

        Insets insets = panel.getInsets();//

        this.exitB = new JButton("Exit");
        exitB.setVisible(true);
        exitB.setLayout(null);
        exitB.addActionListener(this);
        exitB.setBounds(insets.left + ((width + 100) - 200 - 100), insets.top + ((height + 100) - 50), 100, 45);
        panel.add(exitB);

        this.stopButton = new JButton();
        stopButton.setText("Stop");
        stopButton.setVisible(true);
        stopButton.setLayout(null);
        stopButton.setBounds(insets.left + ((width + 100) - 150), insets.top + ((height + 100) - 50), 100, 45);
        stopButton.addActionListener(this);
        panel.add(stopButton);

        this.saveButton = new JButton();
        saveButton.setText("Save");
        saveButton.setVisible(true);
        saveButton.setLayout(null);
        saveButton.setBounds(insets.left + (width - 600), insets.top + ((height + 100) - 50), 100, 45);
        saveButton.addActionListener(this);
        panel.add(saveButton);

        this.labelScore = new JLabel();
        this.hud = new HUD();
        labelScore.setVisible(true);
        labelScore.setLayout(null);
        labelScore.setBounds(insets.left + (width - 700) + 400, insets.top + ((height + 100) - 50), 100, 45);
        panel.add(labelScore);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);//để null cửa sổ sẽ hiện ở giũa màn hình
        frame.setVisible(true);
        game.start();
///////
        this.hud = hud;
        Timer timer = new Timer();
        TimerTask task;
        task = new TimerTask() {
            @Override
            public void run() {
                secondsToWait--;
                labelScore.setText("Point:  " + hud.sco());

                if (secondsToWait == 0) {
                    timer.cancel();
                    timer.purge();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 1, 1);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == exitB) {
            System.exit(0);
        }

        if (e.getSource() == stopButton) {
            String check = stopButton.getText();
//            if (game.getGameState() == Game.STATE.START) {
//                game.setGameState(Game.STATE.RUN);
//                stopButton.setText("Stop");
//        
//            }
            if (check.equalsIgnoreCase("Stop")) {
                game.stop();
                stopButton.setText("Continue");

            } else if (check.equalsIgnoreCase("Continue")) {
                game.start();
                stopButton.setText("Stop");
            }
        }

        if (e.getSource() == saveButton) {
            try {
                save.save(game.getHandler(), String.valueOf(hud.getScore()));
            } catch (IOException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//   labelScore.setText("sc: " + hud.sco());
        //  hud.sco(labelScore);   
    }

}
