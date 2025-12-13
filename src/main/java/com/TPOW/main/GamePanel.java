package com.TPOW.main;
import com.TPOW.database.DBConnector;
import com.TPOW.entity.Entity;
import com.TPOW.entity.Player;
import com.TPOW.object.SuperObject;
import com.TPOW.tile.TileManager;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public final int loginState = 5;
    public final int registerState = 6;
    public int mainMenuIndex = 0;
    public int levelSelectIndex = 0;
    public int currentUserCurrentLevel = 1;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        gameState = loginState;
    }

    public boolean authenticate(String username, String password) {
        String sql = "SELECT password, current_level FROM users WHERE LOWER(username) = LOWER(?)";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            if (conn == null) {
                ui.showMessage("Database error. Try again.");
                return false;
            }
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                if (password.equals(storedPassword)) {
                    currentUserCurrentLevel = rs.getInt("current_level");
                    return true;
                } else {
                    ui.showMessage("Invalid username or password!");
                    return false;
                }
            } else {
                ui.showMessage("Invalid username or password!");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            ui.showMessage("Login failed. Check console.");
            return false;
        }
    }

    public boolean register(String username, String password) {
        try (Connection conn = DBConnector.getConnection()) {
            if (conn == null) {
                ui.showMessage("Database error. Try again.");
                return false;
            }
            try (PreparedStatement check = conn.prepareStatement("SELECT 1 FROM users WHERE LOWER(username) = LOWER(?)")) {
                check.setString(1, username);
                if (check.executeQuery().next()) {
                    ui.showMessage("Username already exists!");
                    return false;
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO users (username, password, current_level) VALUES (?, ?, 1)")) {
                ps.setString(1, username);
                ps.setString(2, password);
                int rows = ps.executeUpdate();
                if (rows > 0) {
                    ui.showMessage("Registration successful!");
                    return true;
                } else {
                    ui.showMessage("Registration failed.");
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ui.showMessage("Registration failed. Check console.");
            return false;
        }
    }

    public void setupGame(int mapNumber) {
        if (mapNumber > currentUserCurrentLevel) {
            mapNumber = currentUserCurrentLevel;
            ui.showMessage("You cannot access this level yet!");
        }
        keyH.upPressed = false;
        keyH.downPressed = false;
        keyH.leftPressed = false;
        keyH.rightPressed = false;
        keyH.ePressed = false;
        changeMap(mapNumber);
        gameState = playState;
    }

    public void updateCurrentUserLevel(int newLevel) {
        String sql = "UPDATE users SET current_level = ? WHERE LOWER(username) = LOWER(?)";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            if (conn == null) {
                System.err.println("Failed to connect to database for updating level.");
                return;
            }
            ps.setInt(1, newLevel);
            ps.setString(2, keyH.inputUsername);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                currentUserCurrentLevel = newLevel;
                System.out.println("User level updated successfully to: " + newLevel);
            } else {
                System.err.println("Failed to update user level.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error updating user level in database.");
        }
    }

    public void setupGame() {
        setupGame(currentUserCurrentLevel);
    }

    public void startGameThread() {
        if (gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();
        }
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
        if (gameState != mainMenuState && gameState != levelSelectState && !ui.gameFinished &&
                gameState != loginState && gameState != registerState) {
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