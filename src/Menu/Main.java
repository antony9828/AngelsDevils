package Menu;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

import static java.lang.Runtime.getRuntime;


public class Main extends Application /*throws Exception*/ {
    @Override
    public void start(Stage primaryStage){
        Pane root = new Pane();
        Image background = new Image(getClass().getResourceAsStream("Images/menubg-cropped.png"));
        ImageView img = new ImageView(background);
        img.setFitHeight(768);
        img.setFitWidth(1024);
        root.getChildren().add(img);

        MenuItem newGame = new MenuItem("Start new game");
        MenuItem savedGames = new MenuItem("Start old game");
        MenuItem options = new MenuItem("Options");
        MenuItem scoreTable = new MenuItem("Highscore Table");
        MenuItem exitGame = new MenuItem("Exit");
        SubMenu mainMenu = new SubMenu(newGame,savedGames,options, scoreTable,  exitGame);

            MenuItem sound = new MenuItem("Sound");
            MenuItem someshit = new MenuItem("Someshit");
            MenuItem backOption = new MenuItem("Back");
            SubMenu optionsMenu = new SubMenu( sound, someshit, backOption);

        MenuBox menuBox = new MenuBox(mainMenu);

        options.setOnMouseClicked(event -> menuBox.setSubMenu(optionsMenu));
        backOption.setOnMouseClicked(event -> menuBox.setSubMenu(mainMenu));
        exitGame.setOnMouseClicked(event -> System.exit(0));
        newGame.setOnMouseClicked(event -> {
            menuBox.setVisible(false);
        });
        root.getChildren().addAll(menuBox);

        Scene scene = new Scene(root, 1024, 768);
            scene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    FadeTransition ft = new FadeTransition(Duration.seconds(0.5),menuBox);
                    if (!menuBox.isVisible()) {
                        System.out.println(true);
                        ft.setFromValue(0);
                        ft.setToValue(1);
                        ft.play();
                        menuBox.setVisible(true);
                    }
                    else {
                        ft.setFromValue(1);
                        ft.setToValue(0);
                        ft.setOnFinished(event1 -> menuBox.setVisible(false));
                        ft.play();
                    }
                }
            });
        primaryStage.setTitle("Angel and Demon");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}