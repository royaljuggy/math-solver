package sample;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class SolverTab extends Tabs {

    // [Fields] Variables used in the Two Variable Tab
    private long var1;
    private long var2;
    private Tab solverTab;
    private GridPane grid;
    private ComboBox functionChooser; // ComboBox to choose which function's nodes to show (only one set of nodes are shown at a time)
    final private int columnIndex = 0;

    public SolverTab() {
        var1 = 0;
        var2 = 0;
        solverTab = new Tab("Two Variables");
        grid = new GridPane();
        functionChooser = new ComboBox<String>();
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
        functionChooser.setPromptText("Select a function/solver");

        // Adding function/solver choices
        functionChooser.getItems().add("Greatest Common Divisor"); // choice 0
        functionChooser.getItems().add("Linear Diophantine Solver"); // choice 1

        // Functionality
        ArrayList<ArrayList<Node>> groupOfFunctionNodes = new ArrayList(); // each element of this ArrayList holds an ArrayList that has the nodes to show for each choice
        groupOfFunctionNodes.add(initChoice0());

        // Changing what's in the tab based on choice
        functionChooser.setOnMouseReleased(ke -> { // pressing enter on the textfield saves the number it gave us

            changeVisualNodes(groupOfFunctionNodes.get(functionChooser.getItems().indexOf(functionChooser.getValue())));

        });

        grid.add(functionChooser, columnIndex, 1);
        solverTab.setContent(grid);

    }

    /**
     * Initializes the nodes that make up the visuals of choice0
     * @return a list of Nodes to show in this Tab
     */

    private ArrayList<Node> initChoice0() {
        // Choice 0: Greatest Common Divisor calculator
        ArrayList<Node> nodesOfChoice0 = new ArrayList<>();

        // Visual nodes to add
        TextField inputNumber1 = new TextField("Input Number 1");
        TextField inputNumber2 = new TextField("Input Number 2");
        Button btnCalculateGCD = new Button("Calculate GCD of numbers 1 and 2");

        // inputNumber1 functionality
        inputNumber1.setOnKeyPressed(ke -> { // pressing enter on the textfield saves the number it gave us

            KeyCode keyCode = ke.getCode();
            if (keyCode.equals(KeyCode.ENTER)) {
                setVar1(inputNumber1.getText());
            }

        });

        // inputNumber1 functionality
        inputNumber1.setOnMouseClicked((MouseEvent me) -> { // clears previous text in box upon focus of textfield

            if (inputNumber1.focusedProperty().get()) {
                inputNumber1.setText("");
            }
        });

        // inputNumber2 functionality
        inputNumber2.setOnKeyPressed(ke -> { // pressing enter on the textfield saves the number it gave us

            KeyCode keyCode = ke.getCode();
            if (keyCode.equals(KeyCode.ENTER)) {
                setVar2(inputNumber2.getText());
            }

        });

        // inputNumber2 functionality
        inputNumber2.setOnMouseClicked((MouseEvent me) -> { // clears previous text in box upon focus of textfield

            if (inputNumber2.focusedProperty().get()) {
                inputNumber2.setText("");
            }
        });

        nodesOfChoice0.add(inputNumber1);
        nodesOfChoice0.add(inputNumber2);
        nodesOfChoice0.add(btnCalculateGCD);

        return nodesOfChoice0;
    }

    /**
     * Changes the visual JavaFX nodes on the tab depending on the ComboBox choice
     * @param nodes the list of nodes to add to the tab
     */
    public void changeVisualNodes(ArrayList<Node> nodes) {
        GridPane newGrid = new GridPane();
        newGrid.setHgap(10);
        newGrid.setVgap(10);
        newGrid.setPadding(new Insets(0, 10, 0, 10));
        newGrid.add(functionChooser, columnIndex, 1);

        for (int x = 0; x < nodes.size(); x++) {
            newGrid.add(nodes.get(x), columnIndex, x+1);
        }

        // Sets the new visuals to the Tab
        solverTab.setContent(newGrid);
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
     * Mutator method for Var1
     * @param var1 a String inputted by the user
     */
    public void setVar1(String var1) {
        try {
            this.var1 = Long.valueOf(var1);
        } catch (NumberFormatException nfe) {
            Main.alert("Please input an integer for number 1!");
            this.var1 = 0;
        }
    }

    /**
     * Mutator method for Var2
     * @param var2 a String inputted by the user
     */
    public void setVar2(String var2) {
        try {
            this.var2 = Long.valueOf(var2);
        } catch (NumberFormatException nfe) {
            Main.alert("Please input an integer for number 2!");
            this.var2 = 0;
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
