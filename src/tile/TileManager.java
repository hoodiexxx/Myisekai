package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[99];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        this.loadMap("/maps/world01.txt");

    }

    public void getTileImage() {

//            tile[0] = new Tile();
//            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));

        //FOR RENDERING FASTER
        // PLACEHOLDER(we don't use it)
        setup(0, "grass1", false);
        setup(1, "grass1", false);
        setup(2, "grass1", false);
        setup(3, "grass1", false);
        setup(4, "grass1", false);
        setup(5, "grass1", false);
        setup(6, "grass1", false);
        setup(7, "grass1", false);
        setup(8, "grass1", false);
        setup(9, "grass1", false);
        // PLACEHOLDER

        setup(10, "grass1", false);
        setup(11, "grass2", false);
        setup(12, "grass3", false);
        setup(13, "grass4", false);

        setup(14, "tree1", true);
        setup(15, "tree2", true);
        setup(16, "tree3", true);
        setup(17, "tree4", true);

        setup(18, "road1", false);
        setup(19, "road2", false);
        setup(20, "road3", false);
        setup(21, "road4", false);
        setup(22, "road5", false);






//        setup(4, "wall", true);
//        setup(2, "water", true);
//        setup(3, "earth", false);
//        setup(4, "tree1", true);
//        setup(5, "road1", false);

//            BufferedImage scaledImage = new BufferedImage(gp.tileSize, gp.tileSize, tile[0].image.getType());
//            Graphics2D g2 = scaledImage.createGraphics();
//            g2.drawImage(tile[0].image,0, 0, gp.tileSize, gp.tileSize, null);
//            tile[0].image = scaledImage;


//            tile[1] = new Tile();
//            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));
//            tile[1].collision = true;
//
//            tile[2] = new Tile();
//            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water.png")));
//            tile[2].collision = true;
//
//            tile[3] = new Tile();
//            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/earth.png")));
//
//            tile[4] = new Tile();
//            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree.png")));
//            tile[4].collision = true;
//
//            tile[5] = new Tile();
//            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sand.png")));


    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imageName + ".png")));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        // READ FILE
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;


        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY; // complicated screen difference offset

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
            worldCol++;


            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;

                worldRow++;

            }

        }


//        g2.drawImage(tile[1].image,0,0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[1].image,48,0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[1].image,96,0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[1].image,144,0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[1].image,192,0, gp.tileSize, gp.tileSize, null);
//
//        g2.drawImage(tile[1].image,0,48, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[0].image,48,48, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[0].image,96,48, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[0].image,144,48, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[1].image,192,48, gp.tileSize, gp.tileSize, null);
//
//        g2.drawImage(tile[1].image,0,96, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[0].image,48,96, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[0].image,96,96, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[0].image,144,96, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[0].image,192,96, gp.tileSize, gp.tileSize, null);
//
//        g2.drawImage(tile[1].image,0,144, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[0].image,48,144, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[0].image,96,144, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[0].image,144,144, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[1].image,192,144, gp.tileSize, gp.tileSize, null);
//
//        g2.drawImage(tile[1].image,0,192, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image,48,192, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image,96,192, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image,144,192, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[1].image,192,192, gp.tileSize, gp.tileSize, null);
    }
}
