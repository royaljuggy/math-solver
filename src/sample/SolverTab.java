package sample;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class SolverTab extends Tabs {

    // [Fields] Variables used in the Two Variable Tab
    private long var1;
    private long var2;
    private long var3;
    private Tab solverTab;
    private GridPane baseGrid;
    private ComboBox functionChooser; // ComboBox to choose which function's nodes to show (only one set of nodes are shown at a time)
    final private int columnIndex = 0;
    private Button btnChangeFunc;
    final private double prefWidth = 150;
    final private double prefHeight = 150;

    public SolverTab() {
        var1 = 0;
        var2 = 0;
        var3 = 0; // a third constant needed for linear diophantine equations
        solverTab = new Tab("Two Variables");
        baseGrid = new GridPane();
        functionChooser = new ComboBox<String>();
        btnChangeFunc = new Button("Change Function/Solver");
        init();
    }

    /**
     * Creates the visuals and functionality of a solver tab instance that can be given to a TabPane
     * > Solvers: finding solutions to certain equations,examples include Linear Diophantine or Modulo equations)
     * @return A <code> Tab </code>
     */
    public void init() {

        // Pane node
        baseGrid.setHgap(10);
        baseGrid.setVgap(10);
        baseGrid.setPadding(new Insets(0, 10, 0, 10));

        // Visual/User interaction objects
        functionChooser.setPromptText("Select a function/solver");

        // Adding function/solver choices
        functionChooser.getItems().add("Greatest Common Divisor"); // choice 0
        functionChooser.getItems().add("Linear Diophantine Solver"); // choice 1

        // Functionality
        ArrayList<ArrayList<Node>> groupOfFunctionNodes = new ArrayList(); // each element of this ArrayList holds an ArrayList that has the nodes to show for each choice
        groupOfFunctionNodes.add(initChoice0());
        groupOfFunctionNodes.add(initChoice1());

        // Changing what's in the tab based on choice
        btnChangeFunc.setOnMouseReleased(ke -> {
            try {
                changeVisualNodes(groupOfFunctionNodes.get(functionChooser.getItems().indexOf(functionChooser.getValue())));
            } catch (IndexOutOfBoundsException e2 ) {
                Main.alert("Invalid option chosen. Try again.");
                solverTab.setContent(baseGrid);
            }

        });

        baseGrid.add(functionChooser, columnIndex, 1);
        baseGrid.add(btnChangeFunc, columnIndex+1, 1);
        solverTab.setContent(baseGrid);

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
        TextArea textOutput = new TextArea("Answer here.");
        textOutput.setEditable(false);
        textOutput.setPrefSize(prefWidth, prefHeight);

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

        btnCalculateGCD.setOnMouseReleased(ke -> {
            setVar1(inputNumber1.getText());
            setVar2(inputNumber2.getText());
            textOutput.setText("The GCD of " + var1 + " and " + var2 + " is: " + MathFunctions.gcd(var1, var2));
        });

        nodesOfChoice0.add(inputNumber1);
        nodesOfChoice0.add(inputNumber2);
        nodesOfChoice0.add(btnCalculateGCD);
        nodesOfChoice0.add(textOutput);

        return nodesOfChoice0;
    }

    /**
     * Initializes the nodes that make up the visuals of choice0
     * @return a list of Nodes to show in this Tab
     */

    private ArrayList<Node> initChoice1() {
        // Choice 1: Linear Diophantine Equation Solver
        ArrayList<Node> nodesOfChoice1 = new ArrayList<>();

        // Visual nodes to add
        TextField inputNumber1 = new TextField("Input number 'a'");
        TextField inputNumber2 = new TextField("Input number 'b'");
        TextField inputNumber3 = new TextField("Input number 'c'");

        TextArea textOutput = new TextArea("Answer here.");
        textOutput.setEditable(false);
        textOutput.setPrefSize(prefWidth, prefHeight);

        Button btnSolveLDE = new Button("Solve the LDE ax+by=c");


        // inputNumber1 functionality
        inputNumber1.setOnMouseClicked((MouseEvent me) -> { // clears previous text in box upon focus of textfield

            if (inputNumber1.focusedProperty().get()) {
                inputNumber1.setText("");
            }
        });

        // inputNumber2 functionality
        inputNumber2.setOnMouseClicked((MouseEvent me) -> { // clears previous text in box upon focus of textfield

            if (inputNumber2.focusedProperty().get()) {
                inputNumber2.setText("");
            }
        });

        // inputNumber3 functionality
        inputNumber3.setOnMouseClicked((MouseEvent me) -> { // clears previous text in box upon focus of textfield

            if (inputNumber3.focusedProperty().get()) {
                inputNumber3.setText("");
            }
        });

        // Solve the Linear Diophantine Equation!
        btnSolveLDE.setOnMouseReleased(ke -> {
            setVar1(inputNumber1.getText());
            setVar2(inputNumber2.getText());
            setVar3(inputNumber3.getText());
            textOutput.setText(MathSolvers.LDEsolnSet(var1,var2,var3));
        });

        nodesOfChoice1.add(inputNumber1);
        nodesOfChoice1.add(inputNumber2);
        nodesOfChoice1.add(inputNumber3);
        nodesOfChoice1.add(btnSolveLDE);
        nodesOfChoice1.add(textOutput);

        return nodesOfChoice1;
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
        newGrid.add(btnChangeFunc, columnIndex+1, 1);

        for (int x = 0; x < nodes.size(); x++) {
            newGrid.add(nodes.get(x), columnIndex, x+2);
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
     * Mutator method for Var3
     * @param var3 a String inputted by the user
     */
    public void setVar3(String var3) {
        try {
            this.var3 = Long.valueOf(var3);
        } catch (NumberFormatException nfe) {
            Main.alert("Please input an integer for number 2!");
            this.var3 = 0;
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
