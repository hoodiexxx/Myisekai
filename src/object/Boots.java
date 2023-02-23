package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Boots extends Entity {

    public Boots(GamePanel gp) {
        super(gp);
        this.name = "Boots";
        this.down1 = setup("/objects/boots");


        //        try {
//            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/boots.png")));
//            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
