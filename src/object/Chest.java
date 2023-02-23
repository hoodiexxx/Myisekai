package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Chest extends Entity {

    public Chest(GamePanel gp){
        super(gp);
        this.name = "Chest";
        this.down1 = setup("/objects/chest");

//        this.gp = gp;
//        this.name = "Chest";
//        try {
//            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest.png")));
//            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        this.collision =true;
    }
}
