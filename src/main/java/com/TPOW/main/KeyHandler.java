package com.TPOW.main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed, ePressed;
    public String inputUsername = "";
    public String inputPassword = "";
    public int activeField = 0;
    public boolean typing = false;
    GamePanel gp;
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (gp.gameState == gp.loginState || gp.gameState == gp.registerState) {
            if (typing) {
                if (code == KeyEvent.VK_BACK_SPACE) {
                    if (activeField == 0 && !inputUsername.isEmpty()) {
                        inputUsername = inputUsername.substring(0, inputUsername.length() - 1);
                    } else if (activeField == 1 && !inputPassword.isEmpty()) {
                        inputPassword = inputPassword.substring(0, inputPassword.length() - 1);
                    }
                } else if (code == KeyEvent.VK_ENTER) {
                    typing = false;
                    if (gp.gameState == gp.loginState) {
                        if (activeField == 0) activeField = 1;
                        else if (activeField == 1) activeField = 2;
                    } else {
                        if (activeField == 0) activeField = 1;
                        else if (activeField == 1) activeField = 2;
                    }
                } else if (code == KeyEvent.VK_ESCAPE) {
                    typing = false;
                } else {
                    char c = e.getKeyChar();
                    if ((Character.isLetterOrDigit(c) || c == '_' || c == '.') &&
                            ((activeField == 0 && inputUsername.length() < 16) ||
                                    (activeField == 1 && inputPassword.length() < 16))) {
                        if (activeField == 0) inputUsername += c;
                        else inputPassword += c;
                    }
                }
            } else {
                if (code == KeyEvent.VK_UP) {
                    activeField = Math.max(0, activeField - 1);
                } else if (code == KeyEvent.VK_DOWN) {
                    if (gp.gameState == gp.loginState) {
                        activeField = Math.min(4, activeField + 1);
                    } else {
                        activeField = Math.min(3, activeField + 1);
                    }
                } else if (code == KeyEvent.VK_ENTER) {
                    if (gp.gameState == gp.loginState) {
                        if (activeField == 0 || activeField == 1) {
                            typing = true;
                        } else if (activeField == 2) {
                            if (!inputUsername.isEmpty() && !inputPassword.isEmpty()) {
                                if (gp.authenticate(inputUsername, inputPassword)) {
                                    gp.gameState = gp.mainMenuState;
                                } else {
                                    gp.ui.showMessage("Invalid username or password!");
                                }
                            } else {
                                gp.ui.showMessage("Please fill all fields!");
                            }
                        } else if (activeField == 3) {
                            gp.gameState = gp.registerState;
                            inputUsername = "";
                            inputPassword = "";
                            activeField = 0;
                        } else if (activeField == 4) {
                            System.exit(0);
                        }
                    } else if (gp.gameState == gp.registerState) {
                        if (activeField == 0 || activeField == 1) {
                            typing = true;
                        } else if (activeField == 2) {
                            if (!inputUsername.isEmpty() && !inputPassword.isEmpty()) {
                                if (gp.register(inputUsername, inputPassword)) {
                                    gp.ui.showMessage("Registration successful!");
                                    gp.gameState = gp.loginState;
                                    inputUsername = "";
                                    inputPassword = "";
                                    activeField = 0;
                                } else {
                                    gp.ui.showMessage("Username already exists!");
                                }
                            } else {
                                gp.ui.showMessage("Please fill all fields!");
                            }
                        } else if (activeField == 3) {
                            gp.gameState = gp.loginState;
                            inputUsername = "";
                            inputPassword = "";
                            activeField = 0;
                        }
                    }
                } else if (code == KeyEvent.VK_ESCAPE) {
                    if (gp.gameState == gp.registerState) {
                        gp.gameState = gp.loginState;
                        inputUsername = "";
                        inputPassword = "";
                        activeField = 0;
                    } else {
                        System.exit(0);
                    }
                }
            }
            return;
        }
        if (gp.gameState == gp.mainMenuState) {
            if (code == KeyEvent.VK_UP) gp.mainMenuIndex = Math.max(0, gp.mainMenuIndex - 1);
            if (code == KeyEvent.VK_DOWN) gp.mainMenuIndex = Math.min(2, gp.mainMenuIndex + 1);
            if (code == KeyEvent.VK_ENTER) {
                if (gp.mainMenuIndex == 0) {
                    gp.setupGame(gp.currentUserCurrentLevel);
                } else if (gp.mainMenuIndex == 1) {
                    gp.gameState = gp.levelSelectState;
                } else if (gp.mainMenuIndex == 2) {
                    gp.gameState = gp.loginState;
                    gp.keyH.inputUsername = "";
                    gp.keyH.inputPassword = "";
                    gp.keyH.activeField = 0;
                    gp.keyH.typing = false;
                }
            }
        } else if (gp.gameState == gp.levelSelectState) {
            if (code == KeyEvent.VK_UP) if (gp.levelSelectIndex >= 3) gp.levelSelectIndex -= 3;
            if (code == KeyEvent.VK_DOWN) if (gp.levelSelectIndex < 3) gp.levelSelectIndex += 3;
            if (code == KeyEvent.VK_LEFT) if (gp.levelSelectIndex % 3 != 0) gp.levelSelectIndex--;
            if (code == KeyEvent.VK_RIGHT) if (gp.levelSelectIndex % 3 != 2) gp.levelSelectIndex++;
            if (code == KeyEvent.VK_ENTER) {
                int selectedLevel = gp.levelSelectIndex + 1;
                if (selectedLevel <= gp.currentUserCurrentLevel) {
                    gp.setupGame(selectedLevel);
                } else {
                    gp.ui.showMessage("Level locked! Complete previous levels first.");
                }
            }
            if (code == KeyEvent.VK_ESCAPE) gp.gameState = gp.mainMenuState;
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
                    if (nextMap <= 3) gp.changeMap(nextMap);
                    else gp.gameThread = null;
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