package FoodItem;

public class FoodItem {
    private String name;
    private int quantity;

    public FoodItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    // 名称取得
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    //数量の取得
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // 数量の加算
    public void addQuantity(int quantityToAdd) {
        this.quantity += quantityToAdd;
    }

    // 数量の減算
    public void subtractQuantity(int quantityToSubtract) {
        if (this.quantity >= quantityToSubtract) {
            this.quantity -= quantityToSubtract;
        } else {
            System.out.println("Error: Quantity to subtract exceeds available quantity.");
        }
    }

    // 変更
    public void editItem(String newName, int newQuantity) {
        this.name = newName;
        this.quantity = newQuantity;
    }

    @Override
    public String toString() {
        return "FoodItem{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
