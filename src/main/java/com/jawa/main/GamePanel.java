package com.jawa.main;

import com.jawa.entity.Entity;
import com.jawa.entity.Player;
import com.jawa.object.SuperObject;
import com.jawa.tile.TileManager;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public int currentMap = 1;
    public boolean chestOpened = false;
    final int FPS = 60;

    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound sound = new Sound();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eventHandler = new EventHandler(this);
    public Player player = new Player(this, keyH);
    public SuperObject[] obj = new SuperObject[10];
    public Entity[] npc = new Entity[10];
    public Entity[] monster = new Entity[20];
    public int gameState;
    public final int mainMenuState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int levelSelectState = 4;
    public int mainMenuIndex = 0;
    public int levelSelectIndex = 0;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        gameState = mainMenuState;
    }

    public void setupGame(int mapNumber) {
        keyH.upPressed = false;
        keyH.downPressed = false;
        keyH.leftPressed = false;
        keyH.rightPressed = false;
        keyH.ePressed = false;
        changeMap(mapNumber);
        gameState = playState;
    }

    public void setupGame() {
        setupGame(1);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void changeMap(int mapNumber) {
        currentMap = mapNumber;
        String mapFile = "/maps/level" + mapNumber + ".txt";
        tileM.loadMap(mapFile);
        assetSetter.setObject();
        assetSetter.setNPC();
        assetSetter.setMonster();
        player.setDefaultValues();
        ui.gameFinished = false;
        chestOpened = false;
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        if (gameState == playState) {
            player.update();
            for (Entity entity : npc) {
                if (entity != null) {
                    entity.update();
                }
            }
            for (int i = 0; i < monster.length; i++) {
                Entity entity = monster[i];
                if (entity != null) {
                    if (entity.alive && !entity.dying) {
                        entity.update();
                    } else if (!entity.alive) {
                        monster[i] = null;
                    }
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (gameState != mainMenuState && gameState != levelSelectState && !ui.gameFinished) {
            tileM.draw(g2);
            for (SuperObject superObject : obj) {
                if (superObject != null) {
                    superObject.draw(g2, this);
                }
            }
            for (Entity entity : npc) {
                if (entity != null) {
                    entity.draw(g2);
                }
            }
            for (Entity entity : monster) {
                if (entity != null) {
                    entity.draw(g2);
                }
            }
            player.draw(g2);
        }

        ui.draw(g2);

        g2.dispose();
    }

    public void playSE(int i) {
        sound.setFile(i);
        sound.play();
    }

    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }
}