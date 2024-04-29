package CSVManage;
import FoodItem.FoodItem;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class CSVReader {
    public static List<FoodItem> readCSV(String filePath) {
        List<FoodItem> items = new ArrayList<>();
        File file = new File(filePath);

        // ファイルが存在しない場合、新しいファイルを作成
        if (!file.exists()) {
            try {
                Files.createFile(Paths.get(filePath));
                System.out.println("File not found. Creating new file: " + filePath);
            } catch (IOException e) {
                System.err.println("Failed to create new file: " + e.getMessage());
                return items;  // 空のリストを返す
            }
        }

        // ファイルが存在する、または新しく作成した後の読み込み処理
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                int quantity = Integer.parseInt(data[1]);
                FoodItem item = new FoodItem(name, quantity);
                items.add(item);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return items;
    }

    public static void main(String[] args) {
        List<FoodItem> fridgeItems = readCSV("fridge.csv");
        for (FoodItem item : fridgeItems) {
            System.out.println(item);
        }
    }
}
