package FridgeMVC;
import FoodItem.*;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;

public class FridgeView {
    private Stage stage;
    private TableView<FoodItem> tableView;
    private TextField nameField, quantityField;
    private Button addButton, deleteButton, editButton, saveButton;
    private Button searchButton;

    public FridgeView(Stage stage, ObservableList<FoodItem> items) {
        this.stage = stage;
        this.tableView = new TableView<>();
        initializeUI(items);
    }


    private void initializeUI(ObservableList<FoodItem> items) {
        tableView.setItems(items);
        tableView.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);

        TableColumn<FoodItem, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        // Name列のセルを右寄せに設定
        nameColumn.setCellFactory(column -> {
            return new TableCell<FoodItem, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item);
                        setStyle("-fx-alignment: CENTER-RIGHT;"); // セルのテキストを右寄せに
                    }
                }
            };
        });

        TableColumn<FoodItem, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        // Quantity列のセルを右寄せに設定
        quantityColumn.setCellFactory(column -> {
            return new TableCell<FoodItem, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.toString());
                        setStyle("-fx-alignment: CENTER-RIGHT;"); // セルのテキストを右寄せに
                    }
                }
            };
        });

        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(quantityColumn);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // 列幅の自動調整を有効にする

    
        nameField = new TextField();
        nameField.setPromptText("Enter name");  // プレースホルダーを設定
        quantityField = new TextField();
        quantityField.setPromptText("Enter quantity");  // プレースホルダーを設定
    
        addButton = new Button("Add");
        deleteButton = new Button("Delete");
        editButton = new Button("Edit");
        saveButton = new Button("Save");
    
        HBox inputBox = new HBox(10);
        inputBox.setAlignment(Pos.CENTER);  // 要素を中央寄せに設定
        inputBox.getChildren().addAll(
            new Label("Name:"), nameField, 
            new Label("Quantity:"), quantityField, 
            addButton, deleteButton, editButton, saveButton
        );
    
        searchButton = new Button("Search Recipe");
        HBox controlBox = new HBox(10, searchButton);
        controlBox.setAlignment(Pos.CENTER_RIGHT);  // 検索ボタンを右寄せに設定
    
        BorderPane root = new BorderPane();
        root.setTop(inputBox);
        root.setCenter(tableView);
        root.setBottom(controlBox);
    
        Scene scene = new Scene(root, 700, 400);
        stage.setTitle("Fridge Management Application");
        stage.setScene(scene);
    }

    public TableView<FoodItem> getTableView() {
        return tableView;
    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Button getEditButton() {
        return editButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }


    public Button getSearchButton() {
        return searchButton;
    }

    public TextField getNameField() {
        return nameField;
    }

    public TextField getQuantityField() {
        return quantityField;
    }
}
