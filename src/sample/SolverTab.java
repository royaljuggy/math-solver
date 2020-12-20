package sample;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class SolverTab extends Tabs {

    // [Fields] Variables used in the Two Variable Tab
    private long var1;
    private long var2;
    private Tab solverTab;
    private GridPane grid;

    public SolverTab() {
        var1 = 0;
        var2 = 0;
        solverTab = new Tab("Two Variables");
        grid = new GridPane();
        init();
    }

    /**
     * Creates the visuals and functionality of a solver tab instance that can be given to a TabPane
     * > Solvers: finding solutions to certain equations,examples include Linear Diophantine or Modulo equations)
     * @return A <code> Tab </code>
     */
    public void init() {

        // Pane node
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        // Visual/User interaction objects
        // > ComboBox to choose which function's nodes to show (only one set of nodes are shown at a time)
        ComboBox functionChooser = new ComboBox<String>();
        functionChooser.setPromptText("Select a function/solver");

        // Adding function/solver choices
        functionChooser.getItems().add("Greatest Common Divisor"); // choice 0
        functionChooser.getItems().add("Linear Diophantine Solver"); // choice 1

        // Functionality
        ArrayList groupOfFunctionNodes = new ArrayList(); // each element of this ArrayList holds an ArrayList that has the nodes to show for each choice
        groupOfFunctionNodes.add(initChoice0());

        grid.add(functionChooser, 0, 1);
        solverTab.setContent(grid);

    }

    /**
     * Initializes the nodes that make up the visuals of choice0
     * @return a list of Nodes to show in this Tab
     */

    private ArrayList<Node> initChoice0() {
        // Choice 0: Greatest Common Divisor calculator
        return null;
    }

    /**
     * Gets the number from a TextField
     * @param tf The TextField FX object to pull a number from
     * @return A <code> non-negative Integer </code>, the number to apply One Variable functions to
     */
    private long getTextFieldNumber(TextField tf) {
        try {
            long temp = Long.valueOf(tf.getText());
            if (temp < 0) {
                throw new NumberFormatException("Input Mismatch Error");
            }

            return temp;
        } catch (NumberFormatException nfe) {
            Main.alert("Please input a natural number (an non-negative integer). " +
                    "\nFor now, n = 0.");
            return 0;
        }
    }

    /**
     * Accessor method for Tab field
     * @return this object's Tab node
     */
    public Tab getTab() {
        return this.solverTab;
    }

}
