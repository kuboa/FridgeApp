package App;
import FridgeMVC.*;
import FoodItem.*;

import javafx.application.Application;
import javafx.stage.Stage;


public class FridgeApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        String filepath = "FridgeContents.csv";
        // Initialize the model
        FridgeModel model = new FridgeModel(); 

        // Load existing data from CSV file
        model.loadItemsFromCSV(filepath);  // Assuming FridgeModel includes a loadFromCSV method

        // Initialize the view
        FridgeView view = new FridgeView(primaryStage, model.getItems()); 

        // Initialize the controller with references to the model's items and the view's table view
        FridgeController controller = new FridgeController(model.getItems(), view.getTableView());

        // Connect UI controls to controller methods (assuming these methods are public and designed to be called from outside)
        view.getAddButton().setOnAction(e -> controller.addItem(view.getNameField(), view.getQuantityField()));
        view.getDeleteButton().setOnAction(e -> controller.deleteItem());
        view.getEditButton().setOnAction(e -> controller.editItem(view.getNameField(), view.getQuantityField()));
        view.getSaveButton().setOnAction(e -> controller.saveToCSV(filepath));
        view.getSearchButton().setOnAction(e -> controller.searchAndShowRecipe());

        // Show the primary stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
