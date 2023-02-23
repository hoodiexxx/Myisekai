package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Door extends Entity {

    public Door(GamePanel gp){
        super(gp);
        this.name = "Door";
        this.down1 = setup("/objects/door");
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 38;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

//        this.gp = gp;
//        this.name = "Door";
//        try {
//            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png")));
//            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
