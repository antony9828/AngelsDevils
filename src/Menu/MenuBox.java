package Menu;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MenuBox extends Pane {
    static SubMenu subMenu;

    public MenuBox(SubMenu subMenu) {
        MenuBox.subMenu = subMenu;

        setVisible(false);
        Rectangle bg = new Rectangle(1024, 768, Color.LIGHTCORAL);
        bg.setOpacity(0.4);
        getChildren().addAll(bg, subMenu);
    }

    public void setSubMenu(SubMenu subMenu) {
        getChildren().remove(MenuBox.subMenu);
        MenuBox.subMenu = subMenu;
        getChildren().add(MenuBox.subMenu);
    }
}
