package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

// The heart of our code, this class creates our GUI
public class Main extends Application {

    private static int oneVarNumber = 0;

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

        Button btnFactorial = new Button("Factorialize it!");
        Button btnFib = new Button("Calculate nth Fibonacci number!");

        //  Adding functionality to our objects

        // Textfield functionality
        numberInput.setOnKeyPressed(ke -> { // pressing enter on the textfield saves the number it gave us

            KeyCode keyCode = ke.getCode();
            if (keyCode.equals(KeyCode.ENTER)) {
                try {
                    oneVarNumber = Integer.valueOf(numberInput.getText());
                } catch (NumberFormatException nfe) {
                    oneVarNumber = 1;
                    System.out.println("alert here");
                }

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
                oneVarNumber = Integer.valueOf(numberInput.getText()); // repeated code!! We want to be DRY!
                System.out.println(MathFunctions.factorial(oneVarNumber));
            }

        });

        // Fibonacci functionality
        btnFib.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                oneVarNumber = Integer.valueOf(numberInput.getText()); // repeated code!! We want to be DRY!
                System.out.println(MathFunctions.fibonacci(oneVarNumber));
            }

        });

        grid.add(numberInput, 1, 1);
        grid.add(btnFactorial, 1, 2);
        grid.add(btnFib, 1, 3);

        oneVarFuncTab.setContent(grid);

        return oneVarFuncTab;
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

    public static void main(String[] args) {
        launch(args);
    }
}
