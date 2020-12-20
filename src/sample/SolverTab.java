package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;

public class SolverTab implements Tabs {

    // [Fields] Variables used in the Two Variable Tab
    private long var1 = 0;
    private long var2 = 0;

    /**
     * Creates the visuals and functionality of a solver tab instance that can be given to a TabPane
     * > Solvers: finding solutions to certain equations,examples include Linear Diophantine or Modulo equations)
     * @return A <code> Tab </code>
     */
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
