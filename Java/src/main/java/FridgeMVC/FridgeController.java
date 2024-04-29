package FridgeMVC;
import CSVManage.*;
import FoodItem.*;
import API.*;


import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class FridgeController {
    private ObservableList<FoodItem> fridgeItems;
    private TableView<FoodItem> tableView;
    

    public FridgeController(ObservableList<FoodItem> fridgeItems, TableView<FoodItem> tableView) {
        this.fridgeItems = fridgeItems;
        this.tableView = tableView;
    }

    public void addItem(TextField nameField, TextField quantityField) {
        String name = nameField.getText();
        // 名前が英字と空白のみで構成されているかチェック
        if (!name.matches("[a-zA-Z ]*")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter English characters only in the Name field.");
            alert.showAndWait();
            nameField.clear();  // フィールドをクリア
            return;  // 処理を中断
        }
    
        // 数量フィールドの値が整数であるかの確認
        try {
            int quantity = Integer.parseInt(quantityField.getText());
            if (quantity > 0) {
                FoodItem newItem = new FoodItem(name, quantity);
                fridgeItems.add(newItem);
                nameField.clear();
                quantityField.clear();
            } else {
                // 数量が0以下の場合はエラーを表示
                throw new NumberFormatException("Quantity must be greater than zero.");
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Quantity");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid integer in the Quantity field.");
            alert.showAndWait();
            quantityField.clear();  // 数量フィールドをクリア
        }
    }

    public void deleteItem() {
        ObservableList<FoodItem> selectedItems = tableView.getSelectionModel().getSelectedItems();
        fridgeItems.removeAll(selectedItems);
    }

    public void editItem(TextField nameField, TextField quantityField) {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String newName = nameField.getText();
            int newQuantity = Integer.parseInt(quantityField.getText());
            if (!newName.isEmpty() && newQuantity > 0) {
                FoodItem editedItem = new FoodItem(newName, newQuantity);
                fridgeItems.set(selectedIndex, editedItem);
            }
        }
    }

    public void saveToCSV(String filepath) {
        CSVWriter.writeCSV(filepath, fridgeItems);
    }

    public void loadFromCSV(String filepath) {
        List<FoodItem> items = CSVReader.readCSV(filepath);
        items.forEach(item -> {
            try {
                int quantity = Integer.parseUnsignedInt(String.valueOf(item.getQuantity()));
                fridgeItems.add(new FoodItem(item.getName(), quantity));
            } catch (NumberFormatException e) {
                System.err.println("Invalid quantity in CSV file: " + item.getQuantity());
            }
        });
    }

    public void searchAndShowRecipe() {
        ObservableList<FoodItem> selectedItems = tableView.getSelectionModel().getSelectedItems();
        String ingredients = selectedItems.stream()
                .map(FoodItem::getName)
                .collect(Collectors.joining(","));
        try {
            String recipeUrl = EdamamAPI.searchRecipes(ingredients);
            Desktop.getDesktop().browse(new URI(recipeUrl));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
