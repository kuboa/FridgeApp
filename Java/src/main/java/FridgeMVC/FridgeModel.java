package FridgeMVC;
import FoodItem.*;
import CSVManage.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.List;

public class FridgeModel {
    private ObservableList<FoodItem> items = FXCollections.observableArrayList();

    public ObservableList<FoodItem> getItems() {
        return items;
    }

    public void addItem(String name, int quantity) {
        items.add(new FoodItem(name, quantity));
    }

    public void removeItem(FoodItem item) {
        items.remove(item);
    }

    public void loadItemsFromCSV(String filepath) {
    try {
        List<FoodItem> loadedItems = CSVReader.readCSV(filepath);
        items.clear(); // 既存のアイテムをクリアして新たに読み込む
        items.addAll(loadedItems);
    } catch (Exception e) {
        System.err.println("Error loading the CSV file: " + e.getMessage());
    }
    }

    public void saveItemsToCSV(String filepath) {
    try {
        CSVWriter.writeCSV(filepath, new ArrayList<>(items)); // ObservableListをArrayListに変換して渡す
    } catch (Exception e) {
        System.err.println("Error writing to the CSV file: " + e.getMessage());
    }
    }
}
