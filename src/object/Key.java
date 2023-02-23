package object;

import entity.Entity;
import main.GamePanel;


public class Key extends Entity {

    public Key(GamePanel gp) {
        super(gp);

        this.name = "Key";
        down1 = setup("/objects/key");


        //        try {
//            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png")));
//            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
