package com.TPOW.main;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args){

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("The Path Of Winter");

        Image icon = Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/icon/icon.png"));
        window.setIconImage(icon);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
