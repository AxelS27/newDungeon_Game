package com.jawa.main;

import com.jawa.object.OBJ_Heart;
import com.jawa.object.OBJ_Key;
import com.jawa.object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;


public class UI {

    GamePanel gp;
    Font arial_40, arial_80;
    BufferedImage keyImage;
    BufferedImage heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Cambria", Font.PLAIN, 40);
        arial_80 = new Font("Arial", Font.PLAIN, 80);
        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
        dFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));

        SuperObject heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        if (gp.gameState == gp.mainMenuState) {
            drawMainMenu(g2);
        } else if (gp.gameState == gp.levelSelectState) {
            drawLevelSelect(g2);
        } else if (gameFinished) {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            String text = "You found the treasure!";
            int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            int x = gp.screenWidth / 2 - textLength / 2;
            int y = gp.screenHeight / 2 - (gp.tileSize * 3);
            g2.drawString(text, x, y);
            text = "Your Time is: " + dFormat.format(playTime) + "!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 + (gp.tileSize * 4);
            g2.drawString(text, x, y);
            g2.setFont(arial_80);
            g2.setColor(Color.yellow);
            text = "Congratulations";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 + (gp.tileSize * 2);
            g2.drawString(text, x, y);
        } else {
            if (gp.gameState == gp.playState) {
                drawPlayerLife(g2);
                g2.setFont(arial_40);
                g2.setColor(Color.white);
                g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2 + 80, gp.tileSize, gp.tileSize, null);
                g2.drawString("x " + gp.player.hasKey, 74, 65 + 80);
                playTime += (double) 1 / 60;
                g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 11, 65);
                if (messageOn) {
                    g2.setFont(g2.getFont().deriveFont(30F));
                    g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);
                    messageCounter++;
                    if (messageCounter > 120) {
                        messageCounter = 0;
                        messageOn = false;
                    }
                }
            } else if (gp.gameState == gp.pauseState) {
                drawPlayerLife(g2);
                drawPauseScreen(g2);
            } else if (gp.gameState == gp.dialogueState) {
                drawPlayerLife(g2);
                drawDialogueScreen(g2);
            }
        }
    }

    public void drawPlayerLife(Graphics2D g2) {
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;
        while (i < gp.player.maxLife / 2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;
        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawDialogueScreen(Graphics2D g2) {
        int x = gp.tileSize * 2, y = gp.tileSize / 2, width = gp.screenWidth - (gp.tileSize * 4), height = gp.tileSize * 4;
        drawSubWindoww(x, y, width, height, g2);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += gp.tileSize;
        y += gp.tileSize;
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindoww(int x, int y, int width, int height, Graphics2D g2) {
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public void drawPauseScreen(Graphics2D g2) {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text, g2);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }

    public void drawMainMenu(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(new Font("Arial", Font.BOLD, 60));
        String title = "DUNGEON QUEST";
        int titleWidth = (int) g2.getFontMetrics().getStringBounds(title, g2).getWidth();
        int x = gp.screenWidth / 2 - titleWidth / 2;
        int y = gp.screenHeight / 2 - 120;
        g2.setColor(Color.YELLOW);
        g2.drawString(title, x, y);

        g2.setFont(new Font("Arial", Font.PLAIN, 40));
        String[] options = {"Start", "Level", "Exit"};
        for (int i = 0; i < options.length; i++) {
            int optionWidth = (int) g2.getFontMetrics().getStringBounds(options[i], g2).getWidth();
            x = gp.screenWidth / 2 - optionWidth / 2;
            y = gp.screenHeight / 2 + i * 60;
            if (i == gp.mainMenuIndex) {
                g2.setColor(Color.GREEN);
                g2.drawString(">", x - 40, y);
            } else {
                g2.setColor(Color.WHITE);
            }
            g2.drawString(options[i], x, y);
        }
    }

    public void drawLevelSelect(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(new Font("Arial", Font.BOLD, 50));
        String title = "SELECT LEVEL";
        int titleWidth = (int) g2.getFontMetrics().getStringBounds(title, g2).getWidth();
        int x = gp.screenWidth / 2 - titleWidth / 2;
        int y = 80;
        g2.setColor(Color.CYAN);
        g2.drawString(title, x, y);

        String[] levels = {"Level 1", "Level 2", "Level 3", "Level 4", "Level 5", "Level 6"};
        int cols = 3;
        int rows = 2;
        int startX = gp.screenWidth / 2 - (cols * 150) / 2;
        int startY = gp.screenHeight / 2 - 50;

        for (int i = 0; i < levels.length; i++) {
            int row = i / cols;
            int col = i % cols;
            x = startX + col * 150;
            y = startY + row * 80;

            if (i == gp.levelSelectIndex) {
                g2.setColor(Color.GREEN);
                g2.drawString(">", x - 40, y + 30);
            } else {
                g2.setColor(Color.WHITE);
            }
            g2.setFont(new Font("Arial", Font.PLAIN, 32));
            g2.drawString(levels[i], x, y + 30);
        }

        g2.setFont(new Font("Arial", Font.PLAIN, 24));
        g2.setColor(Color.LIGHT_GRAY);
        g2.drawString("Use ARROW KEYS to navigate", gp.screenWidth / 2 - 180, gp.screenHeight - 60);
        g2.drawString("Press ENTER to select, ESC to go back", gp.screenWidth / 2 - 220, gp.screenHeight - 30);
    }

    public int getXforCenteredText(String text, Graphics2D g2) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - length / 2;
    }
}