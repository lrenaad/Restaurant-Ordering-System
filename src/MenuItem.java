package data_structure_project_;

public class MenuItem {

    private int itemID;
    private String itemName;
    private double price;
    private String category;

    public MenuItem(int itemID, String itemName, double price, String category) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.price = price;
        this.category = category;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "[" + itemID + "] " + itemName + " SR " + price + " (" + category + ")";
    }
}
