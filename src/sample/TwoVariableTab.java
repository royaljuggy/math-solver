package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;

public class TwoVariableTab implements Tabs {

    // [Fields] Variables used in the Two Variable Tab
    private long var1 = 0;
    private long var2 = 0;

    // Two variable solvers (finding solutions of equations in two variables)
    public Tab init() {
        // Tab
        Tab twoVarSolverTab = new Tab("Two Variable Functions");

        // > Pane node
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        return twoVarSolverTab;
    }

}
