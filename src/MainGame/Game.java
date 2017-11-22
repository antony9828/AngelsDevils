package MainGame;

import javafx.application.Application;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.lang.Character;
import java.util.ArrayList;
import java.util.HashMap;

public class Game extends Application{
    public static ArrayList<Block> platforms = new ArrayList<>();
    private HashMap<KeyCode,Boolean> keys = new HashMap<>();
    public Interface display;

    Image backgroundImg = new Image(getClass().getResourceAsStream("Images/_Background4.png"));

    public static final int SCREEN_WIDTH = 1024;
    public static final int SCREEN_HEIGHT= 768;
    public static final int BLOCK_SIZE = 32;

    public static Pane appRoot = new Pane();
    public static Pane gameRoot = new Pane();

    public CharacterPlayer angel;
    public CharacterPlayer devil;
    int levelNumber = 0;
    private int levelWidth;

    private void initContent(){
        ImageView backgroundIV = new ImageView(backgroundImg);
        backgroundIV.setFitHeight(24*BLOCK_SIZE);
        backgroundIV.setFitWidth(32*BLOCK_SIZE);

        levelWidth = LevelData.levels[levelNumber][0].length()*BLOCK_SIZE;
        for(int i = 0; i < LevelData.levels[levelNumber].length; i++){
            String line = LevelData.levels[levelNumber][i];
            for(int j = 0; j < line.length();j += 5){
                switch (Character.toString(line.charAt(j)) +
                        Character.toString(line.charAt(j+1)) +
                        Character.toString(line.charAt(j+2)) +
                        Character.toString(line.charAt(j+3)))  {
                    // Empty block
                    case "0000":
                        break;
                    // Ground blocks
                    case "0001":
                        Block ground_top = new Block(Block.BlockType.GROUND_TOP, j/5 * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                    case "0002":
                        Block ground_right = new Block(Block.BlockType.GROUND_RIGHT, j/5 * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                    case "0003":
                        Block ground_left = new Block(Block.BlockType.GROUND_LEFT, j/5 * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                    case "0004":
                        Block ground_fill = new Block(Block.BlockType.GROUND_FILL, j/5 * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                    // Star blocks (Bonus)
                    case "0005":
                        Block star = new Block(Block.BlockType.STAR, j/5 * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                    // Cloud blocks
                    case "0101":
                        Block cloud_top = new Block(Block.BlockType.CLOUD_TOP, j/5 * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                    case "0102":
                        Block cloud_right = new Block(Block.BlockType.CLOUD_RIGHT, j/5 * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                    case "0103":
                        Block cloud_left = new Block(Block.BlockType.CLOUD_LEFT, j/5 * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                    // Fire blocks
                    case "0201":
                        Block fire_top = new Block(Block.BlockType.FIRE_TOP, j/5 * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                    case "0202":
                        Block fire_right = new Block(Block.BlockType.FIRE_RIGHT, j/5 * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                    case "0203":
                        Block fire_left = new Block(Block.BlockType.FIRE_LEFT, j/5 * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                    // Fire blocks moving
                    case "1201":
                        Block fire_top_moving = new Block(Block.BlockType.FIRE_TOP, j/5 * BLOCK_SIZE, i * BLOCK_SIZE);
                        fire_top_moving.moving = true;
                        break;
                    case "1202":
                        Block fire_right_moving = new Block(Block.BlockType.FIRE_RIGHT, j/5 * BLOCK_SIZE, i * BLOCK_SIZE);
                        fire_right_moving.moving = true;
                        break;
                    case "1203":
                        Block fire_left_moving = new Block(Block.BlockType.FIRE_LEFT, j/5 * BLOCK_SIZE, i * BLOCK_SIZE);
                        fire_left_moving.moving = true;
                        break;
                    // Cloud blocks moving
                    case "1101":
                        Block cloud_top_moving = new Block(Block.BlockType.CLOUD_TOP, j/5 * BLOCK_SIZE, i * BLOCK_SIZE);
                        cloud_top_moving.moving = true;
                        break;
                    case "1102":
                        Block cloud_right_moving = new Block(Block.BlockType.CLOUD_RIGHT, j/5 * BLOCK_SIZE, i * BLOCK_SIZE);
                        cloud_right_moving.moving = true;
                        break;
                    case "1103":
                        Block cloud_left_moving = new Block(Block.BlockType.CLOUD_LEFT, j/5 * BLOCK_SIZE, i * BLOCK_SIZE);
                        cloud_left_moving.moving = true;
                        break;
                    case "0301":
                        Block door_top_right_ = new Block(Block.BlockType.DOOR_TOP_RIGHT, j/5 * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                    case "0302":
                        Block door_top_left = new Block(Block.BlockType.DOOR_TOP_LEFT, j/5 * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                    case "0303":
                        Block door_bottom_right = new Block(Block.BlockType.DOOR_BOTTOM_RIGHT, j/5 * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                    case "0304":
                        Block door_bottom_left = new Block(Block.BlockType.DOOR_BOTTOM_LEFT, j/5 * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                    case "0551":
                        Block key_devil = new Block(Block.BlockType.KEY_DEVIL, j/5 * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                    case "0552":
                        Block key_angle = new Block(Block.BlockType.KEY_ANGEL, j/5 * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                }
            }
        }
        angel = new CharacterPlayer("ANGEL",20, 430, new Image(getClass().getResourceAsStream("Images/_Character_Angel.PNG")));
        angel.setTranslateX(angel.pos_X);
        angel.setTranslateY(angel.pos_Y);

        devil = new CharacterPlayer("DEVIL",900, 630, new Image(getClass().getResourceAsStream("Images/_Character_Devil.PNG")));
        devil.setTranslateX(devil.pos_X);
        devil.setTranslateY(devil.pos_Y);

        display = new Interface();

        gameRoot.getChildren().addAll(angel, devil);
        appRoot.getChildren().addAll(backgroundIV, gameRoot);
    }

    private void update(){
        // Angel
        if(isPressed(KeyCode.UP) && angel.getTranslateY()>=5){
            angel.jumpPlayer();
        }
        if(isPressed(KeyCode.LEFT) && angel.getTranslateX()>=5){
            angel.setScaleX(-1);
            angel.moveX(-5);
        }
        if(isPressed(KeyCode.RIGHT) && angel.getTranslateX()+75 <= levelWidth-5){
            angel.setScaleX(1);
            angel.moveX(5);
        }
        if(angel.playerVelocity.getY()<10){
            angel.playerVelocity = angel.playerVelocity.add(0,1);
        }
        angel.moveY((int)angel.playerVelocity.getY());
        // Devil
        if(isPressed(KeyCode.W) && devil.getTranslateY()>=5){
            devil.jumpPlayer();
        }
        if(isPressed(KeyCode.A) && devil.getTranslateX()>=5){
            devil.setScaleX(-1);
            devil.moveX(-5);
        }
        if(isPressed(KeyCode.D) && devil.getTranslateX()+75 <= levelWidth-5){
            devil.setScaleX(1);
            devil.moveX(5);
        }
        if(devil.playerVelocity.getY()<10){
            devil.playerVelocity = devil.playerVelocity.add(0,1);
        }
        devil.moveY((int)devil.playerVelocity.getY());

        //interface
        display.devil_score.setText("Devil: " + devil.stars);
        display.angel_score.setText("Angel: " + angel.stars);
        display.devil_deaths.setText("Deaths: " + devil.deaths);
        display.angel_deaths.setText("Deaths: " + angel.deaths);

        for(Block platform : Game.platforms){
            if (platform.type == "STAR"){
                platform.rotate += 1;
                platform.block.setRotate(platform.rotate);
            }
            if (platform.moving == true) {
                platform.blockMove();
            }
        }
    }
    private boolean isPressed(KeyCode key){
        return keys.getOrDefault(key,false);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        {
            initContent();
            Scene scene = new Scene(appRoot, SCREEN_WIDTH, SCREEN_HEIGHT);
            scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
            scene.setOnKeyReleased(event -> {
                keys.put(event.getCode(), false);
            });

            primaryStage.setTitle("Angels & Devils");
            primaryStage.setScene(scene);
            primaryStage.show();
            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    update();
                }
            };
            timer.start();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

