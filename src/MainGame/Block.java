package MainGame;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.util.Random;

public class Block extends Pane {
    public String type;

    public Boolean moving = false;
    public int speed = 1;
    public int range = 200;
    public Random rand = new Random();
    public int count = 0;

    public int x;
    public int y;
    public int rotate = 0;

    public enum BlockType {
        GROUND_TOP, GROUND_FILL, GROUND_RIGHT, GROUND_LEFT,
        CLOUD_TOP, CLOUD_RIGHT, CLOUD_LEFT,
        FIRE_TOP, FIRE_RIGHT, FIRE_LEFT,
        STAR,
        DOOR_TOP_LEFT, DOOR_TOP_RIGHT, DOOR_BOTTOM_LEFT, DOOR_BOTTOM_RIGHT,
        KEY_DEVIL, KEY_ANGEL
    }

    ImageView block;
    Image blocksImg;

    public Block(BlockType blockType, int x, int y) {
        this.x = x;
        this.y = y;
        switch (blockType) {
            case GROUND_TOP:
                type = "GROUND";
                blocksImg = new Image(getClass().getResourceAsStream("Images/_Block_Ground_Top.png"));
                block = new ImageView(blocksImg);
                block.setViewport(new Rectangle2D(0, 0, 32, 32));
                break;
            case GROUND_FILL:
                type = "GROUND_FILL";
                blocksImg = new Image(getClass().getResourceAsStream("Images/_Block_Ground_Fill.jpg"));
                block = new ImageView(blocksImg);
                block.setViewport(new Rectangle2D(0, 0, 32, 32));
                break;
            case GROUND_LEFT:
                type = "GROUND_LEFT";
                blocksImg = new Image(getClass().getResourceAsStream("Images/_Block_Ground_Left.png"));
                block = new ImageView(blocksImg);
                block.setViewport(new Rectangle2D(0, 0, 32, 32));
                break;
            case GROUND_RIGHT:
                type = "GROUND_RIGHT";
                blocksImg = new Image(getClass().getResourceAsStream("Images/_Block_Ground_Right.png"));
                block = new ImageView(blocksImg);
                block.setViewport(new Rectangle2D(0, 0, 32, 32));
                break;
            case CLOUD_TOP:
                type = "CLOUD_TOP";
                blocksImg = new Image(getClass().getResourceAsStream("Images/_Block_Cloud_Top.jpg"));
                block = new ImageView(blocksImg);
                block.setViewport(new Rectangle2D(0, 0, 32, 32));
                break;
            case CLOUD_RIGHT:
                type = "CLOUD_RIGHT";
                blocksImg = new Image(getClass().getResourceAsStream("Images/_Block_Cloud_Right.png"));
                block = new ImageView(blocksImg);
                block.setViewport(new Rectangle2D(0, 0, 32, 32));
                break;
            case CLOUD_LEFT:
                type = "CLOUD_LEFT";
                blocksImg = new Image(getClass().getResourceAsStream("Images/_Block_Cloud_Left.png"));
                block = new ImageView(blocksImg);
                block.setViewport(new Rectangle2D(0, 0, 32, 32));
                break;
            case FIRE_TOP:
                type = "FIRE_TOP";
                blocksImg = new Image(getClass().getResourceAsStream("Images/_Block_Fire_Top.jpg"));
                block = new ImageView(blocksImg);
                block.setViewport(new Rectangle2D(0, 0, 32, 32));
                break;
            case FIRE_RIGHT:
                type = "FIRE_RIGHT";
                blocksImg = new Image(getClass().getResourceAsStream("Images/_Block_Fire_Right.png"));
                block = new ImageView(blocksImg);
                block.setViewport(new Rectangle2D(0, 0, 32, 32));
                break;
            case FIRE_LEFT:
                type = "FIRE_LEFT";
                blocksImg = new Image(getClass().getResourceAsStream("Images/_Block_Fire_Left.png"));
                block = new ImageView(blocksImg);
                block.setViewport(new Rectangle2D(0, 0, 32, 32));
                break;
            case STAR:
                type = "STAR";
                blocksImg = new Image(getClass().getResourceAsStream("Images/_Bonus_Star.png"));
                block = new ImageView(blocksImg);
                block.setViewport(new Rectangle2D(0, 0, 32, 32));
                break;
            case DOOR_TOP_RIGHT:
                type = "DOOR_TOP_RIGHT";
                blocksImg = new Image(getClass().getResourceAsStream("Images/_Door_Top_Right.png"));
                block = new ImageView(blocksImg);
                block.setViewport(new Rectangle2D(0, 0, 32, 32));
                break;
            case DOOR_TOP_LEFT:
                type = "DOOR_TOP_LEFT";
                blocksImg = new Image(getClass().getResourceAsStream("Images/_Door_Top_Left.png"));
                block = new ImageView(blocksImg);
                block.setViewport(new Rectangle2D(0, 0, 32, 32));
                break;
            case DOOR_BOTTOM_RIGHT:
                type = "DOOR_BOTTOM_RIGHT";
                blocksImg = new Image(getClass().getResourceAsStream("Images/_Door_Bottom_Right.png"));
                block = new ImageView(blocksImg);
                block.setViewport(new Rectangle2D(0, 0, 32, 32));
                break;
            case DOOR_BOTTOM_LEFT:
                type = "DOOR_BOTTOM_LEFT";
                blocksImg = new Image(getClass().getResourceAsStream("Images/_Door_Bottom_Left.png"));
                block = new ImageView(blocksImg);
                block.setViewport(new Rectangle2D(0, 0, 32, 32));
                break;
            case KEY_DEVIL:
                type = "KEY_DEVIL";
                blocksImg = new Image(getClass().getResourceAsStream("Images/_Key_Devil.png"));
                block = new ImageView(blocksImg);
                block.setViewport(new Rectangle2D(0, 0, 32, 32));
                break;
            case KEY_ANGEL:
                type = "KEY_ANGEL";
                blocksImg = new Image(getClass().getResourceAsStream("Images/_Key_Angel.png"));
                block = new ImageView(blocksImg);
                block.setViewport(new Rectangle2D(0, 0, 32, 32));
                break;
        }

        block.setFitWidth(Game.BLOCK_SIZE);
        block.setFitHeight(Game.BLOCK_SIZE);
        setTranslateX(x);
        setTranslateY(y);

        getChildren().add(block);
        Game.platforms.add(this);
        Game.gameRoot.getChildren().add(this);
    }
    public void blockMove(){
        if (count < range/2) {
            x += 1;
            count++;
        }else if (count == range/2){
            count = range;
            speed *= -1;
        }else{
            x -= 1;
            count--;
            if (count == range/2){
                count = 0;
                speed *= -1;
            }
        }
        setTranslateX(x);
    }
}
