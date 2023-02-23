package main;

import entity.Entity;
import object.Heart;
import object.Key;
import object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Objects;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;

    //    BufferedImage keyImage;
    BufferedImage heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";

    public BufferedImage currentAvatar;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.0");
    BufferedImage titleBackground;

    public int commandNum = 0;
    public int titleScreenState = 0; // 0: the first screen, 1: the second screen  title screen substate

    public UI(GamePanel gp) {
        this.gp = gp;
        this.arial_40 = new Font("Cambria", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
//        Key key = new Key(this.gp);
//        keyImage = key.image;
        // CREATE HUD OBJECT
        Entity heart = new Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

    }


    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {

        // WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;

        this.drawSubWindow(x, y, width, height);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += 4 * gp.tileSize;
        y += gp.tileSize;
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }

        g2.drawImage(currentAvatar, gp.tileSize * 2 + 15, gp.tileSize, 150, 150, null);
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 200); // a for alpha value for the opacity
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - length / 2;
    }

    public void drawTitleScreen() {
        try {
            titleBackground = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/background/titlebackground.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (titleScreenState == 0) {
            g2.setColor(new Color(70, 120, 80));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            g2.drawImage(titleBackground, 0, 0, null);

            // TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
            String text = "My Isekai Adventure";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 2;

            // SHADOW
            g2.setColor(Color.black);
            g2.drawString(text, x + 5, y + 5);

            // MAIN COLOR
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            // RED HAIR GIRL IMAGE
//        x = gp.screenWidth / 2 - (gp.tileSize * 2)/2;
//        y += gp.tileSize * 2;
//        g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

            // MENU
            Color c = new Color(0, 0, 0, 255);
            g2.setColor(c);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));


            text = "NEW GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize * 4;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize * 1.3;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "HELP";
            x = getXforCenteredText(text);
            y += gp.tileSize * 1.3;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);

            }

            text = "QUIT";
            x = getXforCenteredText(text);
            y += gp.tileSize * 1.3;
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        } else if (titleScreenState == 1) {
            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(42F));
            int x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
            int y = gp.tileSize * 2;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

            String text = "Select your class!";
            x = getXforCenteredText(text);
            y = gp.tileSize * 2 - 15;
            g2.drawString(text, x, y);

            text = "Fighter";
            x = getXforCenteredText(text);
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);

            }

            text = "Thief";
            x = getXforCenteredText(text);
            y += gp.tileSize * 1.3;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);

            }

            text = "Witch";
            x = getXforCenteredText(text);
            y += gp.tileSize * 1.3;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Back";
            x = getXforCenteredText(text);
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.drawString(">", x - gp.tileSize, y);
            }


        } else if (titleScreenState == 2) {
            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(42F));


            String text = "WASD for Move";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;
            g2.drawString(text, x, y);

            text = "left Shift for Sprint";
            x = getXforCenteredText(text);
            y += gp.tileSize * 1.3;
            g2.drawString(text, x, y);

            text = "WASD + Enter for Communication";
            x = getXforCenteredText(text);
            y += gp.tileSize * 1.3;
            g2.drawString(text, x, y);

            text = "P for Pause";
            x = getXforCenteredText(text);
            y += gp.tileSize * 1.3;
            g2.drawString(text, x, y);

            text = "Press enter for back";
            x = getXforCenteredText(text);
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);


        }


    }

    public void drawPlayerLife() {
//        gp.player.life = 5;
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        // DRAW BLANK HEART
        while(i < gp.player.maxLife/2){
            g2.drawImage(heart_blank, x, y, null);
            i ++;
            x += gp.tileSize;

        }

        // RESET
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;

        // DRAW CURRENT LIFE
        while(i < gp.player.life){
            g2.drawImage(heart_half, x, y, null); // increase half heart
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full, x, y, null); // increase half heart
            }
            i ++;
            x += gp.tileSize;

        }


    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);
        //TITLE STATE
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        //PLAY STATE
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
        }
        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            this.drawPauseScreen();
        }
        // DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            this.drawDialogueScreen();
        }


//        if (gameFinished) {
//            g2.setFont(arial_40);
//            g2.setColor(Color.WHITE);
//
//            int x;
//            int y;
//            String text;
//            int textLength;
//
//            text = "You found the treasure!";
//            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//            x = gp.screenWidth/2 - textLength/2;
//            y = gp.screenHeight/2 - (gp.tileSize * 3);
//            g2.drawString(text,x, y);
//
//
//            text = "Your Time is :" + dFormat.format(playTime) + "!";
//            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//            x = gp.screenWidth/2 - textLength/2;
//            y = gp.screenHeight/2 + (gp.tileSize * 4);
//            g2.drawString(text,x, y);
//
//            g2.setFont(arial_80B);
//            g2.setColor(Color.yellow);
//            text = "Congratulations!";
//            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//            x = gp.screenWidth/2 - textLength/2;
//            y = gp.screenHeight/2 + (gp.tileSize * 2);
//            g2.drawString(text,x, y);
//            // stop thread, stop the game
//            gp.gameThread = null;
//        } else {
//            //  g2.setFont(new Font("Arial", Font.PLAIN, 40));  no efficient
//            g2.setFont(arial_40);
//            g2.setColor(Color.WHITE);
//            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
//            g2.drawString("x " + gp.player.hasKey, 74, 65);
//
//            // TIME
//            playTime += (double)1/60;
//            g2.setFont(g2.getFont().deriveFont(30F));
//            g2.drawString("Time " + dFormat.format(playTime), gp.tileSize * 11, 65 );
//
//            // MESSAGE
//            if (messageOn) {
//                g2.setFont(g2.getFont().deriveFont(30F));
//                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);
//
//                messageCounter++;
//                if (messageCounter > 120) {
//                    messageCounter = 0;
//                    messageOn = false;
//                }
//            }
//        }
    }
}
