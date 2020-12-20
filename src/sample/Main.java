package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

// The heart of our code, this class creates our GUI
public class Main extends Application {

    private static String title = "Math Solver";

    @Override
    public void start(Stage primaryStage) throws IOException  {

        // We will initialize GUI objects without FXML.
        primaryStage.setTitle(title);
        TabPane root = initTabs(); // Tabs are initialized in a separate method
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();

        //Parent root2 = FXMLLoader.load(getClass().getResource("sample.fxml"));
    }

    /**
     * A function to initialize the tabs of our TabPane
     * @return A <code> TabPane </code> containing all of the Tabs (with their functionality) of the application
     */
    private static TabPane initTabs() {
        TabPane root = new TabPane();
        ArrayList<Tab> allTabs = new ArrayList<>();

        // Tab objects (not JavaFX tabs)
        OneVariableTab oneVarTab = new OneVariableTab();
        SolverTab solverTab = new SolverTab();
        SupportTab supportTab = new SupportTab();

        // Initializing the Tab nodes and storing them (JavaFX tabs)
        allTabs.add(oneVarTab.init());
        allTabs.add(solverTab.init());
        allTabs.add(supportTab.init());

        // Adding all tabs in allTabs to the root pane, while also adding properties that all tabs hold (like how they are ALL not closable)
        for (Tab t : allTabs) {
            Tab temp = t;
            temp.setClosable(false);
            root.getTabs().add(temp);
        }

        return root;
    }

    // A reusable pop-up alert system
    /**
     * Pop-up alert system
     * @param message A string representing the alert message
     */
    public static void alert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText("Attention!");
        alert.setContentText(message);
        alert.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
