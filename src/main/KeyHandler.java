package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLOutput;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, shiftPressed, enterPressed;

    //DEBUG
    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            if (gp.ui.titleScreenState == 0) {
                if (code == KeyEvent.VK_W) {
                    if (gp.ui.commandNum > 0) {
                        gp.ui.commandNum--;
                    }

                }

                if (code == KeyEvent.VK_S) {
                    if (gp.ui.commandNum < 3) {
                        gp.ui.commandNum++;
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.commandNum == 0) {
                        gp.ui.titleScreenState = 1;

                    }
                    if (gp.ui.commandNum == 1) {
                        // add later
                    }
                    if (gp.ui.commandNum == 2) {
                        gp.ui.titleScreenState = 2;
                    }
                    if (gp.ui.commandNum == 3) {
                        System.exit(0);
                    }
                }
            }
            else if (gp.ui.titleScreenState == 1) {
                if (code == KeyEvent.VK_W) {
                    if (gp.ui.commandNum > 0) {
                        gp.ui.commandNum--;
                    }

                }

                if (code == KeyEvent.VK_S) {
                    if (gp.ui.commandNum < 3) {
                        gp.ui.commandNum++;
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.commandNum == 0) {
                        System.out.println("Do some fighter specific stuff");
                        gp.gameState = gp.playState;

                    }
                    if (gp.ui.commandNum == 1) {
                        System.out.println("Do some thief specific stuff");
                        gp.gameState = gp.playState;
                        // add later
                    }
                    if (gp.ui.commandNum == 2) {
                        System.out.println("Do some witch specific stuff");
                        gp.gameState = gp.playState;
                        // add later
                    }
                    if (gp.ui.commandNum == 3) {
                        gp.ui.titleScreenState = 0;
                    }
                }
            }
            else if(gp.ui.titleScreenState == 2){
                if (code == KeyEvent.VK_ENTER){
                    gp.ui.titleScreenState = 0;
                }
            }
        }
        // PLAY STATE
        else if (gp.gameState == gp.playState) {


            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }

            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }

            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }

            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_SHIFT) {
                shiftPressed = true;
            }
            // DEBUG
            if (code == KeyEvent.VK_T) {
                checkDrawTime = !checkDrawTime;
            }
            if (code == KeyEvent.VK_P) {

                gp.gameState = gp.pauseState;

            }
            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;

            }
        }
        // PAUSE STATE
        else if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_P) {

                gp.gameState = gp.playState;

            }
        }

        //DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState) {
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }

        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }

        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }

        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_SHIFT) {
            shiftPressed = false;
        }
        if (code == KeyEvent.VK_T) {

        }
    }
}
