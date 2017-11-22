package Menu;

import javafx.scene.layout.VBox;

public class SubMenu extends VBox {
    public SubMenu(MenuItem... items) {
        setSpacing(20);
        setTranslateY(200);
        setTranslateX(70);
        for (MenuItem item : items) {
            getChildren().addAll(item);
        }
    }
}
//    initContent();
//    Scene scene = new Scene(appRoot, SCREEN_WIDTH, SCREEN_HEIGHT);
//            scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
//                    scene.setOnKeyReleased(event -> {
//                    keys.put(event.getCode(), false);
//                    });
//
//                    primaryStage.setTitle("Angels & Devils");
//                    primaryStage.setScene(scene);
//                    primaryStage.show();
//                    AnimationTimer timer = new AnimationTimer() {
//@Override
//public void handle(long now) {
//        update();
//        }
//        };
//        timer.start();