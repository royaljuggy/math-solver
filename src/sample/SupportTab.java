package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;

public class SupportTab implements Tabs {

    // Support tab (what this app does, what the output means, etc.)
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
