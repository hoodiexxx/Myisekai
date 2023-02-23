package main;

public class EventHandler {

    GamePanel gp;
    EventRect[][] eventRects;

    int previousEventX, previousEventY;
    boolean canTouchEvent;

    //    Rectangle eventRect;
//    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRects = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRects[col][row] = new EventRect();
            eventRects[col][row].x = 13;
            eventRects[col][row].y = 13;
            eventRects[col][row].width = 23;
            eventRects[col][row].height = 23;
            eventRects[col][row].eventRectDefaultX = eventRects[col][row].x;
            eventRects[col][row].eventRectDefaultY = eventRects[col][row].y;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }


        }


    }

    public void checkEvent() {
        // Check if the player character is more than 1 tile away from the last event
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }

        if (canTouchEvent) {
            if (hit(14, 21, "right")) {//        meaning hit(11, 21, "right") == true;
                // event happens
                damagePit(14, 21, gp.dialogueState);
            }
            if(hit(10, 24, "any")){
                damagePit(10, 24, gp.dialogueState);
            }

            if (hit(10, 21, "any")) {
                healingPool(10, 21, gp.dialogueState);
            }

            if (hit(10, 15, "any")) {
                teleport(gp.dialogueState);
            }

        }
    }

    /**
     * Method that checks event collision.
     */
    public boolean hit(int eventCol, int eventRow, String reqDirection) {

        boolean hit = false;
        // get player current solidArea positions
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        // Getting eventRect's solidArea positions.
        eventRects[eventCol][eventRow].x = eventCol * gp.tileSize + eventRects[eventCol][eventRow].x;
        eventRects[eventCol][eventRow].y = eventRow * gp.tileSize + eventRects[eventCol][eventRow].y;

        // Checking if player's solidArea is colliding with event Rect's solidArea
        if (gp.player.solidArea.intersects(eventRects[eventCol][eventRow]) && !eventRects[eventCol][eventRow].eventDone) {
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;

                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }


        // After checking the collision reset the solidArea x and y;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRects[eventCol][eventRow].x = eventRects[eventCol][eventRow].eventRectDefaultX;
        eventRects[eventCol][eventRow].y = eventRects[eventCol][eventRow].eventRectDefaultY;

        return hit;

    }

    public void damagePit(int col, int row, int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fall into a pit";
        gp.ui.currentAvatar = gp.player.avatar;
        gp.player.life -= 1;
        // eventRects[col][row].eventDone = true;
        this.canTouchEvent = false;
    }

    public void healingPool(int col, int row, int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You drink the water.\nYou live has been recovered";
        gp.ui.currentAvatar = gp.player.avatar2;
        gp.player.life = gp.player.maxLife;
        eventRects[col][row].eventDone = true;
//        if (gp.keyH.enterPressed) {
//            gp.gameState = gameState;
//            gp.ui.currentDialogue = "You drink the water.\nYou live has been recovered";
//            gp.ui.currentAvatar = gp.player.avatar2;
//            gp.player.life = gp.player.maxLife;
//        }


    }

    public void teleport(int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Teleporting";
        gp.ui.currentAvatar = gp.player.avatar3;
        gp.player.worldX = gp.tileSize * 37;
        gp.player.worldY = gp.tileSize * 10;

    }
}
