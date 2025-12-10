package com.jawa.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed, ePressed;
    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (gp.gameState == gp.mainMenuState) {
            if (code == KeyEvent.VK_UP) {
                gp.mainMenuIndex = Math.max(0, gp.mainMenuIndex - 1);
            }
            if (code == KeyEvent.VK_DOWN) {
                gp.mainMenuIndex = Math.min(2, gp.mainMenuIndex + 1);
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.mainMenuIndex == 0) {
                    gp.setupGame();
                    gp.startGameThread();
                } else if (gp.mainMenuIndex == 1) {
                    gp.gameState = gp.levelSelectState;
                } else if (gp.mainMenuIndex == 2) {
                    System.exit(0);
                }
            }
        } else if (gp.gameState == gp.levelSelectState) {
            if (code == KeyEvent.VK_UP) {
                if (gp.levelSelectIndex >= 3) gp.levelSelectIndex -= 3;
            }
            if (code == KeyEvent.VK_DOWN) {
                if (gp.levelSelectIndex < 3) gp.levelSelectIndex += 3;
            }
            if (code == KeyEvent.VK_LEFT) {
                if (gp.levelSelectIndex % 3 != 0) gp.levelSelectIndex--;
            }
            if (code == KeyEvent.VK_RIGHT) {
                if (gp.levelSelectIndex % 3 != 2) gp.levelSelectIndex++;
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.levelSelectIndex >= 0 && gp.levelSelectIndex <= 5) {
                    int mapNumber = gp.levelSelectIndex + 1;
                    gp.setupGame();
                    gp.changeMap(mapNumber);
                    gp.startGameThread();
                }
            }
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.mainMenuState;
            }
        } else if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_W) upPressed = true;
            if (code == KeyEvent.VK_S) downPressed = true;
            if (code == KeyEvent.VK_A) leftPressed = true;
            if (code == KeyEvent.VK_D) rightPressed = true;
            if (code == KeyEvent.VK_ESCAPE) gp.gameState = gp.pauseState;
            if (code == KeyEvent.VK_E) ePressed = true;
            if (code == KeyEvent.VK_SPACE) {
                if (gp.chestOpened && gp.ui.gameFinished) {
                    int nextMap = gp.currentMap + 1;
                    if (nextMap <= 3) {
                        gp.changeMap(nextMap);
                    } else {
                        gp.gameThread = null;
                    }
                }
            }
        } else if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_ESCAPE) gp.gameState = gp.playState;
        } else if (gp.gameState == gp.dialogueState) {
            if (code == KeyEvent.VK_E) gp.gameState = gp.playState;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) upPressed = false;
        if (code == KeyEvent.VK_S) downPressed = false;
        if (code == KeyEvent.VK_A) leftPressed = false;
        if (code == KeyEvent.VK_D) rightPressed = false;
    }
}