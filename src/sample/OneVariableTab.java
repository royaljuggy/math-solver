package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class OneVariableTab extends Tabs {

    private long oneVarNumber;
    private Tab oneVarFuncTab;
    private GridPane grid;

    public OneVariableTab() {
        oneVarNumber = 0;
        oneVarFuncTab = new Tab("One Variable");
        grid = new GridPane();
        init();
    }

    /**
     * Creates the visuals and functionality of the One Variable Tab
     * > This tab applies functions to a single natural number input
     * @return A <code> Tab </code> representing the One Variable tab
     */
    public void init() {
        // Initializing our objects for the tab

        // Grid pane node
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        // Visuals/User interaction objects
        TextField numberInput = new TextField("Input a natural number here!");
        numberInput.setFocusTraversable(false);
        TextArea textDisplay = new TextArea("Your output here.");
        textDisplay.setEditable(false);

        Button btnFactorial = new Button("Factorialize it!");
        Button btnFib = new Button("Calculate nth Fibonacci number!");


        // Adding functionality to our objects

        // Textfield functionality
        numberInput.setOnKeyPressed(ke -> { // pressing enter on the textfield saves the number it gave us

            KeyCode keyCode = ke.getCode();
            if (keyCode.equals(KeyCode.ENTER)) {
                setTextFieldNumber(numberInput);
            }

        });

        // Textfield functionality
        numberInput.setOnMouseClicked((MouseEvent me) -> { // clears previous text in box upon focus of textfield

            if (numberInput.focusedProperty().get()) {
                numberInput.setText("");
            }
        });

        // Factorial functionality
        btnFactorial.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                setTextFieldNumber(numberInput);
                textDisplay.setText(MathFunctions.factorialString(oneVarNumber));
            }

        });

        // Fibonacci functionality
        btnFib.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                setTextFieldNumber(numberInput);
                textDisplay.setText(MathFunctions.fibonacciString(oneVarNumber));
            }

        });

        // Adding visual nodes to the grid pane
        ArrayList<Node> objectsToAdd = new ArrayList<>();
        objectsToAdd.add(numberInput);
        objectsToAdd.add(btnFactorial);
        objectsToAdd.add(btnFib);
        objectsToAdd.add(textDisplay);

        int columnIndex = 1;
        for (int x = 0; x < objectsToAdd.size(); x++) {
            grid.add(objectsToAdd.get(x), columnIndex, (x + 1));
        }

        oneVarFuncTab.setContent(grid);

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
     * Sets the value of oneVarNumber (the number to apply functions to)
     * @param tf The TextField FX object to pull a number from
     */
    private void setTextFieldNumber(TextField tf) {
        try {
            long temp = Long.valueOf(tf.getText());
            if (temp < 0) {
                throw new NumberFormatException("Input Mismatch Error");
            }

            oneVarNumber = temp;
        } catch (NumberFormatException nfe) {
            Main.alert("Please input a natural number (an non-negative integer). " +
                    "\nFor now, n = 0.");
            oneVarNumber = 0;
        }
    }

    /**
     * Accessor method for Tab field
     * @return this object's Tab node
     */
    public Tab getTab() {
        return this.oneVarFuncTab;
    }

}
