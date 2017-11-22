package MainGame;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

class CharacterPlayer extends Pane{
    public Image image;
    public ImageView imageView;

    public String name;

    public Point2D playerVelocity = new Point2D(0,0);
    private boolean canJump = true;

    public static final double SCALE = 0.75;
    public static final int WIDTH = (int)(100 * SCALE);
    public static final int HEIGHT = (int)(75 * SCALE);
    public int pos_X;
    public int pos_Y;
    public int stars = 0;
    public int deaths = 0;


    public CharacterPlayer(String name, int pos_X, int pos_Y, Image image){
        this.name = name;
        this.image = image;
        imageView = new ImageView(this.image);
        this.pos_X = pos_X;
        this.pos_Y = pos_Y;
        imageView.setFitHeight(HEIGHT);
        imageView.setFitWidth(WIDTH);
        getChildren().addAll(this.imageView);
    }

    public void moveX(int value){
        boolean movingRight = value > 0;
        for(int i = 0; i<Math.abs(value); i++) {
            for (Block platform : Game.platforms) {
                if(this.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if(     !((this.name == "ANGEL")&&
                            (    (platform.type == "FIRE_TOP")||
                                    (platform.type == "FIRE_RIGHT")||
                                    (platform.type == "FIRE_LEFT"))    )&&
                            !((this.name == "DEVIL")&&
                                    (   (platform.type == "CLOUD_TOP")||
                                            (platform.type == "CLOUD_RIGHT")||
                                            (platform.type == "CLOUD_LEFT"))    )    ) {
                        if (!(  (platform.type == "DOOR_TOP_RIGHT")||
                                (platform.type == "DOOR_TOP_LEFT")||
                                (platform.type == "DOOR_BOTTOM_RIGHT")||
                                (platform.type == "DOOR_BOTTOM_LEFT")     )) {
                            if (movingRight) {
                                if (this.getTranslateX() + WIDTH == platform.getTranslateX()) {
                                    this.setTranslateX(this.getTranslateX() - 1);
                                    if (platform.type == "STAR") {
                                        Game.gameRoot.getChildren().remove(platform);
                                        Game.platforms.remove(platform);
                                        this.stars += 1;
                                    }
                                    return;
                                }
                            } else {
                                if (this.getTranslateX() == platform.getTranslateX() + Game.BLOCK_SIZE) {
                                    this.setTranslateX(this.getTranslateX() + 1);
                                    if (platform.type == "STAR") {
                                        Game.gameRoot.getChildren().remove(platform);
                                        Game.platforms.remove(platform);
                                        this.stars += 1;
                                    }
                                    return;
                                }
                            }
                        }
                    }
                }
            }
            if ((movingRight)&(this.getTranslateX() <= Game.SCREEN_WIDTH - this.WIDTH)){
                this.setTranslateX(this.getTranslateX() + 1);
            }
            if (!(movingRight)&(this.getTranslateX() >= 0)){
                this.setTranslateX(this.getTranslateX() - 1);
            }
        }
    }
    public void moveY(int value){
        boolean movingDown = value >0;
        for(int i = 0; i < Math.abs(value); i++){
            for(Block platform : Game.platforms){
                if(getBoundsInParent().intersects(platform.getBoundsInParent())){
                    if(     !((this.name == "ANGEL")&&
                                   (    (platform.type == "FIRE_TOP")||
                                        (platform.type == "FIRE_RIGHT")||
                                        (platform.type == "FIRE_LEFT"))    )&&
                            !((this.name == "DEVIL")&&
                                    (   (platform.type == "CLOUD_TOP")||
                                        (platform.type == "CLOUD_RIGHT")||
                                        (platform.type == "CLOUD_LEFT"))    )    ){
                        if (!(  (platform.type == "DOOR_TOP_RIGHT")||
                                (platform.type == "DOOR_TOP_LEFT")||
                                (platform.type == "DOOR_BOTTOM_RIGHT")||
                                (platform.type == "DOOR_BOTTOM_LEFT")     )) {
                            if (movingDown) {
                                if (platform.moving) {
                                    this.setTranslateX(this.getTranslateX() + platform.speed);
                                    if (platform.count == 0) {
                                        this.setTranslateX(this.getTranslateX() - 1);
                                    }
                                    if (platform.count == 200) {
                                        this.setTranslateX(this.getTranslateX() + 1);
                                    }
                                }
                                if (this.getTranslateY() + HEIGHT == platform.getTranslateY()) {
                                    this.setTranslateY(this.getTranslateY() - 1);
                                    if (platform.type == "STAR") {
                                        Game.gameRoot.getChildren().remove(platform);
                                        Game.platforms.remove(platform);
                                        this.stars += 1;
                                    } else {
                                        canJump = true;
                                    }
                                    return;
                                }
                            } else {
                                if (this.getTranslateY() == platform.getTranslateY() + Game.BLOCK_SIZE) {
                                    this.setTranslateY(this.getTranslateY() + 1);
                                    playerVelocity = new Point2D(0, 10);
                                    if (platform.type == "STAR") {
                                        Game.gameRoot.getChildren().remove(platform);
                                        Game.platforms.remove(platform);
                                        this.stars += 1;
                                    }
                                    return;
                                }

                            }
                        }
                    }
                }
            }
            this.setTranslateY(this.getTranslateY() + (movingDown?1:-1));
            if(this.getTranslateY() > Game.SCREEN_HEIGHT){
                this.deaths += 1;
                this.setTranslateX(pos_X);
                this.setTranslateY(pos_Y);
            }
        }
    }
    public void jumpPlayer(){
        if(canJump){
            playerVelocity = playerVelocity.add(0,-30);
            canJump = false;
        }
    }
}
