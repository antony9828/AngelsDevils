package Menu;

import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MenuItem extends StackPane{
    public MenuItem(String name) {
        Rectangle bg = new Rectangle(300, 70, Color.WHITE);
        bg.setOpacity(0.7);

        Text text = new Text(name);
        text.setFill(Color.CORAL);
        text.setFont(Font.font("Algerian", FontWeight.BOLD, 25));

        setAlignment(Pos.CENTER);
        getChildren().addAll(bg, text);
        FillTransition st = new FillTransition(Duration.seconds(0.5), bg);
        setOnMouseEntered(event -> {
            st.setFromValue(Color.DARKGRAY);
            st.setToValue(Color.DARKMAGENTA);
            st.setCycleCount(Animation.INDEFINITE);
            st.setAutoReverse(true);
            st.play();
        });
        setOnMouseExited(event -> {
            st.stop();
            bg.setFill(Color.WHITE);
        });
    }
}
