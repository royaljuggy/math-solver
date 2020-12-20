package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;

public class SupportTab implements Tabs {

    /**
     * Creates the visuals and functionality of the support tab
     * > This tab gives information to first time users on function and solver usage
     * @return A <code> Tab </code> representing the Support Tab
     */
    public Tab init() {
        Tab supportTab = new Tab("Help");

        // > Pane node
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 0, 10));
        return supportTab;
    }
}
