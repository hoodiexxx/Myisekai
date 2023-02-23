package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Heart extends Entity {


    public Heart(GamePanel gp) {
        super(gp);
        this.name = "Heart";
        image = setup("/objects/heart_full");
        image2 = setup("/objects/heart_half");
        image3 = setup("/objects/heart_blank");
//        try {
//            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart_full.png")));
//            image2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart_half.png")));
//            image3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart_blank.png")));
//            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
//            image2 =uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
//            image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
