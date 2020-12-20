package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;

public class SupportTab extends Tabs {

    private Tab supportTab;
    private GridPane grid;

    public SupportTab() {
        supportTab = new Tab("Help");
        grid = new GridPane();
    }

    /**
     * Creates the visuals and functionality of the support tab
     * > This tab gives information to first time users on function and solver usage
     * @return A <code> Tab </code> representing the Support Tab
     */
    public void init() {

        // > Pane node
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 0, 10));

        supportTab.setContent(grid);
    }

    /**
     * Accessor method for Tab field
     * @return this object's Tab node
     */
    public Tab getTab() {
        return this.supportTab;
    }
}
