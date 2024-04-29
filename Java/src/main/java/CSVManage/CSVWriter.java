package CSVManage;
import FoodItem.FoodItem;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CSVWriter {
    public static void writeCSV(String filePath, List<FoodItem> items) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (FoodItem item : items) {
                writer.append(item.getName()).append(",").append(String.valueOf(item.getQuantity())).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<FoodItem> items = new ArrayList<>();
        items.add(new FoodItem("Egg", 6));
        items.add(new FoodItem("Milk", 2));
        writeCSV("FridgContents.csv", items);
    }
}
