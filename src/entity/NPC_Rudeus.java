package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;



public class NPC_Rudeus extends Entity {

    public NPC_Rudeus(GamePanel gp){
        super(gp);

        this.direction = "down";
        this.speed = 1;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 42;
        solidArea.height = 42;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        this.getImage();
        this.setDialogue();

    }

    public void getImage() {
        up1 = setup("/npc/boy_up_1");
        up2 = setup("/npc/boy_up_2");
        up3 = setup("/npc/boy_up_1");
        up4 = setup("/npc/boy_up_2");


        down1 = setup("/npc/boy_down_1");
        down2 = setup("/npc/boy_down_2");
        down3 = setup("/npc/boy_down_1");
        down4 = setup("/npc/boy_down_2");

        left1 = setup("/npc/boy_left_1");
        left2 = setup("/npc/boy_left_2");
        left3 = setup("/npc/boy_left_1");
        left4 = setup("/npc/boy_left_2");

        right1 = setup("/npc/boy_right_1");
        right2 = setup("/npc/boy_right_2");
        right3 = setup("/npc/boy_right_1");
        right4 = setup("/npc/boy_right_2");
        try {
            avatar = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/rudeus_avatar.png")));
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public void setDialogue(){
        dialogues[0] = "Hello, traveler.";
        dialogues[1] = "So you've come to this \nkingdom to find the \ntreasure?";
        dialogues[2] = "I used to be a great wizard \nbefore Alice leave me.";
        dialogues[3] = "Well, good luck on you.";

    }
    @Override
    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // pick up random number from 0+1 to 99+1

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";

            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }

    public void speak(){
        super.speak();
    }
}
