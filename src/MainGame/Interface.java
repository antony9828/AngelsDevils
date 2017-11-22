package MainGame;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Interface {

    public Label devil_score;
    public Label angel_score;
    public Label devil_deaths;
    public Label angel_deaths;
    public Label level;

    public Interface(){
        devil_score = new Label();
        devil_score.setFont(Font.font("Algerian", FontWeight.BOLD, 40));
        devil_score.setTextFill(Color.WHITE);
        devil_score.setTranslateX(830);
        devil_score.setTranslateY(20);

        angel_score = new Label();
        angel_score.setFont(Font.font("Algerian", FontWeight.BOLD, 40));
        angel_score.setTextFill(Color.WHITE);
        angel_score.setTranslateX(20);
        angel_score.setTranslateY(20);

        devil_deaths = new Label();
        devil_deaths.setFont(Font.font("Algerian", FontWeight.BOLD, 24));
        devil_deaths.setTextFill(Color.WHITE);
        devil_deaths.setTranslateX(860);
        devil_deaths.setTranslateY(80);

        angel_deaths = new Label();
        angel_deaths.setFont(Font.font("Algerian", FontWeight.BOLD, 24));
        angel_deaths.setTextFill(Color.WHITE);
        angel_deaths.setTranslateX(20);
        angel_deaths.setTranslateY(80);

        level = new Label();
        level.setText("LEVEL 2");
        level.setFont(Font.font("Algerian", FontWeight.BOLD, 64));
        level.setTextFill(Color.WHITE);
        level.setTranslateX(400);
        level.setTranslateY(20);

        Game.gameRoot.getChildren().addAll(devil_score, angel_score, devil_deaths, angel_deaths, level);
    }
}
