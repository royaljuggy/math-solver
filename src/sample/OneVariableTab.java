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

public class OneVariableTab implements Tabs {

    private long oneVarNumber = 0;

    // First Tab: One variable function
    public Tab init() {
        // Initializing our objects for the tab
        // > Tab
        Tab oneVarFuncTab = new Tab("One Variable Function");

        // > Pane node
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        // > Visuals/User interaction objects
        TextField numberInput = new TextField("Input a natural number here!");
        numberInput.setFocusTraversable(false);
        TextArea textDisplay = new TextArea("Your output here.");
        textDisplay.setEditable(false);

        Button btnFactorial = new Button("Factorialize it!");
        Button btnFib = new Button("Calculate nth Fibonacci number!");


        //  Adding functionality to our objects

        // Textfield functionality
        numberInput.setOnKeyPressed(ke -> { // pressing enter on the textfield saves the number it gave us

            KeyCode keyCode = ke.getCode();
            if (keyCode.equals(KeyCode.ENTER)) {
                oneVarNumber = getTextFieldNumber(numberInput);
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
                oneVarNumber = getTextFieldNumber(numberInput);
                textDisplay.setText(MathFunctions.factorialString(oneVarNumber));
            }

        });

        // Fibonacci functionality
        btnFib.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                oneVarNumber = getTextFieldNumber(numberInput);
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

        return oneVarFuncTab;
    }

    // Sets the number for the one variable tab
    // * Input: TextField
    // * Output: Non-negative integer
    private long getTextFieldNumber(TextField tf) {
        try {
            return Long.valueOf(tf.getText());
        } catch (NumberFormatException nfe) {
            Main.alert("Please input a natural number (an non-negative integer). " +
                    "\nFor now, n = 0.");
            return 0;
        }
    }
}
