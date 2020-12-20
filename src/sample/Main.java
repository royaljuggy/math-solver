package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

// The heart of our code, this class creates our GUI
public class Main extends Application {

    private static long oneVarNumber = 0;

    @Override
    public void start(Stage primaryStage) throws IOException  {


        // We will initialize GUI objects without FXML.
        primaryStage.setTitle("Math Solver");
        TabPane root = initTabs(); // Tabs are initialized in a separate method
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();

        //Parent root2 = FXMLLoader.load(getClass().getResource("sample.fxml"));
        // Unused object
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                System.out.println(MathFunctions.factorial(4));
                System.out.println(MathSolvers.linearDiophantine(3, -4, -5));

            }
        });
    }

    // A function to initialize the tabs of our TabPane
    private static TabPane initTabs() {
        TabPane root = new TabPane();
        ArrayList<Tab> allTabs = new ArrayList<>();

        allTabs.add(initOneVarTab());
        allTabs.add(initTwoVarSolverTab());
        allTabs.add(initSupportTab());

        // Adding all tabs in allTabs to the root pane, while also adding properties that all tabs hold (like how they are ALL not closable)
        for (Tab t : allTabs) {
            Tab temp = t;
            temp.setClosable(false);
            root.getTabs().add(temp);
        }

        return root;
    }

    // First Tab: One variable function
    private static Tab initOneVarTab() {
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

    private static long getTextFieldNumber(TextField tf) {
        try {
            return Long.valueOf(tf.getText());
        } catch (NumberFormatException nfe) {
            // alert here
            return 1;
        }
    }

    // Second Tab: two variable solvers (finding solutions of equations in two variables)
    private static Tab initTwoVarSolverTab() {
        // Tab
        Tab twoVarSolverTab = new Tab("Two Variable Functions");

        // > Pane node
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        return twoVarSolverTab;
    }

    // Third Tab: Support tab (what this app does, what the output means, etc.)
    private static Tab initSupportTab() {
        // Tab
        Tab supportTab = new Tab("Help");

        // > Pane node
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        return supportTab;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
