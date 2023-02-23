package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    // GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
//    public int hasKey = 0;


    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        // this.gp = gp;
        this.keyH = keyH;


        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(10, 18, 23, 23);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        //        solidArea.x = 8;
        //        solidArea.y = 16;
        //        solidArea.width = 32;
        //        solidArea.height = 32;
        attackArea.width = 36;
        attackArea.height = 36;

        this.setDefaultValues();
        this.getPlayerImage();
        this.getPlayerAttackImage();


    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 23; // from parent class
        worldY = gp.tileSize * 21; // from parent class
        speed = 4; // from parent class(Entity)
        direction = "down";

        // PLAYER STATUS
        this.maxLife = 6; // 1 life for half heart and 2 life for full heart
        life = maxLife;
    }

    public void getPlayerImage() {
//        try {
//            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/girl_up_1.png")));
//            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/girl_up_2.png")));
//            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/girl_up_3.png")));
//            up4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/girl_up_4.png")));
//            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/girl_down_1.png")));
//            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/girl_down_2.png")));
//            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/girl_down_3.png")));
//            down4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/girl_down_4.png")));
//            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/girl_left_1.png")));
//            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/girl_left_2.png")));
//            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/girl_left_3.png")));
//            left4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/girl_left_4.png")));
//            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/girl_right_1.png")));
//            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/girl_right_2.png")));
//            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/girl_right_3.png")));
//            right4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/girl_right_4.png")));
//            test = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/window_icon.png")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        up1 = setup("/player/girl_up_1");
        up2 = setup("/player/girl_up_2");
        up3 = setup("/player/girl_up_3");
        up4 = setup("/player/girl_up_4");

        down1 = setup("/player/girl_down_1");
        down2 = setup("/player/girl_down_2");
        down3 = setup("/player/girl_down_3");
        down4 = setup("/player/girl_down_4");

        left1 = setup("/player/girl_left_1");
        left2 = setup("/player/girl_left_2");
        left3 = setup("/player/girl_left_3");
        left4 = setup("/player/girl_left_4");

        right1 = setup("/player/girl_right_1");
        right2 = setup("/player/girl_right_2");
        right3 = setup("/player/girl_right_3");
        right4 = setup("/player/girl_right_4");

        try {
            avatar = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_avatar1.png")));
            avatar2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_avatar2.png")));
            avatar3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_avatar3.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void getPlayerAttackImage() {
        attackUp1 = setup("/player/girl_attack_up1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup("/player/girl_attack_up2", gp.tileSize, gp.tileSize * 2);

        attackDown1 = setup("/player/girl_attack_down1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("/player/girl_attack_down2", gp.tileSize, gp.tileSize * 2);

        attackLeft1 = setup("/player/girl_attack_left1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("/player/girl_attack_left2", gp.tileSize * 2, gp.tileSize);

        attackRight1 = setup("/player/girl_attack_right1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("/player/girl_attack_right2", gp.tileSize * 2, gp.tileSize);


    }

    public void update() {
        if (attacking) {
            this.attacking();
        } else if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed) {

            if (keyH.shiftPressed) {
                // Speed up
                this.speed = 7;
            } else {
                this.speed = 4;
            }
            if (keyH.upPressed) {
                direction = "up";

            } else if (keyH.downPressed) {
                direction = "down";

            } else if (keyH.leftPressed) {
                direction = "left";

            } else if (keyH.rightPressed) {
                direction = "right";

            }

            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            this.pickUpObject(objIndex);

            // CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npcs);
            interactNPC(npcIndex);

            // CHECK MONSTER COLLISION
            int monsterIndex = this.gp.cChecker.checkEntity(this, gp.monsters);
            contactMonster(monsterIndex);

            // CHECK EVENT
            gp.eHandler.checkEvent();


            // IF COLLISION IS FALSE PLAYER CAN MOVE
            if (!collisionOn && !keyH.enterPressed) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            gp.keyH.enterPressed = false;

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 4;
                } else if (spriteNum == 4) {
                    spriteNum = 1;
                }

                spriteCounter = 0;
            }
        }
        // This needs to be outside of key if statement
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void attacking() {
        spriteCounter++;

        if (spriteCounter <= 2) {
            spriteNum = 1;
        }
        if (spriteCounter > 2 && spriteCounter <= 5) {
            spriteNum = 2;
        }
        if (spriteCounter > 5 && spriteCounter <= 20) {
            spriteNum = 3;

            // Save the current worldX, worldY, solidArea

            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust player's worldX/Y for the attackArea
            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }

            // attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            // Check monster collision with the updated worldX, worldY and solidArea
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monsters);
            damageMonster(monsterIndex);

            this.worldX = currentWorldX;
            this.worldY = currentWorldY;
            this.solidArea.width = solidAreaWidth;
            this.solidArea.height = solidAreaHeight;

        }
        if (spriteCounter > 20 && spriteCounter <= 25) {
            spriteNum = 4;
        }
        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {

//            String objectName = gp.obj[i].name;
//
//            switch (objectName){
//                case "Key":
//                    gp.playSE(1);
//                    hasKey++;
//                    gp.obj[i] = null;
//                    gp.ui.showMessage("You got a key!");
//                    break;
//                case "Door":
//                    if(hasKey > 0){
//                        gp.playSE(3);
//                        gp.obj[i] = null;
//                        hasKey --;
//                        gp.ui.showMessage("You opened the door!");
//                    }
//                    else{
//                        gp.ui.showMessage("You need a key!");
//                    }
//
//                    break;
//                case "Boots":
//                    gp.playSE(2);
//                    speed += 1;
//                    gp.obj[i] = null;
//                    gp.ui.showMessage("Speed Up");
//                    break;
//                case "Chest":
//                    gp.ui.gameFinished = true;
//                    gp.stopMusic();
//                    gp.playSE(5);
//                    break;
//            }

        }
    }

    public void interactNPC(int i) {
        if (gp.keyH.enterPressed) {
            if (i != 999) {
                // System.out.println("you are hitting an npc!");
                gp.gameState = gp.dialogueState;
                gp.npcs[i].speak();
            } else {
                gp.playSE(2);
                attacking = true;
            }
        }

    }

    public void contactMonster(int i) {
        if (i != 999) {
            if (!invincible) {
                gp.playSE(6);
                life -= 1;
                invincible = true;
            }

        }
    }

    public void damageMonster(int i) {
        if (i != 999) {
            if (!gp.monsters[i].invincible) {
                gp.playSE(7);
                gp.monsters[i].life -= 1;
                gp.monsters[i].invincible = true;
            }
            if (gp.monsters[i].life <= 0) {
//                System.out.println("null check");
//                gp.monsters[i] = null;
                gp.monsters[i].dying = true;

            }
        }

    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        // reset
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        // g2.setColor(Color.white);
        //  g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        switch (direction) {
            case "up":
                if (!attacking) {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                    if (spriteNum == 3) {
                        image = up3;
                    }
                    if (spriteNum == 4) {
                        image = up4;
                    }
                }
                if (attacking) {
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) {
                        image = attackUp1;
                    }
                    if (spriteNum == 2) {
                        image = attackUp1;
                    }
                    if (spriteNum == 3) {
                        image = attackUp2;
                    }
                    if (spriteNum == 4) {
                        image = attackUp2;
                    }
                }
                break;
            case "down":
                if (!attacking) {
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                    if (spriteNum == 3) {
                        image = down3;
                    }
                    if (spriteNum == 4) {
                        image = down4;
                    }
                }
                if (attacking) {
                    tempScreenY = screenY + 3;
                    if (spriteNum == 1) {
                        image = attackDown1;
                    }
                    if (spriteNum == 2) {
                        image = attackDown1;
                    }
                    if (spriteNum == 3) {
                        image = attackDown2;
                    }
                    if (spriteNum == 4) {
                        image = attackDown2;
                    }
                }
                break;
            case "left":
                if (!attacking) {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                    if (spriteNum == 3) {
                        image = left3;
                    }
                    if (spriteNum == 4) {
                        image = left4;
                    }
                }
                if (attacking) {
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) {
                        image = attackLeft1;
                    }
                    if (spriteNum == 2) {
                        image = attackLeft1;
                    }
                    if (spriteNum == 3) {
                        image = attackLeft2;
                    }
                    if (spriteNum == 4) {
                        image = attackLeft2;
                    }
                }
                break;
            case "right":
                if (!attacking) {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    if (spriteNum == 3) {
                        image = right3;
                    }
                    if (spriteNum == 4) {
                        image = right4;
                    }
                }
                if (attacking) {
                    tempScreenX = screenX + 3;
                    if (spriteNum == 1) {
                        image = attackRight1;
                    }
                    if (spriteNum == 2) {
                        image = attackRight1;
                    }
                    if (spriteNum == 3) {
                        image = attackRight2;
                    }
                    if (spriteNum == 4) {
                        image = attackRight2;
                    }
                }
                break;
        }

        if (invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }


//        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);    //SCALING VERSION

        g2.drawImage(image, tempScreenX, tempScreenY, null);

        //Reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));


//        g2.drawImage(test,0,0, 100, 100, null);

        // DEBUG
//        g2.setFont(new Font("Arial", Font.PLAIN,26));
//        g2.setColor(Color.white);
//        g2.drawString("Invincible:" + invincibleCounter, 10 , 400);
    }


}
