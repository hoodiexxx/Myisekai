package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import entity.Entity;
import entity.Player;
import tile.TileManager;


public class GamePanel extends JPanel implements Runnable {
    //   Screen setting
    final int originalTileSize = 16; // 16x16 pixels tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;    // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow;   //576 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;


    // FPS
    int FPS = 60;

    //SYSTEM
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound soundEffect = new Sound();


    public CollisionChcker cChecker = new CollisionChcker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;
    //ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public Entity[] objs = new Entity[10];
    public Entity[] npcs = new Entity[10];
    public Entity[] monsters = new Entity[20];
    // sorted the entity that has the lowest worldY comes in index 0
    ArrayList<Entity> entityList = new ArrayList<>();

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        this.aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        this.playMusic(0);
        this.gameState = titleState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000.0 / FPS; //0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            long currentTime = System.nanoTime();
//            System.out.println("current Time:" + currentTime);
            // System.out.println("The game loop is running");
            // 1. UPDATE: update information such as character positions
            // 2. DRAW: draw the screen with the updated information
            this.update(); // this.update()
            this.repaint(); // this.repaint()

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000; // convet nanoseconds to millseconds

                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                // TODO
                e.printStackTrace();
            }
        }

    }

    public void update() {
        if (gameState == playState) {
            // PLAYER
            player.update();
            // NPC
            for (Entity npc : npcs) {
                if (npc != null) {
                    npc.update();
                }
            }

            // MONSTER
            for (int i = 0; i < monsters.length; i ++) {
                if (monsters[i] != null) {
                    if (monsters[i].alive && !monsters[i].dying) {
                        monsters[i].update();
                    }
                    if (!monsters[i].alive) {
                        monsters[i] = null;
                        System.out.println("clean check");
                    }
                }
            }
        }
        if (gameState == pauseState) {
            // nothing
        }


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // DEBUG
        long drawStart = 0;
        if (keyH.checkDrawTime) {
            drawStart = System.nanoTime();
        }

        //TITLE SCREEN
        if (gameState == titleState) {
            ui.draw(g2);
        }
        //OTHERS
        else {
            // TILE
            tileM.draw(g2); //draw this layer first

            // ADD ENTITIES TO THE LIST
            entityList.add(player);
            for (Entity npc : npcs) {
                if (npc != null) {
                    entityList.add(npc);
                }
            }

            for (Entity obj : objs) {
                if (obj != null) {
                    entityList.add(obj);
                }
            }

            for (Entity monster : monsters) {
                if (monster != null) {
                    entityList.add(monster);
                }
            }

            // SORT
            entityList.sort(new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    return Integer.compare(e1.worldY, e2.worldY);
                }
            });

            // DRAW ENTITIES
            for (Entity e : entityList) {
                e.draw(g2);
            }
            // EMPTY ENTITY LIST
            if (entityList.size() > 0) {
                entityList.clear();
            }


            // UI
            ui.draw(g2);

        }

        // DEBUG
        if (keyH.checkDrawTime) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }
        g2.dispose();

    }

    public void playMusic(int index) {
        music.setFile(index);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int index) {
        soundEffect.setFile(index);
        soundEffect.play();
    }
}
